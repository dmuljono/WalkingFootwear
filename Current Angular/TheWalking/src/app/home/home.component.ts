import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { StorageService } from '../services/storage.service';
import { CustomerService } from '../services/customer.service';
import { Order } from '../model/order';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  content?: string;
  isLoggedIn = false;
  isCustomer = false;
  currentRole: any;
  currentUser:any;
  orders: Order[];
  constructor(private userService: UserService, private storageService: StorageService, private pserv: CustomerService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
    this.currentUser = this.storageService.getUser();
    this.currentUser.roles.map(role => this.currentRole=role);
    if(this.currentRole == "CUSTOMER"){
      this.isCustomer=true;
    }
    this.listOrder(this.currentUser.id);
    this.userService.getPublicContent().subscribe({
      next: data => {
        this.content = data;
      },
      error: err => {
        if (err.error) {
          try {
            const res = JSON.parse(err.error);
            this.content = res.message;
          } catch {
            this.content = `Error with status: ${err.status} - ${err.statusText}`;
          }
        } else {
          this.content = `Error with status: ${err.status}`;
        }
      }
    });
  }

  listOrder(customerId: number){
    this.pserv.getAllOrdersOnCustomer(customerId).subscribe(
      data=>{
        this.orders=data;
        console.log(data);
      }
    );
  }
}
