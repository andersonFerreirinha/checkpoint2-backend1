package com.dh.grupo05.Dentista.dao.impl;

import com.dh.grupo05.Dentista.dao.ConfiguracaoJDBC;
import com.dh.grupo05.Dentista.dao.IDao;
import com.dh.grupo05.Dentista.model.Endereco;
import com.dh.grupo05.Dentista.model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Configuration
public class PacienteDAOH2 implements IDao<Paciente> {
    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(PacienteDAOH2.class);
    @Override
    public Paciente salvar(Paciente paciente) throws SQLException {
        log.info("Abrindo conexão para salvamento de Pacientes");
        String salvarPaciente = String.format("INSERT INTO PACIENTE " +
                "(nome, sobrenome, endereco, rg, dataCadastro) " +
                "values ('%s','%s', %d, '%s', %s)",
                paciente.getNome(), paciente.getSobrenome(), paciente.getIdEndereco(),
                paciente.getRg(), paciente.getDataCadastro());

        Connection connection = null;

        try{
            log.info("Salvando Paciente: " + paciente.getNome());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/consultorio;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(salvarPaciente, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                paciente.setId(resultSet.getInt(1));
        }catch (Exception e){
            log.error("Erro ao inserir paciente: "+ e.getMessage());
            e.printStackTrace();
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }


        return null;
    }

    @Override
    public void modificar(Paciente pacientes) throws SQLException {

    }

    @Override
    public List<Paciente> consultar() throws SQLException {
        log.info("Abrindo uma conexão");
        Connection connection = null;
        Statement stmt = null;
        String queryConsulta = "Select * from PACIENTE";
        List<Paciente> pacientes = new ArrayList<>();
        try{
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/consultorio;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            connection = configuracaoJDBC.getConnection();
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(queryConsulta);
            log.info("Buscando todos os pacientes no banco no banco");
            while (resultSet.next()) {
                pacientes.add(criarObjetoPaciente(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            log.info("Fechando conexão no banco");
            connection.close();
        }

        return pacientes;
    }

    @Override
    public Optional<Paciente> buscaPorId(int id) throws SQLException {
        return Optional.empty();
    }

    private Paciente criarObjetoPaciente(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String nomePaciente = resultSet.getString("nome");
        String sobrenomePaciente = resultSet.getString("sobrenome");
        Integer pacienteEndereco = resultSet.getInt("endereco");
        String rgPaciente = resultSet.getString("rg");
        Date dataCadastro = resultSet.getTimestamp("dataCadastro");
        return new Paciente(id, nomePaciente, sobrenomePaciente, pacienteEndereco, rgPaciente, dataCadastro);
    }
}
