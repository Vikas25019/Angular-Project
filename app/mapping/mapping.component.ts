import { Component, OnInit } from '@angular/core';
import {Router , ActivatedRoute } from '@angular/router';
import { MappingService } from './mapping.service';
import {Employee} from '../employee/employee';

@Component({
  selector: 'app-mapping',
  templateUrl: './mapping.component.html',
  styleUrls: ['./mapping.component.css']
})
export class MappingComponent implements OnInit {

	employees: Employee[];
  employee = new Employee();
  
  constructor(private _mappingService: MappingService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    this.employee.id = this.route.snapshot.paramMap.get('id');
	this.viewMapping(this.employee.id);
  }

	viewMapping(employeeId: string){
	this._mappingService.viewMapping(employeeId)
		.subscribe((employeeData) => {
			this.employees = employeeData,
			console.log(employeeData)
			},(error) =>{
			console.log(error);})
	}
	
}
