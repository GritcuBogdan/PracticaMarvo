package org.example.practicamrmarvo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShowContracteController {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private ImageView homeButton;

    @FXML
    private ImageView addContracte;

    @FXML
    private ImageView deleteContracte;

    @FXML
    private ImageView updateContracte;

    @FXML
    private TableView<Contract> contractTableView;

    @FXML
    private TableColumn<Contract, String> contractColumn;

    @FXML
    private TableColumn<Contract, LocalDate> dataColumn;

    @FXML
    private TableColumn<Contract, String> clientColumn; // Updated to String type

    @FXML
    private TableColumn<Contract, String> depozitColumn; // Updated to String type

    @FXML
    private TableColumn<Contract, Double> sumaColumn;

    @FXML
    private TableColumn<Contract, Integer> termenColumn;

    @FXML
    private TableColumn<Contract, Double> procentColumn;

    @FXML
    private Label denumireLabel;

    @FXML
    private TextField denumireDeleteInput;

    @FXML
    private Button submitButton;

    @FXML
    private Label editAccessLabel;



    @FXML
    private void backToHome() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StartForm.fxml"));
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddDepozit() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddContract.fxml"));
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        // Initialize table columns with property value factories
        contractColumn.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        depozitColumn.setCellValueFactory(new PropertyValueFactory<>("depozit"));
        sumaColumn.setCellValueFactory(new PropertyValueFactory<>("sumaTotala"));
        termenColumn.setCellValueFactory(new PropertyValueFactory<>("termenPastrare"));
        procentColumn.setCellValueFactory(new PropertyValueFactory<>("procentDobanda"));

        makeColumnsEditable(false);

        contractTableView.setEditable(false);

        loadDataFromFile();


    }


    @FXML
    private void showDeleteControls() {
        // Show delete controls
        denumireLabel.setVisible(true);
        denumireDeleteInput.setVisible(true);
        submitButton.setVisible(true);
    }

    private void loadDataFromFile() {
        List<Contract> contracts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("contracte.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contract contract = parseContract(line);
                if (contract != null) {
                    contracts.add(contract);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        contractTableView.getItems().addAll(contracts);
    }

    private Contract parseContract(String line) {
        String[] parts = line.split(", ");
        if (parts.length != 7) {
            System.out.println("Invalid line format: " + line);
            return null;
        }

        String numarContract = getValueFromLine(parts[0]);
        String data = getValueFromLine(parts[1]);
        String client = getValueFromLine(parts[2]);
        String depozit = getValueFromLine(parts[3]);
        double sumaTotala = parseDoubleFromLine(getValueFromLine(parts[4]));
        int termenPastrare = parseIntFromLine(getValueFromLine(parts[5]));
        double procentDobanda = parseDoubleFromLine(getValueFromLine(parts[6]));

        LocalDate date = LocalDate.parse(data, DateTimeFormatter.ISO_DATE);

        return new Contract(numarContract, date, client, depozit, sumaTotala, termenPastrare, procentDobanda);
    }

    private String getValueFromLine(String part) {
        return part.substring(part.indexOf("=") + 1).trim();
    }

    private int parseIntFromLine(String value) {
        try {
            return Integer.parseInt(value.replace(",", ""));
        } catch (NumberFormatException e) {
            System.out.println("Error parsing integer from line: " + value);
            return 0;
        }
    }

    private double parseDoubleFromLine(String value) {
        try {
            return Double.parseDouble(value.replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Error parsing double from line: " + value);
            return 0;
        }
    }

    private void makeColumnsEditable(boolean editable) {
        dataColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        dataColumn.setEditable(editable);
        dataColumn.setOnEditCommit(event -> {
            Contract contract = event.getRowValue();
            contract.setData(event.getNewValue());
            saveDataToFile();
        });

        clientColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        clientColumn.setEditable(editable);
        clientColumn.setOnEditCommit(event -> {
            Contract contract = event.getRowValue();
            contract.setClient(event.getNewValue());
            saveDataToFile();
        });

        depozitColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        depozitColumn.setEditable(editable);
        depozitColumn.setOnEditCommit(event -> {
            Contract contract = event.getRowValue();
            contract.setDepozit(event.getNewValue());
            saveDataToFile();
        });

        sumaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        sumaColumn.setEditable(editable);
        sumaColumn.setOnEditCommit(event -> {
            Contract contract = event.getRowValue();
            contract.setSumaTotala(event.getNewValue());
            saveDataToFile();
        });

        termenColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        termenColumn.setEditable(editable);
        termenColumn.setOnEditCommit(event -> {
            Contract contract = event.getRowValue();
            contract.setTermenPastrare(event.getNewValue());
            saveDataToFile();
        });

        procentColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        procentColumn.setEditable(editable);
        procentColumn.setOnEditCommit(event -> {
            Contract contract = event.getRowValue();
            contract.setProcentDobanda(event.getNewValue());
            saveDataToFile();
        });
    }




    @FXML
    private void enableEditing() {
        // Enable editing functionality
        contractTableView.setEditable(true);
        makeColumnsEditable(true);
        editAccessLabel.setVisible(true);
    }

    private void saveDataToFile() {
        List<Contract> contracts = contractTableView.getItems();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracte.txt"))) {
            for (Contract contract : contracts) {
                String entry = String.format("numarContract=%s, data=%s, client=%s, depozit=%s, sumaTotala=%.2f, termen=%d, procentDobanda=%.2f",
                        contract.getContractNumber(), contract.getData(), contract.getClient(), contract.getDepozit(), contract.getSumaTotala(), contract.getTermenPastrare(), contract.getProcentDobanda());
                writer.write(entry);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteContractByNumber() {
        String numberToDelete = denumireDeleteInput.getText().trim();

        // Read current contracts from the file
        List<Contract> contracts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("contracte.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contract contract = parseContract(line);
                if (contract != null) {
                    contracts.add(contract);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Filter out the contract to delete based on Nr.Contract
        contracts = contracts.stream()
                .filter(contract -> !contract.getContractNumber().equals(numberToDelete))
                .collect(Collectors.toList());

        // Update the file with the filtered contracts
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracte.txt"))) {
            for (Contract contract : contracts) {
                String entry = String.format("numarContract=%s, data=%s, client=%s, depozit=%s, sumaTotala=%.2f, termen=%d, procentDobanda=%.2f",
                        contract.getContractNumber(), contract.getData(), contract.getClient(), contract.getDepozit(), contract.getSumaTotala(), contract.getTermenPastrare(), contract.getProcentDobanda());
                writer.write(entry);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear the input and update the table view
        denumireDeleteInput.clear();
        contractTableView.getItems().clear();
        contractTableView.getItems().addAll(contracts);
    }


    private void makeColumnsEditable() {
        // Implement logic to make specific columns editable if needed
    }
}
