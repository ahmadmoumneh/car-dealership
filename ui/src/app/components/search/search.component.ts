import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  standalone: true,
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class SearchComponent {
  priceArr: number[];
  yearArr: number[];

  form: FormGroup;

  @Output('search') onSearch = new EventEmitter<string>();

  ngOnInit() {
    this.form = new FormGroup({
      minPrice: new FormControl(''),
      maxPrice: new FormControl(''),
      minYear: new FormControl(''),
      value: new FormControl(''),
      maxYear: new FormControl(''),

    });
  }  

  search(elements: string) {
    this.onSearch.emit(elements);
    console.log(elements);
  
  }
}
