import { Component, OnInit } from '@angular/core';
import {  Router ,ActivatedRoute } from '@angular/router';
import {Client} from '../client';
import { ClientService } from '../client.service';


@Component({
  selector: 'app-update-client',
  templateUrl: './update-client.component.html',
  styleUrls: ['./update-client.component.css']
})
export class UpdateClientComponent implements OnInit {

  clients: Client[];
  client = new Client();
  constructor(private _clientService: ClientService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
	this.client.id = this.route.snapshot.paramMap.get('id');
  }
  
	updateClient():void{
		this._clientService.updateClient(this.client)
		.subscribe((response) => {
			console.log(response);
			alert(response["_body"]);
			this.router.navigate(['./showAllClient']);
		},
		(error)=>{
			console.log(error);
		}
		
		)
	}
}
