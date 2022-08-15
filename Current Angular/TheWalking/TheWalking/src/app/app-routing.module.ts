
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardCustomerComponent } from './board-customer/board-customer.component';
import { BoardEmployeeComponent } from './board-employee/board-employee.component';
import { BoardManagerComponent } from './board-manager/board-manager.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { RegisterProductComponent } from './components/register-product/register-product.component';
import { CreateCustomerComponent } from './components/create-customer/create-customer.component';
import { ViewStockComponent } from './components/view-stock/view-stock.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'customer', component: BoardCustomerComponent },
  { path: 'employee', component: BoardEmployeeComponent },
  { path: 'manager', component: BoardManagerComponent },
  { path: 'search/:keyword', component: ProductListComponent},
  { path: 'registerProduct', component: RegisterProductComponent },
  { path: 'createCustomer', component: CreateCustomerComponent },
  { path: 'category/:id', component: ViewStockComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
