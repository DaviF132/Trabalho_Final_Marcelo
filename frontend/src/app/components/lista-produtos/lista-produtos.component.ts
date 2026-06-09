import { Component, OnInit } from '@angular/core';
import { Produto, ProdutoService } from '../../services/produto.service';

@Component({
  selector: 'app-lista-produtos',
  templateUrl: './lista-produtos.component.html',
  styleUrls: ['./lista-produtos.component.css']
})
export class ListaProdutosComponent implements OnInit {
  produtos: Produto[] = [];
  mensagemErro: string = '';
  mensagemSucesso: string = '';
  carregando: boolean = false;

  constructor(private produtoService: ProdutoService) {}

  ngOnInit(): void {
    this.carregarProdutos();
  }

  carregarProdutos(): void {
    this.carregando = true;
    this.produtoService.listar().subscribe({
      next: (dados) => {
        this.produtos = dados;
        this.carregando = false;
      },
      error: () => {
        this.mensagemErro = 'Erro ao carregar produtos. Verifique se o backend está rodando.';
        this.carregando = false;
      }
    });
  }

  remover(id: number, nome: string): void {
    if (!confirm(`Deseja remover o produto "${nome}"?`)) return;

    this.produtoService.remover(id).subscribe({
      next: () => {
        this.mensagemSucesso = `Produto "${nome}" removido com sucesso!`;
        this.mensagemErro = '';
        this.carregarProdutos();
        setTimeout(() => this.mensagemSucesso = '', 3000);
      },
      error: (err) => {
        this.mensagemErro = err.error?.mensagem || 'Erro ao remover produto.';
        this.mensagemSucesso = '';
      }
    });
  }
}
