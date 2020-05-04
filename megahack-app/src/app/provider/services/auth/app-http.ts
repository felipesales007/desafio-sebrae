import { Injectable } from '@angular/core';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { Observable, from as observableFromPromise, of } from 'rxjs';
import { Network } from '@ionic-native/network/ngx';
import { AuthService } from './auth.service';
import { ToastController } from '@ionic/angular';

export class NotAuthenticatedError {}

@Injectable()
export class AppHttp extends HttpClient{

  constructor(
    private auth: AuthService,
    private httpHandler: HttpHandler,
    public toastController: ToastController,
    private network: Network
  ) {
    super(httpHandler);
  }

  public delete<T>(url: string, options?: any): Observable<T> {
    return this.newRequest<T>(() => super.delete<T>(url, options));
  }

  public patch<T>(url: string, body: any, options?: any): Observable<T> {
    return this.newRequest<T>(() => super.patch<T>(url, options));
  }

  public head<T>(url: string, options?: any): Observable<T> {
    return this.newRequest<T>(() => super.head<T>(url, options));
  }

  public options<T>(url: string, options?: any): Observable<T> {
    return this.newRequest<T>(() => super.options<T>(url, options));
  }

  public get<T>(url: string, options?: any): Observable<T> {
    return this.newRequest<T>(() => super.get<T>(url, options));
  }

  public post<T>(url: string, body: any, options?: any): Observable<T> {
    return this.newRequest<T>(() => super.post<T>(url, body, options));
  }

  public put<T>(url: string, body: any, options?: any): Observable<T> {
    return this.newRequest<T>(() => super.put<T>(url, body, options));
  }

  private newRequest<T>(fn: Function): Observable<T> {
    // watch network for a disconnection
    this.network.onDisconnect().subscribe(() => {
      this.presentToasError('Sem internet :('); 
    });



    if (this.auth.isAccessTokenIvalid()) {
      const chamadaNovoAccessToken = this.auth.getNewAccessToken()
        .then(() => {
          if (this.auth.isAccessTokenIvalid()) {
            throw new NotAuthenticatedError();
          }

          return fn().toPromise();
        });

      return observableFromPromise(chamadaNovoAccessToken);
    } else {
      return fn();
    }
  }
  errorsClient = [400,402,403,402,404,406,407,408,409,410,411,412,422]
  
  public handleError<T>(result?: T) {
    return (error: any): Observable<T> => {

      if(this.errorsClient.includes(error.status)){
        this.presentToasError(error.error.message);
        return of(error as T);
      }

      this.presentToasError('Erro desconhecido'); // Por enquanto printa no console
        
      return of(error as T);
    }
  };


  async presentToasError(message) {
    const toast = await this.toastController.create({
      message: message,
      duration: 3000,
      color: 'danger',
      buttons: [
        {
          text: 'Ok',
          role: 'cancel'
        }
      ]
    });
    toast.present();
  }



}
