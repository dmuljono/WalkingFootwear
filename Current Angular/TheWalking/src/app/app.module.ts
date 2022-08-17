
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { AddProductComponent } from './components/add-product/add-product.component';
import { SearchComponent } from './components/search/search.component';
import { RegisterProductComponent } from './components/register-product/register-product.component';
import { CreateCustomerComponent } from './components/create-customer/create-customer.component';
import { ViewEmployeesComponent } from './components/view-employees/view-employees.component';
import { ViewStockComponent } from './components/view-stock/view-stock.component';
import { AddStockComponent } from './components/add-stock/add-stock.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AllStocksComponent } from './components/all-stocks/all-stocks.component';
import { WalkInBillingComponent } from './components/walk-in-billing/walk-in-billing.component';
import { WalkInBillingCheckoutComponent } from './components/walk-in-billing-checkout/walk-in-billing-checkout.component';
import { CartStatusComponent } from './components/cart-status/cart-status.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { ReturnOrderComponent } from './components/return-order/return-order.component';
import { CancelOrderComponent } from './components/cancel-order/cancel-order.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { CustomerFeedbackComponent } from './components/customer-feedback/customer-feedback.component';
import { ViewFeedbackComponent } from './components/view-feedback/view-feedback.component';
import { ViewCancelReturnComponent } from './components/view-cancel-return/view-cancel-return.component';
import { ViewWalkInOrdersComponent } from './components/view-walk-in-orders/view-walk-in-orders.component';
import { ViewOnlineOrdersComponent } from './components/view-online-orders/view-online-orders.component';
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
    AddStockComponent,
    NotFoundComponent,
    AllStocksComponent,
    WalkInBillingComponent,
    WalkInBillingCheckoutComponent,
    CartStatusComponent,
    ProductDetailsComponent,
    CartDetailsComponent,
    ReturnOrderComponent,
    CancelOrderComponent,
    CheckoutComponent,
    CustomerFeedbackComponent,
    ViewFeedbackComponent,
    ViewCancelReturnComponent,
    ViewWalkInOrdersComponent,
    ViewOnlineOrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }