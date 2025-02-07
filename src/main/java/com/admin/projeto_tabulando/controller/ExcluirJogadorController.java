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

public class ExcluirJogadorController implements Initializable {

    @FXML
    private ComboBox<String> listaJogadores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Jogador> lista = DaoFactory.createJogadorDao().procurarTodos();

        if (lista.isEmpty()) {
            Alerta.mostrarAlerta(null, null, "Nenhum jogador cadastrado.", Alert.AlertType.WARNING);
            return;
        }

        ObservableList<String> obs = FXCollections.observableArrayList();
        for (Jogador jogador : lista) {
            obs.add(jogador.getNome());
        }

        listaJogadores.setItems(obs);
    }

    @FXML
    public void onExcluirJogadorClicked(){  // informações do jogo
        if(listaJogadores.getValue() != null){
            Jogador jogador = DaoFactory.createJogadorDao().procurarPorNome(listaJogadores.getValue());
            DaoFactory.createMonitorDao().removerJogador(jogador.getId());
            Alerta.mostrarAlerta("Exclusão de jogador", null, "Jogador excluido com sucesso!",Alert.AlertType.INFORMATION);
        }
    }

}
