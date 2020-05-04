import { HttpClient, HttpHandler } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { Observable, of } from 'rxjs';

export class NotAuthenticatedError {}

@Injectable()
export class Http extends HttpClient{

  constructor(
    private httpHandler: HttpHandler,
    public toastController: ToastController,
  ) {
    super(httpHandler);
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
