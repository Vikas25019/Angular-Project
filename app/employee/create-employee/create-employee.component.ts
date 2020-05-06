import { Component, OnInit } from '@angular/core';
import {Employee} from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employees: Employee[];
  employee = new Employee();
  constructor(private _employeeService: EmployeeService) { }

  ngOnInit(): void {
  }
  
  
  addEmployee():void{
		this._employeeService.addEmployee(this.employee)
		.subscribe((response) => {
			console.log(response);
			alert(response["_body"]);
			this.reset();
		},
		(error)=>{
			console.log(error);
		}
		
		)
	}
	private reset(){
	  this.employee.id = null;
	  this.employee.clientId = null;
	  this.employee.name = null;
	  this.employee.department = null;
	  this.employee.email = null;
	  this.employee.dateOfBirth = null;
	 
	 }
  
  
	
}
