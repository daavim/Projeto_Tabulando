package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.Application;
import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.entities.Jogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.admin.projeto_tabulando.utils.Alerta;
import javafx.stage.Stage;


public class BuscarJogoController implements Initializable {
    @FXML
    private ComboBox<String> jogosLista;
    @FXML
    private TextField categoria;
    @FXML
    private TextField maxJogadores;
    @FXML
    private ImageView foto;

    @FXML
    public static Stage stage;

    Jogo jogo;

    File file;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Jogo> jogos = DaoFactory.createJogoDao().procurarTodosDisponivel();

        if (jogos == null || jogos.isEmpty()) {
            Alerta.mostrarAlerta(null, null, "Nenhum jogo disponível.", Alert.AlertType.WARNING);
            return;
        }

        // Mapeia apenas os nomes dos jogos e adiciona ao ComboBox
        ObservableList<String> obs = FXCollections.observableArrayList();
        for (Jogo jogo : jogos) {
            obs.add(jogo.getNome());
        }

        jogosLista.setItems(obs);
    }

    @FXML
    public void onFotoClicked(){
        FileChooser fc = new FileChooser();
        file = fc.showOpenDialog(Application.getScene().getWindow());
        if(file!=null){
            foto.setImage(new Image(file.getAbsolutePath()));
        }
    }



    @FXML
    public void onBuscarInformacoesClicked(){  // informações do jogo
        if(jogosLista.getValue() != null){
            jogo = DaoFactory.createJogoDao().procurarPorNome( jogosLista.getValue());
            categoria.setText(jogo.getTipo());
            maxJogadores.setText(String.valueOf(jogo.getMaxJogadores()));
            //Image img = new Image(new ByteArrayInputStream(jogo.getFoto()));
            //foto.setImage(img);
        }
    }

    @FXML
    public void onEntrarClicked() throws IOException {   // Entrar na partida
        stage = Application.newStage("partidas-jogador-view.fxml");
    }

}

