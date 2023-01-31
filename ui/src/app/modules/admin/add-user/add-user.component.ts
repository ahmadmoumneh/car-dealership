import {AfterViewInit, Component, Input, OnInit } from '@angular/core';
import { ValidatorFn } from '@angular/forms';

import { User } from 'src/app/classes/user';
import { Form } from 'src/app/classes/form';
import { confirmationValidator, patternValidator } from 'src/app/common/validators/validators';
import { FormService } from 'src/app/services/form.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit, AfterViewInit {
  currentUser: User;
  users: User[];
  columns: any;
  dataSource: any;
  confirmation: string;
  roles: string[];

  emailPattern: string = '^(.+)@(.+)[.](.+)$';
  emailMessage: string = 'Email should be like \'name@email.com\'.';
  passwordPattern: string = ".{8,}";
  passwordMessage: string = 'Password should be at least 8 characters.';
  
  confirmationValidators: ValidatorFn[];
  emailValidators: ValidatorFn[];
  passwordValidators: ValidatorFn[];

  constructor (private formService: FormService, private userService: UserService) {
  }

  @Input() form: Form;

  ngAfterViewInit() {
    this.formService.addAllControls(this.form);
  }

  ngOnInit() {
    this.form = this.formService.buildForm(this.save, this.reset);
    
    this.roles = ['Admin', 'Sales'];
    this.currentUser = new User();
    
    this.confirmationValidators = [confirmationValidator(this.currentUser)];
    this.emailValidators = [patternValidator('Email', this.emailPattern, this.emailMessage)];
    this.passwordValidators = [patternValidator('Password', this.passwordPattern, this.passwordMessage)];
    
    this.userService.getAllUsers().subscribe(users => {
      this.users = users;
      this.setColumns();
      this.updateDataSource();
    });
  }

  setColumns() {
    this.columns = [
      {
        caption: 'Last Name',
        field: 'lastName'
      },
      {
        caption: 'First Name',
        field: 'firstName'
      },
      {
        caption: 'Email',
        field: 'email'
      },
      {
        caption: 'Role',
        field: 'role'
      }
    ];
  }

  updateDataSource() {
    const data = [];
    
    this.users.forEach(user => {
      const userData = {lastName : user.lastName, firstName : user.firstName, email: user.email, role: user.role};
      data.push(userData);
    });

    this.dataSource = data;
  }

  save = () => {
    this.userService.addUser(this.currentUser).subscribe(user => {
    this.users.push(user);
    this.updateDataSource();
    }) 
  };

  reset = () => this.currentUser.userId = null;
}
