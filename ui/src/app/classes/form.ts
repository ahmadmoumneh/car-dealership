import { NgForm, ValidatorFn } from "@angular/forms";

export class Form {
  ngForm: NgForm;
  onSubmitForm: Function;
  onResetForm: Function;
  onDelete: Function;
  controls: any[];
  validators: Map<string, ValidatorFn[]>;
}