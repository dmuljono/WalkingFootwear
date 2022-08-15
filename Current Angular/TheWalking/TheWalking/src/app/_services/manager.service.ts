import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { map } from 'rxjs';
import { Product } from '../model/product';

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

  getStocks(id:number):Observable<Product[]>{
    const url=`http://localhost:5000/api/test/manager/category/${id}`
    console.log(url);
    return this.http.get<Product[]>(url) ;
}

}
