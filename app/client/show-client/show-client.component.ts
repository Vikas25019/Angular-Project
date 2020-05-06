import { Component, OnInit } from '@angular/core';
import {Client} from '../client';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-show-client',
  templateUrl: './show-client.component.html',
  styleUrls: ['./show-client.component.css']
})
export class ShowClientComponent implements OnInit {

  clients: Client[];
  client = new Client();
  
  constructor(private _clientService: ClientService) { }

  ngOnInit(): void {
  }
	
	retrieveClient(clientId: string){
	this._clientService.retrieveClient(clientId)
		.subscribe((clientData) => {
			console.log(clientData)
			},(error) =>{
			console.log(error);})
	}
}
