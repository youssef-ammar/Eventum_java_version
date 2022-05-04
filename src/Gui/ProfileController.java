/** *****************************************************************************
 * Controller class and logic implementation for movies.fxml
 ***************************************************************************** */
package GUI;

import GUI.*;
import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import eventum.desktop.eventum;
import static eventum.desktop.eventum.UserconnectedC;
import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.imageio.ImageIO;

public class ProfileController implements Initializable {

    @FXML
    private JFXButton closebtn;
    @FXML
    private JFXButton minimisebtn;

    @FXML
    private AnchorPane mainmoviespane;

    UserService us = new UserService();

    @FXML
    private TextField nom_i;
    @FXML
    private Label e_nom;
    @FXML
    private TextField email_i;
    @FXML
    private Label e_mail;
    @FXML
    private TextField telephone_i;
    @FXML
    private Label e_telephone;
    @FXML
    private TextField prenom_i;
    @FXML
    private Label e_prenom;
    private TextField tf_image;
    @FXML
    private Label e_prenom11;
    @FXML
    private DatePicker birthday;
    @FXML
    private Label e_prenom1;
    @FXML
    private JFXButton modifier;
    @FXML
    private Label Nomc;
    StringBuilder errors = new StringBuilder();
    @FXML
    private Pane webcamPane;
    private ImageView imgPrevWeb;
    @FXML
    private Pane paneNoir;
    @FXML
    private TextField addresse_i;
    @FXML
    private VBox pnItems;
    @FXML
    private Label conn;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnProduit;
    @FXML
    private Button btnprofile;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button logout;
    @FXML
    private Button btnstats;

    /**
     * Initialise method required for implementing initializable and, sets up
     * and applies all effects and animations to nodes in logout.fxml
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn.setText(eventum.UserconnectedC.getEmail());

        setUser();

    }

    @FXML
    private void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnProduit) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("User.fxml"));
            mainmoviespane.getChildren().setAll(panee);
            //   btnCustomers.setStyle("-fx-background-color : #02030A");
        }
        if (actionEvent.getSource() == btnCustomers) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("CarteFidelite.fxml"));
            mainmoviespane.getChildren().setAll(panee);
            //    btnReclamation.setStyle("-fx-background-color :#1620A1");
        }
        if (actionEvent.getSource() == btnprofile) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            mainmoviespane.getChildren().setAll(panee);
            //    btnReclamation.setStyle("-fx-background-color :#1620A1");
        }
        if (actionEvent.getSource() == btnstats) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
            mainmoviespane.getChildren().setAll(panee);
            //    btnReclamation.setStyle("-fx-background-color :#1620A1");
        }
    }

    public void setUser() {
        nom_i.setText(UserconnectedC.getNom());
        prenom_i.setText(UserconnectedC.getPrenom());
        telephone_i.setText(String.valueOf(UserconnectedC.getTel()));
        email_i.setText(UserconnectedC.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = UserconnectedC.getBirthday();
        LocalDate localDate = LocalDate.parse(date, formatter);
        birthday.setValue(localDate);
        addresse_i.setText(UserconnectedC.getAdresse());

        Nomc.setText(prenom_i.getText() + " " + nom_i.getText());
        email_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                    + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_mail.setText("");

            } else {
                e_mail.setText("mail invalide ");
                errors.append("- votre email est invalid\n");//string s --- s+="erreur"

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
                e_telephone.setText("le numero du telephone est invalid");
                errors.append("- votre telephone est invalid\n");//string s --- s+="erreur"

            }
        });
        nom_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_nom.setText("");
            } else {
                e_nom.setText("votre nom est invalid");
                errors.append("- votre nom est invalid\n");//string s --- s+="erreur"

            }
        });
        prenom_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_prenom.setText("");

            } else {
                e_prenom.setText("votre prenom est invalid");
                errors.append("- votre prenom est invalid\n");//string s --- s+="erreur"

            }
        });
        birthday.valueProperty().addListener((observable, oldValue, newValue) -> {

            LocalDate dateBefore18years = LocalDate.now(ZoneId.of("Europe/Paris")).minusDays(6570);

            boolean cond = (newValue.isBefore(dateBefore18years));
            System.out.println(cond);
            if (cond) {
                e_prenom1.setText("");

            } else {
                e_prenom1.setText("*vous devez avoir au moins 18 ans");
                errors.append("- Vous devez avoir au moins 18 ans \n");//string s --- s+="erreur"
            }
        });
    }

    @FXML
    private void modifierUtilisateur(ActionEvent event) {

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            UserService us = new UserService();
            User u = new User();
            u.setNom(nom_i.getText());
            u.setPrenom(prenom_i.getText());
            u.setEmail(email_i.getText());
            u.setTel(telephone_i.getText());
            u.setBirthday(java.sql.Date.valueOf(birthday.getValue()).toString());
            u.setAdresse(addresse_i.getText());
            u.setId(UserconnectedC.getId());
            us.modifierprofile(UserconnectedC.getId(), u);
            System.out.println(UserconnectedC.getId());
            System.out.println("updated ! ");
        }
    }

    @FXML
    private void minimiseWindow(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void send(MouseEvent event) {
    }

    @FXML
    private void logout(MouseEvent event) {
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
            UserconnectedC = null;
        } catch (IOException ex) {
            System.err.println("Erreur");
        }
    }

}
