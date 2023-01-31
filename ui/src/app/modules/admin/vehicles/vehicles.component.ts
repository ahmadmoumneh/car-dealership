import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { VehicleService } from 'src/app/services/vehicle.service';
import { FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Vehicle } from 'src/app/classes/vehicle';
import { SearchComponent } from "../../../components/search/search.component";
import { SearchResultComponent } from 'src/app/components/search-result/search-result.component';
import { Router } from '@angular/router';

@Component({
    standalone: true,
    selector: 'app-vehicles',
    templateUrl: './vehicles.component.html',
    styleUrls: ['./vehicles.component.css'],
    imports: [CommonModule, FormsModule, ReactiveFormsModule, SearchComponent, SearchResultComponent]
})

export class VehiclesComponent {
  priceArr: number[];
  yearArr: number[];
  form: FormGroup;
  allVehicles: Vehicle[];

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

  action(id: number, router: Router) {
    router.navigate(['/admin/editvehicle/', id ])
  }

  search(elements: string) {
    
    console.log(elements);
    
    this.vehicleService.searchVehicles(elements, 'All', 'Admin').subscribe(allVehicles => {
        console.log(allVehicles);
        this.allVehicles = allVehicles;
      })

  }

}
