package com.dev.mrv.backend.mapper;

import com.dev.mrv.backend.dto.TarefaDTO;
import com.dev.mrv.backend.model.Pessoa;
import com.dev.mrv.backend.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {

    public static TarefaDTO toDto(Tarefa tarefa) {
        if (tarefa == null) {
            return null;
        }

        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setId(tarefa.getId());
        tarefaDTO.setTitulo(tarefa.getTitulo());
        tarefaDTO.setDescricao(tarefa.getDescricao());
        tarefaDTO.setDataPrazoLimite(tarefa.getDataPrazoLimite());
        tarefaDTO.setEhConcluida(tarefa.getEhConcluida());
        tarefaDTO.setPessoaId(tarefa.getPessoa() != null ? tarefa.getPessoa().getId() : null);
        return tarefaDTO;
    }

    public static Tarefa toEntity(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(tarefaDTO.getId());
        tarefa.setTitulo(tarefaDTO.getTitulo());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setDataPrazoLimite(tarefaDTO.getDataPrazoLimite());
        tarefa.setEhConcluida(tarefaDTO.getEhConcluida());
        tarefa.setPessoa(new Pessoa(tarefaDTO.getPessoaId()));

        return tarefa;
    }
}