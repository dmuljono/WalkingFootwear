import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { map } from 'rxjs';
import { Product } from '../model/product';
import { Employee } from '../model/employee';
import { Order } from '../model/order';
import { CartItem } from '../model/cart-item';
import { ProductService } from './product.service';

const CUSTOMER_API = 'http://localhost:5000/api/test/customer/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) {}

  // submitReturnRequest(orderId: number, reasonForReturn: String): Observable<any> {
  //   return this.http.post(
  //     CUSTOMER_API + 'returnOrder',
  //     {
  //       orderId, reasonForReturn
  //     },
  //     httpOptions
  //   );
  // }

  getAllOrdersOnCustomer(customerId: number):Observable<Order[]>{
    const url=`http://localhost:5000/api/test/customer/allOrdersOnCustomer/${customerId}`
    console.log(url);
    return this.http.get<Order[]>(url) ;
  }
  
  // submitCancelRequest(orderId: number, reasonForReturn: String): Observable<any> {
  //   return this.http.post(
  //     CUSTOMER_API + 'cancelOrder',
  //     {
  //       orderId, reasonForReturn
  //     },
  //     httpOptions
  //   );
  // }

  sendFeedback(orderId:number,rating:number,comment:string, customerId:number, deliveryOnTime: boolean){
    return this.http.post(
      CUSTOMER_API + 'customerFeedback',
      {
        orderId, rating, comment, customerId, deliveryOnTime
      },
      httpOptions
    );
  }

  sendReturnRequest(orderId:number, customerId:number, reasonForReturn:string){
    return this.http.post(
      CUSTOMER_API + 'returnOrder',
      {
        orderId, customerId, reasonForReturn
      },
      httpOptions
    );
  }

  sendCancelRequest(orderId:number, customerId:number, reasonForReturn:string){
    return this.http.post(
      CUSTOMER_API + 'cancelOrder',
      {
        orderId, customerId, reasonForReturn
      },
      httpOptions
    );
  }

  giveAddressAndOrder(customId:number, addr:string, productIds: number[], quantitys: number[]){
    console.log(customId);
    console.log(addr);
    return this.http.post(
      CUSTOMER_API + 'setCustomerAddressOrder',
      {
        customId, addr, productIds, quantitys
      },
      httpOptions
    );
  }

  giveOrder(customerId, productIds: number[], quantity: number[]){
    console.log(customerId);
    console.log(productIds);
    console.log(quantity);
    console.log("sending");
    return this.http.post(
      CUSTOMER_API + 'giveOrder',
      {
        customerId, productIds, quantity
      },
      httpOptions
    );
  }

  //nanti
  // giveOrder(customerId:number, address:string){
  //   return this.http.post(
  //     CUSTOMER_API + 'setCustomerAddress',
  //     {
  //       customerId, address
  //     },
  //     httpOptions
  //   );
  // }

}
