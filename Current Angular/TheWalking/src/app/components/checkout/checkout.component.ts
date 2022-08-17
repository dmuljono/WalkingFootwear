import { Component, OnInit } from '@angular/core';
import { Inject } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import { CartService } from 'src/app/model/cart-service';
import { ShopformService } from 'src/app/services/shopform.service';


@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  checkoutFormGroup : FormGroup;
  totalPrice: number = 0;
  totalQuantity: number = 0;

  creditCardYears : number [] = [];
  creditCardMonths : number [] = [];
  constructor(private formBuilder : FormBuilder,private cartService: CartService, private ShopformService : ShopformService) { }

  ngOnInit(): void {
    this.checkoutFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({

        firstName : [''],
        lastName : [''],
        email : ['']
      })


    });

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

    console.log("Handling the submit button");
    console.log(this.checkoutFormGroup.get('customer').value);

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
