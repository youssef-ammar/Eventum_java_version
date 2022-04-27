/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a37;

import Entities.Events;
import Services.CategorieService;
import Services.EventService;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;

/**
 *
 * @author macbook
 */
public class Pidev3a37 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Personne p1 = new Personne("Nouha","BEN SLIMEN",new Date(System.currentTimeMillis()));
      EventService ps = new EventService();
         CategorieService psc = new CategorieService();
        try {
            //ps.ajouter(p1);
            //ps.ajouterr(p2);
            //System.out.println("personne ajoutee");
          //  System.out.println(ps.afficher().toString());
  System.out.println(psc.afficher());
   
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
