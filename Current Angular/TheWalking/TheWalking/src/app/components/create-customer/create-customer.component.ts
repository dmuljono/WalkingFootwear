import { Component, OnInit } from '@angular/core';
import { ManagerService } from 'src/app/_services/manager.service';
@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})

export class CreateCustomerComponent implements OnInit {
  form: any = {
    firstName: null,
    lastName: null,
    email: null,
    phoneNumber: null,
    address: null,
    password: null
  };
  isSuccessful = false;
  isRegisterFailed = false;
  errorMessage = '';

  constructor(private managerService: ManagerService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { firstName,lastName,email,phoneNumber,address,password} = this.form;

    this.managerService.createCustomer(firstName,lastName,email,phoneNumber,address,password).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isRegisterFailed = false;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isRegisterFailed = true;
      }
    });
  }
}
