package com.admin.projeto_tabulando.model.entities;

import java.util.ArrayList;

public class SalaDeJogos {
    ArrayList<Jogo> jogos;
    ArrayList<Jogador> jogadores;

    public SalaDeJogos() {
        this.jogos = new ArrayList<>();
        this.jogadores = new ArrayList<>();
    }

    public ArrayList<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(ArrayList<Jogo> jogos) {
        this.jogos = jogos;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
