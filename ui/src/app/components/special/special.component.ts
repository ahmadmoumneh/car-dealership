import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Special } from 'src/app/classes/special';
import { SpecialService } from 'src/app/services/special.service';

@Component({
  standalone: true,
  selector: 'app-special',
  templateUrl: './special.component.html',
  styleUrls: ['./special.component.css'],
  imports: [CommonModule]
})
export class SpecialComponent {
  @Input() special: Special;
  @Input() delete: boolean;

  @Output('deleteSpecial') onDeleteSpecial = new EventEmitter<number>();

  constructor(private specialService: SpecialService) {}

  deleteSpecial(id: number) {
    this.specialService.deleteSpecialById(id).subscribe(() => {
      this.onDeleteSpecial.emit(id);
    });
  }
}
