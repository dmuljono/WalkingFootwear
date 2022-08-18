import { Component, OnInit } from '@angular/core';
import { Inject } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import { TitleStrategy } from '@angular/router';
import { CartService } from 'src/app/model/cart-service';
import { CustomerService } from 'src/app/services/customer.service';
import { ShopformService } from 'src/app/services/shopform.service';
import { StorageService } from 'src/app/services/storage.service';


@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  form: any = {
    street: null,
    city: null,
    state: null,
    zipCode: null
  };
  isSuccessful: boolean;
  isRegisterFailed: boolean;
  errorMessage = '';

  checkoutFormGroup : FormGroup;
  totalPrice: number = 0;
  totalQuantity: number = 0;

  creditCardYears : number [] = [];
  creditCardMonths : number [] = [];
  fullAddress: string;
  userId: number;
  productIds: number[] = Array();
  quantitys: number[] = Array();

  constructor(private formBuilder : FormBuilder,private cartService: CartService, private ShopformService : ShopformService, 
    private storageService: StorageService, private customerService: CustomerService) { }

  ngOnInit(): void {
    console.log(this.cartService.totalPriceNum);
    const startMonth : number = new Date().getMonth() +1;
    console.log("startMonth:" + startMonth);

    this.ShopformService.getCreditCardMonths(startMonth).subscribe(
      data => {
        console.log("Retrieve credit card months: " +JSON.stringify(data));
        this.creditCardMonths = data;
      }
    );

    this.ShopformService.getCreditCardYears().subscribe(
      data => {
        console.log("Retrieve credit card years: " +JSON.stringify(data));
        this.creditCardYears = data;
      }
    );
 
  }

  onSubmit(){
    const { street, city, state, zipCode} = this.form;
    const {cardNum, expiry, CVV} = this.form;
    this.fullAddress = street+ " "+city+ " "+state+ " "+zipCode;
    this.userId = this.storageService.getUser().id;
    console.log(this.fullAddress);
    console.log(this.cartService.cartItems);
    console.log(this.cartService.totalQuantityNum+this.cartService.totalPriceNum);
    this.cartService.cartItems.map(data => this.productIds.push(data.id));
    this.cartService.cartItems.map(data => this.quantitys.push(data.quantity));
    console.log(this.quantitys.length+" "+this.productIds.length+" lengths");
    this.customerService.giveAddressAndOrder(this.userId, this.fullAddress, this.productIds, this.quantitys).subscribe({
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

  reviewCartDetails() {

    // subscribe to cartService.totalQuantity
    this.cartService.totalQuantity.subscribe(
      totalQuantity => this.totalQuantity = totalQuantity
    );

    // subscribe to cartService.totalPrice
    this.cartService.totalPrice.subscribe(
      totalPrice => this.totalPrice = totalPrice
    );

  }


  handleMonthsAndYears(){
    const creditCardFormGroup = this.checkoutFormGroup.get('creditCard');

    const currentYear : number = new Date().getFullYear();

    const selectedYear : number = Number(creditCardFormGroup.value.expirationYear);

    let startMonth: number;

    if(currentYear === selectedYear){
      startMonth = new Date().getMonth() +1;

    }
    else{
      startMonth =1;
    }

    this.ShopformService.getCreditCardMonths(startMonth).subscribe(
      data => {
        console.log("Retrieved credit card months: " + JSON.stringify(data));
        this.creditCardMonths = data;
      }


    )


  }

}
