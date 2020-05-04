import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ErrorFormMessageComponent } from './error-form-message.component'

@NgModule({
  declarations: [
    ErrorFormMessageComponent
  ],
  exports: [
    ErrorFormMessageComponent
  ],
  imports: [
    FormsModule,
    CommonModule
  ],
  providers:[]
})
export class YmFormModule { }
