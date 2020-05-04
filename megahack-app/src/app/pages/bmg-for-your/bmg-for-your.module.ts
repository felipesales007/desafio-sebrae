import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';
import { BmgForYourComponent } from './bmg-for-your.component';

export const routes = [{path: '', component: BmgForYourComponent}]

@NgModule({
  declarations: [
    BmgForYourComponent
  ],
  imports: [
    CommonModule,
    IonicModule,
    RouterModule.forChild(routes),
  ],
  providers:[
  ]
})
export class BmgForYouModule { }
