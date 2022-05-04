/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author MSI
 */
public class Carte {
    int id ; 
    int num ; 
    Date date ; 
    String datee ; 
    int user  ; 

    public Carte(int id, int num,  String datee, int user) {
        this.id = id;
        this.num = num;
        this.datee = datee;
        this.user = user;
    }

    public Carte(int num, Date date, String datee, int user) {
        this.num = num;
        this.date = date;
        this.datee = datee;
        this.user = user;
    }

    public Carte() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Carte{" + "id=" + id + ", num=" + num + ", date=" + date + ", datee=" + datee + ", user=" + user + '}';
    }
    
    
}
