package com.dev.mrv.backend.service;

import com.dev.mrv.backend.dto.PessoaDTO;
import com.dev.mrv.backend.dto.RegisterDTO;
import com.dev.mrv.backend.model.Pessoa;
import com.dev.mrv.backend.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class PessoaServiceTests {

    @Autowired
    private PessoaService pessoaService;

    @MockBean
    private PessoaRepository pessoaRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCreatePessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Test User");
        pessoa.setNomeUsuario("testuser");
        pessoa.setEmail("testuser@example.com");
        pessoa.setSenha("password");
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));

        Mockito.when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO createdPessoa = pessoaService.createPessoa(new RegisterDTO(pessoa));

        assertEquals("Test User", createdPessoa.getNome());
    }

    @Test
    public void testGetPessoaById() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Test User");

        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        PessoaDTO foundPessoa = pessoaService.getPessoaById(1L);

        assertEquals("Test User", foundPessoa.getNome());
    }
}
