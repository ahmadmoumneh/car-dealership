import { CommonModule } from '@angular/common';
import { Component, DoCheck, OnChanges, OnInit } from '@angular/core';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  standalone: true,
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
  imports: [CommonModule, AppRoutingModule]
})
export class NavigationComponent implements DoCheck {

  constructor(private authService: AuthService) {}

  ngDoCheck() {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.isAdmin = localStorage.getItem('role') === 'Admin';
  }

  isLoggedIn: boolean;
  isAdmin: boolean

  logout() {
    localStorage.removeItem('username');
    localStorage.removeItem('password');
    localStorage.removeItem('role');
    location.reload();
  }
}
