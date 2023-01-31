import { Injectable } from '@angular/core';
import { ValidatorFn, NgForm, NgModel } from '@angular/forms';
import { Form } from '../classes/form';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  buildForm(save: Function, reset: Function): Form {
    const form = new Form();

    form.controls = [];
    form.validators = new Map<string, ValidatorFn[]>();

    form.onSubmitForm = save;
    form.onResetForm = reset;

    return form; 
  }

  loadForm(ngForm: NgForm, form: Form) {
    form.ngForm = ngForm;
  }

  setDeleteFunction(deleteFn: Function, form: Form) {
    form.onDelete = deleteFn;
  }

  addControl(ngModel: NgModel, form: Form) {
    form.controls.push(ngModel);
  }

  addAllControls(form: Form) {
    form.controls.forEach(control => {
      form.ngForm.addControl(control);
    });
  }

  submitForm(form: Form) {
    form.onSubmitForm();
    form.ngForm.resetForm();
    form.ngForm.form.markAsPristine();
  }

  resetForm(form: Form) {
    form.ngForm.resetForm();
    form.onResetForm();
  }

  performDelete(form: Form) {
    form.onDelete();
  }
}
