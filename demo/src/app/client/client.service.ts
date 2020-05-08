import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { Client } from './client';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable({
	providedIn: 'root'
})
export class ClientService {

	private url: string;
	constructor(private httpService: Http) { }

	addClient(client: Client) {
		this.url = "http://127.0.0.1:8989/ProjectDemo/saveClient";
		let body = JSON.stringify(client);
		let headers = new Headers({ 'Content-Type': 'application/json' });
		let options = new RequestOptions({ headers: headers });
		return this.httpService.post(this.url, body, options);
	}

	getAllClient(): Observable<Client[]> {
		this.url = "http://127.0.0.1:8989/ProjectDemo/retrieveAll";
		return this.httpService.get(this.url)
			.map((response: Response) => response.json()).catch(this.handleError);
	}

	deleteClient(clientId: string) {
		this.url = "http://127.0.0.1:8989/ProjectDemo/deleteClient/";
		return this.httpService.delete(this.url + clientId);
	}

	retrieveClient(clientId: string): Observable<Client[]> {
		this.url = "http://127.0.0.1:8989/ProjectDemo/retrieveClient/";
		return this.httpService.get(this.url + clientId)
			.map((response: Response) => response.json()).catch(this.handleError);;
	}

	updateClient(client: Client) {
		this.url = "http://127.0.0.1:8989/ProjectDemo/updateClient";
		let body = JSON.stringify(client);
		let headers = new Headers({ 'Content-Type': 'application/json' });
		let options = new RequestOptions({ headers: headers });
		return this.httpService.post(this.url, body, options);
	}
	private handleError(error: Response) {
		return Observable.throw(error);
	}

}
