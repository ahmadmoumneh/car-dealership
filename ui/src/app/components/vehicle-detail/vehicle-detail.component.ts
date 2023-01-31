import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vehicle } from 'src/app/classes/vehicle';
import { VehicleService } from '../../services/vehicle.service';


@Component({
  standalone: true,
  selector: 'app-vehicle-detail',
  templateUrl: './vehicle-detail.component.html',
  styleUrls: ['./vehicle-detail.component.css'],
  imports: [CommonModule]
})
export class VehicleDetailComponent implements OnInit {
  
  @Input() vehicle: Vehicle;
  @Input() contact: boolean;

  constructor(private vehicleService: VehicleService, private route: ActivatedRoute, private router:Router) {

  }

  ngOnInit() {
    console.log(this.contact)
    const id = this.route.snapshot.params.vehicleId;
    this.vehicleService.getVehicleById(id).subscribe(vehicle => {
      this.vehicle = vehicle;
    });
  }

  contactUs(){
    
    this.router.navigate(['/sales/', this.vehicle.vehicleId]);
  }
}