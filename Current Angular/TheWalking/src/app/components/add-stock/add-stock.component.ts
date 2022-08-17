import { Component, OnInit } from '@angular/core';
import { ManagerService } from 'src/app/services/manager.service';

@Component({
  selector: 'app-add-stock',
  templateUrl: './add-stock.component.html',
  styleUrls: ['./add-stock.component.css']
})
export class AddStockComponent implements OnInit {
  form: any = {
    productId:null,
    quantity:null
  };
  isSuccessful = false;
  isRegisterFailed = false;
  errorMessage = '';

  constructor(private managerService: ManagerService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { productId, quantity } = this.form;

    this.managerService.createStockRequest(productId, quantity).subscribe({
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
