package org.example.practicamrmarvo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowClientiController {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private Label idnpLabel;

    @FXML
    private Label codFiscalLabel;

    @FXML
    private TextField idnpInput;

    @FXML
    private TextField codFiscalInput;

    @FXML
    private Button submit1;

    @FXML
    private Button submit2;


    @FXML
    private ImageView delete1;

    @FXML
    private ImageView delete2;

    @FXML
    private TableColumn<ClientFizic, String> numeColumn;

    @FXML
    private TableColumn<ClientFizic, String> codPersonalColumn;

    @FXML
    private TableColumn<ClientFizic, String> adresaColumn;

    @FXML
    private TableColumn<ClientFizic, String> telefonColumn;

    @FXML
    private TableColumn<ClientFizic, Integer> numarDepoziteColumn;

    @FXML
    private TableColumn<ClientJuridic, String> codFiscalColumn;

    @FXML
    private TableColumn<ClientJuridic, String> denumireColumn;

    @FXML
    private TableColumn<ClientJuridic, String> tipProprietateColumn;

    @FXML
    private TableColumn<ClientJuridic, String> adresaJuridicColumn;

    @FXML
    private TableColumn<ClientJuridic, String> telefonJuridicColumn;

    @FXML
    private TableColumn<ClientJuridic, String> numeAdministratorColumn;

    @FXML
    private TableColumn<ClientJuridic, String> persoanaContactColumn;

    @FXML
    private RadioButton clientFizicRadioButton;

    @FXML
    private RadioButton clientJuridicRadioButton;

    @FXML
    private AnchorPane clientFizicAnchorPane;

    @FXML
    private AnchorPane clientJuridicAnchorPane;

    @FXML
    private TableView<ClientFizic> clientFizicTableView;

    @FXML
    private TableView<ClientJuridic> clientJuridicTableView;

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

    private ToggleGroup toggleGroup;

    public void initialize() {
        toggleGroup = new ToggleGroup();
        clientFizicRadioButton.setToggleGroup(toggleGroup);
        clientJuridicRadioButton.setToggleGroup(toggleGroup);

        // Initially hide both client panes
        clientFizicAnchorPane.setVisible(false);
        clientJuridicAnchorPane.setVisible(false);

        // Select no radio button by default
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                clientFizicAnchorPane.setVisible(false);
                clientJuridicAnchorPane.setVisible(false);
            }
        });

        // Bind columns to JavaFX properties
        numeColumn.setCellValueFactory(cellData -> cellData.getValue().numeProperty());
        codPersonalColumn.setCellValueFactory(cellData -> cellData.getValue().codPersonalProperty());
        adresaColumn.setCellValueFactory(cellData -> cellData.getValue().adresaProperty());
        telefonColumn.setCellValueFactory(cellData -> cellData.getValue().telefonProperty());
        numarDepoziteColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumarDepozite()).asObject());

        codFiscalColumn.setCellValueFactory(cellData -> cellData.getValue().codFiscalProperty());
        denumireColumn.setCellValueFactory(cellData -> cellData.getValue().denumireProperty());
        tipProprietateColumn.setCellValueFactory(cellData -> cellData.getValue().tipProprietateProperty());
        adresaJuridicColumn.setCellValueFactory(cellData -> cellData.getValue().adresaProperty());
        telefonJuridicColumn.setCellValueFactory(cellData -> cellData.getValue().telefonProperty());
        numeAdministratorColumn.setCellValueFactory(cellData -> cellData.getValue().numeAdministratorProperty());
        persoanaContactColumn.setCellValueFactory(cellData -> cellData.getValue().persoanaContactProperty());

        // Load data from file into TableViews
        loadDataFromFile();
    }

    @FXML
    private void handleClientTypeChange() {
        if (clientFizicRadioButton.isSelected()) {
            clientFizicAnchorPane.setVisible(true);
            delete1.setVisible(true);
            delete2.setVisible(false);
            codFiscalLabel.setVisible(false);
            codFiscalInput.setVisible(false);
            submit2.setVisible(false);
            clientJuridicAnchorPane.setVisible(false);
        } else if (clientJuridicRadioButton.isSelected()) {
            clientFizicAnchorPane.setVisible(false);
            clientJuridicAnchorPane.setVisible(true);
            delete1.setVisible(false);
            delete2.setVisible(true);
            idnpLabel.setVisible(false);
            idnpInput.setVisible(false);
            submit1.setVisible(false);
        }
    }

    public void loadDataFromFile() {
        ObservableList<ClientFizic> clientFizicList = FXCollections.observableArrayList();
        ObservableList<ClientJuridic> clientJuridicList = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader("clienti.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("ClientFizic")) {
                    ClientFizic clientFizic = parseClientFizic(line);
                    if (clientFizic != null) {
                        clientFizicList.add(clientFizic);
                    }
                } else if (line.startsWith("ClientJuridic")) {
                    ClientJuridic clientJuridic = parseClientJuridic(line);
                    if (clientJuridic != null) {
                        clientJuridicList.add(clientJuridic);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Bind data to TableView
        clientFizicTableView.setItems(clientFizicList);
        clientJuridicTableView.setItems(clientJuridicList);
    }

    private ClientFizic parseClientFizic(String line) {
        // Implement parsing logic for ClientFizic entry
        // Example parsing logic (you may need to adjust this based on your actual data structure)
        String[] tokens = line.substring(line.indexOf('[') + 1, line.indexOf(']')).split(", ");
        String nume = "";
        String codPersonal = "";
        String adresa = "";
        String telefon = "";
        int numarDepozite = 0;

        for (String token : tokens) {
            String[] keyValue = token.split("=");
            switch (keyValue[0].trim()) {
                case "nume":
                    nume = keyValue[1];
                    break;
                case "codPersonal":
                    codPersonal = keyValue[1];
                    break;
                case "adresa":
                    adresa = keyValue[1];
                    break;
                case "telefon":
                    telefon = keyValue[1];
                    break;
                case "numarDepozite":
                    numarDepozite = Integer.parseInt(keyValue[1]);
                    break;
                default:
                    // Handle unknown key
                    break;
            }
        }

        return new ClientFizic(nume, codPersonal, adresa, telefon, numarDepozite);
    }

    private ClientJuridic parseClientJuridic(String line) {
        // Implement parsing logic for ClientJuridic entry
        // Example parsing logic (you may need to adjust this based on your actual data structure)
        String[] tokens = line.substring(line.indexOf('[') + 1, line.indexOf(']')).split(", ");
        String codFiscal = "";
        String denumire = "";
        String tipProprietate = "";
        String adresa = "";
        String telefon = "";
        String numeAdministrator = "";
        String persoanaContact = "";

        for (String token : tokens) {
            String[] keyValue = token.split("=");
            switch (keyValue[0].trim()) {
                case "codFiscal":
                    codFiscal = keyValue[1];
                    break;
                case "denumire":
                    denumire = keyValue[1];
                    break;
                case "tipProprietate":
                    tipProprietate = keyValue[1];
                    break;
                case "adresa":
                    adresa = keyValue[1];
                    break;
                case "telefon":
                    telefon = keyValue[1];
                    break;
                case "numeAdministrator":
                    numeAdministrator = keyValue[1];
                    break;
                case "persoanaContact":
                    persoanaContact = keyValue[1];
                    break;
                default:
                    // Handle unknown key
                    break;
            }
        }

        return new ClientJuridic(codFiscal, denumire, tipProprietate, adresa, telefon, numeAdministrator, persoanaContact);
    }

    @FXML
    private void enableDeleteFizic() {
        idnpLabel.setVisible(true);
        idnpInput.setVisible(true);
        submit1.setVisible(true);
    }

    @FXML
    private void enableDeleteJuridic() {
        codFiscalLabel.setVisible(true);
        codFiscalInput.setVisible(true);
        submit2.setVisible(true);
    }

}
