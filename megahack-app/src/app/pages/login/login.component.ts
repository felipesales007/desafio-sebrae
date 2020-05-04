import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from 'src/app/models/login.model';
import { AuthService } from 'src/app/provider/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  hide = true
  login: Login = new Login();
  message: string = ""
  icon: string = 'eye'

  constructor(
    private authService: AuthService,
    private router: Router) {
    }

    registration(){
      this.router.navigate(['/register'], {replaceUrl: true});
    }

    loginWithGoogle(){
      this.router.navigate(['/app/login-social'], {replaceUrl: true});
    }

    ngOnInit() {
      this.authService.clearAccessToken();
    }

    submit() {
      this.authService.login(this.login.email, this.login.password)
      .then(async (data: any) => {
        this.router.navigate(['/app/login-info'], {queryParams: { token : data.access_token} })
      })
      .catch(error => {
        if(error=='BAD_CREDENTIAL')
        this.message = 'usuário e/ou senha incorretos';
        else if (error=='EMAIL_NOT_VERIFIED')
        this.message = 'verifique o e-mail para continuar';
      })
    }
  }
