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
    public boolean iniciarJogo(List<Jogador> jogadores, Jogo jogo) throws SQLException {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);

            st = conn.prepareStatement("UPDATE jogo SET disponivel = false WHERE id = ?");
            st.setInt(1, obterIdJogo(jogo));
            st.executeUpdate();

            st = conn.prepareStatement("INSERT INTO jogo_jogador (jogo_id, jogador_id) VALUES ( ?, ? )");
            for (Jogador jogador : jogadores) {
                st.setInt(1, obterIdJogo(jogo));
                st.setInt(2, obterIdJogador(jogador));
                st.addBatch();
            }
            st.executeBatch();

            conn.commit();
            return true;

        } catch (SQLException e){
            throw new RuntimeException("Erro ao iniciar jogo", e);
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void finalizarJogo(Jogo jogo) {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);

            st = conn.prepareStatement("UPDATE jogo SET disponivel = true WHERE id = ?");
            st.setInt(1, obterIdJogo(jogo));
            st.executeUpdate();

            st = conn.prepareStatement("DELETE FROM jogo_jogador WHERE jogo_id = ?");
            st.setInt(1, obterIdJogo(jogo));
            st.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { /* Tratar erro */ }
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            try { conn.setAutoCommit(true); } catch (SQLException e) { /* Tratar erro */ }
        }
    }

    @Override
    public boolean estaDispnivel(Jogo jogo) {
        return jogo.isDisponivel();
    }


    private int obterIdJogador(Jogador jogador) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "SELECT id FROM jogador WHERE nome = ?"
        );
        st.setString(1, jogador.getNome());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        throw new SQLException("Jogador não encontrado");
    }

    private int obterIdJogo(Jogo jogo) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "SELECT id FROM jogo WHERE nome = ?"
        );
        st.setString(1, jogo.getNome());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        throw new SQLException("Jogo não encontrado");
    }
}
