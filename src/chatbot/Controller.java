package chatbot;

import chatbot.apis.TwitterAPI;
import chatbot.apis.ProductDB;
import chatbot.products.Product;
import chatbot.products.ProductFactory;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import twitter4j.TwitterException;

import chatbot.products.*;
import chatbot.apis.*;

import java.util.ArrayList;
import java.util.List;


public class Controller {

    @FXML
    public Label messageLabel;
    public Rectangle messageBox;

    @FXML //Product fields
    public Button closeBtn;
    public TextField brandTF;
    public TextField modelTF;
    public TextField priceTF;
    public TextField widthTF;
    public TextField heightTF;
    public TextField depthTF;
    public TextField weightTF;

    @FXML //Consumer Electronics fields
    public TextField screenTF;
    public TextField storageTF;
    public TextField osTF;


    @FXML //Mobile Phone fields
    public TextField cameraTF;
    public TextField ramTF;

    @FXML //Laptop fields
    public TextField cpuTF;

    @FXML //Vehicle fields
    public TextField powerTF;
    public TextField fuelTypeTF;

    @FXML //Car fields
    public TextField airConditionerTF;
    public TextField numberOfSeatsTF;

    @FXML //Motorcycle fields
    public CheckBox windshieldCB;
    public CheckBox carrierBoxCB;

    @FXML //Major Appliance fields
    public TextField capacityTF;
    public TextField energyEfficiencyTF;

    @FXML //Refrigerator fields
    public TextField refrigeratorTypeTF;
    public CheckBox iceMakerCB;
    public CheckBox frostFreeCB;
    public CheckBox doorOpenAlarmCB;


    private static final String infoColor = "#3c9ae1";
    private static final String errorColor = "#dd3b3b";
    private static final String successColor = "#3ce07e";

    /**
     * This function is called by close button at the top right
     */
    @FXML
    public void close() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * this function is called by save button of AddPhone form
     */
    @FXML
    public void savePhone(Event event) {

        if (!isSet(brandTF) || !isSet(modelTF) || !isSet(priceTF) || !isSet(osTF)
                || !isSet(heightTF) || !isSet(widthTF) || !isSet(depthTF) || !isSet(weightTF)
                || !isSet(screenTF) || !isSet(storageTF) || !isSet(cameraTF) || !isSet(ramTF)) {
            messageLabel.setText("You must fill the form.");
            messageBox.setFill(Color.web(errorColor));
            return;
        } else {
            messageBox.setFill(Color.web(infoColor));
        }

        Product newMobilePhone;
        try {
            newMobilePhone = ProductFactory.createProduct(
                    brandTF.getText(),
                    modelTF.getText(),
                    Double.parseDouble(priceTF.getText()),
                    Double.parseDouble(heightTF.getText()),
                    Double.parseDouble(widthTF.getText()),
                    Double.parseDouble(depthTF.getText()),
                    Double.parseDouble(weightTF.getText()),
                    Double.parseDouble(screenTF.getText()),
                    Integer.parseInt(storageTF.getText()),
                    Integer.parseInt(cameraTF.getText()),
                    osTF.getText(),
                    Integer.parseInt(ramTF.getText()));
        } catch (Exception e) {
            messageLabel.setText("One or more fields are not filled out correctly");
            messageBox.setFill(Color.web(errorColor));
            return;
        }

        List<Double> reviewList =  getPointFromLatestTweets();
        newMobilePhone.setReviewPoint1(reviewList.get(0));
        newMobilePhone.setReviewPoint2(reviewList.get(1));

        ProductDB mongoDB =  ProductDB.getInstance();
        mongoDB.insert(newMobilePhone);
        mongoDB.close(); //close db connection

        this.close();
    }

    /**
     * this function is called by save button of AddLaptop form
     */
    @FXML
    public void saveLaptop(Event event) {

        if (!isSet(brandTF) || !isSet(modelTF) || !isSet(priceTF) || !isSet(osTF)
                || !isSet(heightTF) || !isSet(widthTF) || !isSet(depthTF) || !isSet(weightTF)
                || !isSet(screenTF) || !isSet(storageTF) || !isSet(ramTF) || !isSet(cpuTF)) {
            messageLabel.setText("You must fill the form.");
            messageBox.setFill(Color.web(errorColor));
            return;
        } else {
            messageBox.setFill(Color.web(infoColor));
        }

        Product newLaptop;
        try {
            newLaptop = ProductFactory.createProduct(
                    brandTF.getText(),
                    modelTF.getText(),
                    Double.parseDouble(priceTF.getText()),
                    Double.parseDouble(heightTF.getText()),
                    Double.parseDouble(widthTF.getText()),
                    Double.parseDouble(depthTF.getText()),
                    Double.parseDouble(weightTF.getText()),
                    Double.parseDouble(screenTF.getText()),
                    Integer.parseInt(storageTF.getText()),
                    Integer.parseInt(ramTF.getText()),
                    cpuTF.getText(),
                    osTF.getText());
        } catch (Exception e) {
            messageLabel.setText("One or more fields are not filled out correctly");
            messageBox.setFill(Color.web(errorColor));
            return;
        }

        List<Double> reviewList =  getPointFromLatestTweets();
        newLaptop.setReviewPoint1(reviewList.get(0));
        newLaptop.setReviewPoint2(reviewList.get(1));

        ProductDB mongoDB = ProductDB.getInstance();
        mongoDB.insert(newLaptop);
        mongoDB.close(); //close db connection

        this.close();
    }

    public void saveCar(Event event) {
        if (!isSet(brandTF) || !isSet(modelTF) || !isSet(priceTF)
                || !isSet(heightTF) || !isSet(widthTF) || !isSet(depthTF) || !isSet(weightTF)
                || !isSet(powerTF) || !isSet(fuelTypeTF) || !isSet(airConditionerTF) || !isSet(numberOfSeatsTF)) {
            messageLabel.setText("You must fill the form.");
            messageBox.setFill(Color.web(errorColor));
            return;
        } else {
            messageBox.setFill(Color.web(infoColor));
        }

        Product newCar;
        try {
            newCar = ProductFactory.createProduct(
                    brandTF.getText(),
                    modelTF.getText(),
                    Double.parseDouble(priceTF.getText()),
                    Double.parseDouble(heightTF.getText()),
                    Double.parseDouble(widthTF.getText()),
                    Double.parseDouble(depthTF.getText()),
                    Double.parseDouble(weightTF.getText()),
                    Integer.parseInt(powerTF.getText()),
                    fuelTypeTF.getText(),
                    Integer.parseInt(numberOfSeatsTF.getText()),
                    airConditionerTF.getText());
        } catch (Exception e) {
            messageLabel.setText("One or more fields are not filled out correctly");
            messageBox.setFill(Color.web(errorColor));
            return;
        }

        List<Double> reviewList =  getPointFromLatestTweets();
        newCar.setReviewPoint1(reviewList.get(0));
        newCar.setReviewPoint2(reviewList.get(1));

        ProductDB mongoDB = ProductDB.getInstance();
        mongoDB.insert(newCar);
        mongoDB.close(); //close db connection

        this.close();

    }

    public void saveMotorcycle(Event event) {
        if (!isSet(brandTF) || !isSet(modelTF) || !isSet(priceTF)
                || !isSet(heightTF) || !isSet(widthTF) || !isSet(depthTF) || !isSet(weightTF)
                || !isSet(powerTF) || !isSet(fuelTypeTF) ) {
            messageLabel.setText("You must fill the form.");
            messageBox.setFill(Color.web(errorColor));
            return;
        } else {
            messageBox.setFill(Color.web(infoColor));
        }

        Product newMotorcycle;
        try {
            newMotorcycle = ProductFactory.createProduct(
                    brandTF.getText(),
                    modelTF.getText(),
                    Double.parseDouble(priceTF.getText()),
                    Double.parseDouble(heightTF.getText()),
                    Double.parseDouble(widthTF.getText()),
                    Double.parseDouble(depthTF.getText()),
                    Double.parseDouble(weightTF.getText()),
                    Integer.parseInt(powerTF.getText()),
                    fuelTypeTF.getText(),
                    windshieldCB.isSelected(),
                    carrierBoxCB.isSelected());
        } catch (Exception e) {
            messageLabel.setText("One or more fields are not filled out correctly");
            messageBox.setFill(Color.web(errorColor));
            return;
        }

        List<Double> reviewList =  getPointFromLatestTweets();
        newMotorcycle.setReviewPoint1(reviewList.get(0));
        newMotorcycle.setReviewPoint2(reviewList.get(1));

        ProductDB mongoDB = ProductDB.getInstance();
        mongoDB.insert(newMotorcycle);
        mongoDB.close(); //close db connection

        this.close();

    }

    public void saveRefrigerator(Event event) {
        if (!isSet(brandTF) || !isSet(modelTF) || !isSet(priceTF)
                || !isSet(heightTF) || !isSet(widthTF) || !isSet(depthTF) || !isSet(weightTF)
                || !isSet(capacityTF) || !isSet(energyEfficiencyTF) || !isSet(refrigeratorTypeTF)) {
            messageLabel.setText("You must fill the form.");
            messageBox.setFill(Color.web(errorColor));
            return;
        } else {
            messageBox.setFill(Color.web(infoColor));
        }

        Product newRfrigerator;
        try {
            newRfrigerator = ProductFactory.createProduct(
                    brandTF.getText(),
                    modelTF.getText(),
                    Double.parseDouble(priceTF.getText()),
                    Double.parseDouble(heightTF.getText()),
                    Double.parseDouble(widthTF.getText()),
                    Double.parseDouble(depthTF.getText()),
                    Double.parseDouble(weightTF.getText()),
                    Integer.parseInt(capacityTF.getText()),
                    energyEfficiencyTF.getText(),
                    refrigeratorTypeTF.getText(),
                    iceMakerCB.isSelected(),
                    frostFreeCB.isSelected(),
                    doorOpenAlarmCB.isSelected()
                    );
        } catch (Exception e) {
            messageLabel.setText("One or more fields are not filled out correctly");
            messageBox.setFill(Color.web(errorColor));
            return;
        }
        List<Double> reviewList =  getPointFromLatestTweets();
        newRfrigerator.setReviewPoint1(reviewList.get(0));
        newRfrigerator.setReviewPoint2(reviewList.get(1));

        ProductDB mongoDB = ProductDB.getInstance();
        mongoDB.insert(newRfrigerator);
        mongoDB.close(); //close db connection

        this.close();

    }

    /**
     * Have to fix every product fields to add calStrategy2 !!!!!!!!!
     * @return
     */
    private List<Double> getPointFromLatestTweets() {
        try {
            TwitterAPI twitterAPI = new TwitterAPI();
            List<Double> calResults = twitterAPI.getReviewPoint(modelTF.getText());
            double calStrategy1 = calResults.get(0);
            double calStrategy2 = calResults.get(1);
            System.out.println("Mean of review points of ten tweets: " + calStrategy1);
            return calResults;
        } catch (TwitterException e) {
            System.out.println("An error ocurred while getting tweets.");
            List<Double> zeroList = new ArrayList<Double>();
            zeroList.add(0.00);
            zeroList.add(0.00);
            return zeroList;
        }
    }

    private boolean isSet(TextField tf) {
        return !(tf.getText().isEmpty() || tf.getText().equals("") || !tf.getText().matches(".*[a-zA-Z0-9]+.*"));
    }
}
