package com.example.atividades.repository;

import com.example.atividades.entity.SugestaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SugestaoRepository extends JpaRepository<SugestaoEntity, Long> {
    List<SugestaoEntity> findAllByOrderByDataAtualizacaoDesc();
    List<SugestaoEntity> findByTituloContainingIgnoreCaseOrderByDataAtualizacaoDesc(String titulo);
}
