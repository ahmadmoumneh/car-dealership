import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Special } from 'src/app/classes/special';
import { Vehicle } from 'src/app/classes/vehicle';
import { AuthService } from 'src/app/services/auth.service';
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
    private specialService: SpecialService,
    private authService: AuthService,
    private router: Router
  ){};

  specials: Special[];
  featuredVehicles: Vehicle[];


  ngOnInit() {
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
