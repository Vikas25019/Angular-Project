import { Component, OnInit } from '@angular/core';
import {Router , ActivatedRoute } from '@angular/router';
import {Employee} from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-view-employee-page',
  templateUrl: './view-employee-page.component.html',
  styleUrls: ['./view-employee-page.component.css']
})
export class ViewEmployeePageComponent implements OnInit {

 
  employees: Employee[];
  employee = new Employee();
  
  constructor(private _employeeService: EmployeeService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
	this.employee.id = this.route.snapshot.paramMap.get('id');
	console.log(this.employee.id);
	this.retrieveEmployee(this.employee.id);
  }
	
	retrieveEmployee(employeeId: string){
	this._employeeService.retrieveEmployee(employeeId)
		.subscribe((employeeData) => {
			this.employees = employeeData,
			console.log(employeeData)
			},(error) =>{
			console.log(error);})
	}
	
	deleteEmployee(employeeId: string){
	this._employeeService.deleteEmployee(employeeId)
		.subscribe((response) => {console.log(response); this.router.navigate(['./showemployee']);},(error) =>{
			console.log(error);})
	}
}
