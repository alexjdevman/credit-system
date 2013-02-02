package com.bionic.gorbachev.banksystem.dao;

import com.bionic.gorbachev.banksystem.entity.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aleksey Gorbachev
 */

public class UsersDAO extends BaseDAO<Users> {
    //Запрос на выборку всех записей из таблицы

    private static final String allQuery = "SELECT * FROM APP.USERS";

    //Запрос на выборку по Id
    private static final String idQuery = "SELECT * FROM APP.USERS WHERE (USERS.ID = ?)";

    //Запрос проверки регистрации пользователя
    private static final String isRegisteredQuery = "SELECT * FROM APP.USERS WHERE (USERS.USERNAME = ?) AND" +
            "(USERS.USERPSW = ?)";
    //Запрос на выборку по логину
    private static final String loginQuery = "SELECT * FROM APP.USERS WHERE (USERS.USERNAME = ?)";

    //Запрос на добавление новой записи
    private static final String insertQuery = "INSERT INTO APP.USERS (USERNAME, USERPSW, USERSTATE, USERTYPEID) VALUES (?, ?, ?, ?)";

    //Получение типа пользователя по айди
    private static final String userTypeIdQuery = "SELECT * FROM APP.USERTYPE WHERE (USERTYPE.ID = ?)";

    //Названия полей таблицы клиентов
    private static final String[] fieldsName = new String[]{
        "Номер", "Логин", "Тип пользователя", "Статус"
    };
    //Типы полей таблицы
    private static Class[] types = new Class[]{
        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
    };
    //Параметры редактирования полей таблицы
    private static boolean[] canEdit = new boolean[]{
        false, false, false, false
    };

    //Статусы пользователей
    private static final String activeState = "АКТИВНЫЙ";
    private static final String nonActiveState = "ЗАБЛОКИРОВАН";

    //Id авторизировавшегося пользователя
    private int curUserId;
    
    //Получение Id
    public int getCurUserId() {
        return curUserId;
    }
    
    //Извлечение обьекта
    public Users fetchObject(ResultSet result) {
        //Создаем обьект для пользователя
        Users user = new Users();
        //Заполняем иформацией из базы
        fillObject(user, result);

        return user;
    }

    //Заполнение обьекта информацией из ResultSet
    public void fillObject(Users user, ResultSet result) {
        //Заполняем информацией из базы
        try {
            user.setId(result.getInt("ID"));
            user.setUserName(result.getString("USERNAME"));
            user.setUserPsw(result.getString("USERPSW"));
            user.setUserState(result.getInt("USERSTATE"));
            user.setUserTypeId(result.getInt("USERTYPEID"));
        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Ошибка при извлечении обьекта из базы");
        }
    }

    //Проверка регистрации пользователя - возвращается пользователь, или null
    public Users checkRegistered(String login, String password) throws SQLException {
        Users user = null;
        //Подключаемся к базе
        PreparedStatement statement = getConnection().prepareStatement(isRegisteredQuery);
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            //Получаем пользователя
            user = fetchObject(result);
            //Устанавливаем Id пользователя
            curUserId = result.getInt("ID");           
        }
        return user;
    }

    //Проверка допустимости логина
    public boolean checkLogin(String login) throws SQLException {
        //Создаем параметризованый запрос
        PreparedStatement statement = getConnection().prepareStatement(loginQuery);
        //Устанавливаем параметр
        statement.setString(1, login);
        //Делаем запрос
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return false;
        } else {
            return true;
        }

    }

    //Получение обьекта по id
    public Users get(int id) {
        Users users = new Users();
        try {
            PreparedStatement statement = getConnection().prepareStatement(idQuery);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                fillObject(users, result);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при создании запроса");
        }
        return users;
    }

    public String getAllQuery() {
        return allQuery;
    }

    //Добавление новой записи в базу и список
    @Override
    public void add(Users element) {
        //Добавляем запись в список
        getList().add(element);
        //2 способ добавления новой записи
        try {
            PreparedStatement statement = getConnection().prepareStatement(insertQuery);
            //Устанавливаем параметры
            statement.setString(1, element.getUserName());
            statement.setString(2, element.getUserPsw());
            statement.setInt(3, element.getUserState());
            statement.setInt(4, element.getUserTypeId());
            //Выполняем добавление записи
            statement.executeUpdate();
        }
        catch(SQLException exc){
            System.out.println("Ошибка при изменении таблицы");
        }
    }
    
    public static String[] getFieldsName() {
        return fieldsName;
    }

    public static boolean[] getCanEdit() {
        return canEdit;
    }

    public static Class[] getTypes() {
        return types;
    }

    //Создание модели для таблицы пользователей
    @Override
    public void initTableModel(JTable table, List<Users> list) {
        //Формируем массив пользователей для модели таблицы
        Object[][] usersArr = new Object[list.size()][table.getColumnCount()];
        int i = 0;
        for (Users user : list) {
            //Создаем массив для пользователя
            Object[] row = new Object[table.getColumnCount()];
            //Заполняем массив данными
            row[0] = user.getId();
            row[1] = user.getUserName();
            //Определяем тип пользователя
            int userTypeId = user.getUserTypeId();
            String userType = "";
            try {
                PreparedStatement statement = getConnection().prepareStatement(userTypeIdQuery);
                statement.setInt(1, userTypeId);
                ResultSet result = statement.executeQuery();
                if (result.next()){
                    userType = result.getString("USERNAME");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка при создании запроса");
            }
            row[2] = userType;
            //Получаем статус пользователя
            int state = user.getUserState();
            String userState;
            switch (state){
                case 1: {
                    userState = activeState;
                    break;
                }
                default: {
                    userState = nonActiveState;
                    break;
                }
            }
            row[3] = userState;
            usersArr[i++] = row;
        }

        table.setModel(new DefaultTableModel(
                usersArr,
                UsersDAO.getFieldsName()) {

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
    }
        
    
}
