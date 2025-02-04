package com.admin.projeto_tabulando.model.dao.impl;

import com.admin.projeto_tabulando.db.DB;
import com.admin.projeto_tabulando.model.dao.JogadorDao;
import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.List;

public class JogadorDaoJDBC implements JogadorDao {
    private Connection conn;

    public JogadorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Jogador procurarPorId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select * from Jogador "+
                    "where ID_jogador=?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Jogador jogador = new Jogador(rs.getString("nome"));
                jogador.setId(rs.getInt("ID_jogador"));
                jogador.setJogoAtual((Jogo) rs.getObject("jogo_atual"));
                jogador.setUsuario(rs.getString("usuario"));
                return jogador;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return null;
    }


    public void registrarJogador(String usuario, String senha, Jogador jogador) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String hashSenha = BCrypt.hashpw(senha, BCrypt.gensalt());  // Criptografa a senha

            st = conn.prepareStatement("INSERT INTO Jogador (nome, usuario, senha) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, jogador.getNome());
            st.setString(2, usuario);
            st.setString(3, hashSenha);

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    jogador.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }


    @Override
    public boolean entrarNoJogo(Jogador jogador, Jogo jogo) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO Jogador_Jogo (ID_jogador, ID_jogo) VALUES (?,?)");
            st.setInt(1, jogador.getId());
            st.setInt(2, jogo.getId());
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
            st = conn.prepareStatement("DELETE FROM Jogador_Jogo WHERE ID_jogo = ? AND ID_jogador = ?");
            st.setInt(1, jogo.getId());
            st.setInt(2, jogador.getId());
            st.executeUpdate();

            jogador.setJogoAtual(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

}
