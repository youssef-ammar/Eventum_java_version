/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.Events;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asmab
 */
public class CategorieService implements IService<Categorie>{
 Connection con;
    Statement stm;
    public CategorieService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void ajouter(Categorie t) throws SQLException {
         
        String req = "INSERT INTO `categorie` (`nom`) VALUES (?)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, t.getNom());
         
        pstm.executeUpdate();
           
    }
    

    @Override
    public void ajouterr(Categorie t) throws SQLException {
       String req = "INSERT INTO `categorie` (`nom`) VALUES (?)";
        
     
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, t.getNom());
     
        pstm.executeUpdate();
    }

    @Override
    public List<Categorie> afficher() throws SQLException {
          String req = "Select * from `categorie`";
          if(con==null){
               System.out.println("nullllllllll");
          }
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Categorie> Categories = new ArrayList<>();
        while(rst.next()){
            
            Categorie p = new Categorie(rst.getInt("id"),rst.getString("nom"));
            Categories.add(p);
            
        
        }
        return Categories;
    }
    public void delete(int id) throws SQLException{
          String sql = "DELETE FROM categorie WHERE id=?";
 
            PreparedStatement statement = con.prepareStatement(sql);
          statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(" deleted successfully!");
}
      }

  
    
}
