package com.produtos.api.dto;

public class TipoProdutoResponseDTO {

    private Long id;
    private String nome;

    public TipoProdutoResponseDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
}
