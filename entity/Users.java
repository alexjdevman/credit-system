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

//Класс пользователя системы
public class Users implements Serializable {
    //Id
    private int id;
    //Логин пользователя
    private String userName;
    //Пароль пользователя
    private String userPsw;
    //Статус пользователя
    private int userState;
    //Тип пользователя
    private int userTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }
}
