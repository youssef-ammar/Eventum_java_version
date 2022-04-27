/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Events;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.Main;
import main.MyListener;

/**
 * FXML Controller class
 *
 * @author asmab
 */
public class ShoweventController implements Initializable {

    @FXML
     Label nom;
    @FXML
    private Label desc;
    @FXML
    private Label date;
  private Events fruit;
    private MyListener myListener;
    @FXML
    private Button back;
    public void setNom(Label nom) {
         
        this.nom = nom;
         System.out.println(nom);
    }

    public void setDesc(Label desc) {
        this.desc = desc;
    }

    public void setDate(Label date) {
        this.date = date;
    }
 public void setData(Events fruit, MyListener myListener) {
        this.fruit = fruit;
        this.myListener = myListener;
        nom.setText(fruit.getNom());
        desc.setText(fruit.getPrenom());
        date.setText(fruit.getDate().toString());
       // Image image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
       // img.setImage(image);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            date.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void event(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            date.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cat(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("afficherCat.fxml"));
            date.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherCatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    
}
