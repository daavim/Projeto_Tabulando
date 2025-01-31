package com.admin.projeto_tabulando.model.entities;

import java.util.List;

public class Jogador {

    private String nome;
    private Jogo jogoAtual;
    private List<Jogo> historico;

    public Jogador(String nome, int idade) {
        this.nome = nome;
        this.jogoAtual = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Jogo getJogoAtual() {
        return jogoAtual;
    }

    public void setJogoAtual(Jogo jogoAtual) {
        this.jogoAtual = jogoAtual;
    }

    public List<Jogo> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Jogo> historico) {
        this.historico = historico;
    }
}