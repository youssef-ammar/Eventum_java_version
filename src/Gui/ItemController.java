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
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import main.Main;
import main.MyListener;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author asmab
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    
    private InputStream input;
    String path;
    
        CategorieService ps = new CategorieService();
  private Events fruit;
    private MyListener myListener;
 Events event= new Events();
    @FXML
    private Button interested;
    @FXML
    private Button a;
    @FXML
    private Button b;
    @FXML
    private Button c;
    public void setData(Events fruit, MyListener myListener) {
       // Image imgx = null ;
        this.fruit = fruit;
        this.myListener = myListener;
        nameLabel.setText(fruit.getNom());
        //animatioooooooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn
        
        new animatefx.animation.Bounce(nameLabel).play();
          new animatefx.animation.RollIn(a).play();
           new animatefx.animation.RollIn(b).play();
            new animatefx.animation.RollIn(c).play();
        // new animatefx.animation.Pulse(nameLabel);
         
        System.out.print(fruit.getCat());
        path=  fruit.getImage();
        System.out.print(path);
//InputStream is = path.getBinaryStream();
// imgx = new Image(path);
//img.setImage(imgx);
        try {
            List<Categorie> cats = ps.afficher();
            for(int i=0;i<cats.size();i++){
                if(cats.get(i).getId()==fruit.getCat()){
                    priceLable.setText(cats.get(i).getNom());
                          new animatefx.animation.FadeIn(priceLable).play();
                }
                
            }
            // priceLable.setText(fruit.getCat().getId().toString());
            // Image image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
            // img.setImage(image);
        } catch (SQLException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    

    @FXML
    private void click(MouseEvent event) {
        
    }
 

 
    

   
    @FXML
    private void delete1(ActionEvent event) {
         EventService ps = new EventService();
        try {
            
           
            ps.delete(fruit.getId()); 
            
         try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            nameLabel.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (SQLException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
    @FXML
    private void detail(ActionEvent event) {
        
         try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showEvent.fxml"));
            Parent root = loader.load();
            AjoutFXMLController controller = loader.getController();
         
        
          
          img.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
*/

    @FXML
    private void show(ActionEvent event ) {
       try {
           /*
           LocalDate todaysDate = LocalDate.now();
        System.out.println(todaysDate);*/
          FXMLLoader loader = new FXMLLoader(getClass().getResource("showevent.fxml"));
            Parent root = loader.load();
            ShoweventController controller = loader.getController();
            Label labnom=new Label(fruit.getNom());
     
        //  controller.setNom(labnom); 
         
     
                    controller.setData(fruit,myListener);
           System.out.print(labnom.getText());
          nameLabel.getScene().setRoot(root); 
        
     
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
        try {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("editeEvent.fxml"));
            Parent root = loader.load();
           EditeEventController controller = loader.getController();
               priceLable.getScene().setRoot(root); 
               
                    controller.setData(fruit,myListener);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
//notificationnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn
    @FXML
    private void btn(ActionEvent event) {
        
        Image img =new Image("etoile.jpg");
        ImageView k = new ImageView(img);
        k.setFitHeight(50);
        k.setFitWidth(50);
       Notifications notifBuilder=Notifications.create()
               
               
                .title("someone is interested in your event ")
                .text("saved")
               

               .graphic(k)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
                notifBuilder.show();
        
        
    }
}

     
