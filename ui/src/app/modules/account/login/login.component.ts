import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Form } from 'src/app/classes/form';
import { AuthService } from 'src/app/services/auth.service';
import { FormService } from 'src/app/services/form.service';
import { UserService } from 'src/app/services/user.service';

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
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit() {
    this.form = this.formService.buildForm(this.login, this.reset);
  }

  ngAfterViewInit() {
    this.formService.addAllControls(this.form);
  }

  login = () => {
    this.userService.getUserByCredentials(this.username, this.password).subscribe(user => {
      localStorage.setItem('username', this.username);
      localStorage.setItem('password', this.password);
      localStorage.setItem('role', user.role);
      this.router.navigate(['home/index']);
    });

    // 
    // 
  }

  reset = () => {

  }
    
}


