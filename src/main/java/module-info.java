module com.admin.projeto_tabulando {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.admin.projeto_tabulando to javafx.fxml;
    exports com.admin.projeto_tabulando;
    exports com.admin.projeto_tabulando.controller;
    opens com.admin.projeto_tabulando.controller to javafx.fxml;
}