import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  @ViewChild('f') form!: NgForm;
  error: undefined | string;

  constructor(private svc: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    if(  this.form.value.username.trim() !== ''
      && this.form.value.email.trim() !== ''
      && this.form.value.password.trim() !== '') {
        this.svc.signup(this.form.value).subscribe(
          resp => {
            console.log(resp);
            this.error = undefined;
            this.router.navigate(['/login']);
          }, err => {
            console.log(err.error.message);
            this.error = err.error.message;
          }
        );
    } else {
      this.error = 'Field Required';
    }

  }
}
