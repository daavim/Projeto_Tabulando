package com.admin.projeto_tabulando.model.dao;

import com.admin.projeto_tabulando.model.entities.Jogo;

import java.util.ArrayList;


public interface SalaDeJogosDao {
    ArrayList<Jogo> JogosDisponiveis();
}
