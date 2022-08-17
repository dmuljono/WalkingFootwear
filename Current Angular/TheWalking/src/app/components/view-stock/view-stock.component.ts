import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { ManagerService } from 'src/app/services/manager.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-stock',
  templateUrl: './view-stock.component.html',
  styleUrls: ['./view-stock.component.css']
})
export class ViewStockComponent implements OnInit {
  products:Product[];
  constructor(private pserv:ManagerService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      let categoryId=+this.route.snapshot.paramMap.get("id");
      if(categoryId==0){
        categoryId=1;
      }
      this.listProduct(categoryId);
    }
    );
  }
  
  listProduct(id:number){
    this.pserv.getStocks(id).subscribe(
      data=>{
        this.products=data;
        console.log(data);
      }
    );
  }
}