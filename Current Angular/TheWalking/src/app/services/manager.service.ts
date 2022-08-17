import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { map } from 'rxjs';
import { Product } from '../model/product';
import { Employee } from '../model/employee';
import { Order } from '../model/order';
import { Feedback } from '../model/feedback';
import { CancelOrder } from '../model/cancel-order';
import { ReturnOrder } from '../model/return-order';
import { WalkInOrder } from '../model/walk-in-order';

const MANAGER_API = 'http://localhost:5000/api/test/manager/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  constructor(private http: HttpClient) {}

  registerProduct(sku: string, name:string, categoryId: number, description:string, unitPrice:number, imageUrl:string,
    available:boolean, unitsInStock:number): Observable<any> {
    return this.http.post(
      MANAGER_API + 'products',
      {
        sku, name, categoryId, description, unitPrice, imageUrl, available, unitsInStock
      },
      httpOptions
    );
  }

  createCustomer(firstName: string, lastName:string, email: string, phoneNumber:string, address:string, password:string): Observable<any> {
    return this.http.post(
      MANAGER_API + 'customers',
      {
        firstName, lastName, email, phoneNumber, address, password
      },
      httpOptions
    );
  }

  createStockRequest(productId:number, quantity:number): Observable<any> {
    return this.http.post(
      MANAGER_API + 'addStock',
      {
        productId, quantity
      },
      httpOptions
    );
  }

  getStocks(id:number):Observable<Product[]>{
    const url=`http://localhost:5000/api/test/manager/category/${id}`
    console.log(url);
    return this.http.get<Product[]>(url) ;
}

getAllStocks():Observable<Product[]>{
  const url=`http://localhost:5000/api/test/manager/allProducts`;
  console.log(url);
  return this.http.get<Product[]>(url) ;
}

getAllEmployees():Observable<Employee[]>{
  const url=`http://localhost:5000/api/test/manager/allEmployees/`;
  console.log(url);
  return this.http.get<Employee[]>(url) ;
}


submitWalkInOrder(phoneNumber:string, email:string, productIds:number[], quantity:number[]): Observable<any> {
  return this.http.post(
    MANAGER_API + 'placeOrderWalkIn',
    {
      productIds, quantity, phoneNumber, email
    },
    httpOptions
  );
}
getAllFeedback():Observable<Feedback[]>{
  const url=`http://localhost:5000/api/test/manager/getAllFeedbacks`;
  console.log(url);
  return this.http.get<Feedback[]>(url) ;
}

getAllCancels():Observable<CancelOrder[]>{
  const url=`http://localhost:5000/api/test/manager/getAllCancels`;
  console.log(url);
  return this.http.get<CancelOrder[]>(url) ;
}

getAllReturns():Observable<ReturnOrder[]>{
  const url=`http://localhost:5000/api/test/manager/getAllReturns`;
  console.log(url);
  return this.http.get<ReturnOrder[]>(url) ;
}

getAllWalkInOrders():Observable<WalkInOrder[]>{
  const url=`http://localhost:5000/api/test/manager/getAllWalkInOrders`;
  console.log(url);
  return this.http.get<WalkInOrder[]>(url) ;
}

getAllOnlineOrders():Observable<Order[]>{
  const url=`http://localhost:5000/api/test/manager/getOnlineOrders`;
  console.log(url);
  return this.http.get<Order[]>(url) ;
}

}
