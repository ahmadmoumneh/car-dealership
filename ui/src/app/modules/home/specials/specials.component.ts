import { Component, Input } from '@angular/core';
import { Form } from 'src/app/classes/form';
import { Special } from 'src/app/classes/special';
import { FormService } from 'src/app/services/form.service';
import { SpecialService } from 'src/app/services/special.service';

@Component({
  selector: 'app-specials',
  templateUrl: './specials.component.html',
  styleUrls: ['./specials.component.css']
})
export class SpecialsComponent {
  specials: Special[];

  constructor(private specialService: SpecialService){}

  ngOnInit() {
    this.specialService.getAllSpecials().subscribe(specials => {
      this.specials = specials;
    });
  }
}
