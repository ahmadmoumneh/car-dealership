import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";
import { User } from "src/app/classes/user";

export const requiredValidator = (fieldName: string): ValidatorFn => {
  return (control: AbstractControl): ValidationErrors | null => {
    const valid = control.value;
    return !valid? {Required: {message: `${fieldName} is required.`}} : null;
  }
};

export const patternValidator = (fieldName: string, pattern: string, message: string): ValidatorFn => {
  return (control: AbstractControl): ValidationErrors | null => {
    const valid = control.value?.match(pattern);
    const error = {};
    error[fieldName] = {message: message, warning: true};
    return !valid? error : null;
  }
}

export const confirmationValidator = (user: User): ValidatorFn => {
  return (control: AbstractControl): ValidationErrors | null => {
    const valid = user.password === control?.value;
    return !valid? {Confimation: {message: 'Confirmation should be equal to password.'}} : null;
  }
};

export const numberValidator = (fieldName: string): ValidatorFn => {
  return (control: AbstractControl): ValidationErrors | null => {

    const valid = !isNaN(control.value);
    const message = {message: `${fieldName} should be a number.`};

    const error = {};
    error[fieldName] = message;

    return !valid? error : null;
  }
};

export const mileageValidator = (type: string): ValidatorFn => {
  return (control: AbstractControl): ValidationErrors | null => {

    const newValid = (+control.value) < 1000;
    const usedValid = (+control.value) >= 1000;

    const messageNew = {Mileage: {message: 'Mileage should be less than 1000'}};
    const messageUsed = {Mileage: {message: 'Mileage should be more than 1000'}};

    

    let valid: boolean;
    let error: any;

    if (type === 'New') {
      valid = newValid;
      error = messageNew;
    }
      
    if (type === 'Used') {
      valid = usedValid;
      error = messageUsed;
    }

     
    if (!valid)
      console.log(error);
    
    return !valid? error : null;
  }
};

export const priceValidator = (fieldName: string, msrp: number, salePrice: number): ValidatorFn => {
  return (control: AbstractControl): ValidationErrors | null => {

    const validSalePrice = control?.value >= salePrice * 0.95;
    const saleMessage = {message: `Purchase price should not be less than 95% of the sales price.`, warning: true};
    
    const validMsrpPrice = control?.value <= msrp;
    const msrpMessage = {message: `Purchase price should not exceed the msrp.`, warning: true};

    const error = {};

    if (!validSalePrice) {
      error[fieldName] = saleMessage;
      return error;
    }
      
    error[fieldName] = msrpMessage;
    
    return !validMsrpPrice? error : null;
  }
}

export default requiredValidator;