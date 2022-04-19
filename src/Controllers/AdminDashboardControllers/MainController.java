package Controllers.AdminDashboardControllers;


import Utils.RessorcesManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public StackPane content;
    @FXML
    public Label adminName;
    @FXML
    public Label adminLastName;
    @FXML
    public Label adminNameAndLastName;
    @FXML
    public Circle adminAvatar;
    @FXML
    private Button exitBtn;
    private double x, y;


    public MainController() {

    }

    @FXML
    void exitApp(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void mouseDragged(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Pane) mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - x);
        stage.setY(mouseEvent.getScreenY() - y);
    }

    @FXML
    void mousePressed(MouseEvent mouseEvent) {
        x = mouseEvent.getSceneX();
        y = mouseEvent.getSceneY();
    }









    @FXML
    void showForumsMenu(ActionEvent event) {
        try {
            Parent menu = FXMLLoader.load(getClass().getResource("/Views/UI/Dashboard/ForumMenu.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(menu);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
