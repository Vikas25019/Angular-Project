import { Component, OnInit } from '@angular/core';
import {Client} from '../client';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.css']
})
export class CreateClientComponent implements OnInit {

  clients: Client[];
  client = new Client();
  constructor(private _clientService: ClientService) { }

  ngOnInit(): void {
  }
  
  addClient():void{
		this._clientService.addClient(this.client)
		.subscribe((response) => {
			console.log(response);
			alert(response["_body"]);
		},
		(error)=>{
			console.log(error);
		}
		
		)
	}
	
  getClient():void{
		this._clientService.getAllClient()
		.subscribe((clientData) => {
			this.clients = clientData,
			console.log(clientData)
			},(error) =>{
			console.log(error);
		});
	}
	
}
