package com.dh.grupo05.Dentista.service;

import com.dh.grupo05.Dentista.dao.IDao;
import com.dh.grupo05.Dentista.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    public Optional<Dentista> buscaPorId(int id) throws SQLException{
        return dentistaDAOH2.buscaPorId(id);
    }

    public void modificar(Dentista dentista) throws SQLException{
        dentistaDAOH2.modificar(dentista);
    }

}
