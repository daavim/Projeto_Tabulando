package com.admin.projeto_tabulando.model.entities;

import java.util.List;

public class Jogador {
    private int id;
    private String nome;
    private String usuario;
    private int jogoAtual;
    private List<Jogo> historico;

    public Jogador(String nome) {
        this.nome = nome;
        this.jogoAtual = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getJogoAtual() {
        return jogoAtual;
    }

    public void setJogoAtual(int jogoAtual) {
        this.jogoAtual = jogoAtual;
    }

    public List<Jogo> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Jogo> historico) {
        this.historico = historico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}