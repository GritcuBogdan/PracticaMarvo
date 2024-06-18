package org.example.practicamrmarvo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.function.UnaryOperator;

public class ContractController {
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private TextField contractInput;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField procentInput;

    @FXML
    private TextField depozitInput;

    @FXML
    private TextField termenInput;

    @FXML
    private TextField clientInput;

    @FXML
    private TextField sumaInput;

    @FXML
    private Button saveDepozitButton;

    @FXML
    private Label messageLabel;

    @FXML
    private ImageView homeButton;

    private String filename = "contracte.txt";

    @FXML
    public void initialize() {
        restrictToDigits(sumaInput);
        restrictToDigits(procentInput);
    }

    @FXML
    private void handleSubmitButton(ActionEvent event) {
        String numarContract = contractInput.getText().trim();
        LocalDate date = datePicker.getValue();
        String client = clientInput.getText().trim();
        String depozit = depozitInput.getText().trim();
        String termenStr = termenInput.getText().trim();
        String procentDobandaStr = procentInput.getText().trim();
        String sumaTotalaStr = sumaInput.getText().trim();

        try {
            // Validate input
            if (numarContract.isEmpty() || date == null || client.isEmpty() || depozit.isEmpty() || termenStr.isEmpty() || procentDobandaStr.isEmpty() || sumaTotalaStr.isEmpty()) {
                messageLabel.setText("Completează toate câmpurile!");
                messageLabel.setTextFill(javafx.scene.paint.Color.RED);
                return;
            }

            // Convert numeric inputs
            int termen = Integer.parseInt(termenStr);
            double procentDobanda = Double.parseDouble(procentDobandaStr.replace(",", "."));
            double sumaTotala = Double.parseDouble(sumaTotalaStr.replace(",", "."));

            // Save contract data to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracte.txt", true))) {
                // Construct the entry to write to the file
                String entry = String.format("numarContract=%s, data=%s, client=%s, depozit=%s, sumaTotala=%.2f, termen=%d, procentDobanda=%.2f",
                        numarContract, date.toString(), client, depozit, sumaTotala, termen, procentDobanda);

                writer.write(entry);
                writer.newLine(); // Add a new line for separation

                messageLabel.setText("Datele au fost introduse cu succes.");
                messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            } catch (IOException e) {
                messageLabel.setText("Eroare la salvarea datelor.");
                messageLabel.setTextFill(javafx.scene.paint.Color.RED);
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Introduceți valori numerice valide pentru termen, procent dobândă și sumă totală.");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
        }

        // Clear input fields after submission
        contractInput.clear();
        clientInput.clear();
        depozitInput.clear();
        sumaInput.clear();
        termenInput.clear();
        procentInput.clear();
        datePicker.setValue(null);
    }


    @FXML
    private void backToHome(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StartForm.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void restrictToDigits(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };

        TextFormatter<String> formatter = new TextFormatter<>(filter);
        textField.setTextFormatter(formatter);
    }


}


