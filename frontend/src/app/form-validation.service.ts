import { Injectable } from '@angular/core';
import {AbstractControl, ValidatorFn, Validators} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class FormValidationService {

  constructor() { }

  getPasswordValidator(): ValidatorFn {
    return Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{2,}$/);
  }

  getPasswordMatchValidator(
    passwordFieldName: string,
    repeatPasswordFieldName: string,
  ) {
    return (control: AbstractControl) => {
      const passwordField = control.get(passwordFieldName);
      const repeatPasswordField = control.get(repeatPasswordFieldName);
      const password = passwordField?.value;
      const repeatPassword = repeatPasswordField?.value;
      if (password !== repeatPassword || !password) {
        repeatPasswordField?.setErrors({matchPassword: true});
      } else {
        repeatPasswordField?.setErrors(null);
      }
    }
  }
}
