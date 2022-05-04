/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.CryptWithMD5;
import Services.UserService;
import Tools.JavaMailUtilUser;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXDatePicker;

import java.io.File;
import java.io.IOException;
import static java.lang.Math.log;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static eventum.desktop.eventum.UserconnectedC;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author user
 */
public class loginController implements Initializable {

    @FXML
    private PasswordField password_lo;
    @FXML
    private TextField email_lo;
    @FXML
    private Button login;
    @FXML
    private TextField nom_i;
    @FXML
    private TextField prenom_i;
    @FXML
    private TextField adresse_i;
    @FXML
    private TextField telephone_i;
    @FXML
    private TextField email_i;
    @FXML
    private PasswordField password_i;
    @FXML
    private Button sign_up_l;
    @FXML
    private Button sign_in_l;
    @FXML
    private Button sign_up_s;
    @FXML
    private Button sign_in_s;
    @FXML
    private Pane pane_signup;
    @FXML
    private Pane pane_signin;
    @FXML
    private Button closeb;
    @FXML
    private Label e_nom;
    @FXML
    private Label e_prenom;
    @FXML
    private Label e_mail;
    @FXML
    private Label e_password;
    @FXML
    private Label e_telephone;
    private String code;
    @FXML
    private Button inscprition;
    @FXML
    private TextField emailm;
    @FXML
    private Pane pane_code;
    @FXML
    private TextField codem;
    @FXML
    private Button motdepasse;
    @FXML
    private Pane pane_password;
    @FXML
    private PasswordField password_nc;
    @FXML
    private PasswordField password_n;
    @FXML
    private Button modifier;
    @FXML
    private Button back_p;
    @FXML
    private Pane pane_email;
    @FXML
    private Button envoyerm;
    @FXML
    private Button back_a;
    @FXML
    private Button envoyer_c;
    @FXML
    private Button back_c;
    @FXML
    private Label pasword_ne;
    @FXML
    private Label pasword_nec;
    private String email;
    @FXML
    private DatePicker dn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //  Payement();
        //  OpenWeather ow = new OpenWeather();

        //    getWeather();
        Font.loadFont(getClass().getResourceAsStream("/css/DroidSerif-Regular.ttf.ttf"), 14);
        email_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                    + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_mail.setText("");

            } else {
                e_mail.setText("*mail invalide ");
            }
        });

        telephone_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[0-9]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                if (newValue.length() == 8) {
                    e_telephone.setText("");
                }

            } else {
                e_telephone.setText("*le numero du telephone est invalid");
            }
        });
        nom_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_nom.setText("");
            } else {
                e_nom.setText("*votre nom est invalid");
            }
        });
        prenom_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_prenom.setText("");

            } else {
                e_prenom.setText("*votre prenom est invalid");
            }
        });
        password_i.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 5) {
                e_password.setText("");
            } else {
                e_password.setText("*Votre mot de passe est invalid");
            }

        });
        email_lo.setText("aziz.khemiraa");
        password_lo.setText("aziz13");
    }

    @FXML
    private void LoginUtilisateur(ActionEvent event) throws IOException {
        UserService us = new UserService();

        if (us.checklogin(email_lo.getText(), CryptWithMD5.cryptWithMD5(password_lo.getText()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("login");
            alert.setContentText("Succes");
            alert.showAndWait();

            User u = us.findByUsername(email_lo.getText());
            UserconnectedC = u;
            u.toString();
            switch (u.getRole()) {
                case "ROLE_ADMIN":

                    System.out.println("bienveunue Passager");
                    try {

                        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        stageclose.close();
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/User.fxml"));
                        Stage stage = new Stage();

                        Scene scene = new Scene(root);

                        stage.setTitle("eventum APP");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        //       Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;

                case "ROLE_CLIENT":

                    System.out.println("bienveunue Passager");
                    try {

                        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        stageclose.close();
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/CarteFidelite.fxml"));
                        Stage stage = new Stage();

                        Scene scene = new Scene(root);

                        stage.setTitle("eventum APP");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        //       Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                default:
                    break;
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login fail");
            alert.setContentText("Username or password invalid");
            alert.showAndWait();
        }

    }

    @FXML
    private void InscriptionUtilisateur(ActionEvent event) {
        UserService us = new UserService();
        User c = new User("", email_i.getText(), password_i.getText(), nom_i.getText(), prenom_i.getText(), dn.getValue().toString(), "homme", telephone_i.getText(), "ROLE_CLIENT", adresse_i.getText());
        User e = us.findByUsername(email_i.getText());
        if (!us.emailExist(email_i.getText())) {
            us.ajouter(c);

            new ZoomIn(pane_signin).play();
            pane_signin.toFront();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inscription");
            alert.setContentText("Fail");
            alert.showAndWait();
        }

    }

    @FXML
    private void To_signin(ActionEvent event) {
        new ZoomIn(pane_signin).play();
        pane_signin.toFront();

    }

    @FXML
    private void To_signup(ActionEvent event) {
        new ZoomIn(pane_signup).play();
        pane_signup.toFront();
    }

    @FXML
    private void Close(ActionEvent event) {
        Stage window = (Stage) closeb.getScene().getWindow();
        window.close();
    }

    @FXML
    private void To_mot_de_passe(ActionEvent event) {
        new ZoomIn(pane_email).play();
        pane_email.toFront();
    }

    @FXML
    private void ModifierPassword(ActionEvent event) {
        if (password_n.getText().length() >= 5) {
            e_password.setText("");
        } else {
            pasword_ne.setText("*Votre mot de passe est invalid");
            return;
        }
        if (password_n.getText().equals(password_nc.getText())) {
            e_password.setText("");
        } else {
            pasword_nec.setText("*Mot de passe de confirmation est invalide");
            return;
        }
        UserService suser = new UserService();
        System.out.println(email);
        if (suser.ModifierMotdePasse(email, password_n.getText()) == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("login");
            alert.setContentText("Succes");
            alert.showAndWait();
            new ZoomIn(pane_signin).play();
            pane_signin.toFront();
        }

    }

    @FXML
    private void BackP(ActionEvent event) {
        new ZoomIn(pane_signin).play();
        pane_signin.toFront();
    }

    public static String getRandomNumberString() {

        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }

    @FXML
    private void EnvoyerMail(ActionEvent event) {
        JavaMailUtilUser mail = new JavaMailUtilUser();

        code = getRandomNumberString();
        String content = "Modifier le mot de passe\n"
                + "une requete du changement de mot passe à eté envoyer \n"
                + "ceci est le code du changement : " + code;
        try {
            mail.sendMail("Changement du mot de passe", content, emailm.getText());
            email = emailm.getText();
        } catch (Exception ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        new ZoomIn(pane_code).play();
        pane_code.toFront();
    }

    @FXML
    private void BackA(ActionEvent event) {
        new ZoomIn(pane_signin).play();
        pane_signin.toFront();
    }

    @FXML
    private void EnvoyerCode(ActionEvent event) {
        if (codem.getText().equals(code)) {
            new ZoomIn(pane_password).play();
            pane_password.toFront();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("login");
            alert.setContentText("Le code est incorrect !");
            alert.showAndWait();
        }
    }

    @FXML
    private void BackC(ActionEvent event) {
        new ZoomIn(pane_signin).play();
        pane_signin.toFront();
    }

}
