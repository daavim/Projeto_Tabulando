package com.admin.projeto_tabulando.model.dao;

import com.admin.projeto_tabulando.model.entities.Jogador;

public interface AuthDao {
    Jogador autenticarJogador(String usuario, String senha);
}
