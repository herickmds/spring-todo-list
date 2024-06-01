package com.dev.mrv.backend.service;

import com.dev.mrv.backend.dto.PessoaDTO;
import com.dev.mrv.backend.dto.RegisterDTO;
import com.dev.mrv.backend.exception.ResourceNotFoundException;
import com.dev.mrv.backend.mapper.PessoaMapper;
import com.dev.mrv.backend.model.Pessoa;
import com.dev.mrv.backend.repository.PessoaRepository;
import com.dev.mrv.backend.util.ErrorMessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PasswordEncoder passwordEncoder;
    private final PessoaMapper pessoaMapper;
    private final ErrorMessageManager errorMessageManager;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, PasswordEncoder passwordEncoder, PessoaMapper pessoaMapper, ErrorMessageManager errorMessageManager) {
        this.pessoaRepository = pessoaRepository;
        this.passwordEncoder = passwordEncoder;
        this.pessoaMapper = pessoaMapper;
        this.errorMessageManager = errorMessageManager;
    }

    public List<PessoaDTO> getAllPessoas() {
        return pessoaRepository.findAll().stream()
                .map(pessoaMapper::toDto)
                .collect(Collectors.toList());
    }

    public PessoaDTO getPessoaById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("pessoa.not.found", id)));
        return pessoaMapper.toDto(pessoa);
    }

    public PessoaDTO createPessoa(RegisterDTO registerDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(registerDTO.getNome());
        pessoa.setNomeUsuario(registerDTO.getNomeUsuario());
        pessoa.setEmail(registerDTO.getEmail());
        pessoa.setSenha(passwordEncoder.encode(registerDTO.getSenha()));
        pessoa.setDataNascimento(registerDTO.getDataNascimento());
        Pessoa savedPessoa = pessoaRepository.save(pessoa);
        return new PessoaDTO(savedPessoa);
    }

    public PessoaDTO getPessoaByUsername(String username) {
        Optional<Pessoa> pessoa = pessoaRepository.findByNomeUsuario(username);
        if (pessoa.isPresent()) {
            return new PessoaDTO(pessoa.get());
        } else {
            throw new RuntimeException(errorMessageManager.getErrorMessage("usuario.not.found"));
        }
    }

    public PessoaDTO updatePessoa(Long id, PessoaDTO pessoaDTO) {
        Pessoa existingPessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("pessoa.not.found", id)));

        Pessoa updatedPessoa = pessoaMapper.toEntity(pessoaDTO);
        updatedPessoa.setId(existingPessoa.getId());

        Pessoa savedPessoa = pessoaRepository.save(updatedPessoa);
        return pessoaMapper.toDto(savedPessoa);
    }

    public void deletePessoa(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("pessoa.not.found", id)));

        pessoaRepository.delete(pessoa);
    }
}
