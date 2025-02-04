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

            st = conn.prepareStatement("UPDATE jogo SET disponivel = false WHERE ID_jogo = ?");
            st.setInt(1, jogo.getId());
            st.executeUpdate();

            st = conn.prepareStatement("INSERT INTO Jogador_Jogo (ID_jogador, ID_jogo) VALUES ( ?, ? )");
            for (Jogador jogador : jogadores) {
                st.setInt(1, jogo.getId());
                st.setInt(2, jogador.getId());
                st.addBatch();
            }
            st.executeBatch();

            conn.commit();
            return true;

        } catch (SQLException e){
            try { conn.rollback(); } catch (SQLException ex) { /* Tratar erro */ }
            throw new RuntimeException("Erro ao iniciar jogo", e);
        } finally {
            DB.closeStatement(st);
            try { conn.setAutoCommit(true); } catch (SQLException e) { /* Tratar erro */ }
        }

    }

    @Override
    public void finalizarJogo(Jogo jogo) {
        PreparedStatement st = null;
        try {
            conn.setAutoCommit(false);

            st = conn.prepareStatement("UPDATE Jogo SET disponivel = true WHERE ID_jogo = ?");
            st.setInt(1, jogo.getId());
            st.executeUpdate();

            st = conn.prepareStatement("DELETE FROM Jogador_Jogo WHERE ID_jogo = ?");
            st.setInt(1, jogo.getId());
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
    public boolean estaDisponivel(Jogo jogo) {
        return jogo.isDisponivel();
    }

}
