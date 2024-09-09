package com.example.atividades.service;

import com.example.atividades.controller.dto.request.AtualizarSugestaoRequest;
import com.example.atividades.controller.dto.request.InserirComentarioRequest;
import com.example.atividades.controller.dto.request.InserirSugestaoRequest;
import com.example.atividades.controller.dto.response.ComentarioResponse;
import com.example.atividades.controller.dto.response.SugestaoResponse;
import com.example.atividades.controller.dto.response.SugestaoResponseComComentarios;
import com.example.atividades.entity.ComentarioEntity;
import com.example.atividades.entity.SugestaoEntity;
import com.example.atividades.repository.ComentarioRepository;
import com.example.atividades.repository.SugestaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SugestaoService {

    private final SugestaoRepository repository;
    private final ComentarioRepository comentarioRepository;

    public SugestaoService(SugestaoRepository repository, ComentarioRepository comentarioRepository) {
        this.repository = repository;
        this.comentarioRepository = comentarioRepository;
    }

    public SugestaoResponse salvar(InserirSugestaoRequest req) {
        log.info("Criando sugestão: {}", req.titulo());

        SugestaoEntity sugestao = new SugestaoEntity();
        sugestao.setTitulo(req.titulo());
        sugestao.setDescricao(req.descricao());
        LocalDateTime now = LocalDateTime.now();
        sugestao.setDataEnvio(now);
        sugestao.setDataAtualizacao(now);

        repository.save(sugestao);
        return new SugestaoResponse(
                sugestao.getId(),
                sugestao.getTitulo(),
                sugestao.getDescricao(),
                sugestao.getDataEnvio(),
                sugestao.getDataAtualizacao()
        );
    }

    public List<SugestaoResponse> consultarSugestoes(String titulo) {
        List<SugestaoEntity> sugestoes;
        if (titulo != null && !titulo.isEmpty()) {
            sugestoes = repository.findByTituloContainingIgnoreCaseOrderByDataAtualizacaoDesc(titulo);
        } else {
            sugestoes = repository.findAllByOrderByDataAtualizacaoDesc();
        }
        return sugestoes.stream()
                .map(s -> new SugestaoResponse(
                        s.getId(),
                        s.getTitulo(),
                        s.getDescricao(),
                        s.getDataEnvio(),
                        s.getDataAtualizacao()
                ))
                .collect(Collectors.toList());
    }

    public SugestaoResponseComComentarios consultarPorId(long id) {
        SugestaoEntity sugestao = repository.findById(id).orElseThrow(() -> new RuntimeException("Sugestão não encontrada"));
        return new SugestaoResponseComComentarios(
                sugestao.getId(),
                sugestao.getTitulo(),
                sugestao.getDescricao(),
                sugestao.getDataEnvio(),
                sugestao.getDataAtualizacao(),
                sugestao.getComentarios().stream()
                        .sorted((c1, c2) -> c2.getDataEnvio().compareTo(c1.getDataEnvio()))
                        .map(c -> new ComentarioResponse(c.getId(), c.getTexto(), c.getDataEnvio()))
                        .collect(Collectors.toList())
        );
    }

    public SugestaoResponse atualizar(long id, AtualizarSugestaoRequest req) {
        SugestaoEntity sugestao = repository.findById(id).orElseThrow(() -> new RuntimeException("Sugestão não encontrada"));
        sugestao.setTitulo(req.titulo());
        sugestao.setDescricao(req.descricao());
        sugestao.setDataAtualizacao(LocalDateTime.now());

        repository.save(sugestao);
        return new SugestaoResponse(
                sugestao.getId(),
                sugestao.getTitulo(),
                sugestao.getDescricao(),
                sugestao.getDataEnvio(),
                sugestao.getDataAtualizacao()
        );
    }

    public SugestaoResponseComComentarios adicionarComentario(long sugestaoId, InserirComentarioRequest req) {
        SugestaoEntity sugestao = repository.findById(sugestaoId)
                .orElseThrow(() -> new RuntimeException("Sugestão não encontrada"));

        ComentarioEntity comentario = new ComentarioEntity();
        comentario.setTexto(req.texto());
        comentario.setSugestao(sugestao);

        comentarioRepository.save(comentario);

        sugestao.setDataAtualizacao(LocalDateTime.now());
        repository.save(sugestao);

        List<ComentarioResponse> comentarios = sugestao.getComentarios().stream()
                .sorted((c1, c2) -> c2.getDataEnvio().compareTo(c1.getDataEnvio()))
                .map(c -> new ComentarioResponse(c.getId(), c.getTexto(), c.getDataEnvio()))
                .collect(Collectors.toList());
        return new SugestaoResponseComComentarios(
                sugestao.getId(),
                sugestao.getTitulo(),
                sugestao.getDescricao(),
                sugestao.getDataEnvio(),
                sugestao.getDataAtualizacao(),
                comentarios
        );
    }
}