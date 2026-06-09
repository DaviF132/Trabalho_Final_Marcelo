import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProdutoService } from '../../services/produto.service';
import { TipoProduto, TipoProdutoService } from '../../services/tipo-produto.service';

@Component({
  selector: 'app-form-produto',
  templateUrl: './form-produto.component.html',
  styleUrls: ['./form-produto.component.css']
})
export class FormProdutoComponent implements OnInit {
  form!: FormGroup;
  tipos: TipoProduto[] = [];
  mensagemSucesso: string = '';
  mensagemErro: string = '';
  salvando: boolean = false;

  constructor(
    private fb: FormBuilder,
    private produtoService: ProdutoService,
    private tipoProdutoService: TipoProdutoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2)]],
      preco: [null, [Validators.required, Validators.min(0.01)]],
      tipoProdutoId: [null, Validators.required]
    });

    this.tipoProdutoService.listar().subscribe({
      next: (dados) => this.tipos = dados,
      error: () => this.mensagemErro = 'Erro ao carregar tipos de produto.'
    });
  }

  get f() { return this.form.controls; }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.salvando = true;
    this.mensagemErro = '';
    this.mensagemSucesso = '';

    this.produtoService.salvar(this.form.value).subscribe({
      next: (produto) => {
        this.mensagemSucesso = `Produto "${produto.nome}" cadastrado com sucesso!`;
        this.form.reset();
        this.salvando = false;
        setTimeout(() => this.router.navigate(['/produtos']), 1500);
      },
      error: (err) => {
        if (err.error && typeof err.error === 'object') {
          const erros = Object.values(err.error).join(' | ');
          this.mensagemErro = erros;
        } else {
          this.mensagemErro = 'Erro ao salvar produto.';
        }
        this.salvando = false;
      }
    });
  }
}
