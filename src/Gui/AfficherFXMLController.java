/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Events;
import static Gui.print.printNode;
import Services.EventService;
import Utils.MyDB;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import main.MyListener;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class AfficherFXMLController implements Initializable {

   Connection con;
    Statement stm;
    ObservableList list;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
      //per = FXCollections.observableArrayList(personnes);
    ObservableList<Events> per = FXCollections.observableArrayList();
// private List<Events> per = new ArrayList<>();
  private MyListener myListener;
    @FXML
    private Button ajouter;
    @FXML
    private TextField search;
    @FXML
    private Button print;
    @FXML
    private AnchorPane AnO;
 //   private ChoiceBox<String> choix;
  
    
    @FXML
    private void ajouter(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutFXML.fxml"));
            Parent root = loader.load();
            AjoutFXMLController controller = loader.getController();
         
         
          
          grid.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
   private List<Events> getData() throws SQLException {
      EventService ps = new EventService();
            List<Events> personnes = ps.afficher(); 
      
  
        
        return personnes;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           
    
            /*// TODO
            try {
            PersonneService ps = new PersonneService();
            List<Personne> personnes = ps.afficher();
            list = FXCollections.observableArrayList(personnes);
            tableview.setItems(list); 

            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }*/
            
            
            
            
            
            
            
          if(search.getText().equals(""))
            per.addAll(getData());
          
          
        } catch (SQLException ex) {
            Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
  int column = 0;
        int row = 1;
for(int i = 0; i < per.size(); i++ ){
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("item.fxml"));
   AnchorPane anchorPane = null;
    try {
        anchorPane = fxmlLoader.load();
    } catch (IOException ex) {
        Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
      ItemController itemController = fxmlLoader.getController();
     
                     itemController.setData(per.get(i),myListener);

      if (column == 3) {
                    column = 0;
                    row++;
                }
                      grid.add(anchorPane, column++, row); //(child,column,row)
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
    }

    @FXML
    private void cat(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherCat.fxml"));
            Parent root = loader.load();
            AfficherCatController controller = loader.getController();
         
         
          
          grid.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
 

    @FXML
    private void changed(InputMethodEvent event) {
    }

    @FXML
    private void of(ActionEvent event) {
        
        
         
         System.out.print(search.getText());
    }

    @FXML
    private void print(ActionEvent event) throws InvocationTargetException {
          try {
            printNode(AnO);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
      @FXML
    private void searchedAvance(KeyEvent event) throws SQLException {
        System.out.println("test");
        grid.getChildren().clear();

        EventService gs = new EventService();
        java.util.List<Events> per1 = new ArrayList<>();

        per1 = gs.recherche(search.getText());

        int column = 0;
        int row = 1;
        
            for (int i = 0; i < per1.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("item.fxml"));
   AnchorPane anchorPane = null;
    try {
        anchorPane = fxmlLoader.load();
    } catch (IOException ex) {
        Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
      ItemController itemController = fxmlLoader.getController();
     
                     itemController.setData(per1.get(i),myListener);

      if (column == 3) {
                    column = 0;
                    row++;
            }
       grid.add(anchorPane, column++, row); //(child,column,row)
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
}


    

