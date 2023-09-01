import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Provincia } from 'src/app/interfaces/provincia';
import { ProvinceService } from 'src/app/services/province.service';

@Component({
  selector: 'app-province',
  templateUrl: './province.component.html',
  styleUrls: ['./province.component.scss']
})
export class ProvinceComponent implements OnInit {

@ViewChild('f') form!: NgForm;
province: Provincia[] = [];

constructor(private svc:ProvinceService){}

ngOnInit(): void {
this.getAll();
}

getAll(){
  this.svc.getAll().subscribe(province =>{
    this.province = province;
  })
}

onSearch() {
    this.svc.searchProvince(this.form.value).subscribe(province =>{
      this.province = province;
      console.log(this.province)
      this.form.reset();
    })
}
}
