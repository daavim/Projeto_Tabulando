package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.Application;
import com.admin.projeto_tabulando.model.dao.DaoFactory;
import java.io.IOException;
import java.util.List;

import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;
import com.admin.projeto_tabulando.utils.Alerta;
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
        stage.setResizable(false);
    }

    public void onRemoverJogoClicked() throws IOException {
        List<Jogo> lista = DaoFactory.createJogoDao().procurarTodos();
        if (lista.isEmpty()) {
            Alerta.mostrarAlerta(null, null, "Nenhum jogo cadastrado.", Alert.AlertType.WARNING);
            return;
        }
        stage = Application.newStage("remover-jogos-adm-view.fxml");
        stage.setResizable(false);
    }

    public void onEncerrarJogoClicked() throws IOException {
        List<Jogo> lista = DaoFactory.createJogoDao().procurarTodosJogando();
        if (lista.isEmpty()) {
            Alerta.mostrarAlerta(null, null, "Nenhum jogo sendo jogado.", Alert.AlertType.WARNING);
            return;
        }
        stage = Application.newStage("encerrar-jogo-view.fxml");
        stage.setResizable(false);
    }

    public void onExcluirJogadorClicked() throws IOException {
        List<Jogador> lista = DaoFactory.createJogadorDao().procurarTodos();
        if (lista.isEmpty()) {
            Alerta.mostrarAlerta(null, null, "Nenhum jogador cadastrado.", Alert.AlertType.WARNING);
            return;
        }
        stage = Application.newStage("excluir-jogador-view.fxml");
        stage.setResizable(false);
    }

    public static Stage getStage(){
        return stage;
    }


}