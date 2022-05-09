package GUI;


import com.itextpdf.text.DocumentException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class StatistiqueController implements Initializable {

    @FXML
    private VBox pnItems = null;

    private Button btnOrders;


    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;
    private ComboBox<String> combotri;

    ObservableList<String> ss = FXCollections.observableArrayList();

    private Button btnReclamation;
    private TextField recherchetf;

    @FXML
    private AnchorPane DashboardUtilis;
  
    @FXML
    private Button btnCustomers;
    private Label totalcmd;
    @FXML
    private Button btnOverview;
    @FXML
    private PieChart stat;
    @FXML
    private LineChart<String, Double> statcourbe;

    ObservableList<PieChart.Data> piechartdata;
    XYChart.Series<String, Double> linechartdata = new XYChart.Series();

    Connection cnx;
    ResultSet rs;
    @FXML
    private Label conn;
    @FXML
    private Button btnProduit;
    @FXML
    private Button btnprofile;
    @FXML
    private Button btnstats;
    @FXML
    private Button logout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
            //Logger.getLogger(StatRatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stat.setData(piechartdata);
        try {
            loadDataLine();
        } catch (SQLException ex) {
            //  Logger.getLogger(StatRatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        statcourbe.getData().add(linechartdata);

    }

    public void loadDataPie() throws SQLException {
        int i = 0;
        int j = 0;
        int k = 0;
        piechartdata = FXCollections.observableArrayList();
        String dburl = "jdbc:mysql://localhost:3306/eventum";
        String dblogin = "root";
        String dbpwd = "";

        cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);
        PreparedStatement pst = cnx.prepareStatement("SELECT * from utilisateur");
        rs = pst.executeQuery();

        while (rs.next()) {
            if (rs.getString("genre").equals("homme")) {
                i++;

            }
            if (rs.getString("genre").equals("femme")) {
                j++;

            }
         

        }
        piechartdata.add(new PieChart.Data("statistique selon le nombre de femmes ", i));
        piechartdata.add(new PieChart.Data("statistique selon le nombre d'hommes ", j));
    }

    public void loadDataLine() throws SQLException {

        String dburl = "jdbc:mysql://localhost:3306/eventum";
        String dblogin = "root";
        String dbpwd = "";

        cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);
        PreparedStatement pst = cnx.prepareStatement("SELECT * from utilisateur");
        rs = pst.executeQuery();

        while (rs.next()) {
            String s;
            s = String.valueOf(rs.getInt("id"));
            linechartdata.getData().add(new XYChart.Data<String, Double>(s, rs.getDouble("id")));
//            name.add(rs.getString("nom_local"));
//            cap.add(rs.getInt("capacite"));             
        }
//        linechart.getData().add(linechartdata);
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
    private void logout(MouseEvent event) {
    }

}