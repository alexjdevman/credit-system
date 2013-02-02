package com.bionic.gorbachev.banksystem.dao;

import com.bionic.gorbachev.banksystem.entity.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aleksey Gorbachev
 */
public class ClientDAO extends BaseDAO<Client> {

    //Запрос на выборку всех записей из таблицы
    private static final String allQuery = "SELECT * FROM APP.CLIENTS";

    //Запрос на выборку по Id
    private static final String idQuery = "SELECT * FROM APP.CLIENTS WHERE (CLIENTS.ID = ?)";

    //Запрос на выборку по UsersId
    private static final String usersIdQuery = "SELECT * FROM APP.CLIENTS WHERE (CLIENTS.USERSID = ?)";

    //Запрос на добавление новой записи
    private static final String insertQuery = "INSERT INTO APP.CLIENTS (CLIENTFIO, CLIENTADR, CLIENTPAS, CLIENTIDCOD, CLIENTTEL, CLIENTLEVEL, CLIENTWORKINFO, SURETYID, USERSID)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


    //Названия полей таблицы клиентов
    private static final String[] fieldsName = new String[]{
        "Номер", "ФИО", "Адресс", "Паспорт", "Идентификационный код", "Телефон", "Уровень доходов", "Информация о работе"
    };
    //Типы полей таблицы
    private static Class[] types = new Class[]{
        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class
    };
    //Параметры редактирования полей таблицы
    private static boolean[] canEdit = new boolean[]{
        false, false, false, false, false, false, false
    };

    //Получение обьекта из базы
    public Client fetchObject(ResultSet result) {
        //Создаем обьект для клиента
        Client client = new Client();
        //Заполняем созданый обьект
        fillObject(client, result);
        return client;
    }

    //Заполнение обьекта данными из resultSet
    public void fillObject(Client client, ResultSet result) {
        //Заполняем данные о клиенте
        try {
            client.setId(result.getInt("ID"));
            client.setFio(result.getString("CLIENTFIO"));
            client.setAddress(result.getString("CLIENTADR"));
            client.setPassport(result.getString("CLIENTPAS"));
            client.setIdCod(result.getString("CLIENTIDCOD"));
            client.setTel(result.getString("CLIENTTEL"));
            client.setLevel(result.getLong("CLIENTLEVEL"));
            client.setWorkInfo(result.getString("CLIENTWORKINFO"));
            client.setSuretyId(result.getInt("SURETYID"));
            client.setUsersId(result.getInt("USERSID"));


        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Ошибка при извлечении обьекта из базы");
        }
    }

    //Получение обьекта по id
    public Client get(int id) {
        Client client = new Client();
        try {
            PreparedStatement statement = getConnection().prepareStatement(idQuery);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                fillObject(client, result);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при создании запроса");
        }
        return client;
    }

    //Получение обьекта по UsersId
    public Client getByUsersId(int usersId) {
        Client client = new Client();
        try {
            PreparedStatement statement = getConnection().prepareStatement(usersIdQuery);
            statement.setInt(1, usersId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                fillObject(client, result);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при создании запроса");
        }
        return client;
    }

    //Обновление записи (передается обновленный клиент)
    public boolean update(Client client) {
        //Получаем текущий обьект по usersId
        int usersId = client.getUsersId();
        Client curClient;
        if ((curClient = getByUsersId(usersId)) == null) {
            //Если обьекта не существует - обновлять нечего
            return false;
        } else {
            //Обновляем запись
            Statement statement = null;
            ResultSet result = null;
            try {
                //Создаем обновляемую выборку
                statement = getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                //Получаем выборку таблицы
                result = statement.executeQuery(allQuery);
                //Находим необходимую запись по id
                while (result.next()) {
                    //Если находим запись - выход из цикла
                    if (result.getInt("ID") == curClient.getId()) {
                        break;
                    }
                }
                //Обновляем запись
                result.updateString("CLIENTFIO", client.getFio());
                result.updateString("CLIENTADR", client.getAddress());
                result.updateString("CLIENTPAS", client.getPassport());
                result.updateString("CLIENTIDCOD", client.getIdCod());
                result.updateString("CLIENTTEL", client.getTel());
                result.updateLong("CLIENTLEVEL", client.getLevel());
                result.updateString("CLIENTWORKINFO", client.getWorkInfo());
                //result.updateInt("SURETYID", client.getSuretyId());
                result.updateInt("USERSID", client.getUsersId());
                //Фиксируем изменения
                result.updateRow();
            } catch (SQLException exc) {
                System.out.println("Ошибка при обновлении данных");
            } finally {
                try {
                    statement.close();
                    result.close();
                } catch (SQLException exc) {
                    System.out.println("Ошибка при закрытии соединения");
                }
            }
        }
        //Обновляем список обьектов
        getList().clear();
        setList(listAll());
        return true;
    }

    public String getAllQuery() {
        return allQuery;
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

    //Создание модели для таблицы
    public void initTableModel(JTable table, List<Client> list) {
        //Формируем массив клиентов для модели таблицы
        Object[][] clientArr = new Object[list.size()][table.getColumnCount()];
        int i = 0;
        for (Client client : list) {
            //Создаем массив для клиента
            Object[] row = new Object[table.getColumnCount()];
            //Заполняем массив данными
            row[0] = client.getId();
            row[1] = client.getFio();
            row[2] = client.getAddress();
            row[3] = client.getPassport();
            row[4] = client.getIdCod();
            row[5] = client.getTel();
            row[6] = client.getLevel();
            row[7] = client.getWorkInfo();
            clientArr[i++] = row;
        }

        table.setModel(new DefaultTableModel(
                clientArr,
                ClientDAO.getFieldsName()) {

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(180);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(160);
        table.getColumnModel().getColumn(5).setPreferredWidth(140);
        table.getColumnModel().getColumn(6).setPreferredWidth(120);
        table.getColumnModel().getColumn(7).setPreferredWidth(180);
    }

    //Добавление новой записи в базу и список (используем )
    @Override
    public void add(Client element) {
        //Добавляем запись в список
        getList().add(element);

        Statement statement = null;
        ResultSet result = null;
        try {
            //Создаем изменяемую выборку
            statement = getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //Получаем набор данных

            result = statement.executeQuery(allQuery);
            //Позиционируемся на добавление новой записи
            result.moveToInsertRow();
            //Заполняем поля
            result.updateString("CLIENTFIO", element.getFio());
            result.updateString("CLIENTADR", element.getAddress());
            result.updateString("CLIENTPAS", element.getPassport());
            result.updateString("CLIENTIDCOD", element.getIdCod());
            result.updateString("CLIENTTEL", element.getTel());
            result.updateLong("CLIENTLEVEL", element.getLevel());
            result.updateString("CLIENTWORKINFO", element.getWorkInfo());
            //result.updateInt("SURETYID", element.getSuretyId());
            result.updateInt("USERSID", element.getUsersId());
            //Фиксируем изменения
            result.insertRow();
        } catch (SQLException ex) {
            System.out.println("Ошибка при изменении таблицы");
        } finally {
            try {
                //Закрываем открытые соединения
                statement.close();
                result.close();
            } catch (SQLException ex) {
                System.out.println("Ошибка при закрытии соединения");
            }
        }
    }
}
