
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
import { ViewEmployeesComponent } from './components/view-employees/view-employees.component';
import { AddStockComponent } from './components/add-stock/add-stock.component';
import { AllStocksComponent } from './components/all-stocks/all-stocks.component';
import { WalkInBillingComponent } from './components/walk-in-billing/walk-in-billing.component';
import { WalkInBillingCheckoutComponent } from './components/walk-in-billing-checkout/walk-in-billing-checkout.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { ReturnOrderComponent } from './components/return-order/return-order.component';
import { CancelOrderComponent } from './components/cancel-order/cancel-order.component';
import { CheckoutComponent } from './components/checkout/checkout.component';

// import { AddProductComponent } from './components/add-product/add-product.component';

const routes: Routes = [
//{ path: 'add', component: AddProductComponent},
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
  { path: 'products', component: ProductListComponent},
  { path: 'viewEmployees', component: ViewEmployeesComponent},
  { path: 'requestStock', component: AddStockComponent},
  {path: 'allProducts', component: AllStocksComponent},
  {path: 'walkInBilling', component:WalkInBillingComponent},
  {path: 'walkInBilling/checkout', component:WalkInBillingCheckoutComponent},
  {path: 'returnOrder', component:ReturnOrderComponent},
  {path: 'cancelOrder', component:CancelOrderComponent},
  {path: 'cart-details', component: CartDetailsComponent},
  {path: 'checkout', component: CheckoutComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full' }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
