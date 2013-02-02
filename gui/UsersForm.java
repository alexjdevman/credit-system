
/*
 * UsersForm.java
 *
 * Created on 10.01.2013, 14:02:57
 */

package com.bionic.gorbachev.banksystem.gui;

import com.bionic.gorbachev.banksystem.dao.CreditProgramDAO;
import com.bionic.gorbachev.banksystem.dao.UsersDAO;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Aleksey Gorbachev
 */

//Форма пользователей системы
public class UsersForm extends javax.swing.JFrame {

    public UsersForm() {
        initComponents();
        locateForm();
        //Инициализируем таблицу пользователей
        usersDAO.initTableModel(tableUsers, usersDAO.listAll());
    }

    //Обьект доступа к базе данных
    private UsersDAO usersDAO = new UsersDAO();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        buttonBlock = new javax.swing.JButton();
        buttonUnBlock = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Список пользователей системы");

        tableUsers.setAutoCreateRowSorter(true);
        tableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Номер", "Логин", "Тип пользователя", "Статус"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUsers.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableUsers.setSelectionBackground(new java.awt.Color(51, 255, 255));
        jScrollPane1.setViewportView(tableUsers);
        tableUsers.getColumnModel().getColumn(0).setPreferredWidth(80);
        tableUsers.getColumnModel().getColumn(1).setPreferredWidth(120);
        tableUsers.getColumnModel().getColumn(2).setPreferredWidth(140);
        tableUsers.getColumnModel().getColumn(3).setPreferredWidth(120);

        buttonBlock.setText("Заблокировать");
        buttonBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBlockActionPerformed(evt);
            }
        });

        buttonUnBlock.setText("Разблокировать");
        buttonUnBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUnBlockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(buttonBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(buttonUnBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonBlock)
                    .addComponent(buttonUnBlock))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Блокировка выбранного пользователя
    private void buttonBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBlockActionPerformed
        setUsersState(0);
        JOptionPane.showMessageDialog(this, "Пользователь заблокирован");
    }//GEN-LAST:event_buttonBlockActionPerformed

    //Разблокирование выбранного пользователя
    private void buttonUnBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUnBlockActionPerformed
        setUsersState(1);
        JOptionPane.showMessageDialog(this, "Пользователь разблокирован");
    }//GEN-LAST:event_buttonUnBlockActionPerformed

    //Размещение формы
    private void locateForm(){
        setLocation(400, 300);
        setVisible(true);
    }

    //Изменение статуса выбранного пользователя
    private void setUsersState(int state){
        //Номер колонки айди пользователя
        final int columnId = 0;
        //Если выбран пользователь в таблице
        if (tableUsers.getSelectedRow() != -1) {
            //Получаем идентификатор выбранного пользователя
            int userId = (Integer)tableUsers.getValueAt(tableUsers.getSelectedRow(), columnId);
            Statement statement = null;
            ResultSet result = null;
            try {
                statement = usersDAO.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                result = statement.executeQuery(usersDAO.getAllQuery());
                //Находим запись по айди
                while (result.next()) {
                    if (result.getInt("ID") == userId) {
                        break;
                    }
                }
                //Устанавливаем статус пользователя
                result.updateInt("USERSTATE", state);
                //Обновляем запись
                result.updateRow();
                //Обновляем таблицу
                usersDAO = new UsersDAO();
                usersDAO.initTableModel(tableUsers, usersDAO.listAll());

            } catch (SQLException exc) {
                JOptionPane.showMessageDialog(this, "Ошибка при обновлении данных");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Не выбран пользователь в таблице!");
            return;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBlock;
    private javax.swing.JButton buttonUnBlock;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUsers;
    // End of variables declaration//GEN-END:variables

}
