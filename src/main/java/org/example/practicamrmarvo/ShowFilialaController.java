package org.example.practicamrmarvo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ShowFilialaController {

    private Stage primaryStage;

    @FXML
    private ImageView homeButton;

    @FXML
    private TableView<Filiala> filialaTableView;

    @FXML
    private TableColumn<Filiala, String> numeColumn;

    @FXML
    private TableColumn<Filiala, String> adresaColumn;

    @FXML
    private TableColumn<Filiala, String> telefonColumn;

    @FXML
    private AnchorPane deleteControls;

    @FXML
    private Label numeLabel;

    @FXML
    private TextField numeTextField;

    @FXML
    private Button submitButton;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        numeColumn.setCellValueFactory(cellData -> cellData.getValue().numeProperty());
        adresaColumn.setCellValueFactory(cellData -> cellData.getValue().adresaProperty());
        telefonColumn.setCellValueFactory(cellData -> cellData.getValue().telefonProperty());

        filialaTableView.setEditable(false);

        // Load data into TableView
        loadFilialaDataFromFile();
    }

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
    private void switchToInput() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFiliala.fxml"));
            Parent root = loader.load();
            FilialaController filialaController = loader.getController();
            filialaController.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului AddFiliala.fxml");
            e.printStackTrace();
        }
    }

    private void loadFilialaDataFromFile() {
        ObservableList<Filiala> filialaList = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("filiale.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Filiala filiala = parseFiliala(line);
                if (filiala != null) {
                    filialaList.add(filiala);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        filialaTableView.setItems(filialaList);
    }

    private Filiala parseFiliala(String line) {
        // Example line: Filiala [nume=Filiala1, adresa=Botanica,str.Cuza-Voda 12, telefon=068984778]
        String[] parts = line.split(", ");
        if (parts.length == 3) {
            String nume = parts[0].substring(parts[0].indexOf('=') + 1);
            String adresa = parts[1].substring(parts[1].indexOf('=') + 1);
            String telefon = parts[2].substring(parts[2].indexOf('=') + 1, parts[2].length() - 1);
            return new Filiala(nume, adresa, telefon);
        }
        return null;
    }

    @FXML
    private void enableEditing() {
        filialaTableView.setEditable(true);
    }

    @FXML
    private void enableDelete() {
        System.out.println("Delete button clicked");
        deleteControls.setVisible(true);
    }

    @FXML
    private void handleDeleteSubmit() {
        String numeToDelete = numeTextField.getText().trim();
        if (!numeToDelete.isEmpty()) {
            deleteFilialaByNume(numeToDelete);
            loadFilialaDataFromFile();
            deleteControls.setVisible(false);
            numeTextField.clear();
        }
    }

    private void deleteFilialaByNume(String nume) {
        File inputFile = new File("filiale.txt");
        File tempFile = new File("filiale_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            List<String> linesToKeep = reader.lines()
                    .filter(line -> !line.contains("nume=" + nume + ","))
                    .collect(Collectors.toList());

            for (String line : linesToKeep) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }
}
