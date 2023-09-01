import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Provincia } from '../interfaces/provincia';

@Injectable({
  providedIn: 'root'
})
export class ProvinceService {

  constructor(private http:HttpClient) { }

  headers = new HttpHeaders();

  getToken(): string {
    const user = localStorage.getItem('user');
    if (user) {
      const userData = JSON.parse(user);
      return userData.accessToken;
    } else {
      return '';
    }
  }

  getAll() {
    this.headers = this.headers.set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.http.get<Provincia[]>('http://localhost:8080/api/province/set', {
      headers: this.headers
    });
  }

  searchProvince(nome:string) {
    this.headers = this.headers.set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    console.log(Object.values(nome))
    return this.http.get<Provincia[]>('http://localhost:8080/api/province/search/' + Object.values(nome) , {
      headers: this.headers

    });

  }
}
