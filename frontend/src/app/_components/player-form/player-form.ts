import { Component, inject, Input, Output, EventEmitter, OnInit, effect, signal } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PlayerService, Jogador } from '../../pages/home/player.service';
import { TipoGrupo } from '../tipo-grupo.enum';

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

  readonly tiposGrupo = Object.values(TipoGrupo);
  readonly isSubmitting = signal(false);

  readonly labelsGrupo: { [key in TipoGrupo]: string } = {
    [TipoGrupo.LIGA_DA_JUSTICA]: 'Liga da Justiça',
    [TipoGrupo.VINGADORES]: 'Vingadores'
  };

  form = this.fb.group({
    nome: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    telefone: [''],
    tipoGrupo: [TipoGrupo.LIGA_DA_JUSTICA, Validators.required]
  });

  constructor() {
    effect(() => {
      const jogadorSelecionado = this.playerService.getJogadorSelecionado();
      if (jogadorSelecionado) {
        this.form.patchValue({
          nome: jogadorSelecionado.nome,
          email: jogadorSelecionado.email,
          telefone: jogadorSelecionado.telefone,
          tipoGrupo: jogadorSelecionado.tipoGrupo
        });
      }
    });
  }

  ngOnInit(): void {
    if (!this.playerService.getJogadorSelecionado()) {
      this.form.reset({
        nome: '',
        email: '',
        telefone: '',
        tipoGrupo: TipoGrupo.LIGA_DA_JUSTICA
      });
    }
  }

  onSubmitForm(): void {
    if (this.form.valid && !this.isSubmitting()) {
      this.isSubmitting.set(true);

      const formData = this.form.getRawValue();
      const jogadorSelecionado = this.playerService.getJogadorSelecionado();

      // Criar objeto jogador
      const jogadorData: Jogador = {
        nome: formData.nome!,
        email: formData.email!,
        telefone: formData.telefone || '',
        tipoGrupo: formData.tipoGrupo!
      };

      // Se estiver atualizando, incluir o ID
      if (jogadorSelecionado && jogadorSelecionado.id) {
        jogadorData.id = jogadorSelecionado.id;
      }

      try {
        if (jogadorSelecionado) {
          // CORREÇÃO: Passar apenas 1 argumento (objeto Jogador completo)
          this.playerService.atualizarJogador(jogadorData);
        } else {
          this.playerService.adicionarJogador(jogadorData);
        }

        this.isSubmitting.set(false);
        this.onSubmit.emit();

        // Limpar seleção após salvar
        this.playerService.limparSelecao();

      } catch (error: any) {
        this.isSubmitting.set(false);
        console.error('Erro ao salvar jogador:', error);
        alert('Erro ao salvar jogador: ' + (error.error?.mensagem || error.message));
      }
    } else {
      this.markAllFieldsAsTouched();
    }
  }

  onCancelForm(): void {
    this.form.reset({
      nome: '',
      email: '',
      telefone: '',
      tipoGrupo: TipoGrupo.LIGA_DA_JUSTICA
    });

    // Limpar seleção ao cancelar
    this.playerService.limparSelecao();
    this.onCancel.emit();
  }

  private markAllFieldsAsTouched(): void {
    Object.keys(this.form.controls).forEach(key => {
      this.form.get(key)?.markAsTouched();
    });
  }

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

  private getFieldLabel(field: string): string {
    const labels: { [key: string]: string } = {
      nome: 'Nome',
      email: 'Email',
      telefone: 'Telefone',
      tipoGrupo: 'Grupo'
    };
    return labels[field] || field.charAt(0).toUpperCase() + field.slice(1);
  }

  hasError(field: string): boolean {
    const control = this.form.get(field);
    return !!(control?.errors && control.touched);
  }

  getLabelGrupo(tipo: TipoGrupo): string {
    return this.labelsGrupo[tipo];
  }
}
