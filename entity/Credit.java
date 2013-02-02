
package com.bionic.gorbachev.banksystem.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Aleksey Gorbachev
 */

//Класс заказа кредита
public class Credit implements Serializable {
    //Id
    private int id;
    //Date
    private Date date;
    //Сумма кредита
    private double sum;
    //Статус кредита
    private int status;
    //Id клиента
    private int clientId;
    //Id кредитной программы
    private int creditProgramId;

    //Setters/Getters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCreditProgramId() {
        return creditProgramId;
    }

    public void setCreditProgramId(int creditProgramId) {
        this.creditProgramId = creditProgramId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}
