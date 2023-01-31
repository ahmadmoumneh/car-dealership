import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Picture } from 'src/app/classes/picture';
import { Special } from 'src/app/classes/special';
import { Vehicle } from 'src/app/classes/vehicle';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  standalone: true,
  selector: 'app-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css'],
  imports: [CommonModule, FormsModule]
})
export class ThumbnailComponent implements OnInit{
  imageUrl: string;

  @Input() vehicleId: number;
  @Input() description: string;

  constructor(private vehicleService: VehicleService) {}

  // pictures : Picture[] = [];
  // vehiclePicture : Picture;
  // sanitizer: any;

  ngOnInit(): void {
    this.imageUrl = 'assets/images/inventory-' + this.vehicleId + '.jpg';
  }
}

  // getPictureById(vehicleId : number) : any{
  //     this.vehicleService.getPicture(vehicleId).subscribe(pictureBytes => {
  //        let pic : Picture = new Picture();
  //        pic.vehicleId = vehicleId;
  //        pic.picture = pictureBytes;
  //        this.pictures.push(pic);
  //        console.log("picture for id: ", vehicleId);
  //        console.log(pic);
  //        this.vehiclePicture = pic;
  //     });   
  //     const objectURL = URL.createObjectURL(this.vehiclePicture.picture);
  //     const img = this.sanitizer.bypassSecurityTrustUrl(objectURL);
  //     return img;
  // }
