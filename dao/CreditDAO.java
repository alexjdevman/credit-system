package com.bionic.gorbachev.banksystem.dao;

import com.bionic.gorbachev.banksystem.entity.Credit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aleksey Gorbachev
 */
public class CreditDAO extends BaseDAO<Credit> {
    //Запрос на выборку всех записей из таблицы

    private static final String allQuery = "SELECT * FROM APP.CREDITS";

    //Запрос на выборку по Id
    private static final String idQuery = "SELECT * FROM APP.CREDITS WHERE (CREDITS.ID = ?)";
    //Запрос на выборку по ClientId
    private static final String clientsIdQuery = "SELECT * FROM APP.CREDITS WHERE (CREDITS.CLIENTSID = ?)";

    //Запрос на добавление новой записи
    private static final String insertQuery = "INSERT INTO APP.CREDITS (CREDITDATE, CREDITSUM, CREDITSTATUS, CLIENTSID, CREDITPROGRAMID)" +
            "VALUES (?, ?, ?, ?, ?)";

    //Названия полей таблицы кредитных программ
    private static final String[] fieldsName = new String[]{
        "Кредитная программа", "Дата", "Сумма", "Статус"
    };
    //Типы полей таблицы
    private static Class[] types = new Class[]{
        java.lang.String.class, java.util.Date.class, java.lang.Double.class, java.lang.String.class
    };
    //Параметры редактирования полей таблицы
    private static boolean[] canEdit = new boolean[]{
        false, false, false, false
    };

    //Параметры активности\неактивности заявок
    public static final String activeStatus = "АКТИВЕН";
    public static final String nonActiveStatus = "НЕАКТИВЕН";
    public static final String cancelStatus = "ОТКЛОНЕН";

    //Получение обьекта из базы
    public Credit fetchObject(ResultSet result) {
        //Создаем обьект для кредита
        Credit credit = new Credit();
        //Заполняем созданый обьект
        fillObject(credit, result);
        return credit;
    }

    //Заполнение обьекта данными из resultSet
    public void fillObject(Credit credit, ResultSet result) {
        //Заполняем кредит
        try {
            credit.setId(result.getInt("ID"));
            credit.setDate(result.getDate("CREDITDATE"));
            credit.setSum(result.getDouble("CREDITSUM"));
            credit.setStatus(result.getInt("CREDITSTATUS"));
            credit.setClientId(result.getInt("CLIENTSID"));
            credit.setCreditProgramId(result.getInt("CREDITPROGRAMID"));

        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Ошибка при извлечении обьекта из базы");
        }
    }

    //Получение обьекта по id
    public Credit get(int id) {
        Credit credit = new Credit();
        try {
            PreparedStatement statement = getConnection().prepareStatement(idQuery);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                fillObject(credit, result);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при создании запроса");
        }
        return credit;
    }

    //Получение списка кредитов по ClientsId(для конкретного клиента)
    public List<Credit> getCreditsByClientsId(int clientsId) {
        //Создаем список кредитов
        List<Credit> list = new ArrayList<Credit>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(clientsIdQuery);
            statement.setInt(1, clientsId);
            ResultSet result = statement.executeQuery();
            //Формируем список кредитов
            while (result.next()) {
                list.add(fetchObject(result));
            }

        } catch (SQLException ex) {
            System.out.println("Ошибка при формировании списка кредитов!");
        }
        return list;
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
    public void initTableModel(JTable table, List<Credit> list) {
        //Формируем массив программ для модели таблицы
        Object[][] creditArr = new Object[list.size()][table.getColumnCount()];
        int i = 0;
        for (Credit credit : list) {
            //Создаем массив для кредитной программы
            Object[] row = new Object[table.getColumnCount()];
            //Заполняем массив данными
            //Создаем обьект доступа к кредитным программам
            CreditProgramDAO crProgDAO = new CreditProgramDAO();
            //Получаем имя кредитной программы по ее Id
            String crProgName = crProgDAO.get(credit.getCreditProgramId()).getName();
            row[0] = crProgName;
            row[1] = credit.getDate();
            row[2] = credit.getSum();
            //Отмечаем статус кредита
            String creditStatus;
            //Получаем статус
            int status = credit.getStatus();
            switch (status){
                case 1 : {
                    creditStatus = activeStatus;
                    break;
                }
                case -1 : {
                    creditStatus = cancelStatus;
                    break;
                }
                default:{
                    creditStatus = nonActiveStatus;
                    break;
                }
            }
            row[3] = creditStatus;
            creditArr[i++] = row;
        }

        table.setModel(new DefaultTableModel(
                creditArr,
                CreditDAO.getFieldsName()) {

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(160);
        table.getColumnModel().getColumn(1).setPreferredWidth(140);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(140);
    }

    //Добавление новой записи в базу и список
    @Override
    public void add(Credit element) {
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
            //Преобразуем обычную дату в формат sql
            result.updateDate("CREDITDATE", new java.sql.Date(element.getDate().getTime()));
            result.updateDouble("CREDITSUM", element.getSum());
            result.updateInt("CREDITSTATUS", element.getStatus());
            result.updateInt("CLIENTSID", element.getClientId());
            result.updateInt("CREDITPROGRAMID", element.getCreditProgramId());
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
