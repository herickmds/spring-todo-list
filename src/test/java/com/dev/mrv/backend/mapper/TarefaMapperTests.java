package com.dev.mrv.backend.mapper;


import com.dev.mrv.backend.dto.TarefaDTO;
import com.dev.mrv.backend.mapper.TarefaMapper;
import com.dev.mrv.backend.model.Tarefa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarefaMapperTests {

    @Test
    public void testConvertToDTO() {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTitulo("Test Tarefa");
        tarefa.setDescricao("Test Description");

        TarefaDTO tarefaDTO = TarefaMapper.toDto(tarefa);

        assertEquals(1L, tarefaDTO.getId());
        assertEquals("Test Tarefa", tarefaDTO.getTitulo());
        assertEquals("Test Description", tarefaDTO.getDescricao());
    }

    @Test
    public void testConvertToEntity() {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setId(1L);
        tarefaDTO.setTitulo("Test Tarefa");
        tarefaDTO.setDescricao("Test Description");

        Tarefa tarefa = TarefaMapper.toEntity(tarefaDTO);

        assertEquals(1L, tarefa.getId());
        assertEquals("Test Tarefa", tarefa.getTitulo());
        assertEquals("Test Description", tarefa.getDescricao());
    }
}