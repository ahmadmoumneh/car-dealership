import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { VehicleService } from 'src/app/services/vehicle.service';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Vehicle } from 'src/app/classes/vehicle';
import { SearchResultComponent } from 'src/app/components/search-result/search-result.component';
import { SearchComponent } from "../../../components/search/search.component";
import { Router } from '@angular/router';

@Component({
    standalone: true,
    selector: 'app-used',
    templateUrl: './used.component.html',
    styleUrls: ['./used.component.css'],
    imports: [CommonModule, FormsModule, ReactiveFormsModule, SearchResultComponent, SearchComponent]
})

export class UsedComponent {
  priceArr: number[];
  yearArr: number[];
  form: FormGroup;
  usedVehicles: Vehicle[];

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
    
    this.vehicleService.searchVehicles(elements, 'Used', "Admin").subscribe(usedVehicles => {
        console.log(usedVehicles);
        this.usedVehicles = usedVehicles;
      })

  }

  action(id: number, router: Router) {
    this.router.navigate(['/inventory/details/', id]);
  }
  
}
