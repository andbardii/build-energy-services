import { Injectable } from '@angular/core';
import { IsignIn } from '../interfaces/IsignIn';
import { HttpClient } from '@angular/common/http';
import { ISignup } from '../interfaces/isignup';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loggedIn: boolean = true;

  constructor( private http: HttpClient) { }


  signin(user: IsignIn) {
    console.log(user);
    return this.http.post('http://localhost:8080/api/auth/login', user);
  }

  signup(user: ISignup) {
    console.log(user);
    return this.http.post('http://localhost:8080/api/auth/register', user);
  }

}
