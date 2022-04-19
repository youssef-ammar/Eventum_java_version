package Views;


import Utils.RessorcesManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception {
        String url;


               url = "/Views/UI/Dashboard/DashboardMain.fxml";
               // url = "/Views/UI/HomeTemplate.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(url));
        primaryStage.setTitle("Eventum");
        try {
            Image image = new Image("Views/Icons/logo_eventum.png");
            primaryStage.getIcons().add(image);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        primaryStage.setMaximized(true);
        //primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setResizable(true);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
