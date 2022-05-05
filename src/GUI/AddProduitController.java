/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Entities.Produit;
import Services.ProduitService;
import Services.ProduitService;
import Tools.MyConnexion;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AddProduitController implements Initializable {

    @FXML
    private TextField nomtf;
    @FXML
    private TextField desctf;
    @FXML
    private TextField prixtf;
    @FXML
    private TextField stocktf;
    final ObservableList<String> options = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();

    
    private boolean update;
    ProduitService cs = new ProduitService();
    String query = null;
    private TextField totaltf;
    int Produitid;
    @FXML
    private Button savebtn;
    ProduitService ps = new ProduitService();
    @FXML
    private ImageView imageproduit;
     File selectedFile;
    private String path;
    @FXML
    private Text img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

        // TODO
    }

  

    private void clean() {
        nomtf.setText(null);
        desctf.setText(null);
        prixtf.setText(null);
        stocktf.setText(null);

    }

    private void getQuery(String nom, String desc, int prix, int stock,String image) {

        if (update == false) {

            Produit c = new Produit( 1,nom, desc, prix, stock,image);
            cs.ajouter(c);
        } else {
            Produit c = new Produit(Produitid,  nom, desc, prix, stock,image);
            cs.modifier(Produitid,c);
        }

    }

    void setTextField(int id, String nom, String desc, int prix, int stock) {

        Produitid = id;
        nomtf.setText(nom);
         desctf.setText(desc);
        prixtf.setText(Integer.toString(prix));
        stocktf.setText(Integer.toString(stock));
        // prodtf.setValue(email);
        img.setText(path);
    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void savetf(MouseEvent event) {
        StringBuilder errors = new StringBuilder();

        try {
            Integer.parseInt(prixtf.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }
        try {
            Integer.parseInt(stocktf.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }

       
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String nom = nomtf.getText();
            String desc = desctf.getText();
             String image = img.getText();
            //  System.out.println(date);
            int prix = Integer.parseInt(prixtf.getText());
            int stock = Integer.parseInt(stocktf.getText());
            int prod = 1;
            getQuery(nom, desc, prix, stock,image);
            clean();
            // to close the window
            Stage stage = (Stage) savebtn.getScene().getWindow();
            stage.close();
        }

    }
//
     @FXML
    private void uploadimage(MouseEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir une image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();
            imageproduit.setImage(new Image(selectedFile.toURI().toURL().toString()));
            imageproduit.setFitHeight(250);
            imageproduit.setFitWidth(250);
            img.setText(path);

        }
    }
}
