/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie;
import Entities.Events;
import Services.CategorieService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asmab
 */
public class AddCatController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Label nomtest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void event(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cat(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("afficherCat.fxml"));
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherCatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void add(ActionEvent event) {
        
        CategorieService ps = new CategorieService();
         Categorie p = new Categorie();
        p.setNom(nom.getText());
       //   boolean nom1=DataVAlidation.textFiedIsNull(nom, nomtest, "Required Field");
  
            try {
                ps.ajouterr(p);
                try {
            Parent root = FXMLLoader.load(getClass().getResource("afficherCat.fxml"));
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherCatController.class.getName()).log(Level.SEVERE, null, ex);
        }
            } catch (SQLException ex) {
                Logger.getLogger(AddCatController.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
    }
    
