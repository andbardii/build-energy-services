import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/interfaces/cliente';
import { ClienteServiceService } from 'src/app/services/cliente-service.service';

@Component({
  selector: 'app-clienti',
  templateUrl: './clienti.component.html',
  styleUrls: ['./clienti.component.scss'],
})
export class ClientiComponent implements OnInit {
  clienti: Cliente[] = [];

  constructor(private svc: ClienteServiceService) {}

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.svc.getAll().subscribe((data) => {
      this.clienti = data;
      console.log(this.clienti);
    });
  }
}
