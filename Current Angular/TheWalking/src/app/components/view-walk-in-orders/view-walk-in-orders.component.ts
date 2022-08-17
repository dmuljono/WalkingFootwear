import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WalkInOrder } from 'src/app/model/walk-in-order';
import { ManagerService } from 'src/app/services/manager.service';

@Component({
  selector: 'app-view-walk-in-orders',
  templateUrl: './view-walk-in-orders.component.html',
  styleUrls: ['./view-walk-in-orders.component.css']
})
export class ViewWalkInOrdersComponent implements OnInit {
  orders: WalkInOrder[];

  constructor(private pserv: ManagerService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.listOrder();
    }
    );
  }


  listOrder(){
    this.pserv.getAllWalkInOrders().subscribe(
      data=>{
        this.orders=data;
        console.log(data);
      }
    );
  }
}