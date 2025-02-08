package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.Application;
import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;
import com.admin.projeto_tabulando.utils.Alerta;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginController {
    @FXML
    private Button BotaoSair;
    @FXML
    private AnchorPane scenePane;

    @FXML
    private Label nome;

    public void setNomeJogador(String nomeJogador) {
        nome.setText(nomeJogador); // Define o texto do label
    }

    private static Stage stage;

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
        stage = Application.newStage("buscar-jogo-view.fxml");
        stage.setResizable(false);
    }

    public static Stage getStage(){
        return stage;
    }
}