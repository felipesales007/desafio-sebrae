import { Component, OnInit } from "@angular/core";
import { AlertController, NavController } from '@ionic/angular';
import { AppInfo } from 'src/app/models/app.info.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/provider/services/auth/auth.service';
import { UserService } from 'src/app/provider/services/user.service';
import { WebsocketService } from 'src/app/provider/services/websocket.service';


@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.scss"],
})
export class NavbarComponent implements OnInit {
  public focus;
  title = 'Entrega de Bairro'
  sidenavOpen: boolean = true;
  name: string = "";
  info: AppInfo = new AppInfo()

  constructor(
    private userService: UserService,
    private alertController: AlertController,
    private authService: AuthService,
    private nav: NavController,
    private websocketService: WebsocketService,
    ) {
  }


  async ngOnInit() {
    this.info = await this.userService.getAppInfo()
    this.getName(this.info.user)
  }

  getName(user: User) {
    if(user && user.name){
      let nameSplited = user.name.split(" ");
      if(nameSplited.length>1)
      this.name = nameSplited[0]
    }
  }


  openAlertLogout(){
    this.alertController.create({
      message: 'Deseja sair do app?',
      backdropDismiss: false,
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel'
        },
        {
          role: null,
          cssClass: 'mb-2',
          text: 'Sair',
          handler: () => {
            this.logout();
          }
        }
      ]
    }).then(data => data.present());
  }

  logout(){
    this.authService.removeAllCaches();
    this.websocketService.disconnect();
    this.nav.pop()
    this.nav.navigateRoot('/app/login', {replaceUrl: true})
  }
}
