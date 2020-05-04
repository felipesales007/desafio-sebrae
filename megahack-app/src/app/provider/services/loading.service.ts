import { Injectable } from '@angular/core';
import { LoadingController, ToastController } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {
  loading: any;
  
  constructor(public loadingController: LoadingController, 
    public toastController: ToastController) {   
  }
  
  public async open(message) {
    this.loading = await this.loadingController.create({
      message: message
    });   
    await this.loading.present();
  }
  
  public async close() {
    await this.loading.dismiss();
  }

  async presentToastSuccess(message) {
    const toast = await this.toastController.create({
      message: message,
      duration: 3000,
      position: 'bottom',
      color: 'success',
      buttons: [
        {
          text: 'Ok',
          role: 'cancel'
        }
      ]
    });
    toast.present();
  }

  async presentToastError(message) {
    const toast = await this.toastController.create({
      message: message,
      position: 'top',
      duration: 3000,
      color: 'middle',
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
