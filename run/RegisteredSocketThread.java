package com.bionic.gorbachev.banksystem.run;

import com.bionic.gorbachev.banksystem.dao.UsersDAO;
import com.bionic.gorbachev.banksystem.entity.Users;
import com.bionic.gorbachev.banksystem.gui.RegForm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

/**
 *
 * @author Aleksey Gorbachev
 */

//Класс для регистрации клиента в отдельном потоке
public class RegisteredSocketThread implements Runnable {
    //Сокет

    private Socket socket;

    //Потоки чтения и записи данных
    private InputStream is;
    private OutputStream os;

    //Ответы, генерируемые сервером клиенту
    public static final String regOk = "OK";   //Успешно
    public static final String regFail = "ERROR";  //Ошибка запроса к БД
    public static final String loginFail = "LOGINFAIL";    //Логин уже занят
    public static final String wrongInput = "WRONG";       //Неправильный логин пароль

    public RegisteredSocketThread(Socket socket) throws IOException {
        this.socket = socket;
        //Устанавливаем потоки
        this.is = socket.getInputStream();
        this.os = socket.getOutputStream();
    }

    private RegisteredSocketThread() {
    }

    //Регистрация клиента
    public void run() {
        //Создаем буферизированный поток чтения
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //Ответ клиенту
        String result;
        while (true) {
            try {
                //Читаем данные от клиента
                String userLogin = br.readLine();
                String userPassword = br.readLine();
                if ((userLogin.length() != 0) && (userPassword.length() != 0)) {
                    try {
                        //Создаем обьект доступа к базе данных
                        UsersDAO users = new UsersDAO();
                        //Проверка существования такого логина
                        if (users.checkLogin(userLogin)) {
                            //Создаем нового пользователя
                            Users user = new Users();
                            //Заполняем данные для пользователя
                            user.setUserName(userLogin);
                            user.setUserPsw(userPassword);
                            user.setUserState(RegForm.userDefaultState);
                            user.setUserTypeId(RegForm.userDefaultType);
                            //Добавляем запись в таблицу
                            users.add(user);
                            //Ответ клиенту
                            result = regOk;
                            break;
                        } else {
                            //Логин уже занят
                            result = loginFail;
                            break;
                        }
                    } catch (SQLException ex) {
                        //Ошибка при создании запроса к БД
                        result = regFail;
                        break;
                    }
                } else {
                    //Недопустимый логин\пароль
                    result = wrongInput;
                    break;
                }

            } catch (IOException ex) {
                result = regFail;
                break;
            }
        }
        //Отсылаем ответ клиенту
        PrintWriter pw = new PrintWriter(os, true);
        pw.println(result);
        //Закрываем потоки ввода\вывода и сокет
        try {
            is.close();
            os.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Ошибка закрытия соединения!");
        }
    }
}
