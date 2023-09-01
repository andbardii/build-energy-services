import { NgForm } from '@angular/forms';
import { ClienteServiceService } from './../../../services/cliente-service.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Cliente } from 'src/app/interfaces/cliente';
import { Fattura } from 'src/app/interfaces/fattura';
import { FatturaService } from 'src/app/services/fattura.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent implements OnInit {
  @ViewChild('f') form!: NgForm;
  cliente:Cliente ={};
  fatture:Fattura[] = [];
  addingFattura:boolean = false;

  constructor(private fatturazione:FatturaService, private svc:ClienteServiceService, private route:ActivatedRoute, private router:Router) {};

  ngOnInit(): void {
    this.getById();
    this.getFatture();
  }

getById(){
    this.route.params
    .subscribe((params:any)=>{
      this.svc.getById(params.id).subscribe(cliente => {
          this.cliente = cliente
      })
    })
  }

getFatture(){
    this.route.params
    .subscribe((params:any)=>{
      this.fatturazione.getFattureByCliente(params.id).subscribe(fatture => {
          this.fatture = fatture
      })
    })
  }

onSubmit() {
  this.route.params
  .subscribe((params:any)=>{
    this.fatturazione.addFattura(params.id, this.form.value).subscribe(fattura => {
       console.log(fattura);
       this.getFatture();
       this.addingFattura = false;
       this.form.reset()
    })
  })
  }
}
