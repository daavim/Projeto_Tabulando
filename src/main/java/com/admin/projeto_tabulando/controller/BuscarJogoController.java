package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.Application;
import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public static Stage stage;

    Jogo jogo;
    String nome;

    public void setNomeJogador(String nomeJogador) {
        nome = nomeJogador;
    }


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
    public void onBuscarInformacoesClicked(){  // informações do jogo
        if(jogosLista.getValue() == null){
            Alerta.mostrarAlerta("Jogo não selecionado.", null, "Selecione um jogo!", Alert.AlertType.INFORMATION);
            return;
        }
        jogo = DaoFactory.createJogoDao().procurarPorNome( jogosLista.getValue());
        categoria.setText(jogo.getTipo());
        maxJogadores.setText(String.valueOf(jogo.getMaxJogadores()));
    }

    @FXML
    public void onEntrarClicked() throws IOException {
        if(jogosLista.getValue() == null){
            Alerta.mostrarAlerta("Dados inválidos", null, "Insira o jogo que deseja jogar!", Alert.AlertType.INFORMATION);
            return;
        }

        Jogo jogo = DaoFactory.createJogoDao().procurarPorNome(jogosLista.getValue());
        Jogador jogador = DaoFactory.createJogadorDao().procurarPorNome(nome);

        DaoFactory.createJogadorDao().entrarNoJogo(jogador, jogo);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/admin/projeto_tabulando/partidas-jogador-view.fxml"));
        Parent root = loader.load();

        PartidasJogadorController partidaController = loader.getController();
        partidaController.setNomeJogo(jogosLista.getValue());
        partidaController.setUsuario(jogador);

        Stage janelaAtual = (Stage) jogosLista.getScene().getWindow();
        janelaAtual.close();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

    }

}

