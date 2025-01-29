package com.admin.projeto_tabulando.model.dao.impl;

import com.admin.projeto_tabulando.model.dao.JogoDao;
import com.admin.projeto_tabulando.model.entities.Jogador;

import java.util.ArrayList;

public class JogoDaoJDBC implements JogoDao {
    @Override
    public boolean iniciarJogo(ArrayList<Jogador> jogadores) {
        return false;
    }

    @Override
    public void finalizarJogo() {

    }

    @Override
    public boolean esta_dispnivel() {
        return false;
    }
}
