import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router) { }
canActivate(): boolean | Promise<boolean> {
    var isAuthenticated = this.authService.isLoggedIn();
    if (!isAuthenticated) {
        this.router.navigate(['account/login']);
    }
    return isAuthenticated;
}}