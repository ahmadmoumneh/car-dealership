import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Form } from 'src/app/classes/form';
import { FormService } from 'src/app/services/form.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  standalone: true,
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  imports: [CommonModule, FormsModule]
})
export class FormComponent implements AfterViewInit {

  @ViewChild('formGroup') ngForm: NgForm;

  @Input() form: Form;
  @Output() formChange = new EventEmitter<Form>();
  @Input() deleteButton: boolean;

  constructor(private formService: FormService){}
  
  ngAfterViewInit() {
    this.formService.loadForm(this.ngForm, this.form);
    this.formChange.emit(this.form);
  }

  save() {
    this.formService.submitForm(this.form);
  }

  reset() {
    this.formService.resetForm(this.form);
  }

  onDelete() {
    this.formService.performDelete(this.form);
  }
}
