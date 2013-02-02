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

//Класс типов пользователей
public class UserType implements Serializable {
    //Id
    private int id;
    //Тип пользователя
    private String userName;

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

}
