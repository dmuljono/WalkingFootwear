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
    selectOrder: null,
    rating:null,
    comment: null,
    //ontime
  };
  isSuccessful = false;
  isSubmittedFailed = false;
  errorMessage = '';
  currentUser: any;
  orders: Order[];
  currentUserId: number;

  constructor(private pserv: CustomerService, private storageService:StorageService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.currentUser = this.storageService.getUser();
    this.route.paramMap.subscribe(()=>{
      this.currentUser = this.storageService.getUser();
      this.currentUserId = this.currentUser.userId;
      let customerId=+this.route.snapshot.paramMap.get("id");
      if(customerId==0){
        customerId=1;
      }
      this.listOrder(customerId);
    }
    );
  }

  onSubmit(): void {
    const { selectOrder, rating, comment } = this.form;

    this.pserv.sendFeedback(selectOrder.orderId, rating, comment, this.currentUser.userId).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSubmittedFailed = false;
      },
      error: err => {
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

