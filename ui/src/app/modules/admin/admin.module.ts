import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { AddVehicleComponent } from './add-vehicle/add-vehicle.component';
import { EditVehicleComponent } from './edit-vehicle/edit-vehicle.component';
import { UsersComponent } from './users/users.component';
import { AddUserComponent } from './add-user/add-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { MakesComponent } from './makes/makes.component';
import { ModelsComponent } from './models/models.component';
import { SpecialsComponent } from './specials/specials.component';
import { SpecialComponent } from '../../components/special/special.component';
import { FormControlComponent } from '../../components/form-control/form-control.component';
import { TableComponent } from '../../components/table/table.component';
import { FormsModule } from '@angular/forms';
import { FormComponent } from 'src/app/components/form/form.component';



@NgModule({
  declarations: [
    AddVehicleComponent,
    EditVehicleComponent,
    UsersComponent,
    AddUserComponent,
    EditUserComponent,
    SpecialsComponent
  ],
  imports: [
    VehiclesComponent,
    CommonModule,
    FormsModule,
    SpecialComponent,
    FormComponent,
    FormControlComponent,
    MakesComponent,
    ModelsComponent,
    TableComponent
  ],
  exports: [
    VehiclesComponent,
    AddVehicleComponent,
    EditVehicleComponent,
    UsersComponent,
    AddUserComponent,
    EditUserComponent,
    MakesComponent,
    ModelsComponent,
    SpecialsComponent
  ]
})
export class AdminModule { }
