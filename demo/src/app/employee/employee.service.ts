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
  getAllEmployee(): Observable<Employee[]>{
	return this._httpService.get("http://127.0.0.1:8989/ProjectDemo/retrieveAllEmployee")
	.map((response: Response)=>response.json()).catch(this.handleError);
  }
  private handleError(error: Response){
	return Observable.throw(error);
  }
  
  updateEmployee(employee:Employee){
	let body = JSON.stringify(employee);
	let headers = new Headers({'Content-Type':'application/json'});
	let options = new RequestOptions({headers:headers});
	return this._httpService.post("http://127.0.0.1:8989/ProjectDemo/updateEmployee",body,options);
  }
   deleteEmployee(employeeId : string){
		return this._httpService.delete("http://127.0.0.1:8989/ProjectDemo/deleteEmployee/"+employeeId);
	}
	 
 retrieveEmployee(employeeId : string): Observable<Employee[]>{
		return this._httpService.get("http://127.0.0.1:8989/ProjectDemo/retrieveEmployee/"+employeeId)
		.map((response: Response)=>response.json()).catch(this.handleError);
	}
}
