/*
 * CreditForm.java
 *
 * Created on 02.01.2013, 16:41:55
 */
package com.bionic.gorbachev.banksystem.gui;

import com.bionic.gorbachev.banksystem.dao.CreditDAO;
import com.bionic.gorbachev.banksystem.dao.CreditProgramDAO;
import com.bionic.gorbachev.banksystem.entity.Credit;
import com.bionic.gorbachev.banksystem.entity.CreditProgram;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Aleksey Gorbachev
 */

//Форма кредитов
public class CreditForm extends javax.swing.JFrame {

    //Создание формы кредитов (передаем Id текущего клиента)
    public CreditForm(int clientsId) {
        //Инициализация компонентов
        initComponents();
        //Размещение формы
        locateForm();
        //Устанавливаем текущего пользователя
        this.clientsId = clientsId;
        //Инициализируем таблицу кредитами текущего пользователя
        creditDAO.initTableModel(creditTable, creditDAO.getCreditsByClientsId(clientsId));
        //Инициализируем перечень кредитных программ
        initComboBoxCreditPrograms();
        //Установка контекстного меню в таблицу
        setPopupMenu(creditTable);
    }
    //Создание формы кредитов по умолчанию
    public CreditForm(){
        //Инициализация компонентов
        initComponents();
        //Размещение формы
        locateForm();
        //Блокировка клавиш
        buttonSend.setEnabled(false);
        buttonBack.setEnabled(false);
    }
    //Id текущего пользователя
    private int clientsId;

    //Обьект доступа к кредитам пользователя
    private CreditDAO creditDAO = new CreditDAO();

    //Обьект доступа к кредитным программам
    private CreditProgramDAO crProgDAO = new CreditProgramDAO();

    //Размещение формы
    private void locateForm() {
        setLocation(400, 200);
        setVisible(true);
    }

    //Инициализация выпадающего списка кредитных программ
    private void initComboBoxCreditPrograms() {
        //Создаем список названий кредитных программ
        List<String> listNames = new ArrayList<String>();
        //Получаем список всех кредитных программ
        List<CreditProgram> listPrograms = crProgDAO.listAll();
        //Формируем список имен кредитных программ
        for (CreditProgram crProg : listPrograms) {
            //Добавляем только активные кредитные программы
            if (crProg.getCreditProgStatus() == 1) {
                listNames.add(crProg.getName());
            }
        }
        сomboBoxCreditPrograms.setModel(new ComboBoxCreditProgramModel(listNames));
    }

    //Добавление контекстного меню в компонент
    private void setPopupMenu(JComponent component) {
        //Создаем контекстное меню
        final JPopupMenu popup = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Отменить заявку");
        popup.add(menuItem);
        //Добавляем слушателя на нажатие кнопки меню и на показ контекстного меню
        menuItem.addActionListener(new ActionListener() {
            //Отмена выбранной заявки

            public void actionPerformed(ActionEvent e) {
                buttonBackActionPerformed(e);
            }
        });

        //Щелчок правой кнопкой мыши
        component.addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent event) {
                //Проверка на вызов контекстного меню
                if (event.isPopupTrigger()) {
                    popup.show(event.getComponent(), event.getX(), event.getY());
                }
            }
        });
        //Добавляем контекстное меню в компонент
        component.add(popup);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        creditTable = new javax.swing.JTable();
        panelInput = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonSend = new javax.swing.JButton();
        сomboBoxCreditPrograms = new javax.swing.JComboBox();
        textFieldSum = new javax.swing.JTextField();
        buttonBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Оформление кредита");

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
        creditTable.getColumnModel().getColumn(0).setPreferredWidth(160);
        creditTable.getColumnModel().getColumn(1).setPreferredWidth(140);
        creditTable.getColumnModel().getColumn(2).setPreferredWidth(140);
        creditTable.getColumnModel().getColumn(3).setPreferredWidth(140);

        panelInput.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Кредитная программа:");

        jLabel2.setText("Сумма кредита:");

        buttonSend.setText("Отправить заявку");
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });

        buttonBack.setText("Отменить заявку");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInputLayout = new javax.swing.GroupLayout(panelInput);
        panelInput.setLayout(panelInputLayout);
        panelInputLayout.setHorizontalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(buttonSend, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(сomboBoxCreditPrograms, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldSum, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(196, Short.MAX_VALUE))
        );
        panelInputLayout.setVerticalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(сomboBoxCreditPrograms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textFieldSum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonBack)
                    .addComponent(buttonSend))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelInput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Отправление заявки на кредит
    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed
        //Проверка возможности оформления кредита по указанной сумме
        //Получаем кредитную программу по ее названию
        CreditProgram crProg = crProgDAO.get(String.valueOf(сomboBoxCreditPrograms.getSelectedItem()));
        //Получаем сумму кредита указанную пользователем
        double creditSum = Double.parseDouble(textFieldSum.getText());
        if ((creditSum <= 0)||(creditSum < crProg.getMinAmount())||creditSum > crProg.getMaxAmount()){
            JOptionPane.showMessageDialog(this, "Невозможно оформить кредит на указанную сумму!");
            return;
        }
        //Создаем новый кредит
        Credit credit = new Credit();
        //Заполняем данными
        try {
            credit.setDate(new Date());
            credit.setSum(creditSum);
            credit.setStatus(0);
            credit.setClientId(clientsId);
            //Устанавливаем идентификатор кредитной программы
            int crProgId = crProg.getId();
            credit.setCreditProgramId(crProgId);
            //Добавляем кредит
            creditDAO.add(credit);
            //Обновляем таблицу для текущего клиента
            creditDAO.initTableModel(creditTable, creditDAO.getCreditsByClientsId(clientsId));
            //Сообщаем о успешной заявке
            JOptionPane.showMessageDialog(this, "Ваша заявка принята!");
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog(this, "Неверный формат данных!");
            return;
        }
    }//GEN-LAST:event_buttonSendActionPerformed

    //Отмена выбранной заявки на кредит
    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        //Индексы названий и статуса заявки
        final int indexColumnName = 0;
        final int indexColumnStatus = 3;
        //Определяем индекс выбранной строки в таблице
        int selectedRowIndex = creditTable.getSelectedRow();
        //Определяем статус выбранной заявки
        String status = String.valueOf(creditTable.getValueAt(selectedRowIndex, indexColumnStatus));
        //Если заявка активна - выход из метода (не можем ее удалить)
        if (status.equals(CreditDAO.activeStatus)) {
            JOptionPane.showMessageDialog(this, "Отменить активную заявку невозможно!");
            return;
        }
        //Получаем название выбранной кредитной программы в таблице
        String programName = String.valueOf(creditTable.getValueAt(selectedRowIndex, indexColumnName));
        //Получаем идентификатор кредитной программы
        CreditProgram crProg = crProgDAO.get(programName);
        int selectedProgramId = crProg.getId();
        //По идентификатору находим кредитную прорамму для данного клиента и удаляем ее
        Statement statement = null;
        ResultSet result = null;
        try {
            //Получаем общую выборку
            statement = creditDAO.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(creditDAO.getAllQuery());
            //Ищем запись для текущего клиента и кредитной программы
            while (result.next()) {
                if ((result.getInt("CLIENTSID") == clientsId) &&
                        (result.getInt("CREDITPROGRAMID") == selectedProgramId)) {
                    break;
                }
            }
            //Удаляем данную запись
            result.deleteRow();
            JOptionPane.showMessageDialog(this, "Заявка отменена");
            //Обновляем таблицу для текущего клиента
            creditDAO.initTableModel(creditTable, creditDAO.getCreditsByClientsId(clientsId));
        } catch (SQLException exc) {
        }
    }//GEN-LAST:event_buttonBackActionPerformed

    //Внутренний класс для модели ComboBox кредитных программ
    private class ComboBoxCreditProgramModel implements ComboBoxModel {

        //Передаем список названий кредитных программ
        public ComboBoxCreditProgramModel(List<String> list) {
            this.list = list;
        }

        //Индекс выбранного элемента
        int i = 0;
        //Список элементов
        private List<String> list = new ArrayList<String>();

        //Выбор элемента
        public void setSelectedItem(Object anItem) {
            i = list.indexOf(anItem);
        }
        //Получение выбранного элемента

        public Object getSelectedItem() {
            return list.get(i);
        }

        //Получение размера списка
        public int getSize() {
            return list.size();
        }

        //Получение обьекта с позиции
        public Object getElementAt(int index) {
            return list.get(index);
        }

        public void addListDataListener(ListDataListener l) {
        }

        public void removeListDataListener(ListDataListener l) {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonSend;
    private javax.swing.JTable creditTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelInput;
    private javax.swing.JTextField textFieldSum;
    private javax.swing.JComboBox сomboBoxCreditPrograms;
    // End of variables declaration//GEN-END:variables
}
