package com.dev.mrv.backend.mapper;

import com.dev.mrv.backend.dto.PessoaDTO;
import com.dev.mrv.backend.model.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {
    public PessoaDTO toDto(Pessoa pessoa) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setNomeUsuario(pessoa.getNomeUsuario());
        dto.setEmail(pessoa.getEmail());
        dto.setDataNascimento(pessoa.getDataNascimento());
        return dto;
    }

    public Pessoa toEntity(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(dto.getId());
        pessoa.setNome(dto.getNome());
        pessoa.setNomeUsuario(dto.getNomeUsuario());
        pessoa.setEmail(dto.getEmail());
        pessoa.setDataNascimento(dto.getDataNascimento());
        return pessoa;
    }
}