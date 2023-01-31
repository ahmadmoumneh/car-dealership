import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Special } from 'src/app/classes/special';
import { SpecialService } from 'src/app/services/special.service';

@Component({
  standalone: true,
  selector: 'app-jumbotron',
  templateUrl: './jumbotron.component.html',
  styleUrls: ['./jumbotron.component.css'],
  imports: [CommonModule]
})
export class JumbotronComponent {
  constructor(private specialService: SpecialService){};

  specials: Special[];

  ngOnInit() {
 
    this.specialService.getAllSpecials().subscribe(specials => {
      this.specials = specials;
      console.log(this.specials);      
    });    
  }

}
