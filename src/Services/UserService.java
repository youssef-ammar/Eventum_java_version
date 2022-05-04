/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import static Services.CryptWithMD5.cryptWithMD5;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class UserService  {

    Connection cnx;

    public UserService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

       public void ajouter(User t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `utilisateur`( `login`, `email`, `mdp`, `nom`, `prenom`, `datenaissance`, `genre`, `tel`, `addresse`,`role`) "
                    + "VALUES ('" + t.getLogin()+ "','" + t.getEmail()+ "','" + cryptWithMD5(t.getPassword())+ "','" + t.getNom()+ "','" + t.getPrenom()+ "','" + t.getBirthday()+ "','" + t.getGenre()+ "','" + t.getTel()+ "','" + t.getAdresse()+ "','" + t.getRole()+ "')";
            st.executeUpdate(query);
            System.out.println("user ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void modifier(long id_amodifier, User t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `utilisateur` SET `login`=?,`email`=?, `mdp`=?,`nom`=?,`prenom`=?,"
                    + "`datenaissance`=? ,`genre`=? ,`tel`=?,`addresse`=?,`role`=? WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getLogin());
            st.setString(2, t.getEmail());
            st.setString(3, cryptWithMD5(t.getPassword()));
            st.setString(4, t.getNom());
            st.setString(5, t.getPrenom());  
            st.setString(6, t.getBirthday());
            st.setString(7, t.getGenre());
            st.setString(8, t.getTel());
            st.setString(9, t.getAdresse());
            st.setString(10, t.getRole());
            st.setLong(11, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("user modifier avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void modifierprofile(long id_amodifier, User t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `utilisateur` SET `email`=?, `nom`=?,`prenom`=?,"
                    + "`datenaissance`=? ,`addresse`=? WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getEmail());
            st.setString(2, t.getNom());
            st.setString(3, t.getPrenom());  
            st.setString(4, t.getBirthday());
            st.setString(5, t.getAdresse());
            st.setLong(6, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("user modifier avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from utilisateur where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 
 


    public List<User> afficher() {
        List<User> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from utilisateur";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User u = new User();
                u.setTel(rs.getString("tel"));
                u.setBirthday(rs.getString("datenaissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getInt("id"));
                u.setAdresse(rs.getString("addresse"));
                u.setNom(rs.getString("nom"));
                u.setPassword(cryptWithMD5(rs.getString("mdp")));
                u.setPrenom(rs.getString("prenom"));
                u.setRole(rs.getString("role"));
                u.setLogin(rs.getString("login"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }


    public User findByid(int id) {
        User u = new User();
        
        try {
            Statement st = cnx.createStatement();
            String query = "select * from utilisateur where id='" + id + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
        u.setTel(rs.getString("phone"));
                u.setBirthday(rs.getString("datenaissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getInt("id"));
                u.setAdresse(rs.getString("addresse"));
                u.setNom(rs.getString("nom"));
                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
                u.setRole(rs.getString("role"));
                u.setLogin(rs.getString("login"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
      public String findByiduser(int user_id) {
        ObservableList<User> myList = FXCollections.observableArrayList();
        String titre="" ; 
        try {
            String requete = "SELECT * FROM utilisateur where id="+user_id;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
              titre=res.getString(2);
              
                 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return titre;
 }

    public User findBylogin(String login) {
        User u = new User();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from utilisateur where login='" + login + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                u.setBirthday(rs.getString("datenaissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getInt("id"));
                u.setAdresse(rs.getString("addresse"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setRole(rs.getString("role"));
                u.setLogin(rs.getString("login"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    
    public User findByUsername(String username) {
        User u = new User();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from utilisateur where email='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
             u.setBirthday(rs.getString("datenaissance"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getInt("id"));
                u.setAdresse(rs.getString("addresse"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setRole(rs.getString("role"));
                u.setLogin(rs.getString("login"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    
        public boolean emailExist(String email) {

        try {
            Statement st = cnx.createStatement();
            String query = "select * from utilisateur where email='" + email + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
     
    public int affichernumber(String role) {
        if (role.equals("ROLE_ADMIN")) {
            try {
                Statement st = cnx.createStatement();
                String query = "select count(*) from utilisateur where role='ROLE_ADMIN'";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    int theCount = rs.getInt(1);
                    System.out.println(theCount);
                    return theCount;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (role.equals("ROLE_CLIENT")) {
            try {
                Statement st = cnx.createStatement();
                String query = "select count(*) from utilisateur where role='ROLE_CLIENT'";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    int theCount = rs.getInt(1);
                    System.out.println(theCount);
                    return theCount;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

       
            return 0;
    }

        public boolean checklogin(String username, String password) {
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `utilisateur` WHERE `email`='" + username + "' AND `mdp`='" + password + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
           public  int ModifierMotdePasse(String email,String mdp) {
        int workload = 13;
        int status = 0;
        String sql = "UPDATE utilisateur SET mdp =? WHERE email=?";

        try {
            String pass = cryptWithMD5(mdp);
            PreparedStatement  preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, pass);
            preparedStatement.setString(2, email);
  

            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
           
 
}
