package com.admin.projeto_tabulando.model.dao;

import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;

import java.util.List;

public interface JogadorDao {
    boolean entrarNoJogo(Jogador jogador, Jogo jogo);
    void sairDoJogo(Jogador jogador, Jogo jogo);
    void registrarJogador(String usuario, String senha, Jogador jogador);
    Jogador procurarPorId(int id);
}
