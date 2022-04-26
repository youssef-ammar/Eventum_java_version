package chatbot;

import Services.ForumService;
import chatbot.apis.ProductDB;
import chatbot.apis.Redis;
import chatbot.apis.Tweet;
import chatbot.products.Product;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import chatbot.products.*;
import chatbot.apis.*;


public class BotHandler extends Application {
    public static BotHandler instance;
    private List<Product> products;
    private HashMap<String, String[]> botAnswers;
    private TreeNode rootNode;
    private TreeNode currentNode;
    private String[] options;
    private String lastState;
    Product selectedProduct;

    private static final String adminName = "admin";
    private static final String adminPass = "pw123";
    private Boolean isLoggedIn;

    @FXML
    public Rectangle header;

    @FXML
    public AnchorPane botAnchor;

    @FXML
    public ScrollPane scrollPane;

    @FXML
    public TextArea chatArea;

    @FXML
    public TextField inputBox;

    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        botAnswers = new HashMap<>();
        String messages[] = new String[]{"Hello.", "Hi.", "Hey there."};
        botAnswers.put("greeting", messages);
        messages = new String[]{"I'm fine, thank you, and you?", "Great! You?", "Pretty good, and you?"};
        botAnswers.put("ask_about", messages);
        messages = new String[]{"Bye bye...", "Goodbye!", "See you later :)", "Bye, come again soon!"};
        botAnswers.put("goodbye", messages);
        messages = new String[]{"Sorry. I can't understand your message.", "Ugh! I can't understand this.", "I can't understand this. Can you repeat?"};
        botAnswers.put("unknown", messages);
        lastState = "";
        isLoggedIn = false;

        primaryStage.getIcons().add(new Image("chatbot/chatbot.png")); //set application image

        new Thread(() -> {
            //take product info from database asynchronously
            ProductDB mongo;
            try {
                mongo =  ProductDB.getInstance();
                List<Product> allProductsList = mongo.getAllProducts();
                mongo.close();
                rootNode = new TreeNode(allProductsList);
            } catch (Exception e) {
                rootNode = null;
            }

        }).start();

        Parent botFxml = FXMLLoader.load(getClass().getResource("Bot.fxml"));
        botAnchor = (AnchorPane) botFxml;
        initForm(primaryStage, botFxml, false);

        chatArea = (TextArea) botAnchor.lookup("#chatBox");
        inputBox = (TextField) botAnchor.lookup("#inputBox");

        inputBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (chatArea == null)
                    chatArea = (TextArea) botAnchor.lookup("#chatBox");

                String uText = inputBox.getText();

                if (uText.length() < 1) {
                    //do not trigger answering logic if message is empty
                    return;
                }

                //auto scroll to the end of chatArea after 50ms
                Timeline autoScroll = new Timeline(new KeyFrame(
                        Duration.millis(50),
                        ae -> chatArea.setScrollTop(Double.MAX_VALUE)));

                if (!lastState.equals("admin:asked_password")) {
                    //normal answer. print it to chatbox
                    chatArea.setText(chatArea.getText() + "You: " + uText + "\n");
                } else {
                    //let's not show the password
                    chatArea.setText(chatArea.getText() + "You: " + String.join("", Collections.nCopies(uText.length(), "*")) + "\n");
                }
                inputBox.setText("");

                uText = uText.toLowerCase();

                if (uText.contains(adminName)) {
                    if (isLoggedIn) {
                        answer("You are already logged in " + adminName);
                    } else {
                        //admin login logic starts here
                        answer("Enter password : ");
                        lastState = "admin:asked_password";
                    }
                } else if (lastState.equals("admin:asked_password") || (isLoggedIn && uText.contains("add"))) {
                    if (isLoggedIn || uText.equals(adminPass)) {
                        isLoggedIn = true;
                        answer("Welcome " + adminName + ". What kind of product do you want to add?");
                        answer("1: Mobile Phone");
                        answer("2: Laptop");
                        answer("3: Car");
                        answer("4: Motorcycle");
                        answer("5: Refrigerator");
                        lastState = "admin:logged_in";
                    } else {
                        answer("Wrong password!");
                        lastState = "";
                    }
                } else if (lastState.equals("admin:logged_in")) {
                    if (!uText.matches("[0-9]+") || Integer.parseInt(uText) > 5) {
                        answer("That's not a valid selection.");
                        lastState = ""; //reset lastState;
                        return;
                    }
                    int selection = Integer.parseInt(uText);
                    answer("Opening new form...");

                    if (selection == 1) {
                        newForm("chatbot/AddPhone.fxml");
                    } else if (selection == 2) {
                        newForm("chatbot/AddLaptop.fxml");
                    } else if (selection == 3) {
                        newForm("chatbot/AddCar.fxml");
                    } else if (selection == 4) {
                        newForm("chatbot/AddMotorcycle.fxml");
                    } else if (selection == 5) {
                        newForm("chatbot/AddRefrigerator.fxml");
                    }
                    lastState = "";
                } else if (isLoggedIn && (uText.contains("refresh") || uText.contains("reload"))) {
                    answer("Refreshing the product list...");

                    //retrieve list from mongoDB again
                    ProductDB mongo = ProductDB.getInstance();
                    List<Product> allProductsList = mongo.getAllProducts();
                    rootNode = new TreeNode(allProductsList);
                    mongo.close();

                    answer("Operation is successfully completed!");
                } else if (lastState.equals("") &&
                        (uText.contains("hello") || uText.contains("hi") || uText.contains("hey"))) {
                    decideRandom("greeting");
                } else if (lastState.equals("") &&
                        ((uText.contains("how") && uText.contains("you")) || (uText.contains("what") && uText.contains("up")))) {
                    decideRandom("ask_about");
                } else if (lastState.equals("") && (uText.contains("buy") || uText.contains("product"))) {
                    //we will print product categories (like "Consumer Electronics", "Major Appliance" ...)

                    if (rootNode == null) {
                        // database connection problem
                        answer("Sorry. I can't read product list.");
                        return;
                    }

                    currentNode = rootNode;
                    printOptions(currentNode);
                    lastState = "product:asked_category";

                } else if (lastState.equals("product:asked_category")) {
                    //we will print product types in selected category (like "Mobile Phone", "Refigerator"...)

                    if (!uText.matches("[0-9]+")) {
                        answer("That's not a valid selection.");
                        lastState = ""; //reset lastState;
                        return;
                    }

                    int selectedIndex = Integer.parseInt(uText) - 1; //user selection starts from 1, arrays starts from 0
                    currentNode = currentNode.getNextNode(options[selectedIndex]);
                    printOptions(currentNode);
                    lastState = "product:asked_type";
                } else if (lastState.equals("product:asked_type") ) {
                    // user selected a product type. we will ask how to sort products.

                    if (!uText.matches("[0-9]+") || Integer.parseInt(uText) > options.length) {
                        answer("That's not a valid selection.");
                        lastState = ""; //reset lastState;
                        return;
                    }

                    int selectedIndex = Integer.parseInt(uText) - 1; //user selection starts from 1, arrays starts from 0
                    currentNode = currentNode.getNextNode(options[selectedIndex]);

                    answer("Do you want to sort products by our advanced algorithm? (Y/N)");

                    lastState = "product:asked_algo";
                } else if (lastState.equals("product:asked_algo")) {
                    //we will print all products in selected type with user selected ordering

                    products = currentNode.getProductList();

                    if (uText.contains("y"))
                        Product.setCalculationMod(2);
                    else
                        Product.setCalculationMod(1);

                    products.sort(Product::compareTo); //sort list using overriden compareTo method

                    for (int i = 0; i < products.size(); i++) {
                        int j = i + 1; // visual only!
                        answer(j + ": " + products.get(i).toShortString());
                    }
                    lastState = "product:asked_product";
                } else if (lastState.equals("product:asked_product")) {
                    //we will print information about selected product

                    if (!uText.matches("[0-9]+") || Integer.parseInt(uText) > products.size()) {
                        answer("That's not a valid selection.");
                       // lastState = ""; //reset lastState;
                       // return;
                    }
                    int selectedIndex = Integer.parseInt(uText) - 1; //user selection starts from 1, arrays starts from 0
                    selectedProduct = products.get(selectedIndex);

                    //print product information to chatarea
                    chatArea.setText(chatArea.getText() + "\n" + selectedProduct);

                    lastState = "product:printed_info";
                } else if (lastState.equals("product:printed_info") && (uText.contains("review") || uText.contains("tweet"))) {

                    Redis db =  Redis.getInstance();
                    List<Tweet> tweetList = db.getTweetsByKeyword(selectedProduct.getModel());

                    if (tweetList == null) {
                        answer("I can't read review/tweet list.");
                    } else if (tweetList.size() == 0) {
                        answer("I can't find any reviews/tweets for this product. ");
                    } else {
                        Tweet.setCalculationMod(2);
                        tweetList.sort(Tweet::compareTo);
                        answer("Some Twitter comments for product : ");
                        for (int i = 0; i < 3 && i < tweetList.size(); i++) { //top 3 tweets will be displayed on Chatbot
                            chatArea.setText(chatArea.getText() + "\n" + tweetList.get(i));

                        }
                    }

                    lastState = "";
                } else if (uText.contains("bye") || uText.contains("later")) {
                    decideRandom("goodbye");
                    lastState = "";

                } else if (uText.contains("clear")) {
                    chatArea.setText("");
                    lastState = "";
                } else if (uText.contains("exit")) {
                    primaryStage.close();
                } else {
                    decideRandom("unknown");
                }

                //auto scroll to the end of chatArea
                autoScroll.play();

            }
        });

        inputBox.requestFocus();
        primaryStage.setTitle("Chatbot");
        primaryStage.show();
    }
    public static BotHandler getInstance () throws SQLException {
        if (instance == null)
            instance = new BotHandler();

        return instance;
    }
    /**
     * Main function of ChatBot
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void answer(String message) {
        chatArea.setText(chatArea.getText() + "AI: " + message + "\n");
    }

    /**
     * Prints options in given node.
     *
     * @param node
     */
    private void printOptions(TreeNode node) {
        options = node.getAllKeys();
        answer("Please select a " + node.nodeType + ":");
        for (int i = 0; i < options.length; i++) {
            int j = i + 1; // visual only!
            answer(j + ": " + options[i]);
        }
    }

    /**
     * Selects one random string from messageList and prints it to chatArea
     *
     * @param key
     */
    private void decideRandom(String key) {
        String messageList[] = botAnswers.get(key);
        int randomIndex = ThreadLocalRandom.current().nextInt(0, messageList.length);
        answer(messageList[randomIndex]);
    }

    /**
     * This function is used to create new modal forms (sub-forms, adding new products).
     *
     * @param formFxml name of fxml file
     */
    private void newForm(String formFxml) {
        try {
            Parent newFxml = FXMLLoader.load(getClass().getResource(formFxml));
            Stage newStage = new Stage();
            initForm(newStage, newFxml, true);
            newStage.show();
        } catch (Exception e) {
            System.out.println("An error occurred while creating a new form: " + e.getMessage());
        }
    }

    /**
     * This function is used to initialize any forms we create.
     * Every fxml must start with AnchorPane as a container!
     *
     * @param stage       form stage
     * @param fxml        form fxml file
     * @param isModalForm true if we are creating subforms
     */
    private void initForm(Stage stage, Parent fxml, boolean isModalForm) {
        //FXML must have AnchorPane as container!
        AnchorPane rootAnchor = (AnchorPane) fxml;
        rootAnchor.setBackground(Background.EMPTY);
        Rectangle header = ((Rectangle) rootAnchor.lookup("#header"));

        //fix for draggable window with custom title bar
        header.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        });

        //fix for draggable window with custom title bar
        header.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            }
        });

        Scene scene = new Scene(rootAnchor, rootAnchor.getPrefWidth(), rootAnchor.getPrefHeight());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        //make background transparent for window shadow
        stage.initStyle(StageStyle.TRANSPARENT);
        if (isModalForm) {
            stage.initModality(Modality.APPLICATION_MODAL);
        }
    }

}
