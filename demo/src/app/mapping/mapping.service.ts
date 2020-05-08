import { Injectable } from '@angular/core';
import {Http,Response , RequestOptions,Headers} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Employee } from '../employee/employee';

@Injectable({
  providedIn: 'root'
})
export class MappingService {

  constructor(private _httpService: Http) { } 
  
  viewMapping(employeeId : string): Observable<Employee[]>{
		return this._httpService.get("http://127.0.0.1:8989/ProjectDemo/viewMapping/"+employeeId)
		.map((response: Response)=>response.json()).catch(this.handleError); 
	}
  private handleError(error: Response){
	return Observable.throw(error);
  }
}
 
