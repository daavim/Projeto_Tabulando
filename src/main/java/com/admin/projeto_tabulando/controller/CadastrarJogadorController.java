package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.Application;
import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.entities.Jogador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
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
    private ImageView foto;

    private File file;

    @FXML
    public void fotoOnClicked(){
        FileChooser fc = new FileChooser();
        file = fc.showOpenDialog(Application.getScene().getWindow());
        if(file!=null){
            foto.setImage(new Image(file.getAbsolutePath()));
        }
    }

    @FXML
    public void salvarOnClicked(){
        Jogador jogador = new Jogador(nome.getText());

        String usuarioJogador = usuario.getText();
        String senhaJogador = senha.getText();

        DaoFactory.createJogadorDao().registrarJogador(usuarioJogador, senhaJogador, jogador);
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Informação!");
        alerta.setContentText((""));
    }

}