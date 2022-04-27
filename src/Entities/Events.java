/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author macbook
 */
public class Events {

    private int id;
    private String nom, prenom ,image ;
    private Date date;
   
private int cat;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

 

   
  

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public Events() {
    }

    public Events(String nom, String prenom, Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
    }

    public Events(int id ,String nom, String prenom, Date date, int cat,String image) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.cat = cat;
        this.image=image;
    }
   
    public Events(int id, String nom, String prenom, Date date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;

    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", date=" + date + '}';
    }

}
