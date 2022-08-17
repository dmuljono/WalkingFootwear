import { Component, OnInit } from '@angular/core';
import {Product} from 'src/app/model/product'
import {ProductService} from 'src/app/services/product.service'
import {ActivatedRoute} from '@angular/router'
import {CartItem} from 'src/app/model/cart-item'
import { CartService } from 'src/app/model/cart-service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products:Product[];
  cartService: CartService;


  constructor(private pserv:ProductService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      let categoryId=+this.route.snapshot.paramMap.get("id");
      if(categoryId==0){
        categoryId = 1;
      }
      this.listProduct(categoryId);
    });
  }

  listProduct(id:number){
    this.pserv.getProductList(id).subscribe(data=>{
      this.products = data;
    });
  }

  addToCart(theProduct : Product){

    console.log('Adding to Cart: ${theProduct.name}, ${theProduct.unitPrice}');
    const theCartItem = new CartItem(theProduct);

    this.cartService.addToCart(theCartItem);



  }

}
