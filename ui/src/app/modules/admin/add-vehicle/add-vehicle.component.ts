import { AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { ValidatorFn } from '@angular/forms';
import { Form } from 'src/app/classes/form';
import { Make } from 'src/app/classes/make';
import { Model } from 'src/app/classes/model';
import { Vehicle } from 'src/app/classes/vehicle';
import { mileageValidator, numberValidator, patternValidator } from 'src/app/common/validators/validators';
import { FormService } from 'src/app/services/form.service';
import { MakeService } from 'src/app/services/make.service';
import { ModelService } from 'src/app/services/model.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-add-vehicle',
  templateUrl: './add-vehicle.component.html',
  styleUrls: ['./add-vehicle.component.css']
})
export class AddVehicleComponent implements OnInit, AfterViewInit {
  currentVehicle: Vehicle;

  makes: Make[];
  makesList: string[];

  models: Model[];
  modelsList: string[];

  types: string[];
  bodyStyles: string[];
  transmissions: string[];
  colors: string[];

  picturePath: string;
  
  vinPattern: string = ".{17}";
  vinMessage: string = 'VIN # should be 17 characters.';

  vinValidators: ValidatorFn[];
  mileageValidators: ValidatorFn[];
  msrpValidators: ValidatorFn[];

  picture: Blob;

  @Input() form: Form;

  constructor (private formService: FormService,
    private vehicleService: VehicleService,
    private makeService: MakeService,
    private modelService: ModelService
  ) {
   
  }

  ngAfterViewInit() {
    this.formService.addAllControls(this.form);
  }

  ngOnInit() {
    this.form = this.formService.buildForm(this.save, this.reset);
    
    this.makeService.getAllMakes().subscribe(makes => {
      this.makes = makes;
      this.makesList = makes.map(make => make.makeName);
    });

    this.types = ['New', 'Used'];
    this.bodyStyles = ['Car', 'SUV', 'Truck', 'Van'];
    this.transmissions = ['Automatic', 'Manual'];
    this.colors = ['Black', 'White', 'Silver', 'Navy', 'Red'];

    this.currentVehicle = new Vehicle();
    this.currentVehicle.model = new Model();
    this.currentVehicle.model.modelMake = new Make();
    
    this.vinValidators = [patternValidator('VIN', this.vinPattern, this.vinMessage)];
    this.msrpValidators = [numberValidator('MSRP')];
  }

  setModels() {
    this.modelService.getAllModels().subscribe(models => {
      this.models = models.filter(model => model.modelMake.makeName === this.currentVehicle.model.modelMake.makeName);
      
      if (this.models.length > 0) {
        this.modelsList = this.models.map(model => model.modelName);
      }
    });
  }

  setModel() {
    const model = this.models.find(model => model.modelName === this.currentVehicle.model.modelName);
    this.currentVehicle.model = model;
  }

  resetMileageValidator() {
    this.mileageValidators = [mileageValidator(this.currentVehicle.vehicleType), numberValidator('Mileage')];
    this.form.ngForm.form.controls['Mileage'].setValidators(this.mileageValidators);
    this.form.ngForm.form.controls['Mileage'].updateValueAndValidity();
  }

  upload(fileList: FileList) {
    this.picturePath = fileList[0].name;
    const files = Array.from<File>(fileList);
    this.picture = new Blob(files, {type: 'image/jpeg'});
    this.form.ngForm.form.controls.Picture.markAsDirty();
  }

  openDialog(uploader: HTMLInputElement) {
    uploader.click();
  }

  save = () => {
    this.vehicleService.addVehicle(this.currentVehicle).subscribe(vehicle => {
      this.vehicleService.uploadPicture(vehicle.vehicleId, this.picture).subscribe();
    }); 
  };

  reset = () => this.currentVehicle.vehicleId = null;
}
