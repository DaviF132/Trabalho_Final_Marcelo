package com.produtos.api.controller;

import com.produtos.api.dto.TipoProdutoRequestDTO;
import com.produtos.api.dto.TipoProdutoResponseDTO;
import com.produtos.api.service.TipoProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-produto")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoProdutoController {

    private final TipoProdutoService service;

    public TipoProdutoController(TipoProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoProdutoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<TipoProdutoResponseDTO> salvar(@Valid @RequestBody TipoProdutoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
