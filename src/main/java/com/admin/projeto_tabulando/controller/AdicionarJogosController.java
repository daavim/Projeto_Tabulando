package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.entities.Jogo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import static com.admin.projeto_tabulando.utils.Alerta.mostrarAlerta;

public class AdicionarJogosController {
    @FXML
    private TextField nome;
    @FXML
    private TextField categoria;
    @FXML
    private TextField maxJogadores;

    @FXML
    public void adicionarOnClicked() {
        try {
            if (nome.getText().isEmpty() || categoria.getText().isEmpty() || maxJogadores.getText().isEmpty()) {
                mostrarAlerta("Erro", null, "Todos os campos são obrigatórios!",Alert.AlertType.ERROR);
                return;
            }

            String nomeJogo = nome.getText();
            String categoriasJogo = categoria.getText();
            int max_jogadores = Integer.parseInt(maxJogadores.getText());


            Jogo novoJogo = new Jogo(nomeJogo, categoriasJogo, max_jogadores);

            DaoFactory.createMonitorDao().adicionarJogo(nomeJogo, categoriasJogo, max_jogadores, novoJogo);

            mostrarAlerta("Sucesso", null,"Jogo adicionado com sucesso!", Alert.AlertType.INFORMATION);

            nome.clear();
            categoria.clear();
            maxJogadores.clear();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", null,"O número máximo de jogadores deve ser um número válido!", Alert.AlertType.ERROR);
        }
    }

}
