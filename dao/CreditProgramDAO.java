package com.bionic.gorbachev.banksystem.dao;

import java.sql.ResultSet;
import com.bionic.gorbachev.banksystem.entity.CreditProgram;
import java.sql.PreparedStatement;
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
public class CreditProgramDAO extends BaseDAO<CreditProgram> {
    //Запрос на выборку всех записей из таблицы

    private static final String allQuery = "SELECT * FROM APP.CREDITPROGRAM";

    //Запрос на выборку по Id
    private static final String idQuery = "SELECT * FROM APP.CREDITPROGRAM WHERE (CREDITPROGRAM.ID = ?)";

    //Запрос на выборку активных кредитных программ
    private static final String activeQuery = "SELECT * FROM APP.CREDITPROGRAM WHERE (CREDITPROGRAM.CREDITPROGSTATUS = 1)";

    //Запрос на выборку по имени кредитной программы
    private static final String nameQuery = "SELECT * FROM APP.CREDITPROGRAM WHERE (CREDITPROGRAM.CREDITNAME = ?)";

    //Запрос на добавление новой записи
    private static final String insertQuery = "INSERT INTO APP.CREDITPROGRAM (CREDITNAME, CREDITMINAMOUNT, CREDITMAXAMOUNT, CREDITDURATION, CREDITSTARTPAY, CREDITPERCENT, CREDITDESCRIPTION, CREDITPROGSTATUS)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    //Названия полей таблицы кредитных программ
    private static final String[] fieldsName = new String[]{
        "Название", "Минимальная сумма", "Максимальная сумма", "Срок(мес.)", "Начальный взнос(%)", "Процент", "Описание", "Статус"
    };
    //Типы полей таблицы
    private static Class[] types = new Class[]{
        java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
    };
    //Параметры редактирования полей таблицы
    private static boolean[] canEdit = new boolean[]{
        false, false, false, false, false, false, false, false
    };

    //Параметры активности/неактивности кредитных программ
    private static final String activeStatus = "ДЕЙСТВИТЕЛЬНА";
    private static final String nonActiveStatus = "НЕДЕЙСТВИТЕЛЬНА";
    //Получение обьекта из базы

    public CreditProgram fetchObject(ResultSet result) {
        //Создаем обьект для кредитной программы
        CreditProgram crProg = new CreditProgram();
        //Заполняем созданый обьект
        fillObject(crProg, result);
        return crProg;
    }

    //Заполнение обьекта данными из resultSet
    public void fillObject(CreditProgram crProg, ResultSet result) {
        //Заполняем кредитную программу
        try {
            crProg.setId(result.getInt("ID"));
            crProg.setName(result.getString("CREDITNAME"));
            crProg.setMinAmount(result.getLong("CREDITMINAMOUNT"));
            crProg.setMaxAmount(result.getLong("CREDITMAXAMOUNT"));
            crProg.setDuration(result.getInt("CREDITDURATION"));
            crProg.setStartPay(result.getDouble("CREDITSTARTPAY"));
            crProg.setPercent(result.getDouble("CREDITPERCENT"));
            crProg.setDescription(result.getString("CREDITDESCRIPTION"));
            crProg.setCreditProgStatus(result.getInt("CREDITPROGSTATUS"));
        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Ошибка при извлечении обьекта из базы");
        }
    }

    //Получение кредитной программы по id
    public CreditProgram get(int id) {
        CreditProgram crProg = new CreditProgram();
        try {
            PreparedStatement statement = getConnection().prepareStatement(idQuery);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                fillObject(crProg, result);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при создании запроса");
        }
        return crProg;
    }

    //Получение кредитной программы по ее имени
    public CreditProgram get(String name) {
        CreditProgram crProg = new CreditProgram();
        try {
            PreparedStatement statement = getConnection().prepareStatement(nameQuery);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                fillObject(crProg, result);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при создании запроса");
        }
        return crProg;
    }

    //Получение списка активных кредитных программ
    public List<CreditProgram> getActivePrograms() {
        //Создаем результирующий список
        List<CreditProgram> resultList = new ArrayList<CreditProgram>();
        try {
            //Создаем запрос к БД
            Statement statement = getConnection().createStatement();
            ResultSet result = statement.executeQuery(activeQuery);
            while (result.next()) {
                resultList.add(fetchObject(result));
            }
        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Ошибка при создании запроса");
        }

        return resultList;
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
    public void initTableModel(JTable table, List<CreditProgram> list) {
        //Формируем массив программ для модели таблицы
        Object[][] crProgArr = new Object[list.size()][table.getColumnCount()];
        int i = 0;
        for (CreditProgram prog : list) {
            //Создаем массив для кредитной программы
            Object[] row = new Object[table.getColumnCount()];
            //Заполняем массив данными
            row[0] = prog.getName();
            row[1] = prog.getMinAmount();
            row[2] = prog.getMaxAmount();
            row[3] = prog.getDuration();
            row[4] = prog.getStartPay();
            row[5] = prog.getPercent();
            row[6] = prog.getDescription();
            //Получаем статус кредитной программы
            String crProgStatus;
            int status = prog.getCreditProgStatus();
            switch (status) {
                case 1: {
                    crProgStatus = activeStatus;
                    break;
                }
                default: {
                    crProgStatus = nonActiveStatus;
                    break;
                }
            }
            row[7] = crProgStatus;
            crProgArr[i++] = row;
        }

        table.setModel(new DefaultTableModel(
                crProgArr,
                CreditProgramDAO.getFieldsName()) {

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(140);
        table.getColumnModel().getColumn(1).setPreferredWidth(140);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(6).setPreferredWidth(160);
        table.getColumnModel().getColumn(7).setPreferredWidth(140);
    }

    //Добавление новой записи в базу и список
    @Override
    public void add(CreditProgram element) {
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
            result.updateString("CREDITNAME", element.getName());
            result.updateLong("CREDITMINAMOUNT", element.getMinAmount());
            result.updateLong("CREDITMAXAMOUNT", element.getMaxAmount());
            result.updateInt("CREDITDURATION", element.getDuration());
            result.updateDouble("CREDITSTARTPAY", element.getStartPay());
            result.updateDouble("CREDITPERCENT", element.getPercent());
            result.updateString("CREDITDESCRIPTION", element.getDescription());
            result.updateInt("CREDITPROGSTATUS", element.getCreditProgStatus());
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

    //Обновление записи (передается обновленная кредитная программа)
    public boolean update(CreditProgram crProg) {
        //Получаем текущий обьект по названию кредитной программы
        String crProgName = crProg.getName();
        CreditProgram curProgram;
        if ((curProgram = get(crProgName)) == null) {
            //Если обьекта c таким именем не существует - обновлять нечего
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
                    if (result.getString("CREDITNAME").equals(curProgram.getName())) {
                        break;
                    }
                }
                //Обновляем запись
                result.updateLong("CREDITMINAMOUNT", crProg.getMinAmount());
                result.updateLong("CREDITMAXAMOUNT", crProg.getMaxAmount());
                result.updateInt("CREDITDURATION", crProg.getDuration());
                result.updateDouble("CREDITSTARTPAY", crProg.getStartPay());
                result.updateDouble("CREDITPERCENT", crProg.getPercent());
                result.updateString("CREDITDESCRIPTION", crProg.getDescription());
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
}
