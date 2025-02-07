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

public class RemoverJogosAdmController implements Initializable {

    @FXML
    private ComboBox<String> listaJogo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Jogo> lista = DaoFactory.createJogoDao().procurarTodos();

        if (lista.isEmpty()) {
            Alerta.mostrarAlerta(null, null, "Nenhum jogo cadastrado.", Alert.AlertType.WARNING);
            return;
        }

        ObservableList<String> obs = FXCollections.observableArrayList();
        for (Jogo jogo : lista) {
            obs.add(jogo.getNome());
        }

        listaJogo.setItems(obs);
    }


    @FXML
    public void onExcluirJogoClicked() {  // informações do jogo
        if (listaJogo.getValue() != null) {
            Jogo jogo = DaoFactory.createJogoDao().procurarPorNome(listaJogo.getValue());
            DaoFactory.createMonitorDao().removerJogo(jogo);
            Alerta.mostrarAlerta("Exclusão de jogo", null, "Jogo excluido com sucesso!", Alert.AlertType.INFORMATION);
        }
    }
}
