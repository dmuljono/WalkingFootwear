import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/model/employee';
import { ManagerService } from 'src/app/services/manager.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-employees',
  templateUrl: './view-employees.component.html',
  styleUrls: ['./view-employees.component.css']
})
export class ViewEmployeesComponent implements OnInit {
  employees:Employee[];
  constructor(private pserv:ManagerService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.listEmployee();
    }
    );
  }
  
  listEmployee(){
    this.pserv.getAllEmployees().subscribe(
      data=>{
        this.employees=data;
        console.log(data);
      }
    );
  }
}