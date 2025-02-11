package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.Application;
import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.utils.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationController {
    /*
    @FXML
    private MenuItem cadastrar;
    @FXML
    private MenuItem login;
    @FXML
    private MenuItem loginMonitor;
    private MenuItem buscar;
    private MenuItem administracao;
    private MenuItem jogos;
    private MenuItem jogosMonitores;
    private MenuItem sair;

    */

    @FXML
    private TextField usuario;
    @FXML
    private PasswordField senha;

    private static Stage stage;

    @FXML
    public void menuItemCadastrarOnClicked() throws IOException {
        Application.newStage("cadastrar-jogador-view.fxml");
    }


    @FXML
    public void menuItemLoginOnClicked() throws IOException {
        String usuarioJogador = usuario.getText();
        String senhaJogador = senha.getText();
        Jogador jogador = DaoFactory.createAuthDao().autenticarJogador(usuarioJogador, senhaJogador);

        if (jogador != null) {

            if (usuarioJogador.equals("admin")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/admin/projeto_tabulando/principal-monitor-view.fxml"));
                Parent root = loader.load();

                PrincipalMonitorController monitorController = loader.getController();
                monitorController.setNomeAdmin(jogador.getNome());

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();

                Stage currentStage = (Stage) usuario.getScene().getWindow();
                currentStage.close();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/admin/projeto_tabulando/principal-view.fxml"));
                Parent root = loader.load();

                PrincipalController principalController = loader.getController();
                principalController.setNomeJogador(jogador.getNome());

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();

                Stage currentStage = (Stage) usuario.getScene().getWindow();
                currentStage.close();
            }

        } else {
            Alerta.mostrarAlerta("Erro de login", "Credenciais inválidas", "Usuário ou senha incorretos. Tente Novamente", Alert.AlertType.ERROR);
        }
    }

    public static Stage getStage(){
        return stage;
    }

}