import { Injectable } from '@angular/core';
import {Http,Response , RequestOptions,Headers} from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { Employee } from './employee';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private _httpService: Http) { }
  addEmployee(employee:Employee){
	let body = JSON.stringify(employee);
	let headers = new Headers({'Content-Type':'application/json'});
	let options = new RequestOptions({headers:headers});
	return this._httpService.post("http://127.0.0.1:8989/ProjectDemo/saveEmployee",body,options);
  }
}
