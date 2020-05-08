import { Component, OnInit } from '@angular/core';
import {  Router ,ActivatedRoute } from '@angular/router';
import {Employee} from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

 
  employees: Employee[];
  employee = new Employee();
  constructor(private _employeeService: EmployeeService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
	this.employee.id = this.route.snapshot.paramMap.get('id');
	this.employee.clientId = this.route.snapshot.paramMap.get('clientId');
  }
  
   updateEmployee():void{
		this._employeeService.updateEmployee(this.employee)
		.subscribe((response) => {
			console.log(response);
			alert(response["_body"]);
			this.router.navigate(['./showAllEmployee']);
		},
		(error)=>{
			console.log(error);
		}
		
		)
	}

}
