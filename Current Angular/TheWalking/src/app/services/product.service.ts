import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl= 'http://localhost:5000/api/test/manager';
  headers= new HttpHeaders( {
      "Content-Type":"application/json"
    });

  constructor(private http:HttpClient) { }

  getProductList(id:number):Observable<Product[]>{
      const url=`http://localhost:5000/api/test/customer/category/${id}`;
      console.log(url);
      return this.http.get<Product[]>(url);
  }

  getAllProductList():Observable<Product[]>{
    const url=`http://localhost:5000/api/test/customer/allProducts`;
    console.log(url);
    return this.http.get<Product[]>(url);
}

  addProduct(p:any):any{
    const url='http://localhost:5000/api/test/manager/addProduct';
     return this.http.post<any>(url,p,{headers:this.headers});
}

getStocks(id:number):Observable<Product[]>{
  const url=`http://localhost:5000/api/test/customer/category/${id}`
  console.log(url);
  return this.http.get<Product[]>(url) ;
}
}
