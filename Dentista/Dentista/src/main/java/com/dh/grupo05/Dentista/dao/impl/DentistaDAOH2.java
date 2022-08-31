package com.dh.grupo05.Dentista.dao.impl;

import com.dh.grupo05.Dentista.dao.ConfiguracaoJDBC;
import com.dh.grupo05.Dentista.dao.IDao;
import com.dh.grupo05.Dentista.model.Dentista;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
@Configuration
public class DentistaDAOH2 implements IDao<Dentista> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(DentistaDAOH2.class);


    @Override
    public Dentista salvar(Dentista dentista) throws SQLException {
        log.info("Abrindo conexão para salvamento de Dentista");
        String salvarDado = String.format("INSERT INTO DENTISTA (nome, sobrenome, matricula) values ('%s','%s','%s') ", dentista.getNome(), dentista.getSobrenome(), dentista.getMatricula());
        Connection connection = null;

        try {
            log.info("Salvando dentista: "+ dentista.getNome());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/consultorio;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(salvarDado, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                dentista.setId(resultSet.getInt(1));
        }catch (Exception e){
            log.error("Erro ao inserir dentista: "+ e.getMessage());
            e.printStackTrace();
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }
        return null;
    }

    @Override
    public void modificar(Dentista dentista) throws SQLException {
        log.info("Abrindo conexão para modificação de Dentista");
        String modificarDado = String.format("UPDATE DENTISTA SET nome = '%s' where Id='%s';",dentista.getNome(),dentista.getId());
        Connection connection = null;

        try {
            log.info("Modificando dentista: "+ dentista.getNome());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/consultorio;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(modificarDado, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                dentista.setId(resultSet.getInt(1));
        }catch (Exception e){
            log.error("Erro ao modificar dentista: "+ e.getMessage());
            e.printStackTrace();
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }
    }


    @Override
    public List<Dentista> consultar() throws SQLException {
        return null;
    }

    @Override
    public Optional<Dentista> buscaPorId(int id) throws SQLException {
        return Optional.empty();
    }
}
