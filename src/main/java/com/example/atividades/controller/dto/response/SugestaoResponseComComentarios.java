package com.example.atividades.controller.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record SugestaoResponseComComentarios(
        Long id,
        String titulo,
        String descricao,
        LocalDateTime dataEnvio,
        LocalDateTime dataAtualizacao,
        List<ComentarioResponse> comentarios
) {
}
