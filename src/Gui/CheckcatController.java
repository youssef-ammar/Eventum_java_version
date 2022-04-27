/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie;
import Entities.Events;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import main.Main;
import main.MyListener;

/**
 * FXML Controller class
 *
 * @author asmab
 */
public class CheckcatController implements Initializable {
      private Categorie fruit;
    private MyListener myListener;
    @FXML
    private CheckBox check;
 public void setData(Categorie fruit, MyListener myListener) {
        this.fruit = fruit;
        this.myListener = myListener;
        check.setText(fruit.getNom());
       
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
    private void checked(ActionEvent event) {
     //   i=fruit.getId();
    }
    
    private int idCat(){
        if (check.isSelected()){
            return fruit.getId();
        }
        return 0;
    }
    
    
}
