/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author MSI
 */
public class User {
    int id ; 
    String login ; 
    String email ; 
    String password ; 
    String nom ; 
    String prenom ; 
    String birthday ; 
    String genre ; 
    String tel ; 
    String role ; 
    String adresse ; 

    public User(int id, String login, String email, String password, String nom, String prenom, String birthday, String genre, String tel, String role, String adresse) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.birthday = birthday;
        this.genre = genre;
        this.tel = tel;
        this.role = role;
        this.adresse = adresse;
    }

    public User(String login, String email, String password, String nom, String prenom, String birthday, String genre, String tel, String role, String adresse) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.birthday = birthday;
        this.genre = genre;
        this.tel = tel;
        this.role = role;
        this.adresse = adresse;
    }

    public User() {
    }
/**getters & setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", email=" + email + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", birthday=" + birthday + ", genre=" + genre + ", tel=" + tel + ", role=" + role + ", adresse=" + adresse + '}';
    }
    
    
}
