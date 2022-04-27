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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import main.MyListener;

/**
 * FXML Controller class
 *
 * @author asmab
 */
public class AfficherCatController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
 private List<Categorie> per = new ArrayList<>();
   private MyListener myListener;
  private List<Categorie> getData() throws SQLException {
      CategorieService ps = new CategorieService();
            List<Categorie> cat = ps.afficher(); 
      
  
        
        return cat;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            per.addAll(getData());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCatController.class.getName()).log(Level.SEVERE, null, ex);
        }
         int column = 0;
        int row = 1;
for(int i = 0; i < per.size(); i++ ){
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("itemCat.fxml"));
   AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(AfficherCatController.class.getName()).log(Level.SEVERE, null, ex);
            }
             ItemCatController itemController = fxmlLoader.getController();
     
                     itemController.setData(per.get(i),myListener);

      if (column == 3) {
                    column = 0;
                    row++;
                }
      grid.add(anchorPane, column++, row);
       grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
              GridPane.setMargin(anchorPane, new Insets(10)); 
    }    
    }

    @FXML
    private void event(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherFXML.fxml"));
            Parent root = loader.load();
            AfficherFXMLController controller = loader.getController();
         
         
          
          grid.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void cat(ActionEvent event) {
        //ajouter cat
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addCat.fxml"));
            Parent root = loader.load();
           // AfficherFXMLController controller = loader.getController();
         
         
          
          grid.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
