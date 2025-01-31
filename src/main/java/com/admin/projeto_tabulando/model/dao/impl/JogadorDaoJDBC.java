package com.admin.projeto_tabulando.model.dao.impl;

import com.admin.projeto_tabulando.db.DB;
import com.admin.projeto_tabulando.model.dao.JogadorDao;
import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JogadorDaoJDBC implements JogadorDao {
    private Connection conn;

    public JogadorDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public boolean entrarNoJogo(Jogador jogador, Jogo jogo) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO jogo_jogador (jogo_id, jogador_id) VALUES (?,?)");
            st.setInt(1, obterIdJogo(jogo));
            st.setInt(2, obterIdJogador(jogador));
            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                jogador.setJogoAtual(jogo);
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sairDoJogo(Jogador jogador, Jogo jogo) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM jogo_jogador WHERE jogo_id = ? AND jogador_id = ?");
            st.setInt(1, obterIdJogo(jogo));
            st.setInt(2, obterIdJogador(jogador));
            st.executeUpdate();

            jogador.setJogoAtual(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
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
