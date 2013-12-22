
/*
 * AdminForm.java
 *
 * Created on 08.01.2013, 23:00:24
 */

package com.bionic.gorbachev.banksystem.gui;

/**
 *
 * @author Aleksey Gorbachev
 */
public class AdminForm extends javax.swing.JFrame {

    public AdminForm() {
        initComponents();
        locateForm();
    }

    private void locateForm(){
        setLocation(300, 300);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonCrProg = new javax.swing.JButton();
        buttonUsers = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Форма администрирования");
        setResizable(false);

        buttonCrProg.setText("Кредитные программы");
        buttonCrProg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCrProgActionPerformed(evt);
            }
        });

        buttonUsers.setText("Пользователи");
        buttonUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUsersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonCrProg, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(buttonUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCrProg)
                    .addComponent(buttonUsers))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Edit credit programs
    private void buttonCrProgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrProgActionPerformed
        new CreditProgramForm().setAdminComponentsState();
    }//GEN-LAST:event_buttonCrProgActionPerformed

    //Show users form
    private void buttonUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUsersActionPerformed
        new UsersForm();
    }//GEN-LAST:event_buttonUsersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCrProg;
    private javax.swing.JButton buttonUsers;
    // End of variables declaration//GEN-END:variables

}
