package com.dh.grupo05.Dentista.dao.impl;

import com.dh.grupo05.Dentista.dao.IDao;
import com.dh.grupo05.Dentista.model.Endereco;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EnderecoDAOH2 implements IDao<Endereco> {

    @Override
    public Endereco salvar(Endereco endereco) throws SQLException {
        return null;
    }

    @Override
    public void modificar(Endereco endereco) throws SQLException {

    }

    @Override
    public List<Endereco> consultar() throws SQLException {
        return null;
    }

    @Override
    public Optional<Endereco> buscaPorId(int id) throws SQLException {
        return Optional.empty();
    }
}
