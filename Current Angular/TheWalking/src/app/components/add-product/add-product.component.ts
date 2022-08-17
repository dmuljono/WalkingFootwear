import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms'
import {ProductService} from 'src/app/services/product.service'

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  pform = new FormGroup(
    {
      sku: new FormControl(),
      name: new FormControl(),
      description: new FormControl(),
      unitPrice: new FormControl(),
      imageUrl: new FormControl(),
      available: new FormControl(),
      unitsInStock: new FormControl(),
      category: new FormGroup(
        {
          categoryId: new FormControl()
        }
      )

    }
  )

  constructor(private pserve: ProductService) { }

  ngOnInit(): void {
  }
  onSubmit(){
    const p = JSON.stringify(this.pform.value);
    this.pserve.addProduct(p).subscribe(data=>{
      console.log(data);
    });
  }

}
