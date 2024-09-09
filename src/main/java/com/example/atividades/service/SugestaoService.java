package com.example.atividades.service;

import com.example.atividades.controller.dto.request.InserirSugestaoRequest;
import com.example.atividades.controller.dto.response.SugestaoResponse;
import com.example.atividades.entity.SugestaoEntity;
import com.example.atividades.repository.SugestaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SugestaoService {

    private final SugestaoRepository repository;

    public SugestaoService(SugestaoRepository repository) {
        this.repository = repository;
    }

    public SugestaoResponse salvar(InserirSugestaoRequest req) {
        log.info("Criando sugestão: {}", req.titulo());

        SugestaoEntity sugestao = new SugestaoEntity();
        sugestao.setTitulo(req.titulo());
        sugestao.setDescricao(req.descricao());

        repository.save(sugestao);
        return new SugestaoResponse(
                sugestao.getId(),
                sugestao.getTitulo(),
                sugestao.getDescricao()
        );
    }
}
