import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Fattura } from '../interfaces/fattura';

@Injectable({
  providedIn: 'root'
})
export class FatturaService{

  constructor(private http: HttpClient) {
    this.getToken();
  }

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

  getFattureByCliente(id: number) {
    this.headers = this.headers.set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.http.get<Fattura[]>('http://localhost:8080/api/fatture/findbycliente/'+ id, {
      headers: this.headers
    });
  }

  addFattura(id: number, fattura: Fattura) {
    fattura.idCliente = id;
    this.headers = this.headers.set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.http.post<Fattura>('http://localhost:8080/api/fatture/post', fattura, {
      headers: this.headers,
    });
  }
}
