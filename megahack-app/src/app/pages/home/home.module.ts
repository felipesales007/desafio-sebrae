import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HomeComponent } from './home.component';
import { HomeRoutes } from './home.routing';
import { IonicModule } from '@ionic/angular';
import { ChatListComponent } from 'src/app/shared/chat-list/chat-list.component';
import { ChatService } from 'src/app/provider/services/chat.service';
import { PurchasesComponent } from 'src/app/shared/purchases/purchases.component';
import { EstablishmentListComponent } from 'src/app/shared/establishment-list/establishment-list.component';
import { NavbarModule } from 'src/app/components/navbar.module';
import { EstablishmentService } from 'src/app/provider/services/establishment.service';
import { ProductsComponent } from 'src/app/shared/products/products.component';
import { OrderMapComponent } from 'src/app/shared/order-map/order-map.component';
import { OrderDeliveyComponent } from 'src/app/shared/order-delivey/order-delivey.component';
import { SpinnerModule } from 'src/app/shared/spinners/loading.module';
import { MapGoogleComponent } from '../map-google/map-google.component';
import { GoogleMaps } from '@ionic-native/google-maps';


@NgModule({
  declarations: [
    OrderMapComponent,
    OrderDeliveyComponent,
    HomeComponent,
    ChatListComponent,
    EstablishmentListComponent,
    ProductsComponent,
    MapGoogleComponent
  ],
  imports: [
    SpinnerModule,
    NavbarModule,
    IonicModule,
    FormsModule,
    CommonModule,
    RouterModule.forChild(HomeRoutes),
  ],
  providers:[ChatService,EstablishmentService, GoogleMaps]
})
export class HomeModule { }


