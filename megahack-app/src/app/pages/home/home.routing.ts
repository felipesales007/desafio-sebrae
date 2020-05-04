import { Routes } from '@angular/router';
import { HomeComponent } from './home.component';
import { MapGoogleComponent } from '../map-google/map-google.component';

export const HomeRoutes: Routes = [
  {
    path: '', component: HomeComponent
  },
  {
    path: 'delivery/:id', component: MapGoogleComponent
  }
]