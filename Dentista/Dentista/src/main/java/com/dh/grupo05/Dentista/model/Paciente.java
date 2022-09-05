package com.dh.grupo05.Dentista.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Paciente {

    private int Id;

    private String nome;

    private String sobrenome;

    private int idEndereco;

    private String rg;

    private Date dataCadastro;
}
