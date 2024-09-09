package com.example.atividades.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comentario")
public class ComentarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sugestao_id")
    private SugestaoEntity sugestao;

    private String texto;

    private LocalDateTime dataEnvio = LocalDateTime.now();
}
