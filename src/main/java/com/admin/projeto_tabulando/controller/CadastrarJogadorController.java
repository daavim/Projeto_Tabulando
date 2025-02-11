package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.Application;
import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.utils.Alerta;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class CadastrarJogadorController {
    @FXML
    private TextField nome;

    @FXML
    private TextField usuario;

    @FXML
    private TextField senha;


    @FXML
    public void salvarOnClicked(){
        Jogador jogador = new Jogador(nome.getText());

        String usuarioJogador = usuario.getText();
        String senhaJogador = senha.getText();

        if (nome.getText().trim().isEmpty() || usuario.getText().trim().isEmpty() || senha.getText().trim().isEmpty()) {
            Alerta.mostrarAlerta("Erro", "Campos inválidos", "Preencha todos os campos!", Alert.AlertType.ERROR);
            return;
        }
        if (usuarioJogador.length() < 3) {
            Alerta.mostrarAlerta("Erro", "Usuário inválido", "O nome de usuário deve ter pelo menos 3 caracteres!", Alert.AlertType.ERROR);
            return;
        }
        if (DaoFactory.createJogadorDao().usuarioExiste(usuarioJogador)) {
            Alerta.mostrarAlerta("Erro", "Usuário já cadastrado", "Este nome de usuário já está em uso. Escolha outro!", Alert.AlertType.ERROR);
            return;
        }

        if (senhaJogador.length() < 6) {
            Alerta.mostrarAlerta("Erro", "Senha inválida", "A senha deve ter pelo menos 6 caracteres!", Alert.AlertType.ERROR);
            return;
        }

        DaoFactory.createJogadorDao().registrarJogador(usuarioJogador, senhaJogador, jogador);
        Alerta.mostrarAlerta("Cadastrado", "Usuario cadastrado com sucesso!", null, Alert.AlertType.INFORMATION);
    }

}