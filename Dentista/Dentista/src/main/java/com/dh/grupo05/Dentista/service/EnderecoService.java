package com.dh.grupo05.Dentista.service;

import com.dh.grupo05.Dentista.dao.IDao;
import com.dh.grupo05.Dentista.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    IDao<Endereco> enderecoDAOH2;

    public Endereco salvar(Endereco endereco) throws SQLException {
        return enderecoDAOH2.salvar(endereco);
    }

    public List<Endereco> buscarTodosEndercos() throws SQLException{
        return enderecoDAOH2.consultar();
    }

    public Optional<Endereco> buscaPorId(int id) throws SQLException{
        return enderecoDAOH2.buscaPorId(id);
    }

    public void modificar(Endereco endereco) throws SQLException{
        enderecoDAOH2.modificar(endereco);
    }
}
