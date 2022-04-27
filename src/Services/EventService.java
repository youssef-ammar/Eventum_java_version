/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Events;
import Utils.MyDB;
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

/**
 *
 * @author macbook
 */
public class EventService implements IService<Events> {

    Connection con;
    Statement stm;

    public EventService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void ajouter(Events t) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`,`date`) VALUES ( '"
                + t.getNom() + "', '" + t.getPrenom() + "', " + t.getDate()+ ") ";
        stm = con.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public void ajouterr(Events t) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`,`date`,`id_cat`,`image`) VALUES (?,?,?,?,?)";
        
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, t.getNom());
        pstm.setString(2, t.getPrenom());
        pstm.setDate(3, t.getDate());
        pstm.setInt(4, t.getCat());
         pstm.setString(5, t.getImage());
        pstm.executeUpdate();
           
    }

    @Override
    public List<Events> afficher() throws SQLException {
        String req = "Select * from `personne`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Events> personnes = new ArrayList<>();
        while(rst.next()){
            
            Events p = new Events(rst.getInt("id"),rst.getString("nom"),rst.getString(3),rst.getDate("date"),rst.getInt("id_cat"),rst.getString("image"));
            personnes.add(p);
            
        
        }
        return personnes;
        
    }
    public void delete(int id) throws SQLException{
          String sql = "DELETE FROM Personne WHERE id=?";
 
            PreparedStatement statement = con.prepareStatement(sql);
          statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(" deleted successfully!");
}
      }
     public void update(Events t) throws SQLException {
          //String req="UPDATE personne SET nom='"+t.getNom()+"',prenom='"+t.getPrenom()+"'WHERE id="+t.getId();
            String req="UPDATE personne SET nom='"+t.getNom()+"',prenom='"+t.getPrenom() +"',date='"+t.getDate()+"',id_cat='"+t.getCat() +"'WHERE id="+t.getId();
       // String req = "UPDATE `personne` SET `nom`=?, `prenom`=? WHERE id=&id";
        PreparedStatement pstm = con.prepareStatement(req);
       
       int rowsDeleted = pstm.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(" update successfully!");
}
            else{
                System.out.println(" failed!");
            }
    }
      
        
    public List<Events> recherche(String searched) {

        List<Events> lista = new ArrayList<>();
        try {

            String req = "select * from personne WHERE nom LIKE '%" + searched + "%' ;";
            PreparedStatement pst = con.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Events p = new Events();
            while (rs.next()) {

                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                
                p.setDate(rs.getDate("date"));
                 p.setCat(rs.getInt("id_cat"));

                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Services.GuideService.recherche()" + ex.getMessage());
        }
        return null;

    } 
     

}
