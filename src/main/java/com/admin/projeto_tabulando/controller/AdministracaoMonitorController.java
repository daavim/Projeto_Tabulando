package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.Application;
import com.admin.projeto_tabulando.model.dao.DaoFactory;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AdministracaoMonitorController {
    @FXML
    private Button AdicionarNovoJogo;
    @FXML
    private Button RemoverJogo;
    @FXML
    private Button LiberarJogo;
    @FXML
    private Button ExpulsarJogador;
    @FXML
    private Button VerHistorico;
    @FXML
    private Button BotaoSair;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label nomeadm;

    public static Stage stage;

    public void setNomeAdmin(String nomeJogador) {
        nomeadm.setText(nomeJogador); // Define o texto do label
    }

    public void menuItemSairOnClicked(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacao");
        alert.setHeaderText("Você está prestes a sair");
        alert.setContentText("Deseja realmente sair?");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("Saiu com sucesso");
            stage.close();
        }

    }

    public void onAdicionarJogoClicked() throws IOException {
        stage = Application.newStage("adicionar-jogo-view.fxml");
    }

    public void onRemoverJogoClicked() throws IOException {
        stage = Application.newStage("remover-jogos-adm-view.fxml");
    }

    public void onEncerrarJogoClicked() throws IOException {
        stage = Application.newStage("encerrar-jogo-view.fxml");
    }

    public void onExcluirJogadorClicked() throws IOException {
        stage = Application.newStage("excluir-jogador-view.fxml");
    }

    public static Stage getStage(){
        return stage;
    }


}