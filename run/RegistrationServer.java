package com.bionic.gorbachev.banksystem.run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Aleksey Gorbachev
 */

//Class for registration new users with sockets
public class RegistrationServer {
    //server port

    public static int serverPort = 8080;

    public static int registeredClients = 0;

    public static void main(String[] args) {
        try {
            //Creating server socket
            final ServerSocket servSocket = new ServerSocket(serverPort);
            JOptionPane.showMessageDialog(null, "Сервер регистрации запущен!");
            while (true) {
                //Waiting for clients connections
                System.out.println("Ожидание подключения...");
                Socket socket = servSocket.accept();
                registeredClients++;
                //Starting registration thread
                new Thread(new RegisteredSocketThread(socket)).start();
                System.out.println("Подключился клиент");
            }

        } catch (IOException exc) {
            JOptionPane.showMessageDialog(null, "Ошибка при инициализации сервера!");
        }
    }
}
