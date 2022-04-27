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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
 
 
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
 
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
 
import main.MyListener;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class AjoutFXMLController implements Initializable {

    @FXML
    private TextField nom;
  
    @FXML
    private DatePicker date;
    @FXML
    private Label username;
 private Events fruit;
    private MyListener myListener;
    @FXML
    private Label nomvide;
    @FXML
    private Label descvide;
    @FXML
    private Label datecon;
    
   final ObservableList<String> options = FXCollections.observableArrayList();
     private List<Categorie> cat = new ArrayList<>();
    @FXML
    private ComboBox<String> combo;
    @FXML
    private TextArea desc;
    @FXML
    private Button file;
    private ImageView imageView;
    private Image image;
    Stage primaryStage;
    @FXML
    private BorderPane borderpane;
    @FXML
    private ImageView ImageView;
    @FXML
    private Label nomimage;
    String path;
    /**
     * Initializes the controller class.
     */
      private List<Categorie> getData() throws SQLException {
      CategorieService ps = new CategorieService();
            List<Categorie> cats = ps.afficher(); 
      
  
        
        return cats;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fillcombobox();
            combo.setItems(options);
            
            //imageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
               TextArea textArea = new TextArea();
      textArea.setPromptText("path");
      textArea.setPrefSize(300, 50);
      textArea.setEditable(false);
      
      
        FileChooser filechooser = new FileChooser();
      filechooser.getExtensionFilters().addAll(
      
      
      new ExtensionFilter("text files","*txt"),
                 new ExtensionFilter("Image File","*.png","*.jpg","*.gif"),
                    new ExtensionFilter("all files","*.*")
      
      );
           file.setOnAction(e->{
            File filee = filechooser.showOpenDialog(primaryStage);
            if(filee != null ){
                textArea.setText(filee.getAbsolutePath());
                image =new Image(filee.toURI().toString(),100,150,true,true);//path 
           System.out.print(filee.toURI().toString());
                
           if(filee.toURI().toString()!=null)
               nomimage.setText(filee.toURI().toString());
           path=filee.toURI().toString();
          //  imageView =new ImageView(image);
         // imageView.setImage(image);
           // imageView.setFitWidth(100);
            //imageView.setFitHeight(150);
          //  imageView.setPreserveRatio(true);
         //   layout.setCenter(imageView);
           // BorderPane.setAlignment(imageView, Pos.TOP_LEFT);
            borderpane.setAlignment(imageView,Pos.CENTER);
            }
            
            
            
           });
            
           //imageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public void setUsername(String s) {
        username.setText(s);
    }

     
           
    @FXML
    private void Ajout(ActionEvent event) throws IOException {
        /* int i;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("checkcat.fxml"));
        AnchorPane imageOverview = (AnchorPane) loader.load();

         CheckcatController   controller = loader.getController();
        
       */
      
        Events p = new Events();
        p.setNom(nom.getText());
        p.setPrenom(desc.getText());
        if(date.getValue()!=null)
        p.setDate(Date.valueOf(date.getValue()));
    p.setImage(path);
        //list cat
        
        
        
        
        
        System.out.print(combo.getValue());
        if(combo.getValue()==null){
        System.out.print("   nullll ");
    }
        else{
      if ( combo.getValue().equals("art")){
            
        p.setCat(1);
       
      }
      else if( combo.getValue().equals("music")){
          p.setCat(2);
         
      }
      else if(combo.getValue().equals("camping")){
          p.setCat(3);
      }
      else {
          p.setCat(4);
      }
        }
      
        EventService ps = new EventService();
          
   
           LocalDate todaysDate = LocalDate.now();
        
     
          LocalDate  dateof = date.getValue();
           ZoneId defaultZoneId = ZoneId.systemDefault();
           Date date777 = java.sql.Date.valueOf(dateof);
           Date date333 = java.sql.Date.valueOf(todaysDate);
         //  Date date777 =   Date.from(dateof.atStartOfDay(defaultZoneId).toInstant());
          //  Date date333=   Date.from(todaysDate.atStartOfDay(defaultZoneIinvalidd).toInstant());
     
            try {
                ps.ajouterr(p);
            } catch (SQLException ex) {
                Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            date.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
        
    @FXML
    private void Afficher(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            date.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void alerte(InputMethodEvent event) {
    }
    
    
    public void fillcombobox() throws SQLException {
        
         CategorieService ps=new CategorieService();
        cat= ps.afficher();
        for(int i=0;i<cat.size();i++){
  options.addAll(cat.get(i).getNom());
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
