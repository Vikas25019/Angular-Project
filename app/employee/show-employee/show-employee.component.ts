import { Component, OnInit } from '@angular/core';
import {Employee} from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-show-employee',
  templateUrl: './show-employee.component.html',
  styleUrls: ['./show-employee.component.css']
})
export class ShowEmployeeComponent implements OnInit {

  employees: Employee[];
  employee = new Employee();
  
  constructor(private _employeeService: EmployeeService) { }

  ngOnInit(): void {
  }
	
	retrieveEmployee(employeeId: string){
	this._employeeService.retrieveEmployee(employeeId)
		.subscribe((employeeData) => {
			console.log(employeeData)
			},(error) =>{
			console.log(error);})
	}

}
