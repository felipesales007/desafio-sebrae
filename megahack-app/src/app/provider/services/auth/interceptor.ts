import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthService } from './auth.service';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(
    private auth: AuthService) {
    }

    intercept(req: any, next: HttpHandler): Observable<HttpEvent<any>> {
      let authRequest: any;

      if (localStorage.getItem('access_token') && req.body!='grant_type=refresh_token') {
        authRequest = req.clone({
          setHeaders: {
            'Authorization': `Bearer `+ localStorage.getItem('access_token')
          }
        });
        return next.handle(authRequest);
      } else {
        return next.handle(req);
      }
    }
  }
