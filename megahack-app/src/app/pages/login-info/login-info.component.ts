import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavController } from '@ionic/angular';
import { Store } from '@ngrx/store';
import { AppAction } from 'src/app/provider/reducers/actions';
import { AppState } from 'src/app/provider/reducers/states';
import { AuthService } from 'src/app/provider/services/auth/auth.service';
import { UserService } from 'src/app/provider/services/user.service';
import { TypeMessage } from 'src/app/provider/reducers/type.message';

@Component({
  selector: 'login-info',
  templateUrl: './login-info.component.html',
  styleUrls: ['./login-info.component.scss'],
})
export class LoginInfoComponent implements OnInit {
  
  constructor( 
    private nav: NavController,
    private userService: UserService,
    private appStore: Store<AppState>,
    private authService: AuthService,
    private route: ActivatedRoute, 
    private router: Router) { }
    
    showSpinner:boolean = false
    
    async ngOnInit() {
      this.route.queryParams
      .subscribe(async params => { 
        await this.authService.saveToken(params.token)
        this.getUserInfo()
      });
    }
    
    message: string = ""
    accessToken: string = ""
    error: boolean = false;
    
    backToLogin(){
      this.nav.pop()
      this.nav.navigateRoot('/app/login', {replaceUrl: true})
    }
    
    async getUserInfo() {
      this.showSpinner = true;
      
      this.message = 'obtendo dados de usuário';
      (await (await this.userService.getUserInfo()).subscribe(data => {
        this.appStore.dispatch(new AppAction(TypeMessage.APP, data))
        this.router.navigateByUrl('/tabs/home')
        this.showSpinner = false;
      }, () => {
        this.showSpinner = false;
        this.message = 'algo deu errado. tente novamente :(';
      }
      ))
    }
    
  }
  