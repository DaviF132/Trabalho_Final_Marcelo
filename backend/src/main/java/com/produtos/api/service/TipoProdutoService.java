package com.produtos.api.service;

import com.produtos.api.domain.TipoProduto;
import com.produtos.api.dto.TipoProdutoRequestDTO;
import com.produtos.api.dto.TipoProdutoResponseDTO;
import com.produtos.api.exception.RegraNegocioException;
import com.produtos.api.repository.ProdutoRepository;
import com.produtos.api.repository.TipoProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoProdutoService {

    private final TipoProdutoRepository tipoProdutoRepository;
    private final ProdutoRepository produtoRepository;

    public TipoProdutoService(TipoProdutoRepository tipoProdutoRepository,
                               ProdutoRepository produtoRepository) {
        this.tipoProdutoRepository = tipoProdutoRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<TipoProdutoResponseDTO> listar() {
        return tipoProdutoRepository.findAll()
                .stream()
                .map(t -> new TipoProdutoResponseDTO(t.getId(), t.getNome()))
                .collect(Collectors.toList());
    }

    public TipoProdutoResponseDTO buscarPorId(Long id) {
        TipoProduto tipo = tipoProdutoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Tipo de produto não encontrado com id: " + id));
        return new TipoProdutoResponseDTO(tipo.getId(), tipo.getNome());
    }

    public TipoProdutoResponseDTO salvar(TipoProdutoRequestDTO dto) {
        if (tipoProdutoRepository.existsByNome(dto.getNome())) {
            throw new RegraNegocioException("Já existe um tipo de produto com o nome: " + dto.getNome());
        }
        TipoProduto tipo = new TipoProduto();
        tipo.setNome(dto.getNome());
        TipoProduto salvo = tipoProdutoRepository.save(tipo);
        return new TipoProdutoResponseDTO(salvo.getId(), salvo.getNome());
    }

    public void remover(Long id) {
        if (!tipoProdutoRepository.existsById(id)) {
            throw new RegraNegocioException("Tipo de produto não encontrado com id: " + id);
        }
        if (produtoRepository.existsByTipoProdutoId(id)) {
            throw new RegraNegocioException("Não é possível remover: existem produtos vinculados a este tipo.");
        }
        tipoProdutoRepository.deleteById(id);
    }
}
