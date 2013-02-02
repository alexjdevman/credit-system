
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

//Форма проверки и подтверждения кредитов пользователя
public class CheckCreditForm extends javax.swing.JFrame {

    //Передаем Id выбранного клиента
    public CheckCreditForm(int clientId) {
        //Фиксируем выбранного клиента
        this.clientId = clientId;
        //Инициализируем компоненты
        initComponents();
        //Размещение формы
        locateForm();
        //Загружаем в таблицу все кредиты клиентов, или кредиты для конкретного клиента
        if (clientId != 0) {
            creditDAO.initTableModel(creditTable, creditDAO.getCreditsByClientsId(clientId));
        } else {
            creditDAO.initTableModel(creditTable, creditDAO.listAll());
        }
        //Добавляем контекстное меню в таблицу
        setPopupMenu(creditTable);
    }

    //Id выбранного клиента
    private int clientId;

    //Обьект доступа к кредитам пользователя
    private CreditDAO creditDAO = new CreditDAO();
    //Обьект доступа к клиентам
    private ClientDAO clientDAO = new ClientDAO();
    //Обьект доступа к кредитным программам
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

    //Установка статуса кредитной заявки
    private void changeCreditStatus(int creditStatus) {
        //Индексы названий и статуса заявки
        final int indexColumnName = 0;
        final int indexColumnStatus = 3;
        //Определяем индекс выбранной строки в таблице
        int selectedRowIndex = creditTable.getSelectedRow();
        //Если отклоняем заявку
        if (creditStatus == -1) {
            //Определяем статус выбранной заявки
            String status = String.valueOf(creditTable.getValueAt(selectedRowIndex, indexColumnStatus));
            //Если заявка активна - выход из метода (не можем ее отклонить)
            if (status.equals(CreditDAO.activeStatus)) {
                JOptionPane.showMessageDialog(this, "Отменить активную заявку невозможно!");
                return;
            }
        }
        //Получаем название выбранной кредитной программы в таблице
        String programName = String.valueOf(creditTable.getValueAt(selectedRowIndex, indexColumnName));
        //Получаем идентификатор кредитной программы
        CreditProgram crProg = crProgDAO.get(programName);
        int selectedProgramId = crProg.getId();
        //По идентификатору находим кредитную прорамму для данного клиента и меняем ее статус
        Statement statement = null;
        ResultSet result = null;
        try {
            //Получаем общую выборку
            statement = creditDAO.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(creditDAO.getAllQuery());
            //Ищем запись для текущего клиента и кредитной программы
            while (result.next()) {
                if ((result.getInt("CLIENTSID") == clientId) &&
                        (result.getInt("CREDITPROGRAMID") == selectedProgramId)) {
                    break;
                }
            }
            //Меняем статус даной заявки
            result.updateInt("CREDITSTATUS", creditStatus);
            result.updateRow();

            if (creditStatus == 1) {
                JOptionPane.showMessageDialog(this, "Заявка активна");
            } else {
                JOptionPane.showMessageDialog(this, "Заявка отклонена");
            }
            //Обновляем таблицу для текущего клиента
            creditDAO.initTableModel(creditTable, creditDAO.getCreditsByClientsId(clientId));
        } catch (SQLException exc) {
            System.out.println("Ошибка при изменении данных!");
        }
    }

    //Добавление контекстного меню в компонент
    private void setPopupMenu(JComponent component){
        //Создаем контекстное меню
        final JPopupMenu popup = new JPopupMenu();
        JMenuItem menuItemOk = new JMenuItem("Подтвердить заявку");
        JMenuItem menuItemCancel = new JMenuItem("Отменить заявку");
        popup.add(menuItemOk);
        popup.addSeparator();
        popup.add(menuItemCancel);
        //Добавляем слушателя на нажатие кнопки меню и на показ контекстного меню
        menuItemOk.addActionListener(new ActionListener(){
            //Отмена выбранной заявки
            public void actionPerformed(ActionEvent e) {
                buttonOkActionPerformed(e);
            }
        });

        menuItemCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                buttonCancelActionPerformed(e);
            }
        });

        //Щелчок правой кнопкой мыши
        component.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent event){
                //Проверка на вызов контекстного меню
                if (event.isPopupTrigger()){
                    popup.show(event.getComponent(), event.getX(), event.getY());
                }
            }
        });
        //Добавляем контекстное меню в компонент
        component.add(popup);
    }

    //Отмена выбранной заявки (установка статуса - отклонена)
    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        changeCreditStatus(-1);
    }//GEN-LAST:event_buttonCancelActionPerformed

    //Подтверждение заявки
    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkActionPerformed
        changeCreditStatus(1);
    }//GEN-LAST:event_buttonOkActionPerformed

    //Размещение формы
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
