import { Injectable, Inject, ErrorHandler } from '@angular/core';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from "@auth0/angular-jwt";
import { HttpClient, HttpHeaders, HttpHandler } from '@angular/common/http';
import { DOCUMENT } from '@angular/common';
import * as storage from 'src/app/utils/storage';
import { GooglePlus } from '@ionic-native/google-plus/ngx';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  api = environment.api
  jwtPayload : any
  
  
  constructor(
    private http: HttpClient,
    private jwtHelper: JwtHelperService,
    private hanlder: HttpHandler,
    private googlePlus: GooglePlus,
    @Inject(DOCUMENT) private document: Document
    ) { }
    
    loginWithGoogle(access_token: string){
      const headers = new HttpHeaders({
        'auth-google-token': access_token,
      });
      return this.http.get(`${this.api.LOGIN_NETWORK}/google/login`, {headers : headers})
      .toPromise()
      .then((response: any ) => {
        this.saveToken(response.access_token)
      })
      .catch(response => {
        if(response.status === 400){
          const responseJason = response
          if(responseJason.error.error === 'invalid_request'){
            return Promise.reject(responseJason.error.message)
          }
        }
        return Promise.reject(response.error)
      });
    }
    
    login(email: string, password: string): Promise<void> {
      const headers = new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic eW91cm1vbmV5OnJAMWxzMG4='
      }
      );
      
      headers.append('Authorization', 'eW91cm1vbmV5OnJAMWxzMG4=')
      
      const body = `username=${email}&password=${password}&grant_type=password`;
      
      return this.http.post(`${this.api.LOGIN}`, body, { headers, withCredentials: false })
      .toPromise()
      .then((response: any ) => {
        this.saveToken(response.access_token)
        return response
      })
      .catch(response => {
        if(response.status === 400){
          const responseJason = response
          if(responseJason.error.error === 'invalid_request'){
            return Promise.reject(responseJason.error.message)
          }
        }
        return Promise.reject(response.error)
      });
    }
    
    hasPermission(permission: string){
      this.loadToken()
      return this.jwtPayload && this.jwtPayload.authorities.includes(permission);
    }
    
    getNewAccessToken(): Promise<void> {
      
      const headers = new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic eW91cm1vbmV5OnJAMWxzMG4='
      });
      
      const body = `grant_type=refresh_token`;
      
      return this.http.post(`${this.api.LOGIN}`, body, { headers, withCredentials: false })
      .toPromise()
      .then((response: any ) => {
        this.saveToken(response.access_token)
      })
      .catch(response => {
        if(response.status === 400){
          const responseJason = response
          if(responseJason.error.error === 'invalid_request'){
            return Promise.resolve(null)
          }
        }
        return Promise.resolve(null)
      });
    }
        
    
    saveToken(token: string){
      const helper = new JwtHelperService();
      this.jwtPayload = helper.decodeToken(token);
      localStorage.setItem('access_token', token)
      
    }

    getIUUID() {
      const helper = new JwtHelperService();
      var payload =  helper.decodeToken(localStorage.getItem('access_token'));
      return payload['uuid']
    }
    
    clearAccessToken() {
      localStorage.removeItem('access_token')
      this.jwtPayload = null
    }
    
    isAccessTokenIvalid() {
      const token = localStorage.getItem('access_token')
      
      return !token || this.jwtHelper.isTokenExpired()
    }
    
    private loadToken(){
      const token = localStorage.getItem('access_token')
      if(token){
        this.saveToken(token)
      }
    }

    hasOnePermission(roles){
      console.log(" hasOnePermission ", roles);
      for(const role of roles){
        
        if(this.hasPermission(role)){
          return true;
        }
      }
      return false;
    }

    removeAllCaches(){
      storage.deleteObject("user-info");
      storage.deleteObject("categories");
      this.clearAccessToken();
      if(environment.production)
        this.googlePlus.disconnect().then();
    }
  }
  