import { Directive } from '@angular/core';
import { NG_VALIDATORS, ValidatorFn, Validator, FormControl, AbstractControl, ValidationErrors } from '@angular/forms';

@Directive({
  selector: '[confirmPassword][ngModel]',
  providers: [
    { provide: NG_VALIDATORS, useExisting: ConfirmePasswordValidator, multi: true }
  ]
})
export class ConfirmePasswordValidator implements Validator {
  validator: ValidatorFn;
  constructor() {
    this.validator = validateJuriNameFactory();
  }

  validate(c: FormControl) {
    return this.validator(c);
  }
}

function validateJuriNameFactory() : ValidatorFn {
  return (c: AbstractControl) => {

    var controls = Object.assign(c.parent.controls);

    if(controls['password'] && controls['password'].value)
      if(c.value != controls['password'].value){
        return {
          confirmPassword: false
        };
      }

    return null
  }
}
