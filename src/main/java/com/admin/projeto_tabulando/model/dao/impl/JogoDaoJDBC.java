package com.admin.projeto_tabulando.model.dao.impl;

import com.admin.projeto_tabulando.db.DB;
import com.admin.projeto_tabulando.model.dao.JogoDao;
import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogoDaoJDBC implements JogoDao {
    private Connection conn;

    public JogoDaoJDBC(Connection conn) {
        this.conn = conn;
    }



    @Override
    public List<Jogo> procurarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Jogo");
            rs = st.executeQuery();

            List<Jogo> lista = new ArrayList<>();

            while (rs.next()) {
                Jogo jogo = new Jogo(rs.getString("nome"), rs.getString("tipo"), rs.getInt("maxJogadores"));
                jogo.setId(rs.getInt("ID_jogo"));
                lista.add(jogo);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }


    @Override
    public List<Jogo> procurarTodosDisponivel() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Jogo WHERE disponivel = true");
            rs = st.executeQuery();

            List<Jogo> lista = new ArrayList<>();

            while (rs.next()) {
                Jogo jogo = new Jogo(rs.getString("nome"), rs.getString("tipo"), rs.getInt("maxJogadores"));
                jogo.setId(rs.getInt("ID_jogo"));
                    lista.add(jogo);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Jogo> procurarTodosJogando() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Jogo WHERE disponivel = false");
            rs = st.executeQuery();

            List<Jogo> lista = new ArrayList<>();

            while (rs.next()) {
                Jogo jogo = new Jogo(rs.getString("nome"), rs.getString("tipo"), rs.getInt("maxJogadores"));
                jogo.setId(rs.getInt("ID_jogo"));
                lista.add(jogo);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Jogo procurarPorNome(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Jogo jogo = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Jogo WHERE nome = ?");
            st.setString(1, nome);
            rs = st.executeQuery();

            if(rs.next()){
                jogo = new Jogo(nome, rs.getString("tipo"), rs.getInt("maxJogadores"));
                jogo.setId(rs.getInt("ID_jogo"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jogo;
    }

    public void marcarComoIndisponivel(Jogo jogo){
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE Jogo SET disponivel = false WHERE ID_jogo = ?");
            st.setInt(1, jogo.getId());
            st.executeUpdate();

            jogo.setDisponivel(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    public void marcarComoDisponivel(Jogo jogo){
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE Jogo SET disponivel = true WHERE ID_jogo = ?");
            st.setInt(1, jogo.getId());
            st.executeUpdate();

            jogo.setDisponivel(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }



}
