import { Component, Input, OnInit } from '@angular/core';
import { ValidatorFn } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Purchase } from 'src/app/classes/purchase';
import { User } from 'src/app/classes/user';
import { Vehicle } from 'src/app/classes/vehicle';
import { PurchaseService } from 'src/app/services/purchase.service';
import { VehicleService } from 'src/app/services/vehicle.service';
import { numberValidator, patternValidator, priceValidator } from 'src/app/common/validators/validators';
import { Form } from 'src/app/classes/form';
import { FormService } from 'src/app/services/form.service';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {
  currentPurchase: Purchase;
  currentVehicle: Vehicle;

  states: string[];
  purchaseTypes: string[];

  emailPattern: string = '^(.+)@(.+)[.](.+)$';
  emailMessage: string = 'Email should be like \'name@email.com\'.';
  emailValidators: ValidatorFn[];

  zipcodePattern: string = '[0-9]{5}';
  zipcodeMessage: string = 'Zipcode should be 5 digits.';
  zipcodeValidators: ValidatorFn[];

  phonePattern: string = '[0-9]{10}'
  phoneMessage: string = 'Phone should be 10 digits.';
  phoneValidators: ValidatorFn[];

  priceValidators: ValidatorFn[];

  @Input() form: Form;

  constructor (
    private formService: FormService,
    private vehicleService: VehicleService,
    private purchaseService: PurchaseService,
    private route: ActivatedRoute
  ) {}

  ngAfterViewInit() {
    this.formService.addAllControls(this.form);
  }

  ngOnInit() {
    this.form = this.formService.buildForm(this.save, this.reset);
    const id = this.route.snapshot.params.vehicleId;
    this.vehicleService.getVehicleById(id).subscribe(vehicle => {
      this.currentVehicle = vehicle;

      this.emailValidators = [patternValidator('Email', this.emailPattern, this.emailMessage)];
      this.zipcodeValidators = [patternValidator('Zipcode', this.zipcodePattern, this.zipcodeMessage), numberValidator('Zipcode')];
      this.phoneValidators = [patternValidator('Phone', this.phonePattern, this.phoneMessage), numberValidator('Phone')];
      this.priceValidators = [
        priceValidator('Purchase Price', this.currentVehicle.msrp, this.currentVehicle.salePrice),
        numberValidator('Purchase Price')
      ];
      
      console.log(vehicle);
    });

    this.currentPurchase = new Purchase();
    
    this.currentPurchase.date = new Date();

    this.states = ['AL', 'AK', 'AS', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'DC', 'FM', 'FL', 'GA', 
          'GU', 'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MH', 'MD', 'MA',
          'MI', 'MN','MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ', 'NM', 'NY', 'NC', 'ND', 
          'MP', 'OH', 'OK', 'OR','PW', 'PA', 'PR', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 
          'VT', 'VI', 'VA', 'WA', 'WV', 'WI','WY' ].sort();

    this.purchaseTypes = ['Bank Finance', 'Cash', 'Dealer Finance'];    
  }

  save = () => {
    const user = new User();

    user.userId = 1;
    user.firstName = 'John';
    user.lastName = 'Doe';
    user.email = 'john.doe@email.com';
    user.password = '1234';
    user.role = 'Sales';

    this.currentPurchase.salesUser = user;

    this.purchaseService.logPurchase(this.currentPurchase, this.currentVehicle.vehicleId).subscribe();
  };

  reset = () => this.currentPurchase.purchaseId = null;
}
