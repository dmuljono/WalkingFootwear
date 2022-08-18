import { Component, OnInit } from '@angular/core';
import {Product} from 'src/app/model/product'
import {ProductService} from 'src/app/services/product.service'
import {ActivatedRoute} from '@angular/router'
import {CartItem} from 'src/app/model/cart-item'
import { CartService } from 'src/app/model/cart-service';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products:Product[] =[];
  totalPrice : number = 0.00;
  totalQuantity : number = 0;
  
  


  constructor(private pserv:ProductService, private route:ActivatedRoute,private cartService: CartService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.listProduct();
      this.updateCartStatus();
    });

  }

  listProduct(){
    this.pserv.getAllProductList().subscribe(data=>{
      this.products = data;
    });
  }

  addToCart(theProduct : Product){

    console.log(`Adding to Cart: ${theProduct.name}, ${theProduct.unitPrice}`);
    
    const theCartItem = new CartItem(theProduct);

    this.cartService.addToCart(theCartItem);
    console.log(this.cartService.cartItems);
    this.cartService.cartItems.map(items => console.log(items));



  }
  updateCartStatus() {
    this.cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );

    this.cartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );
}



}
