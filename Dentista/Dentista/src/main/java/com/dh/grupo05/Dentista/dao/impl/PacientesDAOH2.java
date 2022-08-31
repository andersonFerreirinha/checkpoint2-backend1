package com.dh.grupo05.Dentista.dao.impl;

import com.dh.grupo05.Dentista.dao.IDao;
import com.dh.grupo05.Dentista.model.Pacientes;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PacientesDAOH2 implements IDao<Pacientes> {
    @Override
    public Pacientes salvar(Pacientes pacientes) throws SQLException {
        return null;
    }

    @Override
    public void modificar(Pacientes pacientes) throws SQLException {

    }

    @Override
    public List<Pacientes> consultar() throws SQLException {
        return null;
    }

    @Override
    public Optional<Pacientes> buscaPorId(int id) throws SQLException {
        return Optional.empty();
    }
}
