/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Carte;
import Entities.User;
import Services.CarteService;
import Services.UserService;
import Tools.MyConnexion;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
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
public class AddCarteController implements Initializable {

    final ObservableList<String> options = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();

    private boolean update;
    UserService us = new UserService();
    CarteService cs = new CarteService();
    String query = null;
    int Carteid = 0;
    @FXML
    private TextField numtf;
    @FXML
    private ComboBox<String> usertf;
    @FXML
    private DatePicker expirefid;
    @FXML
    private Button savebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillcombobox();
        usertf.setItems(options);
        // TODO
    }

    public void fillcombobox() {
        try {
            String requete = "SELECT * FROM utilisateur";
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
        numtf.setText(null);
        expirefid.setValue(null);
        usertf.setValue(null);

    }

    private void getQuery(int num, String datee, String user) {

        if (update == false) {
            Date date = new Date(System.currentTimeMillis());

            User u = us.findBylogin(user);
            Carte c = new Carte(num, date, datee, u.getId());
            cs.ajouter(c);
        } else {
            User u = us.findBylogin(user);

            Carte c = new Carte(Carteid, num, datee, u.getId());
            cs.modifier(Carteid, c);
        }

    }

    void setTextField(int id, int num, String datee, int user) {

        Carteid = id;
        String s = Integer.toString(num);

        numtf.setText(s);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void savetf(MouseEvent event) {
        StringBuilder errors = new StringBuilder();

        try {
            Integer.parseInt(numtf.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }
        if (usertf.getValue() == null) {
            errors.append("- Please enter a USER\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            int num = Integer.valueOf(numtf.getText());

            String datee = String.valueOf(expirefid.getValue());
            String gender = usertf.getValue();
            getQuery(num, datee, gender);
            clean();
            // to close the window
            Stage stage = (Stage) savebtn.getScene().getWindow();
            stage.close();
        }
    }

}
