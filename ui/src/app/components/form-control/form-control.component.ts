import { CommonModule } from '@angular/common';
import { AfterViewChecked, AfterViewInit, ChangeDetectorRef, Component, ElementRef, EventEmitter, Injector, Input, OnChanges, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormsModule, NgModel, ValidatorFn } from '@angular/forms';
import { FormService } from 'src/app/services/form.service';
import { FormComponent } from '../form/form.component';
import requiredValidator from '../../common/validators/validators';
import { Form } from 'src/app/classes/form';

@Component({
  standalone: true,
  selector: 'app-form-control',
  templateUrl: './form-control.component.html',
  styleUrls: ['./form-control.component.css'],
  imports: [CommonModule, FormsModule]
})
export class FormControlComponent implements OnChanges, AfterViewInit  {

  @Input() type: string;
  @Input() fieldName: string;
  @Input() fieldData: string;
  @Input() required: boolean = false;
  @Input() disabled: boolean;
  @Input() minlength: number;
  @Input() maxlength: number;
  @Input() pattern: string;
  @Input() dataList: string[];
  @Input() validators: ValidatorFn[];

  form: Form;
  errors: any[];

  @Output('fieldDataChange') dataChange = new EventEmitter<string>();

  @ViewChild('field') field: NgModel;
  @ViewChild('fieldError') fieldError: ElementRef;

  constructor(
    private formService: FormService,
    private injector: Injector,
    private changeDetector: ChangeDetectorRef
  ){}

  ngOnChanges(simpleChanges: SimpleChanges) {
    if (this.type === 'select') {
      if (this.fieldData)
        this.errors = null;
    }

    if (this.field) {
      
      if (this.form.ngForm.form.pristine) {
        this.initValidators();


        if (this.fieldData && !this.field.valid) {
          this.showError();
        }
        
      }

      else {
        if (simpleChanges.required &&
          !simpleChanges.required.previousValue) {
            this.addValidators();
        }
        
        else {
          this.removeValidators();
        }

        if (this.field.valid) {
          this.hideError();
        }

        if (this.field.control.errors) {
          this.errors = Object.values(this.field.control.errors).filter(error => error.message !== undefined);
        }

      }
    }
  }

  ngAfterViewInit() {
    this.form = this.injector.get<FormComponent>(FormComponent).form;
    this.formService.addControl(this.field, this.form);
    this.initValidators();

    if (this.field.control.errors) {
      this.errors = Object.values(this.field.control.errors).filter(error => error.message !== undefined);
    }

    this.changeDetector.detectChanges();
  }

  private setValidators() {
    if (this.validators) {
      this.validators = [...this.validators, requiredValidator(this.fieldName)];
    }

    else {
      this.validators = [requiredValidator(this.fieldName)];
    }
  }

  private addValidators() {
    if (this.required) {
      this.field.control.addValidators(this.validators);
      this.field.control.updateValueAndValidity();
    }
  }

  private initValidators() {
    this.setValidators();
    this.addValidators();
  }

  private removeValidators() {
    if (!this.required) {
      this.field.control.clearValidators();
      this.field.control.updateValueAndValidity();
    }
  }

  onInput(value: string) {
    this.onData(value);
  }

  onSelect(value: string) {
    this.onData(value);
  }

  onData(value: string) {
    this.dataChange.emit(value);
  }

  hideError() {
    this.fieldError.nativeElement.classList.add('alert-hide');

    setTimeout(() => {
      this.fieldError.nativeElement.hidden = true;
    }, 2000)
  }

  showError() {
    if (!this.field.valid && !this.field.pristine && this.errors) {
      this.fieldError.nativeElement.classList.remove('alert-hide');
      this.fieldError.nativeElement.hidden = false;
    }
  }
}
