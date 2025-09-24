import {computed, inject, Injectable, signal} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TipoGrupo} from '../../_components/tipo-grupo.enum';

export interface Jogador {
  id?: number;
  nome: string;
  email: string;
  telefone: string;
  codinome?: string;
  tipoGrupo: TipoGrupo;
  createdAt?: Date;
  ativo?: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/v1/jogadores';

  private readonly jogadores = signal<Jogador[]>([]);
  private readonly jogadorSelecionado = signal<Jogador | null>(null);
  private readonly proximoId = signal(3);

  // Computed para contador de jogadores
  readonly totalJogadores = computed(() => this.jogadores().length);

  // Getters readonly para os signals
  readonly getJogadores = this.jogadores.asReadonly();
  readonly getJogadorSelecionado = this.jogadorSelecionado.asReadonly();

  // Método para adicionar jogador
  adicionarJogador(jogador: Omit<Jogador, 'id'>): void {
    const novoJogador: Jogador = {
      ...jogador,
      id: this.proximoId()
    };

    this.jogadores.update(jogadores => [...jogadores, novoJogador]);
    this.proximoId.update(id => id + 1);
  }

  // Método para atualizar jogador
  atualizarJogador(jogadorAtualizado: Jogador): void {
    this.jogadores.update(jogadores =>
      jogadores.map(j => j.id === jogadorAtualizado.id ? jogadorAtualizado : j)
    );
  }

  // Método para excluir jogador
  excluirJogador(id: number): void {
    this.jogadores.update(jogadores => jogadores.filter(j => j.id !== id));

    // Se o jogador excluído estava selecionado, limpa a seleção
    if (this.jogadorSelecionado()?.id === id) {
      this.jogadorSelecionado.set(null);
    }
  }

  // Método para selecionar jogador para edição
  selecionarJogador(jogador: Jogador): void {
    this.jogadorSelecionado.set(jogador);
  }

  // Método para limpar seleção
  limparSelecao(): void {
    this.jogadorSelecionado.set(null);
  }

  // Método para obter classe CSS baseada no grupo
  getGrupoClass(grupo: TipoGrupo): string {
    return grupo === TipoGrupo.VINGADORES ? 'text-orange-600 font-semibold' : 'text-blue-600 font-semibold';
  }

  // Método para obter ícone baseado no grupo
  getGrupoIcon(grupo: TipoGrupo): string {
    return grupo === TipoGrupo.VINGADORES ? '🦸‍♂️' : '🦹‍♂️';
  }
}
