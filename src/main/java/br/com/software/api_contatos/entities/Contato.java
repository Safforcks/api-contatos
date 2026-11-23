package br.com.software.api_contatos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"idContato", "nome", "email", "telefone"})
public class Contato {

    private Integer idContato;
    private String nome;
    private String email;
    private String telefone;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private Integer ativo;
}
