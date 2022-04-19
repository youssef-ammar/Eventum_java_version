package Controllers.Forum;

import Modules.Forum;
import Services.ForumService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class ForumCell {
    @FXML
    private Circle OrgImage;

    @FXML
    private Label category;

    @FXML
    private Label date;

    @FXML
    private Text forumContent;

    @FXML
    private Label forumTitle;

    @FXML
    private Label nbLikes;

    @FXML
    private Button like;

    @FXML
    private Button responded;

    @FXML
    private Label username;

    @FXML
    private AnchorPane forumCell;

    @FXML
    private Button delete;


    @FXML
    private Button Edit;

    @FXML
    private Button addForum;



    private Forum forum;
    private Parent menu;

    public void setData(Forum forum) {
        this.forum = forum;
        forumTitle.setText(forum.getTitle());
        forumContent.setText(forum.getContent());
        category.setText(forum.getCategoryForum());
        date.setText(String.valueOf(forum.getCreatedAt()));
        nbLikes.setText(Integer.toString(forum.getNbLikes()));


            username.setText("Foulen");



        delete.setVisible(true);
        Edit.setVisible(true);

    }

    @FXML
    void deleteForum(ActionEvent event) {
        try {
            ForumService forumService = ForumService.getInstance();
            forumService.delete(forum);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Response is deleted successfully !");
            alert.show();


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    void EditForum(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/Windows/UpdateForum.fxml"));
            Parent menu = fxmlLoader.load();
            UpdateForumController updateResponse = fxmlLoader.getController();
            updateResponse.setData(forum);
            Scene scene = new Scene(menu);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Like(ActionEvent event) throws SQLException {


    }



    @FXML
    void showDetail(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/Templates/ForumDetail.fxml"));
            menu = fxmlLoader.load();
            System.out.println("========="+forum+"==========");
            ForumDetail forumDetail = fxmlLoader.getController();
            forumDetail.setData(forum);
            Scene scene = new Scene(menu);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void addForum(ActionEvent event) {
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
}
