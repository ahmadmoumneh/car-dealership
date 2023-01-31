import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewComponent } from './new/new.component';
import { UsedComponent } from './used/used.component';
import { DetailsComponent } from './details/details.component';
import { SearchComponent } from '../../components/search/search.component';
import { SearchResultComponent } from '../../components/search-result/search-result.component';
import { VehicleDetailComponent } from '../../components/vehicle-detail/vehicle-detail.component';



@NgModule({
  declarations: [
    DetailsComponent
  ],
  imports: [
    NewComponent,
    UsedComponent,
    CommonModule,
    SearchComponent,
    SearchResultComponent,
    VehicleDetailComponent,
  ],
  exports: [
    DetailsComponent
  ]
})
export class InventoryModule { }
