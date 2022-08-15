
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardCustomerComponent } from './board-customer/board-customer.component';
import { BoardEmployeeComponent } from './board-employee/board-employee.component';
import { BoardManagerComponent } from './board-manager/board-manager.component';

import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { ProductListComponent } from './components/product-list/product-list.component';
import { AddProductComponent } from './comp/add-product/add-product.component';
import { SearchComponent } from './components/search/search.component';
import { RegisterProductComponent } from './components/register-product/register-product.component';
import { CreateCustomerComponent } from './components/create-customer/create-customer.component';
import { ViewEmployeesComponent } from './components/view-employees/view-employees.component';
import { ViewStockComponent } from './components/view-stock/view-stock.component';
import { AddStockComponent } from './components/add-stock/add-stock.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardCustomerComponent,
    BoardEmployeeComponent,
    BoardManagerComponent,
    ProductListComponent,
    AddProductComponent,
    SearchComponent,
    RegisterProductComponent,
    CreateCustomerComponent,
    ViewEmployeesComponent,
    ViewStockComponent,
    AddStockComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }