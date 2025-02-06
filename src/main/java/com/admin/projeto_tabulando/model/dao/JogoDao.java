package com.admin.projeto_tabulando.model.dao;

import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;

import java.sql.SQLException;
import java.util.List;

public interface JogoDao {
    boolean iniciarJogo (List<Jogador> jogador, Jogo jogo) throws SQLException;
    void finalizarJogo (Jogo jogo);
    boolean estaDisponivel (Jogo jogo);
    List<Jogo> procurarTodosDisponivel();
}
