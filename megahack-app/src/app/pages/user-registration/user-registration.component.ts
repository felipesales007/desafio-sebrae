import { Component, OnInit } from '@angular/core';
import { ValidationFormUtil } from 'src/app/utils/validate-form.util';
import { AuthService } from 'src/app/provider/services/auth/auth.service';
import { UserService } from 'src/app/provider/services/user.service';
import { UserRequest } from 'src/app/models/dto/user.request';
import { LoadingService } from 'src/app/provider/services/loading.service';
import { NavController, AlertController } from '@ionic/angular';


@Component({
  selector: 'user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.scss']
})
export class UserRegistrationComponent extends ValidationFormUtil implements OnInit {
  hide = true;
  userRequest: UserRequest
  submited: boolean = false;

  constructor(
    private auth: AuthService,
    private userService: UserService,
    private loadingService: LoadingService,
    private nav: NavController,
    private alertController: AlertController
  ) { 
    super()
    this.userRequest = new UserRequest()
  }

  submit() {
    this.submited = true;
    this.userService.existsEmail(this.userRequest.email).subscribe(data => {
      if (data){
        this.emailExiste();
      } else {
        this.register();
      }
    });

  }

  async register(){
    await this.loadingService.open("Registrando")
    this.userService.postUser(this.userRequest).subscribe(() => {

      this.backToLogin();
      this.loadingService.close();
      this.loadingService.presentToastSuccess("Registrado com sucesso")
    }, () => {
      this.loadingService.close();
    })
  }

  clearConfirme() {
    this.userRequest.confirmPassword = null
  }

  ngOnInit() {
    this.auth.clearAccessToken()

  }

  backToLogin(){
    this.nav.pop()
    this.nav.navigateRoot('/app/login', {replaceUrl: true})
  }
  
  emailExiste(){
      this.alertController.create({
        message: "Email ja existe",
        backdropDismiss: false,
        buttons: [
          {
            text: 'Ok',
            role: 'cancel'
          }
          // {
          //   role: null,
          //   cssClass: 'mb-2',
          //   text: 'Remover',
          //   handler: () => {
          //     $this.delete(card)
          //   }
          // }
        ]
      }).then(data => data.present());
  }
}
