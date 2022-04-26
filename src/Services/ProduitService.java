/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ProduitService  {

    Connection cnx;

    public ProduitService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouter(Produit t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `Produit`( `nom`, `description`, `prix`, `stock`, `image`) "
                    + "VALUES ('" + t.getNom()+ "','" + t.getDescription()+ "','" + t.getPrix()+ "','" + t.getStock()+ "','" + t.getimage()+ "')";
            st.executeUpdate(query);
            System.out.println("Produit ajouté avec success");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifier(int id_amodifier, Produit t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `Produit` SET `nom`=?,`description`=?, `prix`=?,`stock`=?, `image`=?"
                    + " WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getNom());
            st.setString(2, t.getDescription());
            st.setInt(3, t.getPrix());
            st.setInt(4, t.getStock());
            st.setString(5, t.getimage());
         //   st.setDate(2, new java.sql.Date(t.getDate_naissance().getTime()));
            st.setInt(5, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("Produit modifié avec success");
            } else {
                System.out.println("Produit n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from Produit where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("Produit n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    


   public List<Produit> afficher() {
        List<Produit> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from Produit";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Produit u = new Produit();
                u.setNom(rs.getString("nom"));
                u.setDescription(rs.getString("description"));
                u.setPrix(rs.getInt("prix"));
                u.setId(rs.getInt("id"));
                u.setStock(rs.getInt("stock"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
  public String getNom() {
        ObservableList<Produit> myList = FXCollections.observableArrayList();
        String titre="" ; 
        try {
            String requete = "SELECT nom FROM produit";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Produit p = new Produit();
            p.setId(res.getInt(1));
              titre=res.getString(2);
              
                 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return titre;
 }
  public String getProduit(int prod) {
        ObservableList<Produit> myList = FXCollections.observableArrayList();
        String nom="" ; 
        try {
            String requete = "SELECT * FROM produit where id="+prod;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Produit p = new Produit();
            p.setId(res.getInt(1));
              nom=res.getString(2);
              
                 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nom;
 }
  public int getProduitPrix(int prod) {
        ObservableList<Produit> myList = FXCollections.observableArrayList();
        int prix=0 ; 
        try {
            String requete = "SELECT * FROM produit where id="+prod;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Produit p = new Produit();
            p.setId(res.getInt(1));
              prix=res.getInt(4);
              
                 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prix;
 }
  public int getProduitid(String nom) {
        ObservableList<Produit> myList = FXCollections.observableArrayList();
int id=0; 
try {
            String query = "SELECT * FROM `Produit` WHERE `nom`='" + nom + "'";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Produit p = new Produit();
            p.setId(res.getInt(1));
            id= res.getInt(1) ;
           //   nom=res.getString(2);
              
                 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
 }
}
