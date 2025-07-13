import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-player-form',
  imports: [ 
    ReactiveFormsModule,
    CommonModule,
    RouterModule 
  ],
  templateUrl: './player-form.html',
  styleUrl: './player-form.css'
})
export class PlayerForm {
  private fb = inject(FormBuilder);
  private router = inject(Router);

  form = this.fb.group({
    nome: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    telefone: ['', Validators.required],
    grupo: ['Liga da Justiça', Validators.required]
  })

  // Método para submeter o formulário
  onSubmit() {
    if (this.form.valid) {
      console.log('Formulário enviado:', this.form.value);
      
    }
  }

  // Método para navegar para a lista de jogadores
  navigateToList() {
    this.router.navigate(['/jogadores']);
  }
}
