package com.bionic.gorbachev.banksystem.run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Aleksey Gorbachev
 */

//Класс обработки регистрации новых пользователей на основе сокетов
public class RegistrationServer {
    //Порт для сервера

    public static int serverPort = 8080;

    public static int registeredClients = 0;

    public static void main(String[] args) {
        try {
            //Создаем серверный сокет
            final ServerSocket servSocket = new ServerSocket(serverPort);
            JOptionPane.showMessageDialog(null, "Сервер регистрации запущен!");
            while (true) {
                //Ожидаем подключения клиентов для регистрации
                System.out.println("Ожидание подключения...");
                Socket socket = servSocket.accept();
                registeredClients++;
                //Запуск потока регистрации
                new Thread(new RegisteredSocketThread(socket)).start();
                System.out.println("Подключился клиент");
            }

        } catch (IOException exc) {
            JOptionPane.showMessageDialog(null, "Ошибка при инициализации сервера!");
        }
    }
}
