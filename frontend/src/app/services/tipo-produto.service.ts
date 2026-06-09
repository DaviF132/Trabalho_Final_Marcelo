import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface TipoProduto {
  id: number;
  nome: string;
}

@Injectable({
  providedIn: 'root'
})
export class TipoProdutoService {
  private apiUrl = 'http://localhost:8080/api/tipos-produto';

  constructor(private http: HttpClient) {}

  listar(): Observable<TipoProduto[]> {
    return this.http.get<TipoProduto[]>(this.apiUrl);
  }

  salvar(tipoProduto: { nome: string }): Observable<TipoProduto> {
    return this.http.post<TipoProduto>(this.apiUrl, tipoProduto);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
