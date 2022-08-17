import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { ManagerService } from 'src/app/services/manager.service';
import { ActivatedRoute } from '@angular/router';
import { WalkInCartService } from 'src/app/services/walk-in-cart.service';
import { CartItem } from 'src/app/model/cart-item';

@Component({
  selector: 'app-walk-in-billing',
  templateUrl: './walk-in-billing.component.html',
  styleUrls: ['./walk-in-billing.component.css']
})
export class WalkInBillingComponent implements OnInit {
  products:Product[];
  cartNumber: number = 0;
  cartItems: Product[] = Array();
  totalPrice: number = 0;
  constructor(private pserv:ManagerService, private route:ActivatedRoute, private cartService:WalkInCartService) { }

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
      }
    );
  }

  addToCart(tempProduct: Product){
    this.cartNumber += 1;
    this.totalPrice += tempProduct.unitPrice;
    this.cartItems.push(tempProduct);

    const theCartItem = new CartItem(tempProduct);
    console.log("theCartItem");
    this.cartService.addToCart(theCartItem);
    console.log("theCartItem2");
  }

}