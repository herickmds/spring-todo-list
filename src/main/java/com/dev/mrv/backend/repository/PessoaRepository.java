package com.dev.mrv.backend.repository;

import com.dev.mrv.backend.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByNomeUsuario(String nomeUsuario);
    Optional<Pessoa> findByEmail(String email);
}
