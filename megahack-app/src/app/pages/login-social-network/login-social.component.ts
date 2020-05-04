import { Component, OnInit } from '@angular/core';
import { GooglePlus } from '@ionic-native/google-plus/ngx';
import { NavController } from '@ionic/angular';
import { Store } from '@ngrx/store';
import { AppAction } from 'src/app/provider/reducers/actions';
import { AppState } from 'src/app/provider/reducers/states';
import { AuthService } from 'src/app/provider/services/auth/auth.service';
import { UserService } from 'src/app/provider/services/user.service';
import { TypeMessage } from 'src/app/provider/reducers/type.message';

@Component({
  selector: 'login-social',
  templateUrl: './login-social.component.html',
  styleUrls: ['./login-social.component.scss'],
})
export class LoginSocialComponent implements OnInit {
  
  constructor(
    private googlePlus: GooglePlus,
    private nav: NavController,
    private authService: AuthService,
    private userService: UserService,
    private appStore: Store<AppState>) { }
    
    message: string = ""
    accessToken: string = ""
    error: boolean = false;
    showSpinner: boolean = false;
    ngOnInit() {
      this.login();
    }
    
    login() {
      this.showSpinner = true;
      this.message = "solicitando permissão..."
      this.googlePlus.login({
        'webClientId': '',
        'offline': true
      }).then(res => {
        this.error = false;
        this.accessToken = res.token;
        this.autenticate(this.accessToken)
      }).catch(() => {
        this.error = true;
        this.message = 'algo deu errado. tente novamente :(';
        this.showSpinner = false;
      })
    }
    
    backToLogin(){
      this.nav.pop()
      this.nav.navigateRoot('/app/login', {replaceUrl: true})
    }
    
    autenticate(access_token: string) {
      this.message = "autenticando...";
      
      this.authService.loginWithGoogle(access_token)
      .then(async ()=> {
        this.getUserInfo()
        this.showSpinner = false;
      })
      .catch(() => {
        this.message = 'algo deu errado. tente novamente :(';
        this.error = true;
        this.showSpinner = false;
      })
    }
    
    async getUserInfo() {
      this.message = 'obtendo dados de usuário';
      (await (await this.userService.getUserInfo()).subscribe(data => {
        this.appStore.dispatch(new AppAction(TypeMessage.APP, data))
        this.nav.navigateRoot('/tabs/home', {replaceUrl: true})
      }, () => {
        this.showSpinner = false;
        this.error = true;
        this.message = 'algo deu errado. tente novamente :(';
      }))
    }
    
  }
  