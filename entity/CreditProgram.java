
package com.bionic.gorbachev.banksystem.entity;

import java.io.Serializable;

/**
 *
 * @author Aleksey Gorbachev
 */
//Класс кредитной программы
public class CreditProgram implements Serializable {
    //Id программы
    private int id;
    //Название программы
    private String name;
    //Минимальная сумма
    private long minAmount;
    //Максимальная сумма
    private long maxAmount;
    //Продолжительность программы
    private int duration;
    //Начальный взнос
    private double startPay;
    //Процент
    private double percent;
    //Дополнительное описание
    private String description;
    //Статус кредитной программы(Действительна, недействительна)
    private int creditProgStatus;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public long getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(long minAmount) {
        this.minAmount = minAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getStartPay() {
        return startPay;
    }

    public void setStartPay(double startPay) {
        this.startPay = startPay;
    }

    public int getCreditProgStatus() {
        return creditProgStatus;
    }

    public void setCreditProgStatus(int creditProgStatus) {
        this.creditProgStatus = creditProgStatus;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
    
}
