package com.admin.projeto_tabulando;

import com.admin.projeto_tabulando.controller.ApplicationController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application {
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("application-view.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tabulando");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            sair(stage);
        });

    }

    public static Scene getScene(){
        return scene;
    }

    public static Stage newStage(String url) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(url));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        return stage;
    }

    public void sair(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacao");
        alert.setHeaderText("Você está prestes a sair");
        alert.setContentText("Deseja realmente sair?");

        if(alert.showAndWait().get() == ButtonType.OK){
            System.out.println("Saiu com sucesso");
            stage.close();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}