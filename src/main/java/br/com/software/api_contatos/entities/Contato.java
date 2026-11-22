package br.com.software.api_contatos.entities;

import lombok.Data;

@Data
public class Contato {

    private Integer idContato;
    private String nome;
    private String email;
    private String telefone;
}
