package com.admin.projeto_tabulando.model.dao.impl;

import com.admin.projeto_tabulando.db.DB;
import com.admin.projeto_tabulando.model.dao.DaoFactory;
import com.admin.projeto_tabulando.model.dao.JogadorDao;
import com.admin.projeto_tabulando.model.entities.Jogador;
import com.admin.projeto_tabulando.model.entities.Jogo;
import com.admin.projeto_tabulando.utils.Alerta;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
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
                return true;
            }return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sairDoJogo(Jogador jogador, Jogo jogo) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM Jogador_Jogo WHERE ID_jogador = ? AND ID_jogo =?");
            st.setInt(1, jogador.getId());
            st.setInt(2, jogo.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Jogador> procurarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Jogador> Listajogador = new ArrayList<>();
        try {
            st = conn.prepareStatement("SELECT * FROM Jogador");
            rs = st.executeQuery();

            while (rs.next()) {
                Jogador jogador = new Jogador(rs.getString("nome"));
                jogador.setId(rs.getInt("ID_jogador"));
                Listajogador.add(jogador);
            }
            return Listajogador;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Jogador procurarPorNome(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Jogador jogador = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Jogador WHERE nome = ?");
            st.setString(1, nome);
            rs = st.executeQuery();

            if(rs.next()){
                jogador = new Jogador(rs.getString("nome"));
                jogador.setId(rs.getInt("ID_jogador"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jogador;
    }


    @Override
    public List<Jogador> procurarTodosJogando(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Jogador> jogadores = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT ID_jogador FROM Jogador_Jogo WHERE ID_jogo = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            while(rs.next()){
                Jogador jogador = DaoFactory.createJogadorDao().procurarPorId(rs.getInt("ID_jogador"));
                if (jogador != null){
                    jogadores.add(jogador);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jogadores;
    }


    public boolean usuarioExiste(String usuario) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Jogador WHERE usuario = ?");
            st.setString(1, usuario);
            rs = st.executeQuery();

            return rs.next(); // Se existir um resultado, o usuário já está cadastrado

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar usuário existente: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

}
