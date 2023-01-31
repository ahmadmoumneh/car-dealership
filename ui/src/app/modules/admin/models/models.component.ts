import { Component, Input } from '@angular/core';
import { Make } from 'src/app/classes/make';
import { Model } from 'src/app/classes/model';
import { User } from 'src/app/classes/user';
import { MakeService } from 'src/app/services/make.service';
import { ModelService } from 'src/app/services/model.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TableComponent } from 'src/app/components/table/table.component';
import { FormControlComponent } from 'src/app/components/form-control/form-control.component';
import { FormComponent } from 'src/app/components/form/form.component';
import { Form } from 'src/app/classes/form';
import { FormService } from 'src/app/services/form.service';

@Component({
  standalone: true,
  selector: 'app-models',
  templateUrl: './models.component.html',
  styleUrls: ['./models.component.css'],
  imports: [CommonModule, FormsModule, TableComponent, FormComponent, FormControlComponent]
})
export class ModelsComponent {
  models: Model[];
  makes: Make[];
  makesList: string[];
  dataSource: any;
  columns: any;
  currentModel: Model;

  constructor(
    private modelService : ModelService, 
    private makeService: MakeService,
    private formService: FormService
  ){}

  @Input() form: Form;

  ngOnInit(): void {
    this.form = this.formService.buildForm(this.save, this.reset);
  
    this.currentModel = new Model();
    this.currentModel.modelMake = new Make();

    this.modelService.getAllModels().subscribe(models => {
      this.models = models;
      this.makeService.getAllMakes().subscribe(makes => {
        this.makes = makes;
        this.makesList = makes.map(make => make.makeName);
        this.setColumns();
        this.updateDataSource();
      });
    });
  }

  ngAfterViewInit() {
    this.formService.addAllControls(this.form);
  }

  setColumns() {
    this.columns = [
      {
        caption: 'Make',
        field: 'makeName'
      },
      {
        caption: 'Model',
        field: 'modelName'
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

  save = () => {
    this.currentModel.modelDate = new Date();
    const user = new User();

    user.userId = 1;
    user.firstName = 'John';
    user.lastName = 'Doe';
    user.email = 'john.doe@gmail.com';
    user.password = '1234';
    user.role = 'Admin';

    this.currentModel.modelUser = user;
    this.currentModel.modelMake = this.makes.find(make => make.makeName == this.currentModel.modelMake.makeName);
    
    this.modelService.addModel(this.currentModel).subscribe(model => {
      this.currentModel = model;
      this.models.push(model);
      this.updateDataSource();
      this.currentModel = new Model();
      this.currentModel.modelMake = new Make();
    });
  }

  reset = () => this.currentModel.modelId = null;

  updateDataSource(){
    const data = [];
    
    this.models.forEach(model => {
      const modelData = {makeName:model.modelMake.makeName, modelName : model.modelName, dateAdded : model.modelDate, user: model.modelUser.email};
      data.push(modelData);
    });
    
    this.dataSource = data;
  }
}