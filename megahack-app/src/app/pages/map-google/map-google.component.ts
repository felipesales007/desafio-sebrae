import { Component, OnInit, ViewChild } from '@angular/core';
import {Platform, LoadingController } from '@ionic/angular';
import {GoogleMaps, GoogleMap, Marker, Environment, GoogleMapOptions, GoogleMapsEvent, MyLocation, ILatLng, LatLng, GoogleMapsAnimation} from '@ionic-native/google-maps';
import { ActivatedRoute, Router } from '@angular/router';
import { DeliveryService } from 'src/app/provider/services/delivery.service';
declare var google:any;
@Component({
  selector: 'app-map-google',
  templateUrl: './map-google.component.html',
  styleUrls: ['./map-google.component.scss'],
})
export class MapGoogleComponent implements OnInit {

  @ViewChild('map', {static: true}) mapElement:any;
  private loading:any;
  private map:GoogleMap;
  private originMaker:Marker;
  private markerDestination:Marker;
  private markerother:Marker;
  private googleDirectionsService = new google.maps.DirectionsService();
private orderId:number;
  constructor(private platform:Platform,
              private loadingCrtl:LoadingController,
              private activeRouter: ActivatedRoute,
              private deliveryService: DeliveryService,
              private router: Router) {

  }
  ngOnInit(): void {
    this.activeRouter.params.subscribe(params => {
      this.orderId = params['id'];
    });
  this.mapElement = this.mapElement.nativeElement;
  this.mapElement.style.width = this.platform.width()+'px';
  this.mapElement.style.height = this.platform.height()+'px';
  
  console.log('id da order');
  console.log(this.orderId);
  this.loadMap();
  }

  async loadMap(){
     this.loading = await this.loadingCrtl.create({message:'Por favor, aguarde...'});
    await this.loading.present();

    Environment.setEnv({
      'API_KEY_FOR_BROWSER_RELEASE': 'AIzaSyC-5Lp2uPzz7K6K6zcVXmW1OIxvPspWf3M',
      'API_KEY_FOR_BROWSER_DEBUG': 'AIzaSyC-5Lp2uPzz7K6K6zcVXmW1OIxvPspWf3M'
    });

    let mapOptions: GoogleMapOptions = {
    controls: {
      zoom:false
      } 
    }
    
      this.map = GoogleMaps.create(this.mapElement, mapOptions);
    try{
        await this.map.one(GoogleMapsEvent.MAP_READY);
        await this.addOriginMaker();
        this.calcRoute();
    }catch(erro){
        console.log(erro);
    }finally{
        this.loading.dismiss();
    }
   
  }

  async addOriginMaker(){
    try{
     //const myLocation: MyLocation  = await this.map.getMyLocation();
     const mylocation:ILatLng = new LatLng(-12.9744007, -38.4635142);  
      const destLocarion: ILatLng =  new LatLng(-12.9749633, -38.4637988); 
      const other: ILatLng =  new LatLng(-12.9744301, -38.4626079); 
      await this.map.moveCamera({
        target: new LatLng(-12.9744007, -38.4635142),
        zoom:18
      });

     this.originMaker = this.map.addMarkerSync({
        title:'Origim',
        icon:'../assets/icon/delivery-bike.png',
        animation:GoogleMapsAnimation.DROP,
        position: mylocation
      });

     this.markerDestination  =  this.map.addMarkerSync({
        title: 'Origim',
        icon: '../assets/icon/laptop.png',
        animation: GoogleMapsAnimation.DROP,
        position: destLocarion
      });
      
      this.markerother  =  this.map.addMarkerSync({
        title: 'Origim',
        icon: '#000',
        animation: GoogleMapsAnimation.DROP,
        position: other
      });

    }catch(erro){
      console.log(erro);
    }
 
  }

   calcRoute(){

      this.googleDirectionsService.route({
          origin: this.originMaker.getPosition(),
          destination: this.markerDestination.getPosition(),
          waypoints: [
            {
              location: this.markerother.getPosition(),
              stopover: true
            }],
          travelMode:'DRIVING'
      }, async results => {
          console.log(results);
          const points = new Array<ILatLng>();

         const routes = results.routes[0].overview_path;

        for (let i = 0; i < routes.length; i++) {
          points[i] = {
            lat: routes[i].lat(),
            lng: routes[i].lng()
          }
        }
        await this.map.addPolyline({
          points:points,
          color:'#000',
          width:3
        });

        this.map.moveCamera({target:points});

      });
    }
    
    finalizeDelivery(){
      this.deliveryService.putAccomplished(this.orderId).subscribe(d =>{console.log(d)});
      this.router.navigate(['/tabs/home']);
    }

  }