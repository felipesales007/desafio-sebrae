import { Route } from '@angular/compiler/src/core';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, CanLoad, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AlertController, NavController } from '@ionic/angular';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad, CanActivateChild  {
  
  constructor(private auth: AuthService, private nav: NavController,public alertController: AlertController){
    
  }
  canLoad(route: Route): boolean | Observable<boolean> | Promise<boolean> {
    
    if (!this.auth.isAccessTokenIvalid()) {
      return true;
    }else{
      this.auth.clearAccessToken()
      this.nav.navigateForward('/app/login', {replaceUrl: true})
    }
    
    return false;
  }
  
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      return  true;
      
    }
    
    
    canActivateChild(
      next: ActivatedRouteSnapshot,
      state: RouterStateSnapshot
      ): Observable<boolean> | Promise<boolean> | boolean {
        if(state.url.toLowerCase().indexOf('/app/login') != -1 && !this.auth.isAccessTokenIvalid()){
          this.nav.navigateForward('/tabs/home', {replaceUrl: true})  
          return false
        }else if (state.url.toLowerCase().indexOf('/app/login') != -1 && this.auth.isAccessTokenIvalid()) {
          return true;
        }else{
          return true;
        }
      }
      
    }
    