package com.example.atividades.controller;

import com.example.atividades.controller.dto.request.AtualizarSugestaoRequest;
import com.example.atividades.controller.dto.request.InserirSugestaoRequest;
import com.example.atividades.controller.dto.response.SugestaoResponse;
import com.example.atividades.service.SugestaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sugestoes")
public class SugestaoController {

    private final SugestaoService service;

    @PostMapping
    public ResponseEntity<?> criarSugestao(@RequestBody InserirSugestaoRequest inserirSugestaoRequest) {
        try {
            SugestaoResponse criarSugestaoResponse = service.salvar(inserirSugestaoRequest);
            return new ResponseEntity<>(criarSugestaoResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<SugestaoResponse>> consultarSugestoes(
            @RequestParam(required = false) String titulo) {
        List<SugestaoResponse> sugestoes = service.consultarSugestoes(titulo);
        return ResponseEntity.ok(sugestoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultarSugestaoPorId(@PathVariable long id) {
        try {
            SugestaoResponse sugestaoResponse = service.consultarPorId(id);
            return new ResponseEntity<>(sugestaoResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarSugestao(@PathVariable long id, @RequestBody AtualizarSugestaoRequest atualizarSugestaoRequest) {
        try {
            SugestaoResponse atualizarSugestaoResponse = service.atualizar(id, atualizarSugestaoRequest);
            return new ResponseEntity<>(atualizarSugestaoResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
