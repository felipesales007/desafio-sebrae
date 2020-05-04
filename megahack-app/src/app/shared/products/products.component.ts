import { ProductService } from 'src/app/provider/services/product.service';
import { Product } from './../../models/product.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
})
export class ProductsComponent implements OnInit {

  products: Array<Product> = new Array<Product>();
  productService: ProductService;
  showSpinner: boolean = false;

  constructor(productService: ProductService) {
    this.productService = productService;
    this.getAllProduct();
   }

   getAllProduct(){
    this.showSpinner = true
    this.productService.getAllByEstablishment(1).subscribe(data => {
      this.products = data;
      this.showSpinner = false
    }, () => this.showSpinner = false)
   }

  ngOnInit() {}

  disabledProduct(id) {
    this.productService.enableDisableProduct(id, false).subscribe();
    this.getAllProduct();
  }
}
