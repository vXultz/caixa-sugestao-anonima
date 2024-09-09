package com.example.atividades.controller.dto.response;

import java.time.LocalDateTime;

public record SugestaoResponse(
        Long id,
        String titulo,
        String descricao,
        LocalDateTime dataEnvio,
        LocalDateTime dataAtualizacao
) {
}
