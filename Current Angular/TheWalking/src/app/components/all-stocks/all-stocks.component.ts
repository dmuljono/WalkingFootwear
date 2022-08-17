import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { ManagerService } from 'src/app/services/manager.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-all-stocks',
  templateUrl: './all-stocks.component.html',
  styleUrls: ['./all-stocks.component.css']
})
export class AllStocksComponent implements OnInit {
  products:Product[];
  constructor(private pserv:ManagerService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      let categoryId=+this.route.snapshot.paramMap.get("id");
      if(categoryId==0){
        categoryId=1;
      }
      this.listProduct();
    }
    );
  }
  
  listProduct(){
    this.pserv.getAllStocks().subscribe(
      data=>{
        this.products=data;
        console.log(data);
      }
    );
  }
}