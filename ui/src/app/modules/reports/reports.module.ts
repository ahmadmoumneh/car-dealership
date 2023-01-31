import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { SalesComponent } from './sales/sales.component';
import { InventoryComponent } from './inventory/inventory.component';
import { TableComponent } from '../../components/table/table.component';



@NgModule({
  declarations: [
    HomeComponent,
    SalesComponent,
    InventoryComponent
  ],
  imports: [
    CommonModule,
    TableComponent
  ],
  exports: [
    HomeComponent,
    SalesComponent,
    InventoryComponent
  ]
})
export class ReportsModule { }
