import { Component, inject, Output, EventEmitter, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PlayerService, Jogador } from '../../pages/home/player.service';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.html',
  styleUrls: ['./player-list.css'],
  imports: [CommonModule]
})
export class PlayerList {
  private readonly playerService = inject(PlayerService);

  @Output() onEdit = new EventEmitter<number>();
  @Output() onDelete = new EventEmitter<number>();
  @Output() onNew = new EventEmitter<void>();

  // Getters para acessar dados do serviço
  readonly jogadores = this.playerService.getJogadores;
  readonly totalJogadores = this.playerService.totalJogadores;

  // Computed para verificar se a lista está vazia
  readonly isEmpty = computed(() => this.jogadores().length === 0);

  // Método para editar jogador
  editarJogador(id: number): void {
    this.onEdit.emit(id);
  }

  // Método para excluir jogador
  excluirJogador(id: number): void {
    this.onDelete.emit(id);
  }

  // Método para adicionar novo jogador
  novoJogador(): void {
    this.onNew.emit();
  }

  // Método para obter classe CSS baseada no grupo
  getGrupoClass(grupo: string): string {
    return this.playerService.getGrupoClass(grupo);
  }

  // Método para obter ícone baseado no grupo
  getGrupoIcon(grupo: string): string {
    return this.playerService.getGrupoIcon(grupo);
  }

  // Método para obter classe do botão baseada no grupo
  getBotaoClass(grupo: string): string {
    return grupo === 'Vingadores' 
      ? 'bg-orange-500 hover:bg-orange-600' 
      : 'bg-blue-500 hover:bg-blue-600';
  }
}