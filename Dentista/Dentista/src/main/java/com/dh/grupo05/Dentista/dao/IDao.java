package com.dh.grupo05.Dentista.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    public T salvar(T t) throws SQLException;

    public void modificar(T t) throws SQLException;

    public List<T> consultar() throws SQLException;

    public Optional<T> buscaPorId(int id) throws SQLException;
}
