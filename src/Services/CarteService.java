/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Carte;
import static Services.CryptWithMD5.cryptWithMD5;
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

/**
 *
 * @author MSI
 */
public class CarteService {
    
    Connection cnx;

    public CarteService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

       public void ajouter(Carte t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `carte_fidelite`( `utilisateur_id`, `num_fidelite`, `date_ajout`, `date_expiration`) "
                    + "VALUES ('" + t.getUser()+ "','" + t.getNum()+ "','" + t.getDate()+ "','" + t.getDatee()+ "')";
            st.executeUpdate(query);
            System.out.println("Carte ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(CarteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void modifier(int id_amodifier, Carte t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `carte_fidelite` SET `utilisateur_id`=?,`num_fidelite`=?, `date_expiration`=? WHERE id=?");
            System.out.println("2");

            st.setInt(1, t.getUser());
            st.setInt(2, t.getNum());
            st.setString(3, t.getDatee());  
            st.setInt(4, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("Carte modifier avec success");
            } else {
                System.out.println("Carte n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarteService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from carte_fidelite where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("Carte n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarteService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 
 


    public List<Carte> afficher() {
        List<Carte> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from carte_fidelite";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Carte u = new Carte();
                u.setDate(rs.getDate("date_ajout"));
                u.setDatee(rs.getString("date_expiration"));
                u.setNum(rs.getInt("num_fidelite"));
                u.setUser(rs.getInt("utilisateur_id"));
                u.setId(rs.getInt("id"));
             
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
}
