package com.example.atividades.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "sugestao")
public class SugestaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    private String descricao;

    private LocalDateTime dataEnvio = LocalDateTime.now();

    private LocalDateTime dataAtualizacao;
}
