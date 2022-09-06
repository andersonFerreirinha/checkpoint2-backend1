package com.dh.grupo05.Dentista.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Endereco {

    private int Id;

    private String rua;

    private String numero;

    private String complemento;

    private String cep;

    private String cidade;

    private String estado;

}
