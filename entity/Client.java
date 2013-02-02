/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bionic.gorbachev.banksystem.entity;

import java.io.Serializable;

/**
 *
 * @author Aleksey Gorbachev
 */

//Класс клиента
public class Client implements Serializable {
    //Id
    private int id;
    //ФИО
    private String fio;
    //Адресс
    private String address;
    //Паспортные данные
    private String passport;
    //Ид. код
    private String idCod;
    //Телефон
    private String tel;
    //Уровень дохода
    private long level;
    //Информация о месте работы
    private String workInfo;
    //Данные о пользователе системы
    private int usersId;
   //Id поручителя
    private int suretyId;

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCod() {
        return idCod;
    }

    public void setIdCod(String idCod) {
        this.idCod = idCod;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(String workInfo) {
        this.workInfo = workInfo;
    }

    public int getSuretyId() {
        return suretyId;
    }

    public void setSuretyId(int suretyId) {
        this.suretyId = suretyId;
    } 
}
