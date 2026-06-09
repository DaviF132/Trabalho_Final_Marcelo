package com.produtos.api.service;

import com.produtos.api.domain.Produto;
import com.produtos.api.domain.TipoProduto;
import com.produtos.api.dto.ProdutoRequestDTO;
import com.produtos.api.dto.ProdutoResponseDTO;
import com.produtos.api.exception.RegraNegocioException;
import com.produtos.api.repository.ProdutoRepository;
import com.produtos.api.repository.TipoProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final TipoProdutoRepository tipoProdutoRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          TipoProdutoRepository tipoProdutoRepository) {
        this.produtoRepository = produtoRepository;
        this.tipoProdutoRepository = tipoProdutoRepository;
    }

    public List<ProdutoResponseDTO> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Produto não encontrado com id: " + id));
        return toDTO(produto);
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        TipoProduto tipo = tipoProdutoRepository.findById(dto.getTipoProdutoId())
                .orElseThrow(() -> new RegraNegocioException("Tipo de produto não encontrado com id: " + dto.getTipoProdutoId()));

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setTipoProduto(tipo);

        Produto salvo = produtoRepository.save(produto);
        return toDTO(salvo);
    }

    public void remover(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RegraNegocioException("Produto não encontrado com id: " + id);
        }
        produtoRepository.deleteById(id);
    }

    private ProdutoResponseDTO toDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getTipoProduto().getId(),
                produto.getTipoProduto().getNome()
        );
    }
}
