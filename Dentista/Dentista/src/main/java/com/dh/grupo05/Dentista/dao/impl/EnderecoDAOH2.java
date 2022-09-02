package com.dh.grupo05.Dentista.dao.impl;

import com.dh.grupo05.Dentista.dao.ConfiguracaoJDBC;
import com.dh.grupo05.Dentista.dao.IDao;
import com.dh.grupo05.Dentista.model.Dentista;
import com.dh.grupo05.Dentista.model.Endereco;
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
public class EnderecoDAOH2 implements IDao<Endereco> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(DentistaDAOH2.class);

    @Override
    public Endereco salvar(Endereco endereco) throws SQLException {
        log.info("Abrindo conexão para salvamento de Endereço");
        String salvarDado = String.format("INSERT INTO ENDERECO " +
                "(rua, numero, complemento, cep, cidade, estado) " +
                "values ('%s','%s','%s', '%s','%s','%s') ",
                endereco.getRua(), endereco.getNumero(), endereco.getComplemento(),
                endereco.getCep(), endereco.getCidade(), endereco.getEstado() );
        Connection connection = null;

        try {
            log.info("Salvando endereço: "+endereco.getRua());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/consultorio;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(salvarDado, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                endereco.setId(resultSet.getInt(1));
        }catch (Exception e){
            log.error("Erro ao inserir endereco: "+ e.getMessage());
            e.printStackTrace();
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }
        return null;

    }

    @Override
    public void modificar(Endereco endereco) throws SQLException {
        log.info("Abrindo conexão para modificação de Endereco");
        String modificarDado = String.format("UPDATE ENDERECO SET rua = '%s' where Id='%s';",endereco.getRua(),endereco.getId());
        Connection connection = null;

        try {
            log.info("Modificando endereco: "+ endereco.getRua());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/consultorio;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(modificarDado);


        }catch (Exception e){
            log.error("Erro ao modificar endereco: "+ e.getMessage());
            e.printStackTrace();
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }
    }

    @Override
    public List<Endereco> consultar() throws SQLException {
        log.info("Abrindo uma conexão");
        Connection connection = null;
        Statement stmt = null;
        String queryConsulta = "Select * from ENDERECO";
        List<Endereco> enderecos = new ArrayList<>();
        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/consultorio;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            connection = configuracaoJDBC.getConnection();
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(queryConsulta);
            log.info("Buscando todos os enderecos no banco");
            while (resultSet.next()) {
                enderecos.add(criarObjetoEndereco(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            log.info("Fechando conexão no banco");
            connection.close();
        }
        return enderecos;
    }

    @Override
    public Optional<Endereco> buscaPorId(int id) throws SQLException {
        log.info("Abrir conexão");
        Connection connection = null;
        Statement stmt = null;
        String query = String.format("SELECT * FROM Endereco WHERE ID = %s", id);
        Endereco endereco = null;
        try{
            configuracaoJDBC= new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/consultorio;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            log.info("Buscando endereco com id: " + id);
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                endereco = criarObjetoEndereco(resultSet);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            log.info("Fechando a conexão do banco");
            connection.close();
        }

        return endereco != null ? Optional.of(endereco) : Optional.empty();
    }

    private Endereco criarObjetoEndereco(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String ruaEndereco = resultSet.getString("rua");
        String numeroEnderco = resultSet.getString("numero");
        String complementoEndereco = resultSet.getString("complemento");
        String cepEndereco = resultSet.getString("cep");
        String cidadeEndereco = resultSet.getString("cidade");
        String estadoEnderco = resultSet.getString("estado");
        return new Endereco(id, ruaEndereco, numeroEnderco, complementoEndereco, cepEndereco, cidadeEndereco, estadoEnderco);
    }
}