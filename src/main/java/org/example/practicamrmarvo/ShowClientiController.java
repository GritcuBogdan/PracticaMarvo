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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
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
    private ImageView addButton;

    @FXML
    private ImageView updateButton;


    @FXML
    private void handleAddClient() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddClientFizic.fxml"));
            Parent root = loader.load();
            ClientController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        clientFizicTableView.setEditable(false);
        clientJuridicTableView.setEditable(false);


        // Load data from file into TableViews
        loadDataFromFile();
    }

    @FXML
    private void handleUpdateTables() {
        // Enable editing for both tables
        clientFizicTableView.setEditable(true);
        clientJuridicTableView.setEditable(true);

        // Set up cell factories for editable columns
        setupEditableColumns();
    }

    private void setupEditableColumns() {
        // ClientFizic columns
        numeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        codPersonalColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        adresaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telefonColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        numarDepoziteColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        // ClientJuridic columns
        codFiscalColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        denumireColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tipProprietateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        adresaJuridicColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telefonJuridicColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        numeAdministratorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        persoanaContactColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Add commit edit event handlers to save data to file
        numeColumn.setOnEditCommit(event -> {
            event.getRowValue().setNume(event.getNewValue());
            saveDataToFile();
        });
        codPersonalColumn.setOnEditCommit(event -> {
            event.getRowValue().setCodPersonal(event.getNewValue());
            saveDataToFile();
        });
        adresaColumn.setOnEditCommit(event -> {
            event.getRowValue().setAdresa(event.getNewValue());
            saveDataToFile();
        });
        telefonColumn.setOnEditCommit(event -> {
            event.getRowValue().setTelefon(event.getNewValue());
            saveDataToFile();
        });
        numarDepoziteColumn.setOnEditCommit(event -> {
            event.getRowValue().setNumarDepozite(event.getNewValue());
            saveDataToFile();
        });

        codFiscalColumn.setOnEditCommit(event -> {
            event.getRowValue().setCodFiscal(event.getNewValue());
            saveDataToFile();
        });
        denumireColumn.setOnEditCommit(event -> {
            event.getRowValue().setDenumire(event.getNewValue());
            saveDataToFile();
        });
        tipProprietateColumn.setOnEditCommit(event -> {
            event.getRowValue().setTipProprietate(event.getNewValue());
            saveDataToFile();
        });
        adresaJuridicColumn.setOnEditCommit(event -> {
            event.getRowValue().setAdresa(event.getNewValue());
            saveDataToFile();
        });
        telefonJuridicColumn.setOnEditCommit(event -> {
            event.getRowValue().setTelefon(event.getNewValue());
            saveDataToFile();
        });
        numeAdministratorColumn.setOnEditCommit(event -> {
            event.getRowValue().setNumeAdministrator(event.getNewValue());
            saveDataToFile();
        });
        persoanaContactColumn.setOnEditCommit(event -> {
            event.getRowValue().setPersoanaContact(event.getNewValue());
            saveDataToFile();
        });
    }


    private void saveDataToFile() {
        List<String> lines = new ArrayList<>();

        // Collect data from clientFizicTableView
        for (ClientFizic client : clientFizicTableView.getItems()) {
            lines.add(formatClientFizic(client));
        }

        // Collect data from clientJuridicTableView
        for (ClientJuridic client : clientJuridicTableView.getItems()) {
            lines.add(formatClientJuridic(client));
        }

        // Write data to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clienti.txt"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatClientFizic(ClientFizic client) {
        return String.format("ClientFizic [nume=%s, codPersonal=%s, adresa=%s, telefon=%s, numarDepozite=%d]",
                client.getNume(), client.getCodPersonal(), client.getAdresa(), client.getTelefon(), client.getNumarDepozite());
    }

    private String formatClientJuridic(ClientJuridic client) {
        return String.format("ClientJuridic [codFiscal=%s, denumire=%s, tipProprietate=%s, adresa=%s, telefon=%s, numeAdministrator=%s, persoanaContact=%s]",
                client.getCodFiscal(), client.getDenumire(), client.getTipProprietate(), client.getAdresa(), client.getTelefon(), client.getNumeAdministrator(), client.getPersoanaContact());
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

    @FXML
    private void handleDeleteClientJuridic() {
        String codFiscalToDelete = codFiscalInput.getText();

        if (codFiscalToDelete == null || codFiscalToDelete.isEmpty()) {
            // Show an error message or handle empty input
            return;
        }

        List<String> remainingLines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("clienti.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ClientJuridic") && line.contains("codFiscal=" + codFiscalToDelete)) {
                    found = true;
                    continue; // Skip the line to be deleted
                }
                remainingLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("clienti.txt"))) {
                for (String line : remainingLines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Update the TableView
            loadClientJuridicData();
        } else {
            // Show an error message or handle case where the codFiscal is not found
        }
    }

    private void loadClientJuridicData() {
        ObservableList<ClientJuridic> clientJuridicList = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader("clienti.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ClientJuridic")) {
                    ClientJuridic clientJuridic = parseClientJuridic(line);
                    if (clientJuridic != null) {
                        clientJuridicList.add(clientJuridic);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        clientJuridicTableView.setItems(clientJuridicList);
    }



    @FXML
    private void handleDeleteClientFizic() {
        String idnpToDelete = idnpInput.getText();

        if (idnpToDelete == null || idnpToDelete.isEmpty()) {
            // Show an error message or handle empty input
            return;
        }

        List<String> remainingLines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("clienti.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ClientFizic") && line.contains("codPersonal=" + idnpToDelete)) {
                    found = true;
                    continue; // Skip the line to be deleted
                }
                remainingLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("clienti.txt"))) {
                for (String line : remainingLines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Update the TableView
            loadClientFizicData();
        } else {
            // Show an error message or handle case where the IDNP is not found
        }
    }

    private void loadClientFizicData() {
        ObservableList<ClientFizic> clientFizicList = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader("clienti.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ClientFizic")) {
                    ClientFizic clientFizic = parseClientFizic(line);
                    if (clientFizic != null) {
                        clientFizicList.add(clientFizic);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        clientFizicTableView.setItems(clientFizicList);
    }



    private ClientFizic parseClientFizic(String line) {
        // Remove square brackets and split by commas
        String data = line.substring(line.indexOf('[') + 1, line.indexOf(']')).trim();
        String[] tokens = data.split(", ");

        // Initialize variables to store parsed values
        String nume = null;
        String codPersonal = null;
        String adresa = null;
        String telefon = null;
        int numarDepozite = 0;

        // Process each token to extract key-value pairs
        for (String token : tokens) {
            String[] keyValue = token.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                switch (key) {
                    case "nume":
                        nume = value;
                        break;
                    case "codPersonal":
                        codPersonal = value;
                        break;
                    case "adresa":
                        adresa = value;
                        break;
                    case "telefon":
                        telefon = value;
                        break;
                    case "numarDepozite":
                        try {
                            numarDepozite = Integer.parseInt(value);
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing numarDepozite: " + e.getMessage());
                        }
                        break;
                    default:
                        // Handle unexpected keys or log them if necessary
                        System.err.println("Unexpected key: " + key);
                        break;
                }
            } else {
                // Handle cases where the token doesn't split into key-value pairs correctly
                System.err.println("Unexpected token format: " + token);
            }
        }

        // Create and return a new ClientFizic object
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
