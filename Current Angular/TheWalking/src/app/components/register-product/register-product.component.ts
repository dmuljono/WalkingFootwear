import { Component, OnInit } from '@angular/core';
import { ManagerService } from 'src/app/services/manager.service';

@Component({
  selector: 'app-register-product',
  templateUrl: './register-product.component.html',
  styleUrls: ['./register-product.component.css']
})
export class RegisterProductComponent implements OnInit {
  form: any = {
    sku: null,
    productName: null,
    categoryId: null,
    description: null,
    unitPrice: null,
    imageUrl: null,
    available: false,
    unitsInStock: null
  };
  isSuccessful = false;
  isRegisterFailed = false;
  errorMessage = '';

  constructor(private managerService: ManagerService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { sku, productName, categoryId, description, unitPrice, imageUrl, available, unitsInStock } = this.form;

    this.managerService.registerProduct(sku, productName, categoryId, description, unitPrice, imageUrl, available, unitsInStock).subscribe({
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
