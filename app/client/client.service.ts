import { Injectable } from '@angular/core';
import {Http,Response , RequestOptions,Headers} from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { Client } from './client';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private _httpService: Http) { }
  
  addClient(client:Client){
	let body = JSON.stringify(client);
	let headers = new Headers({'Content-Type':'application/json'});
	let options = new RequestOptions({headers:headers});
	return this._httpService.post("http://127.0.0.1:8989/ProjectDemo/saveClient",body,options);
  }
  
  getAllClient(): Observable<Client[]>{
	return this._httpService.get("http://127.0.0.1:8989/ProjectDemo/retrieveAll")
	.map((response: Response)=>response.json()).catch(this.handleError);
  }
  private handleError(error: Response){
	return Observable.throw(error);
  }
  
  deleteClient(clientId : string){
		return this._httpService.delete("http://127.0.0.1:8989/ProjectDemo/deleteClient/"+clientId);
	}

  retrieveClient(clientId : string): Observable<Client[]>{
		return this._httpService.get("http://127.0.0.1:8989/ProjectDemo/retrieveClient/"+clientId)
		.map((response: Response)=>response.json()).catch(this.handleError);;
	}
	
  updateClient(client:Client){
	let body = JSON.stringify(client);
	let headers = new Headers({'Content-Type':'application/json'});
	let options = new RequestOptions({headers:headers});
	return this._httpService.post("http://127.0.0.1:8989/ProjectDemo/updateClient",body,options);
  }
  
}
