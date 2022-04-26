package GUI;

import Entities.Produit;
import Services.ProduitService;
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

public class ProduitController implements Initializable {

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
    private TableView<Produit> tableviewProduit;
    
    @FXML
    private TableColumn<Produit, String> nom_col;
    @FXML
    private TableColumn<Produit, String> desc_col;
    @FXML
    private TableColumn<Produit, Integer> produit_col;
    @FXML
    private TableColumn<Produit, Integer> prix_col;
    @FXML
    private TableColumn<Produit, Integer> stock_col;
    @FXML
    private TableColumn<Produit, String> action_col;
    ObservableList<Produit> data = FXCollections.observableArrayList();
    ProduitService cs = new ProduitService();
    ProduitService ps = new ProduitService();
    Produit Produit = null;
    ObservableList<Produit> ProduitList = FXCollections.observableArrayList();
    @FXML
    private ImageView refreshfid;
    @FXML
    private Button btnPanier;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableviewProduit.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
        data = FXCollections.observableArrayList(cs.afficher());
        //produit_col.setCellValueFactory(cellData -> new SimpleStringProperty(ps.getProduit(cellData.getValue().getId())));
       produit_col.setCellValueFactory(new PropertyValueFactory<>("id"));
       nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));

        desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
        stock_col.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //add cell of button edit 
        Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFoctory = (TableColumn<Produit, String> param) -> {
            // make cell containing buttons
            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {
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
                                Produit = tableviewProduit.getSelectionModel().getSelectedItem();
                                cs.supprimer((int) Produit.getId());

                                refreshlist();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            Produit = tableviewProduit.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/GUI/addProduit.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                ex.getMessage();
                            }

                            AddProduitController addProduitController = loader.getController();
                            addProduitController.setUpdate(true);
                            addProduitController.setTextField(Produit.getId(), Produit.getNom(),
                                    Produit.getDescription(), Produit.getPrix(),Produit.getStock());
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
        tableviewProduit.setItems(data);

    }
// refresh fct

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(cs.afficher());
       // produit_col.setCellValueFactory(cellData -> new SimpleStringProperty(ps.getProduit(cellData.getValue().getId())));

        produit_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));

        desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
        stock_col.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableviewProduit.setItems(data);
    }
// add btn

    @FXML
    private void addProduit(MouseEvent event) {
        Produit = tableviewProduit.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/addProduit.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            ex.getMessage();
        }

        AddProduitController addProduitController = loader.getController();
        addProduitController.setUpdate(false);

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
