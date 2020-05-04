import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { PipeModule } from '../../shared/pipes/pipe.module';
import { RouterModule } from '@angular/router';
import { ChatComponent } from './chat.component';
import { OrderModule } from 'src/app/shared/modals/order/order.module';

export const routes = [{path: ':to_user/:establishment', component: ChatComponent}]

@NgModule({
  declarations: [
    ChatComponent
  ],
  imports: [
    OrderModule,
    PipeModule,
    CommonModule,
    IonicModule,
    FormsModule,
    RouterModule.forChild(routes),
  ],
  providers:[
  ]
})
export class ChatModule { }
