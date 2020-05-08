import { Component, OnInit } from '@angular/core';
import {Employee} from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-show-all-employee',
  templateUrl: './show-all-employee.component.html',
  styleUrls: ['./show-all-employee.component.css']
})
export class ShowAllEmployeeComponent implements OnInit {

employees: Employee[];
  employee = new Employee();
  
  constructor(private _employeeService: EmployeeService) { }
	
  getEmployee():void{
		this._employeeService.getAllEmployee()
		.subscribe((employeeData) => {
			this.employees = employeeData,
			console.log(employeeData)
			},(error) =>{
			console.log(error);
		});
	}
  ngOnInit(): void {
  this.getEmployee();
  }
  
  deleteEmployee(employeeId: string){
	
	this._employeeService.deleteEmployee(employeeId)
		.subscribe((response) => {console.log(response); this.getEmployee()},(error) =>{
			console.log(error);})
	}

}
