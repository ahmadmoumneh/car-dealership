import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MakesComponent } from './modules/admin/makes/makes.component';
import { ModelsComponent } from './modules/admin/models/models.component';
import { SpecialsComponent as SpecialsHomeComponent } from './modules/home/specials/specials.component';
import { SpecialsComponent } from './modules/admin/specials/specials.component';
import { HomeComponent } from './modules/home/home/home.component';
import { HomeComponent as SalesHomeComponent} from './modules/sales/home/home.component';
import { AuthGuard } from './services/auth.guard';
import { ContactComponent } from './modules/home/contact/contact.component';
import { AddVehicleComponent } from './modules/admin/add-vehicle/add-vehicle.component';
import { PurchaseComponent } from './modules/sales/purchase/purchase.component';
import { AddUserComponent } from './modules/admin/add-user/add-user.component';
import { NewComponent } from './modules/inventory/new/new.component';
import { UsedComponent } from './modules/inventory/used/used.component';
import { VehiclesComponent } from './modules/admin/vehicles/vehicles.component';
import { VehicleDetailComponent } from './components/vehicle-detail/vehicle-detail.component';
import { EditVehicleComponent } from './modules/admin/edit-vehicle/edit-vehicle.component';

const routes: Routes = [

  {path:'home/index',component: HomeComponent},
  {path:'home/specials',component: SpecialsHomeComponent},
  {path:'home/contact',component: ContactComponent},
  {path: 'inventory/new', component:NewComponent},
  {path: 'inventory/used', component:UsedComponent},
  {path: 'inventory/details/:vehicleId', component: VehicleDetailComponent},
  {path: 'admin/specials',component: SpecialsComponent},
  {path: 'admin/makes', component: MakesComponent},
  {path: 'admin/models', component: ModelsComponent},
  {path: 'admin/addvehicle', component: AddVehicleComponent},
  {path: 'admin/editvehicle/:vehicleId', component: EditVehicleComponent},
  {path: 'admin/adduser', component: AddUserComponent},
  {path: 'admin/vehicles', component: VehiclesComponent},
  {path: 'sales/home', component: SalesHomeComponent},
  {path: 'sales/:vehicleId', component:PurchaseComponent},
  {path:'', redirectTo:'home/index', pathMatch: 'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
