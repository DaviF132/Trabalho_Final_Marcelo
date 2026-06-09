package com.produtos.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProdutoRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private Double preco;

    @NotNull(message = "O tipo de produto é obrigatório")
    private Long tipoProdutoId;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public Long getTipoProdutoId() { return tipoProdutoId; }
    public void setTipoProdutoId(Long tipoProdutoId) { this.tipoProdutoId = tipoProdutoId; }
}
