/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eventum.desktop;

import Entities.Commande;
import Entities.Produit;
import Entities.User;
import Services.CommandeService;
import Services.ProduitService;
import Services.UserService;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author MSI
 */
public class EVENTUMDESKTOP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProduitService ps = new ProduitService();
        CommandeService cs = new CommandeService();
        Date d = Date.valueOf(LocalDate.of(2000, Month.MARCH, 17));
        Date date = new Date(System.currentTimeMillis());
         //  Produit a=new Produit(7,"java","madesccription",100,10);
          // Commande c=new Commande(20,a,"ss",98488528,"salut",10,d,10);
        // cs.ajouterCommande(c);

        System.out.println(ps.afficher()); 
    }

}
