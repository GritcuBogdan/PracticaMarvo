package org.example.practicamrmarvo;

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
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShowDepozitController {
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private ImageView homeButton;

    @FXML
    private TableView<Depozit> depozitTableView;

    @FXML
    private TableColumn<Depozit, String> denumireColumn;

    @FXML
    private TableColumn<Depozit, String> tipColumn;

    @FXML
    private TableColumn<Depozit, Double> procentColumn;

    @FXML
    private TableColumn<Depozit, String> valutaColumn;

    @FXML
    private Label denumireLabel;

    @FXML
    private TextField denumireDeleteInput;

    @FXML
    private Button submitButton;

    @FXML
    private Label editAccessLabel;  // Add this line

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
    private void handleAddDepozit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDepozit.fxml"));
            Parent root = loader.load();
            DepozitController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        denumireColumn.setCellValueFactory(new PropertyValueFactory<>("denumire"));
        tipColumn.setCellValueFactory(new PropertyValueFactory<>("tip"));
        procentColumn.setCellValueFactory(new PropertyValueFactory<>("procentAnual"));
        valutaColumn.setCellValueFactory(new PropertyValueFactory<>("valuta"));

        makeColumnsEditable();

        depozitTableView.setEditable(false);

        loadDepoziteFromFile();
    }

    @FXML
    private void enableEditing() {
        depozitTableView.setEditable(true);
        editAccessLabel.setVisible(true);
    }

    @FXML
    private void showDeleteControls() {
        denumireLabel.setVisible(true);
        denumireDeleteInput.setVisible(true);
        submitButton.setVisible(true);
    }

    @FXML
    private void deleteDepozit() {
        String denumireToDelete = denumireDeleteInput.getText();
        if (denumireToDelete == null || denumireToDelete.trim().isEmpty()) {
            System.err.println("Denumire is empty. Cannot delete.");
            return;
        }

        File inputFile = new File("depozite.txt");
        File tempFile = new File("depozite_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("denumire=" + denumireToDelete + ",")) {
                    found = true;  // Skip this line, do not write it to the temp file
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!found) {
                System.err.println("Denumire not found: " + denumireToDelete);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputFile.delete()) {
            if (tempFile.renameTo(inputFile)) {
                System.out.println("Depozit deleted successfully");
                loadDepoziteFromFile();  // Reload the table to reflect the changes
            } else {
                System.err.println("Failed to rename temp file");
            }
        } else {
            System.err.println("Failed to delete original file");
        }

        denumireDeleteInput.clear();
    }

    private void makeColumnsEditable() {
        denumireColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        denumireColumn.setOnEditCommit(event -> {
            Depozit depozit = event.getRowValue();
            String oldValue = event.getOldValue();  // Use the old value for matching
            depozit.setDenumire(event.getNewValue());
            System.out.println("Updated denumire from " + oldValue + " to " + event.getNewValue());
            updateDepozitInFile(oldValue, depozit);  // Pass old value to method
        });

        tipColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tipColumn.setOnEditCommit(event -> {
            Depozit depozit = event.getRowValue();
            String oldValue = depozit.getTip();
            depozit.setTip(event.getNewValue());
            System.out.println("Updated tip from " + oldValue + " to " + event.getNewValue());
            updateDepozitInFile(depozit.getDenumire(), depozit);  // Use denumire for matching
        });

        procentColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        procentColumn.setOnEditCommit(event -> {
            Depozit depozit = event.getRowValue();
            double oldValue = depozit.getProcentAnual();
            depozit.setProcentAnual(event.getNewValue());
            System.out.println("Updated procentAnual from " + oldValue + " to " + event.getNewValue());
            updateDepozitInFile(depozit.getDenumire(), depozit);  // Use denumire for matching
        });

        valutaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valutaColumn.setOnEditCommit(event -> {
            Depozit depozit = event.getRowValue();
            String oldValue = depozit.getValuta();
            depozit.setValuta(event.getNewValue());
            System.out.println("Updated valuta from " + oldValue + " to " + event.getNewValue());
            updateDepozitInFile(depozit.getDenumire(), depozit);  // Use denumire for matching
        });
    }

    private void updateDepozitInFile(String oldDenumire, Depozit updatedDepozit) {
        File inputFile = new File("depozite.txt");
        File tempFile = new File("depozite_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            List<String> lines = reader.lines().collect(Collectors.toList());

            for (String line : lines) {
                if (line.contains("denumire=" + oldDenumire + ",")) {
                    String newLine = "Depozit [tip=" + updatedDepozit.getTip() +
                            ", denumire=" + updatedDepozit.getDenumire() +
                            ", procent=" + updatedDepozit.getProcentAnual() +
                            ", valuta=" + updatedDepozit.getValuta() + "]";
                    System.out.println("Writing new line: " + newLine);
                    writer.write(newLine);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputFile.delete()) {
            if (tempFile.renameTo(inputFile)) {
                System.out.println("File updated successfully");
            } else {
                System.err.println("Failed to rename temp file");
            }
        } else {
            System.err.println("Failed to delete original file");
        }
    }

    private void loadDepoziteFromFile() {
        List<Depozit> depozite = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("depozite.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Depozit depozit = parseDepozit(line);
                if (depozit != null) {
                    depozite.add(depozit);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObservableList<Depozit> depozitList = FXCollections.observableArrayList(depozite);
        depozitTableView.setItems(depozitList);
    }

    private Depozit parseDepozit(String line) {
        String[] tokens = line.substring(line.indexOf('[') + 1, line.indexOf(']')).split(", ");
        String denumire = "";
        String tip = "";
        double procentAnual = 0; // Default value
        String valuta = "";

        for (String token : tokens) {
            String[] keyValue = token.split("=");
            switch (keyValue[0].trim()) {
                case "denumire":
                    denumire = keyValue[1];
                    break;
                case "tip":
                    tip = keyValue[1];
                    break;
                case "procent":
                    try {
                        procentAnual = Double.parseDouble(keyValue[1]);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid procent value: " + keyValue[1]);
                        return null; // Skip this entry or handle error as needed
                    }
                    break;
                case "valuta":
                    valuta = keyValue[1];
                    break;
                default:
                    break;
            }
        }

        return new Depozit(tip, denumire, procentAnual, valuta);
    }
}
