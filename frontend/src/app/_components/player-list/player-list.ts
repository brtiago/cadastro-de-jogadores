import { Component, signal, inject, ChangeDetectionStrategy } from '@angular/core';
import { PlayerForm } from '../player-form/player-form';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

interface Jogador {
  id: number;
  nome: string;
  email: string;
  telefone: string;
  codinome: string;
  grupo: string;
}

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.html',
  styleUrls: ['./player-list.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  imports: [CommonModule]
})
export class PlayerList {
  private readonly router = inject(Router);

  // Signal para lista de jogadores
  readonly jogadores = signal<Jogador[]>([
    {
      id: 1,
      nome: 'João Silva',
      email: 'joao@email.com',
      telefone: '(11) 99999-9999',
      codinome: 'Batman',
      grupo: 'Liga da Justiça'
    },
    {
      id: 2,
      nome: 'Maria Santos',
      email: 'maria@email.com',
      telefone: '(11) 88888-8888',
      codinome: 'Flash',
      grupo: 'Vingadores'
    }
  ]);

  // Método para editar jogador
  editarJogador(id: number): void {
    console.log('Editando jogador:', id);
    this.router.navigate(['/player-form', id]);
  }

  // Método para excluir jogador
  excluirJogador(id: number): void {
    console.log('Excluindo jogador:', id);
    const jogadoresAtuais = this.jogadores();
    const novosJogadores = jogadoresAtuais.filter(j => j.id !== id);
    this.jogadores.set(novosJogadores);
  }

  // Método para navegar para novo jogador
  novoJogador(): void {
    this.router.navigate(['/player-form']);
  }

  // Método para obter classe CSS baseada no grupo
  getGrupoClass(grupo: string): string {
    return grupo === 'Vingadores' ? 'text-orange-600 font-semibold' : 'text-blue-600 font-semibold';
  }
}
