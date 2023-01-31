import { Component, OnInit } from '@angular/core';
import { Make } from './classes/make';
import { User } from './classes/user';
import { MakeService } from './services/make.service';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  user: User;
  make: Make;

  constructor(
    private makeService: MakeService,
    private userService: UserService,
    ) {}
  
  ngOnInit() {
    // this.user = new User();
    // this.user.firstName = "John";
    // this.user.lastName = "Doe";
    // this.user.email = "john.doe@email.com";
    // this.user.password = "1234"
    // this.user.role = "Admin";

    // this.make = new Make();
    // this.make.makeName = "BMW";
    // this.make.makeDate = new Date("1/20/2023");

    // this.userService.addUser(this.user).subscribe(user => {
    //   this.make.makeUser = user;
    //   this.makeService.addMake(this.make).subscribe(console.log);
    // });
    
  }

}
