import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:5000/api/test/';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getCustomerBoard(): Observable<any> {
    return this.http.get(API_URL + 'customer', { responseType: 'text' });
  }
  
  getEmployeeBoard(): Observable<any> {
    return this.http.get(API_URL + 'employee', { responseType: 'text' });
  }

  getManagerBoard(): Observable<any> {
    return this.http.get(API_URL + 'manager', { responseType: 'text' });
  }
}