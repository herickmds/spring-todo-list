package com.dev.mrv.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String nomeUsuario;

    private String email;

    private String senha;

    private LocalDate dataNascimento;


    public Pessoa(Long pessoaId) {
    }
}