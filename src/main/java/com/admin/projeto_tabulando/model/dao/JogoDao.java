package com.admin.projeto_tabulando.model.dao;

import com.admin.projeto_tabulando.model.entities.Jogador;

import java.util.ArrayList;

public interface JogoDao {
    boolean iniciarJogo (ArrayList<Jogador> jogadores);
    void finalizarJogo ();
    boolean esta_dispnivel ();
}
