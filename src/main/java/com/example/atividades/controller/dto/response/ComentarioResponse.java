package com.example.atividades.controller.dto.response;

import java.time.LocalDateTime;

public record ComentarioResponse(
        Long id,
        String texto,
        LocalDateTime dataEnvio
) {
}
