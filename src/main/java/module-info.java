module org.example.practicamrmarvo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;


    opens org.example.practicamrmarvo to javafx.fxml,javafx.base;
    exports org.example.practicamrmarvo;
}