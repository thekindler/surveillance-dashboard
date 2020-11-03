import { NavbarComponent } from './navbar.component';
import { Routes } from '@angular/router';
import { HomeComponent } from 'app/home/home.component';
import { DashboardComponent } from 'app/dashboard/dashboard.component';
import { DataflowComponent } from 'app/dataflow/dataflow.component';

export const navbarRoute: Routes = [
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
  },
  {
    path: 'dataflow',
    component: DataflowComponent,
  },
  {
    path: '',
    component: NavbarComponent,
    outlet: 'navbar',
  },
];

// export default navbarRoute;
