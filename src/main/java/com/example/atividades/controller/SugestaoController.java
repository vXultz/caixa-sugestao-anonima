package com.example.atividades.controller;

import com.example.atividades.controller.dto.request.InserirSugestaoRequest;
import com.example.atividades.controller.dto.response.SugestaoResponse;
import com.example.atividades.service.SugestaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
