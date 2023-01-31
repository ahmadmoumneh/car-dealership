import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Vehicle } from 'src/app/classes/vehicle';
import { MileagePipe } from 'src/app/pipes/mileage.pipe';


@Component({
  standalone: true,
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css'],
  imports: [CommonModule, MileagePipe]
})
export class SearchResultComponent {
  @Input() vehicle: Vehicle;
  @Input() buttonType: string;
  @Input() action: Function;

  constructor(public router: Router) {
  }

  go(id: number, router: Router) {
    this.action(id, router);
  }
}
