package com.admin.projeto_tabulando.model.entities;

public class Jogo {
    private String nome;
    private String tipo;
    private int numJogadores;

    public Jogo(String nome, String tipo, int numJogadores, boolean disponibilidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.numJogadores = numJogadores;
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
}
