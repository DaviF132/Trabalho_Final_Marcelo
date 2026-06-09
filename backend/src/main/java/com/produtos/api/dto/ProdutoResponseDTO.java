package com.produtos.api.dto;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private Double preco;
    private Long tipoProdutoId;
    private String tipoProdutoNome;

    public ProdutoResponseDTO(Long id, String nome, Double preco, Long tipoProdutoId, String tipoProdutoNome) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tipoProdutoId = tipoProdutoId;
        this.tipoProdutoNome = tipoProdutoNome;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Double getPreco() { return preco; }
    public Long getTipoProdutoId() { return tipoProdutoId; }
    public String getTipoProdutoNome() { return tipoProdutoNome; }
}
