import { Component, OnInit, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ValidationFormUtil } from 'src/app/utils/validate-form.util';

@Component({
  selector: 'error-form-message',
  templateUrl: './error-form-message.component.html'
})
export class ErrorFormMessageComponent implements OnInit {

  constructor() { }

  @Input() frm: FormControl
  @Input() label: string

  ngOnInit() {
  }

  get errorMessage(){

    for(const propertyName in this.frm.errors){
      if(this.frm.errors.hasOwnProperty(propertyName) &&
        this.frm.touched
      ){
        return ValidationFormUtil.getErrorMsg(this.label, propertyName, this.frm.errors[propertyName])
      }
    }

  }

}
