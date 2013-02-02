
/*
 * RegForm.java
 *
 * Created on 29.12.2012, 21:48:36
 */
package com.bionic.gorbachev.banksystem.gui;

import com.bionic.gorbachev.banksystem.dao.UsersDAO;
import com.bionic.gorbachev.banksystem.entity.Users;
import com.bionic.gorbachev.banksystem.run.RegisteredSocketThread;
import com.bionic.gorbachev.banksystem.run.RegistrationServer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Aleksey Gorbachev
 */
public class RegForm extends javax.swing.JFrame {
    //Состояние пользователя по умолчанию(активен)

    public static final int userDefaultState = 1;
    //Тип пользователя по умолчанию (клиент)
    public static final int userDefaultType = 3;

    //Тип регистрации (через сокеты или обычный)
    private boolean isSocketRegistration;

    //Сокет
    private Socket socket;

    //Создание формы регистрации
    public RegForm() {
        initComponents();
        locateForm();
        initButtonClicks();
        //!!!!!!!!!!!!!!!!!!!!!
        //Подключение сокета для регистрации (закомментировать для регистрации без сокетов)
        createRegistrationSocket();
    }

    //Создание сокета для регистрации
    private void createRegistrationSocket() {
        try {
            isSocketRegistration = true;
            //Создаем сокет
            socket = new Socket("localhost", RegistrationServer.serverPort);
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(this, "Невозможно подключиться к порту!");
            System.exit(1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Ошибка подключения к серверу регистрации!");
            System.exit(1);
        }
        JOptionPane.showMessageDialog(null, "Соединение с сервером регистрации установлено!");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLog = new javax.swing.JPanel();
        loginField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pasField = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        btnReg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Регистрация");

        panelLog.setBackground(new java.awt.Color(204, 204, 204));
        panelLog.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Логин:");

        jLabel2.setText("Пароль:");

        btnClear.setText("Очистить");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnReg.setText("Отправить");
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLogLayout = new javax.swing.GroupLayout(panelLog);
        panelLog.setLayout(panelLogLayout);
        panelLogLayout.setHorizontalGroup(
            panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogLayout.createSequentialGroup()
                        .addComponent(btnReg, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loginField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(pasField, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelLogLayout.setVerticalGroup(
            panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pasField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReg)
                    .addComponent(btnClear))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Размещение и отображение формы кредитных программ

    private void locateForm() {
        setLocation(450, 250);
        setVisible(true);
    }

    //Обработка нажатия кнопки Enter и Esc
    private void initButtonClicks(){
        //Создаем слушателя на нажаьте кнопок
        KeyListener keyListener = new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    btnReg.doClick();
                }
                else {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                        dispose();
                    }
                }
            }
        };
        //Добавляем слушателей на поля ввода

       loginField.addKeyListener(keyListener);
       pasField.addKeyListener(keyListener);
    }

    //Очистка полей ввода
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        loginField.setText("");
        pasField.setText("");
}//GEN-LAST:event_btnClearActionPerformed

    //Регистрация нового пользователя
    private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegActionPerformed
        //Регистрируем через сокеты или без них
        if (isSocketRegistration != true) {
            //Создаем обьект доступа к базе данных
            UsersDAO users = new UsersDAO();
            String userLogin = loginField.getText();
            if ((userLogin.length() != 0) && (pasField.getPassword().length != 0)) {
                try {
                    //Проверка существования такого логина
                    if (users.checkLogin(userLogin)) {
                        //Создаем нового пользователя
                        Users user = new Users();
                        //Заполняем данные для пользователя
                        user.setUserName(userLogin);
                        user.setUserPsw(new String(pasField.getPassword()));
                        user.setUserState(userDefaultState);
                        user.setUserTypeId(userDefaultType);
                        //Добавляем запись в таблицу
                        users.add(user);

                        JOptionPane.showMessageDialog(this, "Регистрация успешна!");
                        //Закрываем форму регистрации
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Данный логин уже занят!");
                    }
                } catch (SQLException ex) {
                    System.out.println("Ошибка при создании запроса");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Недопустимый логин или пароль!");
            }
        } else //Регистрация через сокеты
        {
            try {
                //Создаем потоки чтения и записи для сокета
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                //Отправляем значения полей ввода на сервер
                pw.println(loginField.getText());
                pw.println(new String(pasField.getPassword()));
                //Получаем ответ от сервера
                String result = br.readLine();
                if (RegisteredSocketThread.regOk.equals(result)) {
                    JOptionPane.showMessageDialog(this, "Регистрация успешна");
                } else {
                    if (RegisteredSocketThread.regFail.equals(result)) {
                        JOptionPane.showMessageDialog(this, "Ошибка при создании запроса!");
                    } else {
                        if (RegisteredSocketThread.loginFail.equals(result)) {
                            JOptionPane.showMessageDialog(this, "Данный логин уже занят!");
                        } else {
                            if (RegisteredSocketThread.wrongInput.equals(result)) {
                                JOptionPane.showMessageDialog(this, "Недопустимый логин или пароль!");
                            }
                        }
                    }
                }
                //Закрываем форму регистрации
                dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка чтения/записи данных!");
            }
        }
    }//GEN-LAST:event_btnRegActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnReg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField loginField;
    private javax.swing.JPanel panelLog;
    private javax.swing.JPasswordField pasField;
    // End of variables declaration//GEN-END:variables
}
