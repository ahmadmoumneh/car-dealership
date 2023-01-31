import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  standalone: true,
  name: 'mileage'
})
export class MileagePipe implements PipeTransform {

  transform(value: number): unknown {
    return value < 1000? 'New' : value;
  }

}
