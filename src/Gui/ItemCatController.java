/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie;
import Entities.Events;
import Services.CategorieService;
import Services.EventService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import main.MyListener;

/**
 * FXML Controller class
 *
 * @author asmab
 */

public class ItemCatController implements Initializable {
 private Categorie fruit;
    private MyListener myListener;
 Categorie cat= new Categorie();
 
 
 public void setData(Categorie fruit, MyListener myListener) {
        this.fruit = fruit;
        this.myListener = myListener;
         nameLabel.setText(fruit.getNom());
 }
    @FXML
    private Label nameLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void delete(ActionEvent event) {
         CategorieService ps = new CategorieService();
        try {
            
           
            ps.delete(fruit.getId()); 
            
         try {
            Parent root = FXMLLoader.load(getClass().getResource("afficherCat.fxml"));
            nameLabel.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (SQLException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
