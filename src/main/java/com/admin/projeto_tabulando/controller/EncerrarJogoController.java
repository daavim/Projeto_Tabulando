package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;
import com.admin.projeto_tabulando.utils.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EncerrarJogoController implements Initializable {
    @FXML
    private ComboBox<String> listaJogos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Jogo> lista = DaoFactory.createJogoDao().procurarTodosJogando();

        if (lista.isEmpty()) {
            Alerta.mostrarAlerta(null, null, "Nenhum jogo sendo jogado.", Alert.AlertType.WARNING);
            return;
        }

        ObservableList<String> obs = FXCollections.observableArrayList();
        for (Jogo jogo : lista) {
            obs.add(jogo.getNome());
        }

        listaJogos.setItems(obs);
    }

}
