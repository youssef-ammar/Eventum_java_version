/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Entities.Produit;
import Services.CommandeService;
import Services.ProduitService;
import Tools.MyConnexion;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Tools.Smsapi;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AddCommandeController implements Initializable {

    @FXML
    private TextField adressetf;
    @FXML
    private DatePicker datetf;
    @FXML
    private TextField nbrtf;
    final ObservableList<String> options = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> prodtf;
    private boolean update;
    CommandeService cs = new CommandeService();
    String query = null;
    private TextField totaltf;
    int commandeid;
    @FXML
    private Button savebtn;
    ProduitService ps = new ProduitService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillcombobox();
        prodtf.setItems(options);

        // TODO
    }

    public void fillcombobox() {
        try {
            String requete = "SELECT * FROM produit";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                options.add(res.getString(2));

            }
        } catch (SQLException ex) {
            System.out.println("aaaa");
            System.out.println(ex.getMessage());
        }
    }

    private void clean() {
        adressetf.setText(null);
        datetf.setValue(null);
        nbrtf.setText(null);
        prodtf.setValue(null);

    }

    private void getQuery(String adress, int nbr, int prod, int total, String date) {

        if (update == false) {

            Commande c = new Commande(prod, "1", 96227122, adress, nbr, total, date);
            cs.ajouterCommande(c);
            Smsapi.sendSMS("Commande effectuée");
        } else {
            Commande c = new Commande(commandeid, prod, "1", 96227122, adress, nbr, total, date);
            cs.modifierCommande(c);
        }

    }

    void setTextField(int id, String name, int adress, int total, String date) {

        commandeid = id;
        adressetf.setText(name);
        //  datetf.setTex(date);
        nbrtf.setText(Integer.toString(adress));
        totaltf.setText(Integer.toString(total));
        // prodtf.setValue(email);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void savetf(MouseEvent event) {
        StringBuilder errors = new StringBuilder();

        try {
            Integer.parseInt(nbrtf.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }

        if (adressetf.getText().trim().isEmpty()) {
            errors.append("- Please enter an address\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String adress = adressetf.getText();
            String date = String.valueOf(datetf.getValue());
            //  System.out.println(date);
            int nbr = Integer.parseInt(nbrtf.getText());
            int prodid = ps.getProduitid(prodtf.getValue());
            int total = nbr*(ps.getProduitPrix(prodid));
            System.out.println(prodtf.getValue());
            System.out.println(prodid);
            int prod = 1;
            getQuery(adress, nbr, prodid, total, date);
            clean();
            // to close the window
            Stage stage = (Stage) savebtn.getScene().getWindow();
            stage.close();
        }

    }
}
