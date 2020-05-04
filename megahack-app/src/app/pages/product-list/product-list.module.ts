import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OrderService } from 'src/app/provider/services/order.service';
import { ProductService } from 'src/app/provider/services/product.service';
import { SpinnerModule } from 'src/app/shared/spinners/loading.module';
import { ProductListComponent } from './product-list.component';
import { OrderlComponent } from 'src/app/shared/modals/order/order.component';

export const routesProduct = [{path: ':establishmentId', component: ProductListComponent}]

@NgModule({
  declarations: [
    ProductListComponent  
  ],
  imports: [
    SpinnerModule,
    CommonModule,
    RouterModule.forChild(routesProduct),
  ],
  providers: [ProductService, OrderService]
})
export class ProductListModule { }


