
/*
 * CheckCreditForm.java
 *
 * Created on 04.01.2013, 11:56:46
 */
package com.bionic.gorbachev.banksystem.gui;

import com.bionic.gorbachev.banksystem.dao.ClientDAO;
import com.bionic.gorbachev.banksystem.dao.CreditDAO;
import com.bionic.gorbachev.banksystem.dao.CreditProgramDAO;
import com.bionic.gorbachev.banksystem.entity.CreditProgram;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author Aleksey Gorbachev
 */

//Form for checking user credits
public class CheckCreditForm extends javax.swing.JFrame {

    public CheckCreditForm(int clientId) {
        //Fix clients id
        this.clientId = clientId;
        
        initComponents();
        
        locateForm();
        //Load to table all credita or credits for concrete user
        if (clientId != 0) {
            creditDAO.initTableModel(creditTable, creditDAO.getCreditsByClientsId(clientId));
        } else {
            creditDAO.initTableModel(creditTable, creditDAO.listAll());
        }
        //Set the context menu to table
        setPopupMenu(creditTable);
    }

    private int clientId;

    private CreditDAO creditDAO = new CreditDAO();
    

    private ClientDAO clientDAO = new ClientDAO();
    private CreditProgramDAO crProgDAO = new CreditProgramDAO();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        creditTable = new javax.swing.JTable();
        panelAction = new javax.swing.JPanel();
        buttonOk = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Кредиты клиента");

        creditTable.setAutoCreateRowSorter(true);
        creditTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Кредитная программа", "Дата", "Сумма", "Статус"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
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
        creditTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        creditTable.setSelectionBackground(new java.awt.Color(153, 255, 255));
        jScrollPane1.setViewportView(creditTable);
        creditTable.getColumnModel().getColumn(0).setPreferredWidth(140);
        creditTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        creditTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        creditTable.getColumnModel().getColumn(3).setPreferredWidth(140);

        panelAction.setBackground(new java.awt.Color(204, 204, 204));
        panelAction.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonOk.setText("Подтвердить заявку");
        buttonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkActionPerformed(evt);
            }
        });

        buttonCancel.setText("Отменить заявку");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelActionLayout = new javax.swing.GroupLayout(panelAction);
        panelAction.setLayout(panelActionLayout);
        panelActionLayout.setHorizontalGroup(
            panelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        panelActionLayout.setVerticalGroup(
            panelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOk)
                    .addComponent(buttonCancel))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelAction, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Set status of credit request
    private void changeCreditStatus(int creditStatus) {
    
        final int indexColumnName = 0;
        final int indexColumnStatus = 3;
        
        int selectedRowIndex = creditTable.getSelectedRow();
        
        if (creditStatus == -1) {
            String status = String.valueOf(creditTable.getValueAt(selectedRowIndex, indexColumnStatus));
            
            if (status.equals(CreditDAO.activeStatus)) {
                JOptionPane.showMessageDialog(this, "Отменить активную заявку невозможно!");
                return;
            }
        }
        String programName = String.valueOf(creditTable.getValueAt(selectedRowIndex, indexColumnName));
        
        CreditProgram crProg = crProgDAO.get(programName);
        int selectedProgramId = crProg.getId();
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = creditDAO.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(creditDAO.getAllQuery());
            
            while (result.next()) {
                if ((result.getInt("CLIENTSID") == clientId) &&
                        (result.getInt("CREDITPROGRAMID") == selectedProgramId)) {
                    break;
                }
            }
            result.updateInt("CREDITSTATUS", creditStatus);
            result.updateRow();

            if (creditStatus == 1) {
                JOptionPane.showMessageDialog(this, "Заявка активна");
            } else {
                JOptionPane.showMessageDialog(this, "Заявка отклонена");
            }
            creditDAO.initTableModel(creditTable, creditDAO.getCreditsByClientsId(clientId));
        } catch (SQLException exc) {
            System.out.println("Ошибка при изменении данных!");
        }
    }

    //Set the context menu to the component
    private void setPopupMenu(JComponent component){
    
        final JPopupMenu popup = new JPopupMenu();
        JMenuItem menuItemOk = new JMenuItem("Подтвердить заявку");
        JMenuItem menuItemCancel = new JMenuItem("Отменить заявку");
        popup.add(menuItemOk);
        popup.addSeparator();
        popup.add(menuItemCancel);
        
        menuItemOk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                buttonOkActionPerformed(e);
            }
        });

        menuItemCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                buttonCancelActionPerformed(e);
            }
        });

        //Right mouse click
        component.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent event){
                if (event.isPopupTrigger()){
                    popup.show(event.getComponent(), event.getX(), event.getY());
                }
            }
        });
        
        component.add(popup);
    }

    //Cancel the request
    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        changeCreditStatus(-1);
    }//GEN-LAST:event_buttonCancelActionPerformed

    //Confirm the request
    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkActionPerformed
        changeCreditStatus(1);
    }//GEN-LAST:event_buttonOkActionPerformed

    private void locateForm() {
        setLocation(400, 200);
        setTitle(getTitle() + " [ " + clientDAO.get(clientId).getFio() + " ]");
        setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonOk;
    private javax.swing.JTable creditTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAction;
    // End of variables declaration//GEN-END:variables
}
