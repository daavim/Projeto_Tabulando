package com.admin.projeto_tabulando.model.dao.impl;

import com.admin.projeto_tabulando.db.DB;
import com.admin.projeto_tabulando.model.dao.SalaDeJogosDao;
import com.admin.projeto_tabulando.model.entities.Jogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaDeJogosDaoJDBC implements SalaDeJogosDao {
    private Connection conn;

    public SalaDeJogosDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ArrayList<Jogo> JogosDisponiveis() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM jogo WHERE disponivel = true");
            rs = st.executeQuery();

            ArrayList<Jogo> jogos = new ArrayList<>();

            while(rs.next()){
                Jogo jogo = new Jogo(
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getInt("maxJogadores")
                );
                jogos.add(jogo);
            }
            return jogos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
