import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { ManagerService } from 'src/app/services/manager.service';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/model/cart-item';
import { WalkInCartService } from 'src/app/services/walk-in-cart.service';

@Component({
  selector: 'app-walk-in-billing-checkout',
  templateUrl: './walk-in-billing-checkout.component.html',
  styleUrls: ['./walk-in-billing-checkout.component.css']
})
export class WalkInBillingCheckoutComponent implements OnInit {
  totalPrice: number = 0.00;
  totalQuantity: number = 0;
  form: any = {
    phoneNumber: null,
    email: null
  };
  isSuccessful = false;
  isRegisterFailed = false;
  errorMessage = '';
  productIds: number[] = Array();
  quantities: number[] = Array();

  constructor(private pserv:ManagerService, private route:ActivatedRoute, private cartService: WalkInCartService) { }

  ngOnInit(): void {
    this.updateCartStatus();
  }

  updateCartStatus(){
    this.totalPrice = this.cartService.totalPriceNum;
    this.totalQuantity = this.cartService.totalPriceNum;
  }

  onSubmit(): void {
    const { phoneNumber,email} = this.form;
    this.productIds = Array();
    this.quantities = Array();
    this.cartService.cartItems.map(iteme => this.productIds.push(iteme.id));
    this.cartService.cartItems.map(iteme => this.quantities.push(iteme.quantity));
    this.pserv.submitWalkInOrder(phoneNumber, email, this.productIds, this.quantities).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isRegisterFailed = false;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isRegisterFailed = true;
      }
    });
  }
  
}