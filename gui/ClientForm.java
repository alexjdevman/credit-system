
/*
 * ClientForm.java
 *
 * Created on 30.12.2012, 23:11:17
 */
package com.bionic.gorbachev.banksystem.gui;

import com.bionic.gorbachev.banksystem.dao.ClientDAO;
import com.bionic.gorbachev.banksystem.entity.Client;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Aleksey Gorbachev
 */
public class ClientForm extends javax.swing.JFrame {

    //Создание формы клиента (передаем Id текущего пользователя)
    public ClientForm(int curUserId) {
        //Устанавливаем текущего пользователя
        this.curUserId = curUserId;
        //Инициализация компонентов
        initComponents();
        //Размещение формы
        locateForm();
        //Заполняем данными поля клиента
        initTextFields();
        //Установка обязательности заполнения полей формы
        setTextFieldVerifiers();
    }

    //По умолчанию (вход под администратором)
    public ClientForm() {
        this(1);
    }

    //Обьект доступа к базе данных
    private ClientDAO clientDAO = new ClientDAO();

    //Id текущего пользователя
    private int curUserId;

    //Получение текущего id пользователя
    public int getCurUserId() {
        return curUserId;
    }
    //Установка текущего Id

    public void setCurUserId(int curUserId) {
        this.curUserId = curUserId;
    }

    //Размещение формы
    private void locateForm() {
        setLocation(300, 200);
        setVisible(true);
    }

    //Инициализация полей формы
    private void initTextFields() {
        //Получаем клиента по текущему пользователю
        Client client = clientDAO.getByUsersId(curUserId);
        //Если есть информация
        if (client != null) {
            textFieldFio.setText(client.getFio());
            textFieldAdr.setText(client.getAddress());
            textFieldPas.setText(client.getPassport());
            textFieldIdCod.setText(client.getIdCod());
            textFieldTel.setText(client.getTel());
            textFieldLevel.setText(String.valueOf(client.getLevel()));
            textAreaWorkInfo.setText(client.getWorkInfo());
        }
    }

    //Установка проверок на поля формы
    private void setTextFieldVerifiers() {
        //Определяем массив текстовых компонентов
        final JComponent[] componentArr = new JComponent[]{textFieldFio, textFieldAdr, textFieldPas, textFieldIdCod, textFieldTel, textFieldLevel, textAreaWorkInfo};
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
                        buttonSendInfo.setEnabled(enabled);
                        break;
                    }
                    buttonSendInfo.setEnabled(enabled);
                }
            }
        };

        //Цепляем слушателя на все компоненты
        for (JComponent component : componentArr) {
            component.addKeyListener(listener);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInfo = new javax.swing.JPanel();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaWorkInfo = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        buttonSendInfo = new javax.swing.JButton();
        buttonCredProg = new javax.swing.JButton();
        buttonRequest = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Данные о клиенте");

        panelInfo.setBackground(new java.awt.Color(204, 204, 204));
        panelInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("ФИО:");

        jLabel2.setText("Адресс:");

        jLabel3.setText("Паспорт (серия, номер):");

        jLabel4.setText("Идентификационный код:");

        textFieldIdCod.setName("idcod"); // NOI18N

        jLabel5.setText("Контактный телефон:");

        textFieldTel.setText("+3");
        textFieldTel.setName("telephone"); // NOI18N

        jLabel6.setText("Уровень доходов:");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textAreaWorkInfo.setColumns(20);
        textAreaWorkInfo.setLineWrap(true);
        textAreaWorkInfo.setRows(5);
        textAreaWorkInfo.setAutoscrolls(false);
        jScrollPane2.setViewportView(textAreaWorkInfo);

        jLabel7.setText("Информация о работе:");

        buttonSendInfo.setText("Сохранить данные");
        buttonSendInfo.setEnabled(false);
        buttonSendInfo.setPreferredSize(new java.awt.Dimension(180, 23));
        buttonSendInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendInfoActionPerformed(evt);
            }
        });

        buttonCredProg.setText("Кредитные программы");
        buttonCredProg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCredProgActionPerformed(evt);
            }
        });

        buttonRequest.setText("Кредиты");
        buttonRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(buttonSendInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCredProg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonRequest, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldFio, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldAdr, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldLevel, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                            .addComponent(textFieldTel, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                            .addComponent(textFieldPas, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                            .addComponent(textFieldIdCod, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textFieldFio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldAdr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textFieldPas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textFieldIdCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textFieldTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(textFieldLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSendInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCredProg)
                    .addComponent(buttonRequest))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Отображение кредитных программ
    private void buttonCredProgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCredProgActionPerformed
        new CreditProgramForm().setDefaultComponentsState();
    }//GEN-LAST:event_buttonCredProgActionPerformed

    //Сохранение данных о клиенте
    private void buttonSendInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendInfoActionPerformed
        //Создаем нового клиента по заполненным полям
        Client client = new Client();
        try {
            client.setFio(textFieldFio.getText());
            client.setAddress(textFieldAdr.getText());
            client.setPassport(textFieldPas.getText());
            client.setIdCod(textFieldIdCod.getText());
            client.setTel(textFieldTel.getText());
            client.setLevel(Long.parseLong(textFieldLevel.getText()));
            client.setWorkInfo(textAreaWorkInfo.getText());
            client.setUsersId(curUserId);
        //Временное значение для поручителя
        //client.setSuretyId(1);

        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog(this, "Неверный формат данных!");
            return;
        }
        //Обновляем или добавляем нового клиента
        if (clientDAO.update(client) != true) {
            clientDAO.add(client);
        }
        JOptionPane.showMessageDialog(this, "Данные успешно сохранены");
    }//GEN-LAST:event_buttonSendInfoActionPerformed

    //Просмотр и оформление кредитов
    private void buttonRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRequestActionPerformed
        //Получаем текущего клиента по Id пользователя
        Client client = clientDAO.getByUsersId(curUserId);
        //Если клиент заполнил информацию о себе
        if (client != null) {
            //Получаем айди клиента
            int clientsId = client.getId();
            //Отображаем форму кредитов для текущего клиента
            new CreditForm(clientsId);
        }
        else {
            //Отображаем форму по умолчанию
            new CreditForm();
        }
    }//GEN-LAST:event_buttonRequestActionPerformed

    //Класс проверки полей формы (должны быть все заполнены)
    class TextFieldVerifier extends InputVerifier {
        //Шаблоны для ограничений на идентификационный код и телефон

        private Pattern patternIdCod = Pattern.compile("[0-9]{10}");
        private Pattern patternTel = Pattern.compile("\\+30[0-9]{2,4}[0-9]{5,7}");

        @Override
        public boolean verify(JComponent input) {

            Matcher matcherIdCod = patternIdCod.matcher(textFieldIdCod.getText());
            Matcher matcherTel = patternTel.matcher(textFieldTel.getText());

            if (input instanceof JTextField) {
                JTextField tf = (JTextField) input;
                String name = tf.getName();
                //Установка дополнительных ограничений на иден. код
                if ("idcod".equals(name)) {
                    return (tf.getText().length() != 0) && matcherIdCod.matches();
                } //Установка ограничений на телефон
                else if ("telephone".equals(name)) {
                    return (tf.getText().length() != 0) && matcherTel.matches();
                }
                return (tf.getText().length() != 0);
            } else {
                JTextArea ta = (JTextArea) input;
                return (ta.getText().length() != 0);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCredProg;
    private javax.swing.JButton buttonRequest;
    private javax.swing.JButton buttonSendInfo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JTextArea textAreaWorkInfo;
    private javax.swing.JTextField textFieldAdr;
    private javax.swing.JTextField textFieldFio;
    private javax.swing.JTextField textFieldIdCod;
    private javax.swing.JTextField textFieldLevel;
    private javax.swing.JTextField textFieldPas;
    private javax.swing.JTextField textFieldTel;
    // End of variables declaration//GEN-END:variables
}

