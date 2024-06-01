package com.dev.mrv.backend.service;

import com.dev.mrv.backend.exception.ResourceNotFoundException;
import com.dev.mrv.backend.model.Pessoa;
import com.dev.mrv.backend.repository.PessoaRepository;
import com.dev.mrv.backend.util.ErrorMessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.mrv.backend.dto.TarefaDTO;
import com.dev.mrv.backend.mapper.TarefaMapper;
import com.dev.mrv.backend.model.Tarefa;
import com.dev.mrv.backend.repository.TarefaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {


    private final TarefaRepository tarefaRepository;

    private final PessoaRepository pessoaRepository;

    private final ErrorMessageManager errorMessageManager;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository, PessoaRepository pessoaRepository, ErrorMessageManager errorMessageManager) {
        this.tarefaRepository = tarefaRepository;
        this.pessoaRepository = pessoaRepository;
        this.errorMessageManager = errorMessageManager;
    }

    public List<TarefaDTO> getAllTarefas() {
        return tarefaRepository.findAll().stream()
                .map(TarefaMapper::toDto)
                .collect(Collectors.toList());
    }

    public TarefaDTO getTarefaById(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("tarefa.not.found", id)));
        return TarefaMapper.toDto(tarefa);
    }

    public TarefaDTO createTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = TarefaMapper.toEntity(tarefaDTO);
        Pessoa pessoa = pessoaRepository.findById(tarefaDTO.getPessoaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("pessoa.not.found", tarefaDTO.getPessoaId())));
        tarefa.setPessoa(pessoa);
        Tarefa savedTarefa = tarefaRepository.save(tarefa);
        return TarefaMapper.toDto(savedTarefa);
    }

    public TarefaDTO updateTarefa(Long id, TarefaDTO tarefaDTO) {
        Tarefa existingTarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("tarefa.not.found", id)));

        Tarefa updatedTarefa = TarefaMapper.toEntity(tarefaDTO);
        updatedTarefa.setId(existingTarefa.getId());
        Pessoa pessoa = pessoaRepository.findById(tarefaDTO.getPessoaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("pessoa.not.found", tarefaDTO.getPessoaId())));
        updatedTarefa.setPessoa(pessoa);

        Tarefa savedTarefa = tarefaRepository.save(updatedTarefa);
        return TarefaMapper.toDto(savedTarefa);
    }

    public void deleteTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("tarefa.not.found", id)));

        tarefaRepository.delete(tarefa);
    }
}
