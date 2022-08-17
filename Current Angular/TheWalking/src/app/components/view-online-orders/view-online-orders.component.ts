import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Feedback } from 'src/app/model/feedback';
import { Order } from 'src/app/model/order';
import { ManagerService } from 'src/app/services/manager.service';

@Component({
  selector: 'app-view-online-orders',
  templateUrl: './view-online-orders.component.html',
  styleUrls: ['./view-online-orders.component.css']
})
export class ViewOnlineOrdersComponent implements OnInit {
  orders: Order[];

  constructor(private pserv: ManagerService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.listOrder();
    }
    );
  }


  listOrder(){
    this.pserv.getAllOnlineOrders().subscribe(
      data=>{
        this.orders=data;
        console.log(data);
      }
    );
  }
}