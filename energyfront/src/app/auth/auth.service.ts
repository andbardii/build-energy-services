import { Injectable } from '@angular/core';
import { IsignIn } from '../interfaces/IsignIn';
import { HttpClient } from '@angular/common/http';
import { ISignup } from '../interfaces/isignup';
import { BehaviorSubject, map, tap } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  loggedIn: boolean = false;

  jwtHelper: JwtHelperService = new JwtHelperService();
  private authSubject = new BehaviorSubject<null | Object>(null);

  user$ = this.authSubject.asObservable();
  isLoggedIn$ = this.user$.pipe(map((data: any) => Boolean(data)));

  constructor(private http: HttpClient, private router: Router) {
    this.restoreUser();
  }

  signin(user: IsignIn) {
    console.log(user);
    return this.http
      .post('http://localhost:8080/api/auth/login', user)
      .pipe(tap((data: any) => this.authSubject.next(data)));
  }

  signup(user: ISignup) {
    console.log(user);
    return this.http.post('http://localhost:8080/api/auth/register', user);
  }

  // isLogged() {
  //   return new Promise((resolve, reject) => {
  //     setTimeout(() => {
  //       resolve(this.loggedIn);
  //     }, 1000);
  //   });
  // }

  restoreUser() {
    const userJson = localStorage.getItem('user');
    if (!userJson) {
      return;
    }
    const user = JSON.parse(userJson);
    if (this.jwtHelper.isTokenExpired(user.accessToken)) {
      console.log(user.accessToken);
      this.router.navigate(['/login']);
      localStorage.clear();
      return;
    } else {
      this.authSubject.next(user);
      return;
    }
  }
}
