package com.dh.grupo05.Dentista.controller;


import com.dh.grupo05.Dentista.model.Endereco;
import com.dh.grupo05.Dentista.model.Paciente;
import com.dh.grupo05.Dentista.service.EnderecoService;
import com.dh.grupo05.Dentista.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService service;

    @PostMapping
    public Paciente salvarDentista(@RequestBody Paciente paciente) throws SQLException {
        return service.salvar(paciente);
    }

    @GetMapping
    public List<Paciente> buscarTodosEnderecos() throws SQLException {
        return service.buscarTodosPacientes();
    }

    @RequestMapping(value = "/buscaId")
    public Paciente buscaPorId(@RequestParam("id") int id) throws SQLException{
        return service.buscaPorId(id).isEmpty() ? new Paciente() : service.buscaPorId(id).get();
    }

    @PatchMapping
    public void modificar(@RequestBody Paciente paciente) throws SQLException{
        service.modificar(paciente);
    }


}
