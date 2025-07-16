import { Component, inject, Input, Output, EventEmitter, OnInit, effect } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PlayerService, Jogador } from '../../pages/home/player.service';

@Component({
  selector: 'app-player-form',
  imports: [ 
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './player-form.html',
  styleUrl: './player-form.css'
})
export class PlayerForm implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly playerService = inject(PlayerService);

  @Input() botaoTexto: string = 'Adicionar Jogador';
  @Output() onSubmit = new EventEmitter<void>();
  @Output() onCancel = new EventEmitter<void>();

  readonly grupos = ['Liga da Justiça', 'Vingadores'];

  form = this.fb.group({
    nome: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    telefone: ['', Validators.required],
    codinome: ['', Validators.required],
    grupo: ['Liga da Justiça', Validators.required]
  });

  // Effect para carregar dados quando um jogador é selecionado
  constructor() {
    effect(() => {
      const jogadorSelecionado = this.playerService.getJogadorSelecionado();
      if (jogadorSelecionado) {
        this.form.patchValue({
          nome: jogadorSelecionado.nome,
          email: jogadorSelecionado.email,
          telefone: jogadorSelecionado.telefone,
          codinome: jogadorSelecionado.codinome,
          grupo: jogadorSelecionado.grupo
        });
      }
    });
  }

  ngOnInit(): void {
    // Limpa o formulário se não há jogador selecionado
    if (!this.playerService.getJogadorSelecionado()) {
      this.form.reset({
        nome: '',
        email: '',
        telefone: '',
        codinome: '',
        grupo: 'Liga da Justiça'
      });
    }
  }

  // Método para submeter o formulário
  onSubmitForm(): void {
    if (this.form.valid) {
      const formData = this.form.getRawValue();
      const jogadorSelecionado = this.playerService.getJogadorSelecionado();

      if (jogadorSelecionado) {
        // Atualizar jogador existente
        const jogadorAtualizado: Jogador = {
          ...jogadorSelecionado,
          nome: formData.nome!,
          email: formData.email!,
          telefone: formData.telefone!,
          codinome: formData.codinome!,
          grupo: formData.grupo!
        };
        this.playerService.atualizarJogador(jogadorAtualizado);
      } else {
        // Adicionar novo jogador
        this.playerService.adicionarJogador({
          nome: formData.nome!,
          email: formData.email!,
          telefone: formData.telefone!,
          codinome: formData.codinome!,
          grupo: formData.grupo!
        });
      }

      this.onSubmit.emit();
    } else {
      this.markAllFieldsAsTouched();
    }
  }

  // Método para cancelar
  onCancelForm(): void {
    this.form.reset({
      nome: '',
      email: '',
      telefone: '',
      codinome: '',
      grupo: 'Liga da Justiça'
    });
    this.onCancel.emit();
  }

  // Método para marcar todos os campos como tocados (para mostrar erros)
  private markAllFieldsAsTouched(): void {
    Object.keys(this.form.controls).forEach(key => {
      this.form.get(key)?.markAsTouched();
    });
  }

  // Método para obter mensagem de erro
  getErrorMessage(field: string): string {
    const control = this.form.get(field);
    if (control?.errors && control.touched) {
      if (control.errors['required']) {
        return `${this.getFieldLabel(field)} é obrigatório`;
      }
      if (control.errors['email']) {
        return 'Email deve ter um formato válido';
      }
    }
    return '';
  }

  // Método para obter label do campo
  private getFieldLabel(field: string): string {
    const labels: { [key: string]: string } = {
      nome: 'Nome',
      email: 'Email',
      telefone: 'Telefone',
      codinome: 'Codinome',
      grupo: 'Grupo'
    };
    return labels[field] || field;
  }

  // Método para verificar se campo tem erro
  hasError(field: string): boolean {
    const control = this.form.get(field);
    return !!(control?.errors && control.touched);
  }
}