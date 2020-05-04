import { registerLocaleData } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import br from '@angular/common/locales/br';
import pt from '@angular/common/locales/pt';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouteReuseStrategy, RouterModule } from '@angular/router';
import { JwtModule } from '@auth0/angular-jwt';
import { FivAppBarModule, FivFabModule, FivIconModule } from '@fivethree/core';
import { Network } from '@ionic-native/network/ngx';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { StoreModule } from '@ngrx/store';
import { BsDropdownModule } from 'ngx-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarModule } from './components/navbar.module';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';
import { UserRegistrationComponent } from './pages/user-registration/user-registration.component';
import { metaReducers, reducers } from './provider/reducers';
import { AuthService } from './provider/services/auth/auth.service';
import { Interceptor } from './provider/services/auth/interceptor';
import { YmFormModule } from './shared/error-form-message/ym.form.module';
import { GooglePlus } from '@ionic-native/google-plus/ngx';
import { Http } from './provider/services/auth/http';
import { AppHttp } from './provider/services/auth/app-http';
import { WebsocketService } from './provider/services/websocket.service';
import { StompService, StompConfig } from '@stomp/ng2-stompjs';
import { stompConfig } from './constants/stomp.config';
import { SpinnerModule } from './shared/spinners/loading.module';

registerLocaleData(br, 'pt-BR');
registerLocaleData(pt, 'pt');

export function getToken() {
  return localStorage.getItem("access_token");
}
@NgModule({
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    AuthLayoutComponent,
    UserRegistrationComponent
  ],
  entryComponents: [],
  imports: [
    FivAppBarModule,
    FivIconModule,
    FivFabModule,
    BrowserModule,
    IonicModule.forRoot(),
    BrowserAnimationsModule,
    YmFormModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    FormsModule,
    HttpClientModule,
    NavbarModule,
    RouterModule,
    BsDropdownModule.forRoot(),
    AppRoutingModule,
    StoreModule.forRoot(reducers, {
      metaReducers,
      runtimeChecks: {
        strictStateImmutability: true,
        strictActionImmutability: true
      }
    }),
    JwtModule.forRoot({
      config: {
        tokenGetter: getToken
      }
    })
  ],
  providers: [
    [
      StompService ,{
      provide: StompConfig,
      useValue: stompConfig
    }],
    WebsocketService,
    GooglePlus,
    StatusBar,
    SplashScreen,
    { 
      provide: RouteReuseStrategy,
      useClass: IonicRouteStrategy 
    },
    AuthService,
    AppHttp,
    Http,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },
    Network
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
