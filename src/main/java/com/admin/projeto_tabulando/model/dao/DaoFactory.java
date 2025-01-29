package com.admin.projeto_tabulando.model.dao;

import com.admin.projeto_tabulando.db.DB;
import com.admin.projeto_tabulando.model.dao.impl.JogadorDaoJDBC;
import com.admin.projeto_tabulando.model.dao.impl.JogoDaoJDBC;
import com.admin.projeto_tabulando.model.dao.impl.MonitorDaoJDBC;
import com.admin.projeto_tabulando.model.dao.impl.SalaDeJogosDaoJDBC;

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
}
