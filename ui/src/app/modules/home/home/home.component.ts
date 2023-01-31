import { Component, OnInit } from '@angular/core';
import { Special } from 'src/app/classes/special';
import { Vehicle } from 'src/app/classes/vehicle';
import { SpecialService } from 'src/app/services/special.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private vehicleService: VehicleService,
    private specialService: SpecialService
  ){};

  specials: Special[];
  featuredVehicles: Vehicle[];

  ngOnInit() {
    // this.vehicleService.searchNewVehicles().subscribe(newVehicles => {
    //   console.log(newVehicles);
    // })
    
    this.specialService.getAllSpecials().subscribe(specials => {
      this.specials = specials;
      console.log(this.specials);      
    }); 

    this.vehicleService.getFeaturedVehicles().subscribe(fVehicles => {
      this.featuredVehicles = fVehicles;
      
      console.log(this.featuredVehicles);      
    });   
  }
}
