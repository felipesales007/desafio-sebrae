import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SpinnerModule } from 'src/app/shared/spinners/loading.module';
import { OrderDetaillComponent } from './order-detail.component';

export const routes1 = [{path: ':id', component: OrderDetaillComponent}]
@NgModule({
  declarations: [
    OrderDetaillComponent
  ],
  imports: [
    SpinnerModule,
    CommonModule,
    RouterModule.forChild(routes1),
  ]
})
export class OrderDetailModule { }


