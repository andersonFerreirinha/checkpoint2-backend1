/*package com.dh.grupo05.Dentista.dao.impl;

import com.dh.grupo05.Dentista.dao.ConfiguracaoJDBC;
import com.dh.grupo05.Dentista.dao.IDao;
import com.dh.grupo05.Dentista.model.Endereco;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class EnderecoDAOH2 implements IDao<Endereco> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(DentistaDAOH2.class);

    @Override
    public Endereco salvar(Endereco endereco) throws SQLException {
        log.info("Abrindo conexão para salvamento de Endereço");
        String salvarDado = String.format("INSERT INTO ENDERECO (rua, numero, complemento, cep, cidade, estado) values ('%s','%s','%s' '%s','%s','%s') ", endereco.getRua(), endereco.getNumero(), endereco.getComplemento(),  );
        Connection connection = null;

        try {
            log.info("Salvando dentista: "+ .getNome());
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
*/