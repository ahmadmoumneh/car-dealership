import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Form } from 'src/app/classes/form';
import { Make } from 'src/app/classes/make';
import { User } from 'src/app/classes/user';
import { FormControlComponent } from 'src/app/components/form-control/form-control.component';
import { FormComponent } from 'src/app/components/form/form.component';
import { TableComponent } from 'src/app/components/table/table.component';
import { FormService } from 'src/app/services/form.service';
import { MakeService } from 'src/app/services/make.service';

@Component({
  standalone: true,
  selector: 'app-makes',
  templateUrl: './makes.component.html',
  styleUrls: ['./makes.component.css'],
  imports: [CommonModule, FormsModule, TableComponent, FormComponent, FormControlComponent]
})
export class MakesComponent implements OnInit {
  makes: Make[];
  
  dataSource: any;

  columns: any;
  currentMake: Make;

  constructor(private makeService : MakeService, 
    private formService: FormService){}

  @Input() form: Form;

  ngOnInit() {
    this.form = this.formService.buildForm(this.save, this.reset);
    
    this.currentMake = new Make();

    this.makeService.getAllMakes().subscribe(makes => {
      this.makes = makes;
      this.setColumns();
      this.updateDataSource();
    });
  }

  ngAfterViewInit() {
    this.formService.addAllControls(this.form);
  }

  save = () => {
    this.currentMake.makeDate = new Date();

    const user = new User();

    user.userId = 1;
    user.firstName = 'John';
    user.lastName = 'Doe';
    user.email = 'john.doe@email.com';
    user.password = '1234';
    user.role = 'Admin';

    this.currentMake.makeUser = user;

    this.makeService.addMake(this.currentMake).subscribe(make => {
      this.currentMake = make;
      this.makes.push(make);
      this.updateDataSource();
      this.currentMake = new Make();
    });
  }

  reset = () => this.currentMake.makeId = null;

  updateDataSource() {
    const data = [];
    
    this.makes.forEach(make => {
      const makeData = {makeName : make.makeName, dateAdded : make.makeDate, user: make.makeUser.email};
      data.push(makeData);
    });

    this.dataSource = data;
  }

  setColumns() {
    this.columns = [
      {
        caption: 'Make',
        field: 'makeName'
      },
      {
        caption: 'Date Added',
        field: 'dateAdded'
      },
      {
        caption: 'User',
        field: 'user'
      }
    ];
  }
}
