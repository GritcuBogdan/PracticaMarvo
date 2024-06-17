package org.example.practicamrmarvo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DepozitController {
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private ImageView homeButton;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField denumireInput;

    @FXML
    private TextField tipInput;
    @FXML
    private TextField procentInput;
    @FXML
    private TextField valutaInput;



    @FXML
    private void backToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StartForm.fxml"));
            Parent root = loader.load();
            BankApp.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSaveDepozit() {
        String denumire = denumireInput.getText();
        String tip = tipInput.getText();
        String procent = procentInput.getText();
        String valuta = valutaInput.getText();

        if (denumire.isEmpty() || tip.isEmpty() || procent.isEmpty() || valuta.isEmpty()) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("All fields must be filled out.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("depozite.txt", true))) {
            writer.write(String.format("Depozit [denumire=%s, tip=%s, procent=%s, valuta=%s]", denumire, tip, procent, valuta));
            writer.newLine();
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Depozit salvat cu succes!");
        } catch (IOException e) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Toate campurile trebuie completate!");
            e.printStackTrace();
        }

        // Clear the input fields
        denumireInput.clear();
        tipInput.clear();
        procentInput.clear();
        valutaInput.clear();
    }



}
