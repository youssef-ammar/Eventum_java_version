package GUI;

import Entities.Commande;
import Services.CommandeService;
import Services.ProduitService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

public class CommandeController implements Initializable {

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
    private TextField recherchetf;
    private Label currentTimeTF;
    @FXML
    private TableView<Commande> tableviewcommande;
    @FXML
    private TableColumn<Commande, String> produit_col;
    @FXML
    private TableColumn<?, ?> tel_col;
    @FXML
    private TableColumn<?, ?> adresse_col;
    @FXML
    private TableColumn<?, ?> nbproduit_col;
    @FXML
    private TableColumn<?, ?> date_col;
    @FXML
    private TableColumn<?, ?> total_col;
    @FXML
    private TableColumn<Commande, String> action_col;
    ObservableList<Commande> data = FXCollections.observableArrayList();
    CommandeService cs = new CommandeService();
    ProduitService ps = new ProduitService();
    Commande commande = null;
    ObservableList<Commande> CommandeList = FXCollections.observableArrayList();
    @FXML
    private ImageView refreshfid;
    @FXML
    private Button btnPanier;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableviewcommande.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // to load data 
        loadData();

    }

    @FXML
    private void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnProduit) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Produit.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btnCustomers) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Commande.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btnPanier) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Panier.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
        }
    }

    private void loadData() {
        data.clear();
        data = FXCollections.observableArrayList(cs.affichageCommandes());
        produit_col.setCellValueFactory(cellData -> new SimpleStringProperty(ps.getProduit(cellData.getValue().getProduit())));
        tel_col.setCellValueFactory(new PropertyValueFactory<>("tel"));

        adresse_col.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        nbproduit_col.setCellValueFactory(new PropertyValueFactory<>("nbprod"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        total_col.setCellValueFactory(new PropertyValueFactory<>("total"));

        //add cell of button edit 
        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFoctory = (TableColumn<Commande, String> param) -> {
            // make cell containing buttons
            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {
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
                                commande = tableviewcommande.getSelectionModel().getSelectedItem();
                                cs.supprimerCommande((int) commande.getId());

                                refreshlist();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            commande = tableviewcommande.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/GUI/addCommande.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                ex.getMessage();
                            }

                            AddCommandeController addCommandeController = loader.getController();
                            addCommandeController.setUpdate(true);
                            addCommandeController.setTextField(commande.getId(), commande.getAdresse(),
                                    commande.getNbprod(), commande.getTotal(),commande.getDate());
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
        tableviewcommande.setItems(data);

    }
// refresh fct

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(cs.affichageCommandes());
        produit_col.setCellValueFactory(cellData -> new SimpleStringProperty(ps.getProduit(cellData.getValue().getProduit())));

        produit_col.setCellValueFactory(new PropertyValueFactory<>("produit"));
        tel_col.setCellValueFactory(new PropertyValueFactory<>("tel"));

        adresse_col.setCellValueFactory(new PropertyValueFactory<>("adresse"));
//String age =calculateAge(new PropertyValueFactory<>("birthday")) ;
        nbproduit_col.setCellValueFactory(new PropertyValueFactory<>("nbprod"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        total_col.setCellValueFactory(new PropertyValueFactory<>("total"));

        tableviewcommande.setItems(data);
    }
// add btn

    @FXML
    private void addCommande(MouseEvent event) {
        commande = tableviewcommande.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/addCommande.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            ex.getMessage();
        }

        AddCommandeController addCommandeController = loader.getController();
        addCommandeController.setUpdate(false);

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
// refresh btn 

    @FXML
    private void refreshfct(MouseEvent event) {
        loadData();

    }

}
