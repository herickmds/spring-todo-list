package com.dev.mrv.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TarefaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataPrazoLimite;
    private Long pessoaId;
    private Boolean ehConcluida;
}