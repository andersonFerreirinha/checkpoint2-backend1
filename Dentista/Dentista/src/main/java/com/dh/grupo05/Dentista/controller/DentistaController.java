package com.dh.grupo05.Dentista.controller;

import com.dh.grupo05.Dentista.model.Dentista;
import com.dh.grupo05.Dentista.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    DentistaService service;

    @PostMapping
    public Dentista salvarDentista(@RequestBody Dentista dentista) throws SQLException{
        return service.salvar(dentista);
    }

    @GetMapping
    public List<Dentista>buscarTodosDentistas() throws SQLException {
        return service.buscarTodosDentistas();
    }

    @RequestMapping(value = "/buscaId")
    public Dentista buscaPorId(@RequestParam("id") int id) throws SQLException{
        return service.buscaPorId(id).isEmpty() ? new Dentista() : service.buscaPorId(id).get();
    }

    @PatchMapping
    public void modificar(@RequestBody Dentista dentista) throws SQLException{
        service.modificar(dentista);
    }



}
