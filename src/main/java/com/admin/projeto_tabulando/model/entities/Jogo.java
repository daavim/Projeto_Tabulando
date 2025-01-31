package com.admin.projeto_tabulando.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Jogo {
    private String nome;
    private String tipo;
    private int maxJogadores;
    private List<Jogador> jogadores;
    private boolean disponivel;

    public Jogo(String nome, String tipo, int maxJogadores) {
        this.nome = nome;
        this.tipo = tipo;
        this.maxJogadores = maxJogadores;
        this.disponivel = true;
        this.jogadores = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMaxJogadores() {
        return maxJogadores;
    }

    public void setMaxJogadores(int maxJogadores) {
        this.maxJogadores = maxJogadores;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
