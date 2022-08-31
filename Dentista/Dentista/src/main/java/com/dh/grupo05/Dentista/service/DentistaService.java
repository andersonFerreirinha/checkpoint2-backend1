package com.dh.grupo05.Dentista.service;

import com.dh.grupo05.Dentista.dao.IDao;
import com.dh.grupo05.Dentista.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DentistaService  {

    @Autowired
    IDao<Dentista> dentistaDAOH2;

    public Dentista salvar(Dentista dentista) throws SQLException {
        return dentistaDAOH2.salvar(dentista);
    }

    public List<Dentista> buscarTodosDentistas() throws SQLException{
        return dentistaDAOH2.consultar();
    }

}
