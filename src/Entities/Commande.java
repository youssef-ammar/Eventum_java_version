/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author karim
 */
public class Commande {

    int id;
    int produit;
    String user;
    long tel;
    String adresse;
    int nbprod;
    String date;
    int total;

    public Commande(int id, int produit, String user, long tel, String adresse, int nbprod, int total, String date) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.tel = tel;
        this.adresse = adresse;
        this.nbprod = nbprod;
        this.total = total;
        this.date = date;

    }

    public Commande(int produit, String user, long tel, String adresse, int nbprod, int total, String date) {
        this.produit = produit;
        this.user = user;
        this.tel = tel;
        this.adresse = adresse;
        this.nbprod = nbprod;
        this.total = total;
        this.date = date;
    }

    public Commande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNbprod() {
        return nbprod;
    }

    public void setNbprod(int nbprod) {
        this.nbprod = nbprod;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", produit=" + produit + ", user=" + user + ", tel=" + tel + ", adresse=" + adresse + ", nbprod=" + nbprod + ", date=" + date + ", total=" + total + '}';
    }

}
