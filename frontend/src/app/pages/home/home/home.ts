import { Component } from '@angular/core';
import { PlayerForm } from '../../../_components/player-form/player-form';
import { PlayerList } from '../../../_components/player-list/player-list';

@Component({
  selector: 'app-home',
  imports: [ PlayerForm, PlayerList ],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
