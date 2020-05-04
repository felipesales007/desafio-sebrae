import { NgForm, FormControl } from '@angular/forms'
import { ViewChild, Directive, ContentChild, ElementRef } from '@angular/core';

export abstract class ValidationFormUtil {
 
  verifyValidTouched(field) {
    return this.invalidAndTouched(field)
  }

  showCssError(field) {
    return {
      'is-invalid': this.invalidAndTouched(field)
    }
  }

  invalidAndTouched(field) {
    return !field.valid && field.touched
  }

  public static getErrorMsg(fieldName: string, validatorName: string, validatorValue?: any) {
    const config = {
      'email': `email inválido`,
      'confirmPassword': `a senhas estào diferentes`,
      'required': `${fieldName} obrigatório`,
      'minlength': `digite pelo menos ${validatorValue.requiredLength} caracteres`,
      'maxlength': `digite no máximo ${validatorValue.requiredLength} caracteres`
    }
    return config[validatorName]
  }

  abstract submit(frm?)

  onSubmit(frm: NgForm) {      
    if (frm.valid) {
      this.submit(frm)
    } else {
      Object.values(frm.controls).forEach(field => {
        field.markAsTouched()
      })
    }
  }

  reset(frm){
    if(frm){
      Object.keys(frm.controls).forEach(key => {
        frm.controls[key].setErrors(null);
        frm.controls[key].markAsUntouched();
        frm.controls[key].clearValidators(); 
      });
    }   
  }

  confirmPassword(value: FormControl, otherValue: FormControl){
    if(value != otherValue){
      value.markAsTouched()
    }
  }
}
