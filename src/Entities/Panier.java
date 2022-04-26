/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author MSI
 */
public class Panier {
    int id ; 
int produit_id ; 
int user_id ; 
int qte ; 
double total ; 

    public Panier(int id, int produit_id, int user_id, int qte, double total) {
        this.id = id;
        this.produit_id = produit_id;
        this.user_id = user_id;
        this.qte = qte;
        this.total = total;
    }

    public Panier(int produit_id, int user_id, int qte, double total) {
        this.produit_id = produit_id;
        this.user_id = user_id;
        this.qte = qte;
        this.total = total;
    }

    public Panier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getproduit_id() {
        return produit_id;
    }

    public void setproduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", produit_id=" + produit_id + ", total_panier=" + total + ", qte=" + qte + ", user_id=" + user_id + '}'+"\n";
    }


}
