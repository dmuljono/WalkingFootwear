import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { CustomerService } from 'src/app/services/customer.service';
import { ActivatedRoute } from '@angular/router';
import { Order } from 'src/app/model/order';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-return-order',
  templateUrl: './return-order.component.html',
  styleUrls: ['./return-order.component.css']
})
export class ReturnOrderComponent implements OnInit {
  orders: Order[];
  currentUser: any;
  currentUserId: number;
  targetOrder: Order;
  form: any = {
    description:null,
  };
  constructor(private pserv:CustomerService, private route:ActivatedRoute, private storageService: StorageService) { }

  //keepcheck
  ngOnInit(): void {
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
  
  listOrder(customerId: number){
    this.pserv.getAllOrdersOnCustomer(customerId).subscribe(
      data=>{
        this.orders=data;
        console.log(data);
      }
    );
  }
  
  focusOrder(target: Order){
    this.targetOrder=target;
    
  }

  onSubmit(){
    const { description } = this.form;
    this.pserv.submitReturnRequest(this.targetOrder.orderId, description);
  }


}