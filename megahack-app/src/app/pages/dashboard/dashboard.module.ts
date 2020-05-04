import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OrderService } from 'src/app/provider/services/order.service';
import { ProductService } from 'src/app/provider/services/product.service';
import { OrderModule } from 'src/app/shared/modals/order/order.module';
import { DashboardComponent } from './dashboard.component';
import { NavbarModule } from 'src/app/components/navbar.module';

export const routes = [{path: '', component: DashboardComponent}]

@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    NavbarModule,
    OrderModule,
    CommonModule,
    RouterModule.forChild(routes),
  ],
  providers: [ProductService, OrderService]
})
export class DashboardModule { }


