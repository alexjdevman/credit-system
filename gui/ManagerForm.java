
/*
 * ManagerForm.java
 *
 * Created on 03.01.2013, 15:00:13
 */
package com.bionic.gorbachev.banksystem.gui;

import com.bionic.gorbachev.banksystem.dao.ClientDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Aleksey Gorbachev
 */

//Форма менеджера
public class ManagerForm extends javax.swing.JFrame {

    //Создание формы менеджера
    public ManagerForm() {
        initComponents();
        //Размещение формы
        locateForm();
        //Инициализируем таблицу списком клиентов
        clientDAO.initTableModel(tableClients, clientDAO.listAll());
        //Добавляем слушателей на таблицу
        addListenersToTable();
        //Добавляем контекстное меню
        setPopupMenu(tableClients);
    }

    //Обьект доступа к таблице клиентов
    private ClientDAO clientDAO = new ClientDAO();

    //Добавление контекстного меню в компонент
    private void setPopupMenu(JComponent component) {
        //Создаем контекстное меню
        final JPopupMenu popup = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Кредиты клиента..");
        popup.add(menuItem);
        //Добавляем слушателя на нажатие кнопки меню и на показ контекстного меню
        menuItem.addActionListener(new ActionListener() {
            //Показ формы кредитов выбранного клиента

            public void actionPerformed(ActionEvent e) {
                buttonCreditActionPerformed(e);
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

    //Размещение формы
    private void locateForm() {
        setLocation(300, 200);
        setVisible(true);
    }
    //Добавление слушателей на таблицу

    private void addListenersToTable() {
        TableListener listener = new TableListener();
        tableClients.addMouseListener(listener);
        tableClients.addKeyListener(listener);
    }


    //Класс для прослушивания событий на таблице
    private class TableListener extends MouseAdapter implements KeyListener {
        //Событие нажатия мышки

        private MouseEvent ev = null;
        //Массив значений из таблицы
        private Object[] values = new Object[tableClients.getColumnCount()];
        //Обработчик щелчка мыши на записи

        public void mouseClicked(MouseEvent e) {
            //Запоминаем событие для обработки кнопки
            ev = e;
            //Получаем выбраную строку в таблице
            int selectedRowIndex = tableClients.getSelectedRow();
            //Получаем значения
            for (int i = 0; i < tableClients.getColumnCount(); i++) {
                values[i] = tableClients.getValueAt(selectedRowIndex, i);
            }
            //Заполняем поля
            textFieldFio.setText(String.valueOf(values[1]));
            textFieldAdr.setText(String.valueOf(values[2]));
            textFieldPas.setText(String.valueOf(values[3]));
            textFieldIdCod.setText(String.valueOf(values[4]));
            textFieldTel.setText(String.valueOf(values[5]));
            textFieldLevel.setText(String.valueOf(values[6]));
            textAreaWorkInfo.setText(String.valueOf(values[7]));
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
        }
        //Обработчик нажатия клавиатуры

        public void keyReleased(KeyEvent e) {
            mouseClicked(ev);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelManage = new javax.swing.JPanel();
        buttonCredit = new javax.swing.JButton();
        buttonPrograms = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textFieldFio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textFieldAdr = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textFieldPas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textFieldIdCod = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textFieldTel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textFieldLevel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaWorkInfo = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableClients = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Управление кредитами и программами");

        panelManage.setBackground(new java.awt.Color(204, 204, 204));
        panelManage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonCredit.setText("Кредиты");
        buttonCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreditActionPerformed(evt);
            }
        });

        buttonPrograms.setText("Кредитные программы");
        buttonPrograms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProgramsActionPerformed(evt);
            }
        });

        jLabel1.setText("ФИО:");

        textFieldFio.setEditable(false);

        jLabel2.setText("Адресс:");

        textFieldAdr.setEditable(false);

        jLabel3.setText("Паспорт (серия, номер):");

        textFieldPas.setEditable(false);

        jLabel4.setText("Идентификационный код:");

        textFieldIdCod.setEditable(false);
        textFieldIdCod.setName("idcod"); // NOI18N

        jLabel5.setText("Контактный телефон:");

        textFieldTel.setEditable(false);
        textFieldTel.setName("telephone"); // NOI18N

        jLabel6.setText("Уровень доходов:");

        textFieldLevel.setEditable(false);

        jLabel7.setText("Информация о работе:");

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaWorkInfo.setColumns(20);
        textAreaWorkInfo.setEditable(false);
        textAreaWorkInfo.setLineWrap(true);
        textAreaWorkInfo.setRows(5);
        textAreaWorkInfo.setAutoscrolls(false);
        jScrollPane3.setViewportView(textAreaWorkInfo);

        javax.swing.GroupLayout panelManageLayout = new javax.swing.GroupLayout(panelManage);
        panelManage.setLayout(panelManageLayout);
        panelManageLayout.setHorizontalGroup(
            panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManageLayout.createSequentialGroup()
                        .addComponent(buttonCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(buttonPrograms, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelManageLayout.createSequentialGroup()
                        .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelManageLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldAdr, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
                            .addGroup(panelManageLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldFio, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
                            .addGroup(panelManageLayout.createSequentialGroup()
                                .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldIdCod, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                                    .addComponent(textFieldPas, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                                    .addComponent(textFieldLevel, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                                    .addComponent(textFieldTel, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))))
                        .addGap(22, 22, 22)
                        .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addComponent(jLabel7))))
                .addContainerGap())
        );
        panelManageLayout.setVerticalGroup(
            panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManageLayout.createSequentialGroup()
                        .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(textFieldFio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldAdr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(textFieldPas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(textFieldIdCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(textFieldTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(textFieldLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(panelManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonCredit)
                            .addComponent(buttonPrograms)))
                    .addGroup(panelManageLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tableClients.setAutoCreateRowSorter(true);
        tableClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Номер", "ФИО", "Адресс", "Паспорт", "Идентификационный код", "Контактный телефон", "Уровень доходов", "Информация о работе"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableClients.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableClients.setSelectionBackground(new java.awt.Color(102, 255, 255));
        jScrollPane2.setViewportView(tableClients);
        tableClients.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableClients.getColumnModel().getColumn(1).setPreferredWidth(180);
        tableClients.getColumnModel().getColumn(2).setPreferredWidth(200);
        tableClients.getColumnModel().getColumn(3).setPreferredWidth(120);
        tableClients.getColumnModel().getColumn(4).setPreferredWidth(180);
        tableClients.getColumnModel().getColumn(5).setPreferredWidth(140);
        tableClients.getColumnModel().getColumn(6).setPreferredWidth(120);
        tableClients.getColumnModel().getColumn(7).setPreferredWidth(180);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelManage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelManage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Отображаем кредитные программы
    private void buttonProgramsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProgramsActionPerformed
        new CreditProgramForm().setDefaultComponentsState();
    }//GEN-LAST:event_buttonProgramsActionPerformed

    //Отображение формы проверки кредитов клиента
    private void buttonCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreditActionPerformed
        final int columnIdIndex = 0;
        //Получаем Id выбранного клиента
        int rowSelectedIndex = tableClients.getSelectedRow();
        int selectedClientId;
        //Если выбрана запись в таблице
        if (rowSelectedIndex != -1) {
            String stringValueId = String.valueOf(tableClients.getValueAt(rowSelectedIndex, columnIdIndex));
            selectedClientId = Integer.parseInt(stringValueId);
        }
        else {
            selectedClientId = 0;
        }
        //Открываем форму проверки кредитов
        new CheckCreditForm(selectedClientId);
    }//GEN-LAST:event_buttonCreditActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCredit;
    private javax.swing.JButton buttonPrograms;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelManage;
    private javax.swing.JTable tableClients;
    private javax.swing.JTextArea textAreaWorkInfo;
    private javax.swing.JTextField textFieldAdr;
    private javax.swing.JTextField textFieldFio;
    private javax.swing.JTextField textFieldIdCod;
    private javax.swing.JTextField textFieldLevel;
    private javax.swing.JTextField textFieldPas;
    private javax.swing.JTextField textFieldTel;
    // End of variables declaration//GEN-END:variables
}
