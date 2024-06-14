package org.example.practicamrmarvo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class FilialaController {
    private Stage primaryStage;

    @FXML
    private ImageView homeButton;

    @FXML
    private TextField numeInput;
    @FXML
    private TextField adresaInput;
    @FXML
    private TextField telefonInput;
    @FXML
    private Label errorLabel2;
    @FXML
    private Label messageLabel;
    @FXML
    private Button saveFilialaButton;

    private static ArrayList<Filiala> filialeList = new ArrayList<>();

    public FilialaController() {
    }

    public FilialaController(TextField nume, TextField adresa, TextField telefon) {
        this.numeInput = nume;
        this.adresaInput = adresa;
        this.telefonInput = telefon;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        telefonInput.setTextFormatter(new TextFormatter<>(forceDigitsOnly()));
        saveFilialaButton.setOnAction(e -> saveFiliala());
        homeButton.setOnMouseClicked(this::backToHome);
        homeButton.setOnMouseEntered(this::onMouseEnteredImage);
        homeButton.setOnMouseExited(this::onMouseExitedImage);
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

    public UnaryOperator<TextFormatter.Change> forceDigitsOnly() {
        return change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                errorLabel2.setText("");
                return change;
            } else {
                errorLabel2.setText("Puteți introduce doar cifre!");
                return null;
            }
        };
    }

    @FXML
    private void saveFiliala() {
        String nume = numeInput.getText();
        String adresa = adresaInput.getText();
        String telefon = telefonInput.getText();

        if (nume.isEmpty() || adresa.isEmpty() || telefon.isEmpty()) {
            messageLabel.setText("Toate câmpurile trebuie completate!");
            messageLabel.setTextFill(Color.RED);
            messageLabel.setLayoutX(470);
        } else {
            Filiala filiala = new Filiala(nume, adresa, telefon);
            filialeList.add(filiala);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("filiale.txt", true))) {
                writer.write(filiala.toString());
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Eroare la scrierea in fisierul filiale.txt");
            }

            messageLabel.setText("Filiala a fost adăugată cu succes!");
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setLayoutX(485);
            numeInput.clear();
            adresaInput.clear();
            telefonInput.clear();
        }
    }

    public static ArrayList<Filiala> getFilialeList() {
        return filialeList;
    }


    @FXML
    private void onMouseEnteredImage(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        imageView.setImage(new Image("/org/example/practicamrmarvo/img/home-page-grey-icon-removebg-preview.png"));
    }

    @FXML
    private void onMouseExitedImage(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        imageView.setImage(new Image("/org/example/practicamrmarvo/img/home-page-white-icon-removebg-preview.png"));
    }
}
