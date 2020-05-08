import { Component, OnInit } from '@angular/core';
import { Client } from '../client';
import { ClientService } from '../client.service';

@Component({
	selector: 'app-show-all-client',
	templateUrl: './show-all-client.component.html',
	styleUrls: ['./show-all-client.component.css']
})
export class ShowAllClientComponent implements OnInit {

	clients: Client[];
	client = new Client();

	constructor(private _clientService: ClientService) { }

	getClient(): void {
		this._clientService.getAllClient()
			.subscribe((clientData) => {
				this.clients = clientData,
					console.log(clientData)
			}, (error) => {
				console.log(error);
			});
	}
	ngOnInit(): void {
		this.getClient();
	}

	deleteClient(clientId: string) {
		this._clientService.deleteClient(clientId)
			.subscribe((response) => { console.log(response); this.getClient() }, (error) => {
				console.log(error);
			})
	}
}
