//package com.dh.grupo05.Dentista.service;
//
//
//import com.dh.grupo05.Dentista.model.Endereco;
//import com.dh.grupo05.Dentista.model.Paciente;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PacienteService {
//
//    @Autowired
//    IDao<Paciente> pacienteDAOH2;
//
//    public Paciente salvar(Paciente paciente) throws SQLException {
//        return pacienteDAOH2.salvar(paciente);
//    }
//
//    public List<Paciente> buscarTodosPacientes() throws SQLException{
//        return pacienteDAOH2.consultar();
//    }
//
//    public Optional<Paciente> buscaPorId(int id) throws SQLException{
//        return pacienteDAOH2.buscaPorId(id);
//    }
//
//    public void modificar(Paciente paciente) throws SQLException {
//        pacienteDAOH2.modificar(paciente);
//    }
//
//}
