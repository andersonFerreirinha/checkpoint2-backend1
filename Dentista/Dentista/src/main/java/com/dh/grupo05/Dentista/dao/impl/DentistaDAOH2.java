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
import java.util.ArrayList;
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
            statement.execute(modificarDado);


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
        log.info("Abrindo uma conexão");
        Connection connection = null;
        Statement stmt = null;
        String queryConsulta = "Select * from DENTISTA";
        List<Dentista>dentistas = new ArrayList<>();
        try{
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/consultorio;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(queryConsulta);
            log.info("Buscando todos os dentistas no banco");
            while (resultSet.next()){
                dentistas.add(criarObjetoDentista(resultSet));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            log.info("Fechando conexão no banco");
            connection.close();
        }
        return dentistas;
    }

    @Override
    public Optional<Dentista> buscaPorId(int id) throws SQLException {
        log.info("Abrir conexão");
        Connection connection = null;
        Statement stmt = null;
        String query = String.format("SELECT * FROM Dentista WHERE ID = %s", id);
        Dentista dentista = null;
        try{
            configuracaoJDBC= new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/consultorio;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            log.info("Buscando dentista com id: " + id);
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                dentista = criarObjetoDentista(resultSet);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            log.info("Fechando a conexão do banco");
            connection.close();
        }

        return dentista != null ? Optional.of(dentista) : Optional.empty();
    }

    private Dentista criarObjetoDentista(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String nomeDentista = resultSet.getString("nome");
        String sobrenomeDentista = resultSet.getString("sobrenome");
        String matriculaDentista = resultSet.getString("matricula");
        return new Dentista(id, nomeDentista, sobrenomeDentista, matriculaDentista);
    }

}
