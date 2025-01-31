package com.admin.projeto_tabulando.model.dao.impl;

import com.admin.projeto_tabulando.model.dao.MonitorDao;
import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;
import com.admin.projeto_tabulando.model.entities.SalaDeJogos;

import java.sql.Connection;

public class MonitorDaoJDBC implements MonitorDao {
    private Connection conn;

    public MonitorDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void removerJogador(Jogador jogador) {

    }

    @Override
    public void encerrarJogo(Jogo Jogo) {

    }

    @Override
    public void liberarJogo(Jogo jogo) {

    }

    @Override
    public void adicionarJogo(Jogo jogo) {

    }

    @Override
    public void removerJogo(Jogo jogo) {

    }
}
