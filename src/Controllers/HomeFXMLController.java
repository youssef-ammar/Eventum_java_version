package Controllers;

import Utils.CropImg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeFXMLController implements Initializable {

    @FXML
    public Text profileNameAndLastName;
    public Circle userAvatar;
    @FXML
    private Button exitBtn;

    @FXML
    private Button SearchBtn;

    @FXML
    private Button PostBtn;

    @FXML
    private Button EventBtn;

    @FXML
    private Button ForumBtn;

    @FXML
    private Button OfferBtn;

    @FXML
    private Button AnnounceBtn;

    @FXML
    private Button AddBtn;

    @FXML
    private Button LogoutBtn;

    @FXML
    private StackPane content;

    @FXML
    private MenuItem eventAddItem;

    @FXML
    private MenuItem forumAddItem;

    @FXML
    private MenuItem offreAddItem;

    @FXML
    private MenuItem postAddItem;

    @FXML
    private MenuItem alertAddItem;



    @FXML
    void exitApp(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    void showAnnounceMenu(ActionEvent announce) {

    }

    @FXML
    void showEventMenu(ActionEvent event) {


    }

    @FXML
    void showSearchList(MouseEvent event) {

    }

    @FXML
    void AddAlert(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Views/UI/Announcement/AddAlert.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void showForum(ActionEvent event) {
        try {
            Parent menu = FXMLLoader.load(getClass().getResource("/Views/UI/ForumMenu.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void logout(ActionEvent actionEvent) {

    }

    @FXML
    void showOfferMenu(ActionEvent event) {

    }

    @FXML
    void showPostMenu(ActionEvent event) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



            forumAddItem.setVisible(true);



    }

    public void AddOffer(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Views/Windows/AddOffer.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddForum(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Views/Windows/AddForumWindow.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddEvent(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Views/Windows/AddEvent.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddPost(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Views/Windows/AddPost.fxml"));
            System.out.println(parent);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void addPic(ActionEvent actionEvent) {

    }
}
