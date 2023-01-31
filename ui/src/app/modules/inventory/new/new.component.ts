import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { VehicleService } from 'src/app/services/vehicle.service';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Vehicle } from 'src/app/classes/vehicle';
import { SearchResultComponent } from "../../../components/search-result/search-result.component";
import { SearchComponent } from "../../../components/search/search.component";
import { Router } from '@angular/router';

@Component({
    standalone: true,
    selector: 'app-new',
    templateUrl: './new.component.html',
    styleUrls: ['./new.component.css'],
    imports: [CommonModule, FormsModule, ReactiveFormsModule, SearchResultComponent, SearchComponent]
})

export class NewComponent {
  priceArr: number[];
  yearArr: number[];
  form: FormGroup;
  newVehicles: Vehicle[];

  constructor(private vehicleService: VehicleService) { }
  

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
    
    this.vehicleService.searchVehicles(elements, 'New', "Admin").subscribe(newVehicles => {
        console.log(newVehicles);
        this.newVehicles = newVehicles;
      });
  }

  action(id: number, router: Router) {
    router.navigate(['/inventory/details/', id])
  }
}
