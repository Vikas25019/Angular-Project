import { Component, OnInit } from '@angular/core';
import {Router , ActivatedRoute } from '@angular/router';
import {Client} from '../client';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-view-client-page',
  templateUrl: './view-client-page.component.html',
  styleUrls: ['./view-client-page.component.css']
})
export class ViewClientPageComponent implements OnInit {

  clients: Client[];
  client = new Client();
  
  constructor(private _clientService: ClientService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
	this.client.id = this.route.snapshot.paramMap.get('id');
	this.retrieveClient(this.client.id);
  }
	
	retrieveClient(clientId: string){
	this._clientService.retrieveClient(clientId)
		.subscribe((clientData) => {
			this.clients = clientData,
			console.log(clientData)
			},(error) =>{
			console.log(error);})
	}
	
	deleteClient(clientId: string){
	this._clientService.deleteClient(clientId)
		.subscribe((response) => {console.log(response); this.router.navigate(['./showclient']);},(error) =>{
			console.log(error);})
	}
}
