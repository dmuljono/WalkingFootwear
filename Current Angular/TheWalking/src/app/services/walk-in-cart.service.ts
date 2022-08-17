import { Injectable } from '@angular/core';
import { CartItem } from '../model/cart-item';
import { Product } from '../model/product';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WalkInCartService {
  cartItems: CartItem[] = new Array();
  totalPrice: Subject<number> = new Subject<number>(); 
  totalQuantity: Subject<number> = new Subject<number>();
  totalPriceNum: number;
  totalQuantityNum: number;

  constructor() { }

  public addToCart(theCartItem: CartItem){
    let alreadyExistsInCart: boolean = false;
    let existingCartItem: CartItem = undefined;

    console.log(theCartItem.name);

    if (this.cartItems.length > 0) {
      for (let tempCartItem of this.cartItems) {
        if (tempCartItem.id === theCartItem.id) {
          existingCartItem = tempCartItem;
          break;
          }
      }
      alreadyExistsInCart = (existingCartItem != undefined)
    }

    

    if(alreadyExistsInCart){
      existingCartItem.quantity++;
    }
    else {
      this.cartItems.push(theCartItem);
    }

    this.computeCartTotals();    
  }

  public computeCartTotals(){
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0; 

    for(let currentCartItem of this.cartItems) {
      totalPriceValue += currentCartItem.quantity*currentCartItem.unitPrice;
      totalQuantityValue +=currentCartItem.quantity;
    }
    console.log(totalPriceValue);
    console.log(this.totalPrice);
    console.log(totalQuantityValue);
    this.totalPriceNum=totalPriceValue;
    this.totalQuantityNum=totalQuantityValue;
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);
  }

  public getTotalPrice():any{
    this.totalPrice.asObservable();
  }
}
