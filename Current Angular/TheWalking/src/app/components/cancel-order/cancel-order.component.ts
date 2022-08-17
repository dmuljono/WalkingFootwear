import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { CustomerService } from 'src/app/services/customer.service';
import { ActivatedRoute } from '@angular/router';
import { Order } from 'src/app/model/order';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-cancel-order',
  templateUrl: './cancel-order.component.html',
  styleUrls: ['./cancel-order.component.css']
})
export class CancelOrderComponent implements OnInit {
  form: any = {
    tempOrder:null,
    reasonforReturn:null
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
    const { tempOrder, reasonForReturn } = this.form;
    console.log(this.currentUser);
    this.pserv.sendCancelRequest(tempOrder, this.currentUser.id, reasonForReturn).subscribe({
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