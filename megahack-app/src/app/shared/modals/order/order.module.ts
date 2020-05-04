import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { OrderlComponent } from './order.component';
import { ChangePaymentComponent } from '../../change-payment/change-payment.component';
import { SpinnerModule } from '../../spinners/loading.module';

@NgModule({
  declarations: [
    OrderlComponent,
    ChangePaymentComponent
  ],
  exports: [OrderlComponent],
  imports: [
    SpinnerModule,
    CommonModule
  ]
})
export class OrderModule { }


