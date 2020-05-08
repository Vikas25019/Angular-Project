import { Component, OnInit } from '@angular/core';
import { Client } from '../client';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.css']
})
export class CreateClientComponent implements OnInit {

  client = new Client();
  constructor(private _clientService: ClientService) { }

  ngOnInit(): void {
  }

  addClient(): void {
    this._clientService.addClient(this.client)
      .subscribe((response) => {
        console.log(response);
        alert(response["_body"]);
        this.reset();
      },
        (error) => {
          console.log(error);
        }

      )
  }

  private reset() {
    this.client.id = null;
    this.client.name = null;
    this.client.address = null;
  }

}
