import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { LoadingLoginComponent } from './loading-login/loading-login.component';
import { LoadingComponent } from './loading/loading.component';

@NgModule({
  declarations: [
    LoadingComponent,
    LoadingLoginComponent
  ],
  exports: [
    LoadingComponent,
    LoadingLoginComponent
  ],
  imports: [
    CommonModule,
    MatProgressSpinnerModule
  ]
})
export class SpinnerModule { }


