package com.dh.grupo05.Dentista.controller;

import com.dh.grupo05.Dentista.model.Endereco;
import com.dh.grupo05.Dentista.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping
    public Endereco salvarDentista(@RequestBody Endereco endereco) throws SQLException{
        return service.salvar(endereco);
    }

    @GetMapping
    public List<Endereco>buscarTodosEnderecos() throws SQLException {
        return service.buscarTodosEndercos();
    }

    @RequestMapping(value = "/buscaId")
    public Endereco buscaPorId(@RequestParam("id") int id) throws SQLException{
        return service.buscaPorId(id).isEmpty() ? new Endereco() : service.buscaPorId(id).get();
    }

    @PatchMapping
    public void modificar(@RequestBody Endereco endereco) throws SQLException{
        service.modificar(endereco);
    }
}
