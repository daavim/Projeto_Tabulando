package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.Application;
import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;
import com.admin.projeto_tabulando.utils.Alerta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PartidasJogadorController implements Initializable {

    @FXML
    private Label nomeJogo;
    @FXML
    private TableView<Jogador> listaJogadores;

    @FXML
    private Pane scenePane;
    @FXML
    private TableColumn<Jogador, String> colunaNome;

    Jogador usuario;

    public void setUsuario(Jogador jogador) {
        usuario= jogador;
    }

    public void setNomeJogo(String nome) {
        nomeJogo.setText(nome);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Jogo jogo = DaoFactory.createJogoDao().procurarPorNome(nomeJogo.getText());
        if (jogo == null){
            Alerta.mostrarAlerta("Dados inválidos",null,"Jogador não encontrado!", Alert.AlertType.INFORMATION);
            return;
        }
        List<Jogador> jogadores = DaoFactory.createJogadorDao().procurarTodosJogando(jogo.getId());
        ObservableList<Jogador> obs = FXCollections.observableArrayList(jogadores);
        listaJogadores.setItems(obs);

        colunaNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
    }


    @FXML
    public void onSairPartidaClicked(){
        Jogador jogador = DaoFactory.createJogadorDao().procurarPorId(usuario.getId());

        Optional<ButtonType> resultado = Alerta.mostrarAlerta("Confirmação", null, "Deseja realmente sair?", Alert.AlertType.CONFIRMATION);

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            DaoFactory.createJogadorDao().sairDoJogo(jogador);
            Stage stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }

}
