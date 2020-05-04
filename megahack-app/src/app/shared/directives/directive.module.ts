import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { VarDirective } from './var.directive';
@NgModule({
  declarations: [
    VarDirective
  ],
  exports: [VarDirective],
  imports: [
    CommonModule
  ]
})
export class DirectiveModule { }
