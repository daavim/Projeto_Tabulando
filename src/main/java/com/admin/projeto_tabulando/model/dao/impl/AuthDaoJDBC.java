package com.admin.projeto_tabulando.model.dao.impl;
import com.admin.projeto_tabulando.db.DB;
import com.admin.projeto_tabulando.model.dao.AuthDao;
import com.admin.projeto_tabulando.model.entities.Jogador;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDaoJDBC implements AuthDao {
    private Connection conn;

    public AuthDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public Jogador autenticarJogador(String usuario, String senha) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Jogador WHERE usuario = ?");
            st.setString(1, usuario);
            rs = st.executeQuery();

            if (rs.next()) {
                String hashArmazenado = rs.getString("senha");
                boolean senhaValida = BCrypt.checkpw(senha, hashArmazenado);  // Verifica senha com o hash armazenado
                String nome = rs.getString("nome");

                if (senhaValida) {
                    // Cria e retorna o objeto Jogador com os dados do banco
                    Jogador jogador = new Jogador(nome);
                    jogador.setId(rs.getInt("ID_jogador"));
                    jogador.setUsuario(rs.getString("usuario"));
                    // Adicione outros campos conforme necessário
                    return jogador;
                }
            }
            return null; // Usuário não encontrado
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
