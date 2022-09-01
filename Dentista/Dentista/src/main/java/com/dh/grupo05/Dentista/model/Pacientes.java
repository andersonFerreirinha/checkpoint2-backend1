package com.dh.grupo05.Dentista.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Pacientes {

    private int Id;

    private String nome;

    private String sobrenome;

    private int idEndereco;

    private String rg;

    private LocalDate dataCadastro;
}
