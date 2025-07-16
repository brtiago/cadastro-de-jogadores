import { Component, signal, computed, inject } from '@angular/core';
import { PlayerForm } from '../../_components/player-form/player-form';
import { PlayerList } from '../../_components/player-list/player-list';
import { PlayerService } from '../home/player.service';

@Component({
  selector: 'app-home',
  imports: [ PlayerForm, PlayerList ],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  private readonly playerService = inject(PlayerService);

  // Signal para controlar qual componente mostrar
  readonly currentView = signal<'list' | 'form'>('list');

  // Computed para título dinâmico
  readonly titulo = computed(() => {
    const jogadorSelecionado = this.playerService.getJogadorSelecionado();
    return jogadorSelecionado ? 'Editar Jogador' : 'Novo Jogador';
  });

  // Computed para texto do botão
  readonly botaoTexto = computed(() => {
    const jogadorSelecionado = this.playerService.getJogadorSelecionado();
    return jogadorSelecionado ? 'Salvar Alterações' : 'Adicionar Jogador';
  });

  // Método para alternar para formulário
  showForm(): void {
    this.currentView.set('form');
  }

  // Método para alternar para lista
  showList(): void {
    this.currentView.set('list');
    this.playerService.limparSelecao();
  }

  // Método para editar jogador
  editarJogador(id: number): void {
    const jogadores = this.playerService.getJogadores();
    const jogador = jogadores.find(j => j.id === id);
    
    if (jogador) {
      this.playerService.selecionarJogador(jogador);
      this.currentView.set('form');
    }
  }

  // Método para excluir jogador
  excluirJogador(id: number): void {
    if (confirm('Tem certeza que deseja excluir este jogador?')) {
      this.playerService.excluirJogador(id);
    }
  }

  // Método para lidar com salvamento do formulário
  onFormSubmit(): void {
    this.currentView.set('list');
  }
}