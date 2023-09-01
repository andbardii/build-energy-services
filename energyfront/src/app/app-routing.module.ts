import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanActivate } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './auth/auth.guard';
import { ClientiComponent } from './pages/clienti/clienti.component';
import { ClienteComponent } from './pages/clienti/cliente/cliente.component';
import { ProvinceComponent } from './pages/province/province.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  {
    path: 'clienti',
    component: ClientiComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'province',
    component: ProvinceComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'cliente/:id',
    component: ClienteComponent,
    canActivate: [AuthGuard],
  },
  {
    path: '',
    redirectTo: 'register',
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
