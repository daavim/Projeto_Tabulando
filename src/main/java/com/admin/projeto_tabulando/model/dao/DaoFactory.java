package com.admin.projeto_tabulando.model.dao;

import com.admin.projeto_tabulando.db.DB;
import com.admin.projeto_tabulando.model.dao.impl.*;

public interface DaoFactory {
    public static JogoDao createJogoDao(){
        return new JogoDaoJDBC(DB.getConnection());
    }
    public static JogadorDao createJogadorDao(){
        return new JogadorDaoJDBC(DB.getConnection());
    }
    public static MonitorDao createMonitorDao(){
        return new MonitorDaoJDBC(DB.getConnection());
    }
    public static SalaDeJogosDao createSalaDeJogosDao(){
        return new SalaDeJogosDaoJDBC(DB.getConnection());
    }
    public static AuthDao createAuthDao(){
        return new AuthDaoJDBC(DB.getConnection());
    }
}
