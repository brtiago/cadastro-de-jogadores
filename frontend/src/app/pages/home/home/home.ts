import { Component } from '@angular/core';
import { PlayerForm } from '../../../_components/player-form/player-form';

@Component({
  selector: 'app-home',
  imports: [ PlayerForm ],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
