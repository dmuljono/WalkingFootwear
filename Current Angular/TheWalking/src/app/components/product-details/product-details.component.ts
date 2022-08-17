import { Component, OnInit } from '@angular/core';
import { CartItem } from 'src/app/model/cart-item';
import { Product } from 'src/app/model/product';
import { CartService } from 'src/app/model/cart-service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  product: Product = new Product();
    cartService: CartService;
  
  constructor() { }

  ngOnInit(): void {
  }

  addToCart(){

    console.log('Adding to cart: ${this.product.name}, ${this.product.unitPrice}');
    const theCartItem = new CartItem(this.product);

    this.cartService.addToCart(theCartItem);
  }

}
