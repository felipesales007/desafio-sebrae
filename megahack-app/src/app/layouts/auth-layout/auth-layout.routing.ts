import { Routes } from "@angular/router";
import { LoginComponent } from 'src/app/pages/login/login.component';
import { LoginInfoComponent } from 'src/app/pages/login-info/login-info.component';
import { LoginSocialComponent } from 'src/app/pages/login-social-network/login-social.component';

export const AuthLayoutRoutes: Routes = [
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "login-info",
    component: LoginInfoComponent
  },
  {
    path: "login-social",
    component: LoginSocialComponent
  }
];
