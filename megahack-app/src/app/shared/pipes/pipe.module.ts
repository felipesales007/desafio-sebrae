import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DateTablePipe } from './date-table.pipe';
import { MessageIsmePipe } from './message-isme.pipe';
import { MatMomentDateModule } from '@angular/material-moment-adapter';

@NgModule({
  declarations: [
    MessageIsmePipe,
    DateTablePipe,
  ],
  exports: [DateTablePipe,MessageIsmePipe],
  imports: [
    CommonModule,
    MatMomentDateModule
  ]
})
export class PipeModule { }
