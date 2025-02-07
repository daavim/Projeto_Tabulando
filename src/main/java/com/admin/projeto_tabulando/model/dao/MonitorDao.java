package com.admin.projeto_tabulando.model.dao;

import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;
import com.admin.projeto_tabulando.model.entities.SalaDeJogos;

public interface MonitorDao {
    void removerJogador(int id);
    void encerrarJogo(Jogo Jogo);
    void adicionarJogo(String nome, String tipo, int maxJogadores, Jogo jogo);
    void removerJogo(Jogo jogo);
}
