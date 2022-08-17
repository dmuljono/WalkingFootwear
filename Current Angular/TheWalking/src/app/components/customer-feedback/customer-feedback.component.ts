import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order } from 'src/app/model/order';
import { CustomerService } from 'src/app/services/customer.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-customer-feedback',
  templateUrl: './customer-feedback.component.html',
  styleUrls: ['./customer-feedback.component.css']
})
export class CustomerFeedbackComponent implements OnInit {
  form: any = {
    tempOrder:null,
    rating:null,
    comment: null,
    deliveryOnTime: false,
  };
  isSuccessful = false;
  isSubmittedFailed = false;
  errorMessage = '';
  currentUser: any;
  orders: Order[];
  currentOrder: Order;

  constructor(private pserv: CustomerService, private storageService:StorageService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.currentUser = this.storageService.getUser();
    console.log(this.currentUser);
    this.route.paramMap.subscribe(()=>{
      let customerId=+this.route.snapshot.paramMap.get("id");
      this.listOrder(this.currentUser.id);
    }
    );
  }

  onSubmit(): void {
    const { tempOrder, rating, comment, deliveryOnTime } = this.form;
    console.log(tempOrder, rating);
    console.log(this.currentUser);
    this.pserv.sendFeedback(tempOrder, rating, comment, this.currentUser.id, deliveryOnTime).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSubmittedFailed = false;
      },
      error: err => {
        console.log(err);
        this.errorMessage = err.error.message;
        this.isSubmittedFailed = true;
      }
    });
  }

  listOrder(customerId: number){
    this.pserv.getAllOrdersOnCustomer(customerId).subscribe(
      data=>{
        this.orders=data;
        console.log(data);
      }
    );
  }
}

