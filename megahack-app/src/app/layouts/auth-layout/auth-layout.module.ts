import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { RouterModule } from "@angular/router";
import { LoginComponent } from 'src/app/pages/login/login.component';
import { YmFormModule } from 'src/app/shared/error-form-message/ym.form.module';
import { AuthLayoutRoutes } from "./auth-layout.routing";
import { IonicModule } from '@ionic/angular';
import { GooglePlus } from '@ionic-native/google-plus/ngx';
import { LoginInfoComponent } from 'src/app/pages/login-info/login-info.component';
import { SpinnerModule } from 'src/app/shared/spinners/loading.module';
import { LoginSocialComponent } from 'src/app/pages/login-social-network/login-social.component';
import { StompService, StompConfig } from '@stomp/ng2-stompjs';
import { stompConfig } from 'src/app/constants/stomp.config';
import { WebsocketService } from 'src/app/provider/services/websocket.service';

@NgModule({
  imports: [
    SpinnerModule,
    IonicModule,
    CommonModule,
    RouterModule.forChild(AuthLayoutRoutes),
    FormsModule,
    YmFormModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  declarations: [
    LoginComponent,
    LoginInfoComponent,
    LoginSocialComponent
  ],
  providers: [
    WebsocketService]
})
export class AuthLayoutModule {}
