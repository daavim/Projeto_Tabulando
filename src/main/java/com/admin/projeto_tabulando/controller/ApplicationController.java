package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.Application;
import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.entities.Jogador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
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
    public void menuItemBuscarOnClicked() throws IOException {
        stage = Application.newStage("buscar-jogo-view.fxml");
        stage.setResizable(false);
    }

    @FXML
    public void menuItemLoginOnClicked() throws IOException {
        String usuarioJogador = usuario.getText();
        String senhaJogador = senha.getText();
        Jogador jogador = DaoFactory.createAuthDao().autenticarJogador(usuarioJogador, senhaJogador);

        if (jogador != null) {

            if (usuarioJogador.equals("admin")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/admin/projeto_tabulando/administracao-monitor-view.fxml"));
                Parent root = loader.load();

                AdministracaoMonitorController monitorController = loader.getController();
                monitorController.setNomeAdmin(jogador.getNome());

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();

                Stage currentStage = (Stage) usuario.getScene().getWindow();
                currentStage.close();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/admin/projeto_tabulando/login-view.fxml"));
                Parent root = loader.load();

                LoginController loginController = loader.getController();
                loginController.setNomeJogador(jogador.getNome());

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();

                Stage currentStage = (Stage) usuario.getScene().getWindow();
                currentStage.close();
            }

        } else {
            System.out.println("Credenciais inválidas");
        }
    }

    @FXML
    public void menuItemAdministracaoOnClicked() throws IOException {
        stage = Application.newStage("administracao-monitor-view.fxml");
        stage.setResizable(false);
    }

    @FXML
    public void menuItemJogosMonitoresOnClicked() throws IOException {
        stage = Application.newStage("jogos-monitor-view.fxml");
        stage.setResizable(false);
    }

    @FXML
    public void menuItemJogosOnClicked() throws IOException {
        stage = Application.newStage("jogos-jogador-view.fxml");
        stage.setResizable(false);
    }

    @FXML
    public void menuItemSairOnClicked() throws IOException {
        stage = Application.newStage("sair-jogador-view.fxml");
        stage.setResizable(false);
    }

    public static Stage getStage(){
        return stage;
    }

}