import { CartItem } from './cart-item';
import { Subject } from 'rxjs';

export class CartService {

    cartItems: CartItem[] = Array();
    totalPrice: Subject<number> = new Subject<number>();
    totalQuantity: Subject<number> = new Subject<number>();
    totalPriceNum: number = 0;
    totalQuantityNum: number = 0;


    addToCart(theCartItem :CartItem){
        let alreadyExistsInCart: boolean = false;
        let existingCartItem: CartItem = undefined;
        console.log(theCartItem);
        console.log(this.cartItems.length);

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
    console.log(this.cartItems.length);
    console.log()
  }

    computeCartTotals(){
        let totalPriceValue : number = 0;
        let totalQuantityValue : number = 0;

        for(let currentCartItem of this.cartItems){
            totalPriceValue += currentCartItem.quantity * currentCartItem.unitPrice;
            totalQuantityValue += currentCartItem.quantity;
        }
        this.totalPrice.next(totalPriceValue);
        this.totalQuantity.next(totalQuantityValue);
        this.totalPriceNum = totalPriceValue;
        console.log(this.totalPriceNum);
        this.totalQuantityNum = totalQuantityValue;
    }

}
