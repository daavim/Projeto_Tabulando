package com.admin.projeto_tabulando.model.entities;

public class Monitor {
    private String nome;
    private SalaDeJogos sala;

    public Monitor(String nome, SalaDeJogos sala) {
        this.nome = nome;
        this.sala = sala;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
