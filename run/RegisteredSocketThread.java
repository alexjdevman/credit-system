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

//Class for registration new client in the separate thread
public class RegisteredSocketThread implements Runnable {
    //socket

    private Socket socket;

    private InputStream is;
    private OutputStream os;

    //Server responses
    public static final String regOk = "OK";   //Success
    public static final String regFail = "ERROR";  //Fail response to DB
    public static final String loginFail = "LOGINFAIL";    //Login is already in use
    public static final String wrongInput = "WRONG";       

    public RegisteredSocketThread(Socket socket) throws IOException {
        this.socket = socket;
        this.is = socket.getInputStream();
        this.os = socket.getOutputStream();
    }

    private RegisteredSocketThread() {
    }

    //Clients registration
    public void run() {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        String result;
        while (true) {
            try {
                //Reading data from client
                String userLogin = br.readLine();
                String userPassword = br.readLine();
                if ((userLogin.length() != 0) && (userPassword.length() != 0)) {
                    try {
                    
                        UsersDAO users = new UsersDAO();
                        //Check for user login
                        if (users.checkLogin(userLogin)) {
                            Users user = new Users();
                            
                            user.setUserName(userLogin);
                            user.setUserPsw(userPassword);
                            user.setUserState(RegForm.userDefaultState);
                            user.setUserTypeId(RegForm.userDefaultType);
                            
                            users.add(user);                            
                            result = regOk;
                            break;
                        } else {
                            result = loginFail;
                            break;
                        }
                    } catch (SQLException ex) {                    
                        result = regFail;
                        break;
                    }
                } else {                    
                    result = wrongInput;
                    break;
                }

            } catch (IOException ex) {
                result = regFail;
                break;
            }
        }
        // Send response to client
        PrintWriter pw = new PrintWriter(os, true);
        pw.println(result);
        try {
            is.close();
            os.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Ошибка закрытия соединения!");
        }
    }
}

