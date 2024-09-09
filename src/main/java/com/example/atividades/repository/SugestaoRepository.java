package com.example.atividades.repository;

import com.example.atividades.entity.SugestaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SugestaoRepository extends JpaRepository<SugestaoEntity, Long> {
}
