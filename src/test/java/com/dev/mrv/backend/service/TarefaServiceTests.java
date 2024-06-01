package com.dev.mrv.backend.service;

import com.dev.mrv.backend.dto.TarefaDTO;
import com.dev.mrv.backend.mapper.TarefaMapper;
import com.dev.mrv.backend.model.Pessoa;
import com.dev.mrv.backend.model.Tarefa;
import com.dev.mrv.backend.repository.PessoaRepository;
import com.dev.mrv.backend.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class TarefaServiceTests {

    @Autowired
    private TarefaService tarefaService;

    @MockBean
    private TarefaRepository tarefaRepository;
    @MockBean
    private PessoaRepository pessoaRepository;

    @Test
    public void testCreateTask() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Test Tarefa");
        tarefa.setDescricao("Test Description");
        Pessoa pessoa = new Pessoa(1L);
        tarefa.setPessoa(pessoa);

         Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        Mockito.when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        TarefaDTO tarefaDTO = TarefaMapper.toDto(tarefa);
        tarefaDTO.setPessoaId(1L);

        TarefaDTO createdTarefa = tarefaService.createTarefa(tarefaDTO);

        assertEquals("Test Tarefa", createdTarefa.getTitulo());
        assertEquals("Test Description", createdTarefa.getDescricao());
    }

    @Test
    public void testGetTaskById() {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTitulo("Test Tarefa");

        Mockito.when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        TarefaDTO foundTask = tarefaService.getTarefaById(1L);

        assertEquals("Test Tarefa", foundTask.getTitulo());
    }
}
