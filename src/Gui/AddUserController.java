/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AddUserController implements Initializable {

    private TextField adressetf;
    private DatePicker datetf;
    private TextField nbrtf;
    final ObservableList<String> options = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();

    private ComboBox<String> prodtf;
    private boolean update;
    UserService us = new UserService();
    String query = null;
    private TextField totaltf;
    int Userid;
    @FXML
    private Button savebtn;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField prenomfid;
    @FXML
    private TextField logintf;
    @FXML
    private ComboBox<String> gendertf;
    @FXML
    private TextField emailfid;
    @FXML
    private TextField passwordfid;
    @FXML
    private DatePicker birthdayfid;
    @FXML
    private TextField adressfid;
    @FXML
    private ComboBox<String> rolefid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gendertf.setItems(ss);
        ss.add("femme");
        ss.add("homme");
        options.add("ROLE_ADMIN");
        options.add("ROLE_CLIENT");
        rolefid.setItems(options);
        // TODO
    }

    private void clean() {

    }

    private void getQuery(String adress, String login, String nom, String prenom, String email, String password, String date, String gender, String role) {

        if (update == false) {
            User u = new User(27, "lolllll", "lol@lol.tn", "pass", "slim", "ayadi", "12/2/2020", "homme", "12345678", "ROLE_ADMIN", "sfax");

            User c = new User(login, email, password, nom, prenom, date, gender, "12345678", role, adress);
            us.ajouter(c);
        } else {
            User c = new User(Userid, login, email, password, nom, prenom, date, gender, "12345678", role, adress);
            us.modifier(Userid, c);
        }

    }

    void setTextField(int id, String adresse, String birthday, String genre, String login, String nom, String prenom, String role, String email) {

        Userid = id;
        nomtf.setText(nom);
        prenomfid.setText(prenom);
        logintf.setText(login);
        emailfid.setText(email);
        adressfid.setText(adresse);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void savetf(MouseEvent event) {
           StringBuilder errors = new StringBuilder();

    

        if (adressfid.getText().trim().isEmpty()) {
            errors.append("- Please enter a login \n");
        }
        if (logintf.getText().trim().isEmpty()) {
            errors.append("- Please enter a login \n");
        }

        if (nomtf.getText().trim().isEmpty()) {
            errors.append("- Please enter a last name\n");
        }

        if (prenomfid.getText().trim().isEmpty()) {
            errors.append("- Please enter a first name\n");
        }

        if (emailfid.getText().trim().isEmpty()) {
            errors.append("- Please enter an email\n");
        }
        if (rolefid.getValue() == null) {
            errors.append("- Please enter a role\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String adress = adressfid.getText();
            String login = logintf.getText();
            String nom = nomtf.getText();
            String prenom = prenomfid.getText();
            String email = emailfid.getText();
            String password = passwordfid.getText();
            String date = String.valueOf(birthdayfid.getValue());
            String gender = gendertf.getValue();
            String role = rolefid.getValue();
            getQuery(adress, login, nom, prenom, email, password, date, gender, role);
            clean();
            // to close the window
            Stage stage = (Stage) savebtn.getScene().getWindow();
            stage.close();
        }
    }

 
}
