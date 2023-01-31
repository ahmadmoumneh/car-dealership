import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { VehicleService } from 'src/app/services/vehicle.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Vehicle } from 'src/app/classes/vehicle';
import { SearchComponent } from "../../../components/search/search.component";
import { SearchResultComponent } from 'src/app/components/search-result/search-result.component';
import { Router } from '@angular/router';

@Component({
    standalone: true,
    selector: 'sales-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
    imports: [CommonModule, FormsModule, ReactiveFormsModule, SearchComponent, SearchResultComponent]
})

export class HomeComponent {
  priceArr: number[];
  yearArr: number[];
  form: FormGroup;
  salesVehicles: Vehicle[];

  constructor(private vehicleService: VehicleService, private router: Router) { }

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
    
    console.log(elements);
    
    this.vehicleService.searchVehicles(elements,"All", "Sales").subscribe(salesVehicles => {
        this.salesVehicles = salesVehicles;
      });

  }
  
  action(id: number, router: Router) {
    this.router.navigate(['/sales/', id ])
  }

}

