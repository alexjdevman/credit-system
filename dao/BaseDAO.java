package com.bionic.gorbachev.banksystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Aleksey Gorbachev
 */

//Абстрактный класс DAO
public abstract class BaseDAO<T> {
    //URL-драйвера

    private static final String driverPath = "org.apache.derby.jdbc.ClientDriver";
    //URL-базы данных
    private static final String baseURL = "jdbc:derby://localhost:1527/DBBank";
    //Соединение с базой данных
    private static Connection conn = null;

    //Загрузка драйвера Derby
    static {
        try {
            Class.forName(driverPath);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Нет доступа к драйверу!");
        }
    }
    //Список обьектов
    private List<T> list = new ArrayList<T>();
    //Установка соединения с базой

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(baseURL, "app", null);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Нет доступа к базе данных!");
                System.exit(1);
            }
        }
        return conn;
    }

    //Получение всего списка обьектов (обновленного)
    public List<T> listAll() {
        //Устанавливаем соединение с базой
        conn = getConnection();
        try {
            //Получение всех обьектов из базы
            Statement statement = conn.createStatement();
            //Получаем результат
            ResultSet result = statement.executeQuery(getAllQuery());
            //Формируем список
            while (result.next()){
                list.add(fetchObject(result));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Невозможно создать запрос!");
        }
        //Возвращаем список
        return list;
    }

    //Получение списка обьектов
    public List<T> getList() {
        return list;
    }

    //Установка списка обьектов
    public void setList(List<T> list) {
        this.list = list;
    }

    //Инициализация компонента-таблицы из базы данных на основе списка обьектов
    public abstract void initTableModel(JTable table, List<T> list);

    //Добавление новой записи в базу
    public abstract void add(T element);

    //Получение обьекта из базы

    public abstract T fetchObject(ResultSet result);

    //Заполнение обьекта из базы
    public abstract void fillObject(T object, ResultSet result);

    //Получение обьекта по id

    public abstract T get(int id);
    //Получение запроса на выборку

    public abstract String getAllQuery();
}
