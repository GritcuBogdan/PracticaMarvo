package org.example.practicamrmarvo;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BankApp extends Application {

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        BankApp.primaryStage = primaryStage;
    }

    @FXML
    private Button showContracte;

    @FXML
    private Button showDepozite;

    @FXML
    private Button addDepozit;

    @FXML
    private Button addFiliala;

    @FXML
    private Button showFiliala;
    @FXML
    private Button showClienti;

    @FXML
    private Button addContract;

    @Override
    public void start(Stage primaryStage) {
        BankApp.primaryStage = primaryStage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartForm.fxml"));
            AnchorPane root = fxmlLoader.load();
            primaryStage.setTitle("Marvo Bank");

            BankApp bankAppController = fxmlLoader.getController();
            bankAppController.setPrimaryStage(primaryStage);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Error reading StartForm.fxml file");
            e.printStackTrace();
        }
    }



    @FXML
    private void initialize() {
        addFiliala.setOnAction(e -> {
            try {
                switchToAddFiliala();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        showFiliala.setOnAction(e -> {
            try {
                switchToShowFiliala();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        showClienti.setOnAction(e -> {
            try {
                showClienti();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        addDepozit.setOnAction(e ->{
            try{
                switchToAddDepozit();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        showDepozite.setOnAction(e -> {
            try{
                switchToShowDepozit();
            }catch (Exception EX){
                EX.printStackTrace();
            }
        });

        addContract.setOnAction(e -> {
            try{
                switchToAdddContract();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

    }


    public void switchToShowDepozit() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowDepozit.fxml"));
        Parent root = loader.load();
        ShowDepozitController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.getScene().setRoot(root);
    }

    public void switchToAddDepozit() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDepozit.fxml"));
        Parent root = loader.load();
        DepozitController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.getScene().setRoot(root);
    }


    public void switchToAddFiliala() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFiliala.fxml"));
        Parent root = loader.load();
        FilialaController filialaController = loader.getController();
        filialaController.setPrimaryStage(primaryStage);
        primaryStage.getScene().setRoot(root);
    }

    public void switchToShowFiliala() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowFiliala.fxml"));
        Parent root = loader.load();
        ShowFilialaController showFilialaController = loader.getController();
        showFilialaController.setPrimaryStage(primaryStage);
        primaryStage.getScene().setRoot(root);
    }

    public void switchToAddClient() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddClientFizic.fxml"));
        Parent root = loader.load();
        ClientController clientController = loader.getController();
        clientController.setPrimaryStage(primaryStage);
        primaryStage.getScene().setRoot(root);
    }

    @FXML
    public void switchToShowContract() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowContracte.fxml"));
        Parent root = loader.load();
        ShowContracteController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.getScene().setRoot(root);
    }

    @FXML
    private void showClienti() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowClienti.fxml"));
            Parent root = loader.load();
            ShowClientiController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println("Error loading ShowClienti.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToAdddContract()  throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddContract.fxml"));
        Parent root = loader.load();
        ContractController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.getScene().setRoot(root);
    }

    @FXML
    private void onMouseEntered(MouseEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals("Afișează contracte")) {
            button.setStyle("-fx-background-color: #88a162; -fx-border-color: #6D8B74; -fx-border-width: 2 2 2 2;");
        } else {
            button.setStyle("-fx-background-color: #88a162; -fx-border-color: #6D8B74; -fx-border-width: 2 2 0 2;");
        }
    }

    @FXML
    private void onMouseExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals("Afișează contracte")) {
            button.setStyle("-fx-background-color: #A4BE7B; -fx-border-color: #6D8B74; -fx-border-width: 2 2 2 2;");
        } else {
            button.setStyle("-fx-background-color: #A4BE7B; -fx-border-color: #6D8B74; -fx-border-width: 2 2 0 2;");
        }
    }

    @FXML
    private void onMousePressed(MouseEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals("Afișează contracte")) {
            button.setStyle("-fx-background-color: #c5ed87; -fx-border-color: #6D8B74; -fx-border-width: 2 2 2 2;");
        } else {
            button.setStyle("-fx-background-color: #c5ed87; -fx-border-color: #6D8B74; -fx-border-width: 2 2 0 2;");
        }
    }

    @FXML
    private void onMouseReleased(MouseEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals("Afișează contracte")) {
            button.setStyle("-fx-background-color: #88a162; -fx-border-color: #6D8B74; -fx-border-width: 2 2 2 2;");
        } else {
            button.setStyle("-fx-background-color: #88a162; -fx-border-color: #6D8B74; -fx-border-width: 2 2 0 2;");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
