package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.entities.Jogo;
import com.admin.projeto_tabulando.utils.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PrincipalController {
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label nome;


    public void setNomeJogador(String nomeJogador) {
        nome.setText(nomeJogador);
    }


    public void menuItemSairOnClicked(ActionEvent event) {
        Optional<ButtonType> resultado = Alerta.mostrarAlerta("Confirmacao", "Você está prestes a sair", "Deseja realmente sair?", Alert.AlertType.CONFIRMATION);

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Stage stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("Saiu com sucesso");
            stage.close();
        }
    }


    public void menuBuscarJogoOnClicked() throws IOException {
        List<Jogo> jogos = DaoFactory.createJogoDao().procurarTodosDisponivel();

        if (jogos.isEmpty()) {
            Alerta.mostrarAlerta(null, null, "Nenhum jogo disponível.", Alert.AlertType.WARNING);
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/admin/projeto_tabulando/entrar-jogo-view.fxml"));
        Parent root = loader.load();

        EntrarJogoController entrarJogoController = loader.getController();
        entrarJogoController.setNomeJogador(nome.getText());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}