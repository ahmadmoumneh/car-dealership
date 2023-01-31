import { Component, Input, OnInit } from '@angular/core';
import { Form } from 'src/app/classes/form';
import { Special } from 'src/app/classes/special';
import { FormService } from 'src/app/services/form.service';
import { SpecialService } from 'src/app/services/special.service';

@Component({
  selector: 'app-specials',
  templateUrl: './specials.component.html',
  styleUrls: ['./specials.component.css']
})
export class SpecialsComponent implements OnInit {
  currentSpecial: Special;
  specials: Special[];

  @Input() form: Form;

  constructor (
    private formService: FormService,
    private specialService: SpecialService
  ) {}

  ngOnInit() {
    this.form = this.formService.buildForm(this.save, this.reset);
    this.currentSpecial = new Special();

    this.specialService.getAllSpecials().subscribe(specials => {
      this.specials = specials;
    });
  }

  ngAfterViewInit() {
    this.formService.addAllControls(this.form);
  }

  save = () => {
    this.specialService.addSpecial(this.currentSpecial).subscribe(special => {
      this.specials.push(special);
      this.currentSpecial = new Special();
    });
  };

  reset = () => this.currentSpecial.specialId = null;

  deleteSpecialById(id: number) {
    console.log('delete special');

    const index = this.specials.findIndex(special => special.specialId === id);
    this.specials.splice(index, 1);
  }
}
