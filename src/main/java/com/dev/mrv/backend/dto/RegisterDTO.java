package com.dev.mrv.backend.dto;

import com.dev.mrv.backend.model.Pessoa;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDTO {
    private String nome;
    private String nomeUsuario;
    private String email;
    private String senha;
    private LocalDate dataNascimento;

    public RegisterDTO() {}

    public RegisterDTO(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.nomeUsuario = pessoa.getNomeUsuario();
        this.email = pessoa.getEmail();
        this.dataNascimento = pessoa.getDataNascimento();
    }
}
