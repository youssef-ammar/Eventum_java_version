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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.Main;
import main.MyListener;

/**
 * FXML Controller class
 *
 * @author asmab
 */
public class EditeEventController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextArea desc;
    @FXML
    private DatePicker date;
  private Events fruit;
    private MyListener myListener;
    @FXML
    private Label nametest;
    @FXML
    private Label desctest;
    @FXML
    private Label datetest;
    @FXML
    private ComboBox<String> combo;
    
    
    
     final ObservableList<String> options = FXCollections.observableArrayList();
     private List<Categorie> cat = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    
    public void fillcombobox() throws SQLException {
        
         CategorieService ps=new CategorieService();
        cat= ps.afficher();
        for(int i=0;i<cat.size();i++){
  options.addAll(cat.get(i).getNom());
        }
           
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
           
            fillcombobox();
        } catch (SQLException ex) {
            Logger.getLogger(EditeEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
            combo.setItems(options);
        
    } 
     public void setData(Events fruit, MyListener myListener) {
        this.fruit = fruit;
        this.myListener = myListener;
        nom.setText(fruit.getNom());
        desc.setText(  fruit.getPrenom());
       // date.setAccessibleText(fruit.getDate().toString());
       // Image image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
       // img.setImage(image);
    }
      

    @FXML
    private void editez(ActionEvent event) {
         
        
        try {
                if(combo.getValue()==null){
        System.out.print("   nullll ");
        
    }
                else{
      if ( combo.getValue().equals("art")){
            
        fruit.setCat(1);
       
      }
      else if( combo.getValue().equals("music")){
          fruit.setCat(2);
         
      }
      else if(combo.getValue().equals("camping")){
          fruit.setCat(3);
      }
      else {
          fruit.setCat(4);
      }
                }
              EventService ps = new EventService();
          LocalDate todaysDate = LocalDate.now();
           Date date333 = java.sql.Date.valueOf(todaysDate);
           
         
        if(date.getValue()!=null){
          LocalDate datee = date.getValue();
           Date date777 = java.sql.Date.valueOf(datee);
            fruit.setDate(date777);
      //  boolean date7=DataVAlidation.datadate(date777,date333 ,datetest, "invalid dateeee ");
        }
      //fruit.setDate( date);
            // List<Personne> personnes = ps.afficher();
         
       
         if(nom.getText()!=null){
               fruit.setNom(nom.getText());
         }
         else 
         {
              fruit.setNom(null);
         }
         if(desc.getText()!=null){
        fruit.setPrenom(desc.getText());
         }
         else{
              fruit.setPrenom(null);
         }
     
         
            ps.update(fruit);
          
             try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            date.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (SQLException ex) {
            Logger.getLogger(EditeEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
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
