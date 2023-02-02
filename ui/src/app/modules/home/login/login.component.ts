import { Component, Input, OnInit } from '@angular/core';
import { Form } from 'src/app/classes/form';
import { AuthService } from 'src/app/services/auth.service';
import { FormService } from 'src/app/services/form.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})


export class LoginComponent implements OnInit{
  @Input() form: Form;
  username:string;
  password:string;
  
  
  constructor (
    private formService: FormService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.form = this.formService.buildForm(this.login, this.reset);

  }

  ngAfterViewInit() {
    this.formService.addAllControls(this.form);
  }

  login = () => {
    localStorage.setItem('username', this.username);
    localStorage.setItem('password', this.password);

  }

  reset = () => {

  }
    
  }


