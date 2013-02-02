
/*
 * CreditProgramForm.java
 *
 * Created on 28.12.2012, 18:47:45
 */
package com.bionic.gorbachev.banksystem.gui;

import com.bionic.gorbachev.banksystem.dao.CreditProgramDAO;
import com.bionic.gorbachev.banksystem.entity.CreditProgram;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Aleksey Gorbachev
 */

//Форма кредитных программ
public class CreditProgramForm extends javax.swing.JFrame {
    //Создание формы отображения кредитных программ

    public CreditProgramForm() {
        initComponents();
        //Заполняем таблицу из базы
        crProgDAO.initTableModel(creditProgramTable, crProgDAO.listAll());
        //Цепляем обработку событий на таблицу
        addListenersToTable();
        //Размещаем форму
        locateForm();
        //Установка обязательности заполнения полей формы
        setTextFieldVerifiers();
    }

    //Создаем обьект доступа к базе
    private CreditProgramDAO crProgDAO = new CreditProgramDAO();

    //Файл для сохранения свойств и положения формы
    private static final String FILENAME = "AdminFormBounds.xml";

    //Константы для вызова справки
    public static final String helpProg = "hh.exe";
    public static final String helpFile = "help.chm";
    
    //Класс для прослушивания событий на таблице
    private class TableListener extends MouseAdapter implements KeyListener {
        //Событие нажатия мышки

        private MouseEvent ev = null;
        //Массив значений из таблицы
        private Object[] values = new Object[creditProgramTable.getColumnCount()];
        //Обработчик щелчка мыши на записи

        public void mouseClicked(MouseEvent e) {
            //Запоминаем событие для обработки кнопки
            ev = e;
            //Получаем выбраную строку в таблице
            int selectedRowIndex = creditProgramTable.getSelectedRow();
            if (selectedRowIndex != -1) {
                //Получаем значения
                for (int i = 0; i < creditProgramTable.getColumnCount(); i++) {
                    values[i] = creditProgramTable.getValueAt(selectedRowIndex, i);
                }
                //Заполняем поля
                textFieldName.setText(String.valueOf(values[0]));
                textFieldMinAmount.setText(String.valueOf(values[1]));
                textFieldMaxAmount.setText(String.valueOf(values[2]));
                textFieldDuration.setText(String.valueOf(values[3]));
                textFieldStartPay.setText(String.valueOf(values[4]));
                textFieldPercent.setText(String.valueOf(values[5]));
                textAreaDescription.setText(String.valueOf(values[6]));
            }
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            mouseClicked(ev);
        }
        //Обработчик нажатия клавиатуры

        public void keyReleased(KeyEvent e) {
            mouseClicked(ev);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        creditProgramTable = new javax.swing.JTable();
        detailPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textFieldName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textFieldMinAmount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textFieldMaxAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textFieldDuration = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textFieldStartPay = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textFieldPercent = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaDescription = new javax.swing.JTextArea();
        buttonNew = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonBreak = new javax.swing.JButton();
        buttonActivate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Кредитные программы");

        creditProgramTable.setAutoCreateRowSorter(true);
        creditProgramTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Название", "Минимальная сумма", "Максимальная сумма", "Срок(мес.)", "Начальный взнос(%)", "Процент", "Описание", "Статус"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
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
        creditProgramTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(creditProgramTable);
        creditProgramTable.getColumnModel().getColumn(1).setPreferredWidth(140);
        creditProgramTable.getColumnModel().getColumn(2).setPreferredWidth(140);
        creditProgramTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        creditProgramTable.getColumnModel().getColumn(6).setPreferredWidth(160);
        creditProgramTable.getColumnModel().getColumn(7).setPreferredWidth(120);

        detailPanel.setBackground(new java.awt.Color(204, 204, 204));
        detailPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Название:");

        textFieldName.setEditable(false);

        jLabel2.setText("Минимальная сумма:");

        textFieldMinAmount.setEditable(false);

        jLabel3.setText("Максимальная сумма:");

        textFieldMaxAmount.setEditable(false);

        jLabel4.setText("Срок:");

        textFieldDuration.setEditable(false);

        jLabel5.setText("Начальный взнос:");

        textFieldStartPay.setEditable(false);

        jLabel6.setText("Процент:");

        textFieldPercent.setEditable(false);

        jLabel7.setText("Описание:");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaDescription.setColumns(20);
        textAreaDescription.setEditable(false);
        textAreaDescription.setLineWrap(true);
        textAreaDescription.setRows(5);
        textAreaDescription.setAutoscrolls(false);
        jScrollPane2.setViewportView(textAreaDescription);

        buttonNew.setText("Новая");
        buttonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewActionPerformed(evt);
            }
        });

        buttonSave.setText("Сохранить");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonBreak.setText("Деактивировать");
        buttonBreak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBreakActionPerformed(evt);
            }
        });

        buttonActivate.setText("Активировать");
        buttonActivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActivateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout detailPanelLayout = new javax.swing.GroupLayout(detailPanel);
        detailPanel.setLayout(detailPanelLayout);
        detailPanelLayout.setHorizontalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(detailPanelLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(detailPanelLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textFieldMinAmount))
                        .addGroup(detailPanelLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textFieldMaxAmount))
                        .addGroup(detailPanelLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textFieldDuration))
                        .addGroup(detailPanelLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textFieldStartPay))
                        .addGroup(detailPanelLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textFieldPercent)))
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addComponent(buttonNew, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailPanelLayout.createSequentialGroup()
                        .addComponent(buttonBreak, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonActivate, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        detailPanelLayout.setVerticalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textFieldMinAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(textFieldMaxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(textFieldDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(textFieldStartPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(textFieldPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNew)
                    .addComponent(buttonSave)
                    .addComponent(buttonActivate)
                    .addComponent(buttonBreak))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detailPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(detailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Добавление новой кредитной программы
    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewActionPerformed
        //Отменяем выделение записи в таблице
        creditProgramTable.clearSelection();
        //Очищаем поля ввода
        textFieldName.setText("");
        textFieldMinAmount.setText("");
        textFieldMaxAmount.setText("");
        textFieldDuration.setText("");
        textFieldStartPay.setText("");
        textFieldPercent.setText("");
        textAreaDescription.setText("");
        buttonSave.setEnabled(false);
        textFieldName.requestFocus();
    }//GEN-LAST:event_buttonNewActionPerformed

    //Деактивация кредитной программы
    private void buttonBreakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBreakActionPerformed
        //Отменить создание новой программы
        if (creditProgramTable.getSelectedRow() == -1) {
            creditProgramTable.setRowSelectionInterval(0, 0);
            textFieldName.setText("");
            textFieldMinAmount.setText("");
            textFieldMaxAmount.setText("");
            textFieldDuration.setText("");
            textFieldStartPay.setText("");
            textFieldPercent.setText("");
            textAreaDescription.setText("");
            buttonSave.setEnabled(false);
        } else {
            //Устанавливаем статус
            setCreditProgramStatus(0);
            JOptionPane.showMessageDialog(this, "Кредитная программа деактивирована!");
        }
    }//GEN-LAST:event_buttonBreakActionPerformed

    //Сохранение новой или обновление кредитной программы
    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        //Создаем новую кредитную программу по заполненным полям
        CreditProgram crProg = new CreditProgram();
        try {
            crProg.setName(textFieldName.getText());
            crProg.setMinAmount(Long.parseLong(textFieldMinAmount.getText()));
            crProg.setMaxAmount(Long.parseLong(textFieldMaxAmount.getText()));
            crProg.setDuration(Integer.parseInt(textFieldDuration.getText()));
            crProg.setStartPay(Double.parseDouble(textFieldStartPay.getText()));
            crProg.setPercent(Double.parseDouble(textFieldPercent.getText()));
            crProg.setDescription(textAreaDescription.getText());
            crProg.setCreditProgStatus(1);
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog(this, "Неверный формат данных!");
            return;
        }
        //Обновляем или добавляем новую кредитную программу
        if (crProgDAO.update(crProg) != true) {
            crProgDAO.add(crProg);
        }
        JOptionPane.showMessageDialog(this, "Данные успешно сохранены");
        //Обновляем таблицу
        crProgDAO = new CreditProgramDAO();
        crProgDAO.initTableModel(creditProgramTable, crProgDAO.listAll());
    }//GEN-LAST:event_buttonSaveActionPerformed

    //Активация кредитной программы
    private void buttonActivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActivateActionPerformed
        setCreditProgramStatus(1);
        JOptionPane.showMessageDialog(this, "Кредитная программа активирована!");
    }//GEN-LAST:event_buttonActivateActionPerformed

    //Добавление слушателей на таблицу
    private void addListenersToTable() {
        TableListener listener = new TableListener();
        creditProgramTable.addMouseListener(listener);
        creditProgramTable.addKeyListener(listener);
    }

    //Установка состояния кнопок и полей по умолчанию(для клиента и менеджера)
    public void setDefaultComponentsState() {
        //Гасим кнопки
        buttonNew.setVisible(false);
        buttonSave.setVisible(false);
        buttonBreak.setVisible(false);
        buttonActivate.setVisible(false);
        //Для клиента и менеджера отображаем только действительные кредитные программы
        crProgDAO.initTableModel(creditProgramTable, crProgDAO.getActivePrograms());
    }

    //Установка состояния кнопок и полей для администратора
    public void setAdminComponentsState() {
        //Включаем кнопки
        buttonNew.setVisible(true);
        buttonSave.setVisible(true);
        buttonBreak.setVisible(true);
        buttonActivate.setVisible(true);
        //Делаем редактируемыми поля ввода
        textFieldName.setEditable(true);
        textFieldMinAmount.setEditable(true);
        textFieldMaxAmount.setEditable(true);
        textFieldDuration.setEditable(true);
        textFieldStartPay.setEditable(true);
        textFieldPercent.setEditable(true);
        textAreaDescription.setEditable(true);
        //Устанавливаем возможность вызова справки
        KeyListener keyListenerF1 = new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                //Вызов справки
                if (e.getKeyCode() == KeyEvent.VK_F1){
                    try {
                        //Имя программы и файл справки
                        String[] callArgs = new String[]{helpProg, helpFile};
                        Runtime.getRuntime().exec(callArgs);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(CreditProgramForm.this, "Ошибка при загрузке файла справки");
                    }
                }
            }
        };
        setFocusable(true);
        //Добавляем слушателя на хелп в компоненты
        addKeyListener(keyListenerF1);
        creditProgramTable.addKeyListener(keyListenerF1);
        //Устанавливаем сохранение размеров формы администратора и их восстановление
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    //Сохраняем размеры и положение формы в XML документ
                    final XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(FILENAME)));
                    //Получаем размеры и положение
                    Rectangle rect = getBounds();
                    encoder.writeObject(rect);
                    encoder.close();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(CreditProgramForm.this, "Файл свойств формы не найден!");
                }
            }
        });
        try {
            //Устанавливаем размеры сохраненные при последнем закрытии формы
            final XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FILENAME)));
            //Получаем размеры и положение формы
            Rectangle rect = (Rectangle) decoder.readObject();
            //Устанавливаем размеры
            setBounds(rect);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Файл инициализации формы не найден");
        }
    }

    //Размещение и отображение формы кредитных программ
    private void locateForm() {
        setLocation(400, 250);
        setVisible(true);
    }

    //Установка статуса кредитной программы
    private void setCreditProgramStatus(int status) {
        //Номер колонки названия кредитной программы
        final int columnName = 0;
        //Если выбрана кредитная программа в таблице
        if (creditProgramTable.getSelectedRow() != -1) {
            //Получаем выбранную кредитную программу
            String crProgName = (String) creditProgramTable.getValueAt(creditProgramTable.getSelectedRow(), columnName);
            //Получаем ее идентификатор
            int crProgId = crProgDAO.get(crProgName).getId();
            Statement statement = null;
            ResultSet result = null;
            try {
                statement = crProgDAO.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                result = statement.executeQuery(crProgDAO.getAllQuery());
                //Находим запись по айди
                while (result.next()) {
                    if (result.getInt("ID") == crProgId) {
                        break;
                    }
                }
                //Устанавливаем статус недействительной кредитной программы
                result.updateInt("CREDITPROGSTATUS", status);
                //Обновляем запись
                result.updateRow();
                //Обновляем таблицу
                crProgDAO = new CreditProgramDAO();
                crProgDAO.initTableModel(creditProgramTable, crProgDAO.listAll());

            } catch (SQLException exc) {
                JOptionPane.showMessageDialog(this, "Ошибка при обновлении данных");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Выберите кредитную программу в таблице");
            return;
        }
    }

    //Установка проверок на поля формы
    private void setTextFieldVerifiers() {
        //Определяем массив текстовых компонентов
        final JComponent[] componentArr = new JComponent[]{textFieldName, textFieldMinAmount, textFieldMaxAmount, textFieldDuration, textFieldStartPay, textFieldPercent, textAreaDescription};
        //Создаем обьект проверки
        final TextFieldVerifier verifier = new TextFieldVerifier();
        //Установка его в компоненты
        for (JComponent component : componentArr) {
            component.setInputVerifier(verifier);
        }

        //Создаем слушателя на заполнения поля
        KeyListener listener = new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                for (JComponent component : componentArr) {
                    boolean enabled = verifier.verify(component);
                    if (enabled == false) {
                        buttonSave.setEnabled(enabled);
                        break;
                    }
                    buttonSave.setEnabled(enabled);
                }
            }
        };

        //Цепляем слушателя на все компоненты
        for (JComponent component : componentArr) {
            component.addKeyListener(listener);
        }
    }

    //Класс проверки полей формы (должны быть все заполнены)
    class TextFieldVerifier extends InputVerifier {

        @Override
        public boolean verify(JComponent input) {
            if (input instanceof JTextField) {
                JTextField tf = (JTextField) input;
                String name = tf.getName();
                return (tf.getText().length() != 0);
            } else {
                JTextArea ta = (JTextArea) input;
                return (ta.getText().length() != 0);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonActivate;
    private javax.swing.JButton buttonBreak;
    private javax.swing.JButton buttonNew;
    private javax.swing.JButton buttonSave;
    private javax.swing.JTable creditProgramTable;
    private javax.swing.JPanel detailPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textAreaDescription;
    private javax.swing.JTextField textFieldDuration;
    private javax.swing.JTextField textFieldMaxAmount;
    private javax.swing.JTextField textFieldMinAmount;
    private javax.swing.JTextField textFieldName;
    private javax.swing.JTextField textFieldPercent;
    private javax.swing.JTextField textFieldStartPay;
    // End of variables declaration//GEN-END:variables
}
