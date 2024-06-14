module org.example.practicamrmarvo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.practicamrmarvo to javafx.fxml;
    exports org.example.practicamrmarvo;
}