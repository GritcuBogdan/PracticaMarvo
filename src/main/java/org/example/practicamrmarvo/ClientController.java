package org.example.practicamrmarvo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class ClientController {

    private Stage primaryStage;

    @FXML
    private ImageView homeButton;

    @FXML
    private RadioButton clientFizicRadioButton;

    @FXML
    private RadioButton clientJuridicRadioButton;

    @FXML
    private ToggleGroup clientTypeToggleGroup;

    @FXML
    private AnchorPane clientFizicPane;

    @FXML
    private AnchorPane clientJuridicPane;

    // Client Fizic Fields
    @FXML
    private TextField numeInput;

    @FXML
    private TextField codPersonalInput;

    @FXML
    private TextField adresaInput;

    @FXML
    private TextField telefonInput;

    @FXML
    private TextField numarDepoziteInput;

    // Client Juridic Fields
    @FXML
    private TextField codFiscalInput;

    @FXML
    private TextField denumireInput;

    @FXML
    private TextField tipProprietateInput;

    @FXML
    private TextField adresaJuridicaInput;

    @FXML
    private TextField telefonJuridicInput;

    @FXML
    private TextField numeAdministratorInput;

    @FXML
    private TextField persoanaContactInput;

    @FXML
    private void initialize() {
        // Initialize toggle group
        clientFizicRadioButton.setToggleGroup(clientTypeToggleGroup);
        clientJuridicRadioButton.setToggleGroup(clientTypeToggleGroup);

        // Add listener to toggle group
        clientTypeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == clientFizicRadioButton) {
                clientFizicPane.setVisible(true);
                clientJuridicPane.setVisible(false);
            } else if (newValue == clientJuridicRadioButton) {
                clientFizicPane.setVisible(false);
                clientJuridicPane.setVisible(true);
            }
        });

        homeButton.setOnMouseClicked(event -> switchToStartForm());

        // Restrict input to digits for telefonInput, codPersonalInput, and codFiscalInput in clientFizicPane
        restrictToDigits(telefonInput);
        restrictToDigits(codPersonalInput);
        restrictToDigits(numarDepoziteInput);

        // Restrict input to digits for telefonJuridicInput and codFiscalInput in clientJuridicPane
        restrictToDigits(telefonJuridicInput);
        restrictToDigits(codFiscalInput);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void switchToStartForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StartForm.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSubmitClientFizic() {
        String nume = numeInput.getText();
        String codPersonal = codPersonalInput.getText();
        String adresa = adresaInput.getText();
        String telefon = telefonInput.getText();
        String numarDepozite = numarDepoziteInput.getText();

        // Save to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clienti.txt", true))) {
            writer.write("ClientFizic [nume=" + nume + ", codPersonal=" + codPersonal + ", adresa=" + adresa + ", telefon=" + telefon + ", numarDepozite=" + numarDepozite + "]");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear fields
        numeInput.clear();
        codPersonalInput.clear();
        adresaInput.clear();
        telefonInput.clear();
        numarDepoziteInput.clear();
    }

    @FXML
    private void handleSubmitClientJuridic() {
        String codFiscal = codFiscalInput.getText();
        String denumire = denumireInput.getText();
        String tipProprietate = tipProprietateInput.getText();
        String adresa = adresaJuridicaInput.getText();
        String telefon = telefonJuridicInput.getText();
        String numeAdministrator = numeAdministratorInput.getText();
        String persoanaContact = persoanaContactInput.getText();

        // Save to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clienti.txt", true))) {
            writer.write("ClientJuridic [codFiscal=" + codFiscal + ", denumire=" + denumire + ", tipProprietate=" + tipProprietate + ", adresa=" + adresa + ", telefon=" + telefon + ", numeAdministrator=" + numeAdministrator + ", persoanaContact=" + persoanaContact + "]");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear fields
        codFiscalInput.clear();
        denumireInput.clear();
        tipProprietateInput.clear();
        adresaJuridicaInput.clear();
        telefonJuridicInput.clear();
        numeAdministratorInput.clear();
        persoanaContactInput.clear();
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
