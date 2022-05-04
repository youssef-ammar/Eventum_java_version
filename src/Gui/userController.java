/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import eventum.desktop.eventum;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class userController implements Initializable {

    @FXML
    private AnchorPane DashboardUtilis;
    @FXML
    private Button btnProduit;
    @FXML
    private Button btnCustomers;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private ImageView refreshfid;
    @FXML
    private TableColumn<?, ?> login_col;
    @FXML
    private TableColumn<?, ?> email_col;
    @FXML
    private TableColumn<?, ?> nom_col;
    @FXML
    private TableColumn<?, ?> addresse_col;
    @FXML
    private TableColumn<User, String> birthday_col;
    @FXML
    private TableColumn<?, ?> role_col;
    @FXML
    private TableColumn<User, String> action_col;
    @FXML
    private TableView<User> tableviewuser;
    User user = null;
    ObservableList<User> data = FXCollections.observableArrayList();
    UserService us = new UserService();
    @FXML
    private VBox pnItems;
    @FXML
    private Button btnOverview;
    @FXML
    private Label conn;
    @FXML
    private Button logout;
    @FXML
    private Button btnprofile;
    @FXML
    private Button btnstats;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableviewuser.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // to load data 
        loadData();
        conn.setText(eventum.UserconnectedC.getEmail());
    }

    private void loadData() {
        data.clear();
        data = FXCollections.observableArrayList(us.afficher());
        login_col.setCellValueFactory(new PropertyValueFactory<>("login"));

        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        addresse_col.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        birthday_col.setCellValueFactory(new PropertyValueFactory<>("birthday"));

        role_col.setCellValueFactory(new PropertyValueFactory<>("role"));

        //add cell of button edit 
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
            // make cell containing buttons
            final TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                user = tableviewuser.getSelectionModel().getSelectedItem();

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation Dialog");
                                alert.setContentText("Etes vous sure de supprimer cette element ?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    us.supprimer((int) user.getId());

                                    loadData();
                                } else {

                                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                                    alert1.setTitle("Information Dialog");
                                    alert1.setHeaderText(null);
                                    alert1.setContentText("Veuillez sélectionner un element à supprimer.");

                                    alert1.showAndWait();
                                }

                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            user = tableviewuser.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/GUI/adduser.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                ex.getMessage();
                            }

                            AddUserController adduserController = loader.getController();
                            adduserController.setUpdate(true);
                            adduserController.setTextField(user.getId(), user.getAdresse(),
                                    user.getBirthday(), user.getGenre(), user.getLogin(), user.getNom(), user.getPrenom(), user.getRole(), user.getEmail());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        action_col.setCellFactory(cellFoctory);
        tableviewuser.setItems(data);

    }

    @FXML
    private void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnProduit) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("User.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
            //   btnCustomers.setStyle("-fx-background-color : #02030A");
        }
        if (actionEvent.getSource() == btnCustomers) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("CarteFidelite.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
            //    btnReclamation.setStyle("-fx-background-color :#1620A1");
        }
        if (actionEvent.getSource() == btnprofile) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
            //    btnReclamation.setStyle("-fx-background-color :#1620A1");
        }
        if (actionEvent.getSource() == btnstats) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
            //    btnReclamation.setStyle("-fx-background-color :#1620A1");
        }
    }

    @FXML
    private void refreshfct(MouseEvent event) {
        loadData();

    }
    // refresh fct

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(us.afficher());
        login_col.setCellValueFactory(new PropertyValueFactory<>("login"));

        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        addresse_col.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        birthday_col.setCellValueFactory(new PropertyValueFactory<>("total"));
        role_col.setCellValueFactory(new PropertyValueFactory<>("total"));
        tableviewuser.setItems(data);
    }

    @FXML
    private void addUser(MouseEvent event) {
        user = tableviewuser.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/Adduser.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            ex.getMessage();
        }

        AddUserController adduserController = loader.getController();
        adduserController.setUpdate(false);

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
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
            eventum.UserconnectedC = null;
        } catch (IOException ex) {
            System.err.println("Erreur");
        }

    }

}
