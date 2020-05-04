import { Component, HostListener, OnDestroy, OnInit } from "@angular/core";
import { NavController } from '@ionic/angular';
import { WebsocketService } from 'src/app/provider/services/websocket.service';
import { AuthService } from 'src/app/provider/services/auth/auth.service';

@Component({
  selector: "admin-layout",
  templateUrl: "./admin-layout.component.html",
  styleUrls: ["./admin-layout.component.scss"]
})
export class AdminLayoutComponent implements OnInit, OnDestroy {
  isMobileResolution: boolean;
  
  
  constructor(private nav: NavController, private websocketService: WebsocketService, private auth: AuthService) {
    if (window.innerWidth < 1200) {
      this.isMobileResolution = true;
    } else {
      this.isMobileResolution = false;
    }    
    this.websocketService.connectWebSocket()
  }

  ngOnDestroy(): void {
      //this.websocketService.disconnect();
  }
  
  @HostListener("window:resize", ["$event"])
  isMobile(event) {
    if (window.innerWidth < 1200) {
      this.isMobileResolution = true;
    } else {
      this.isMobileResolution = false;
    }
  }
  ngOnInit() {
    
  }

  hasRole(role) {
    return this.auth.hasPermission(role)
  }
    
  openPageCards(){
    this.nav.pop()
    this.nav.navigateRoot('/tabs/cards', {replaceUrl: true})
  }
  
  openPageTees(){
    this.nav.pop()
    this.nav.navigateRoot('/tabs/tees', {replaceUrl: true})
  }
  
  openPageDashboard(){
    this.nav.pop()
    this.nav.navigateRoot('/tabs/home', {replaceUrl: true})
  }
  
  openPageRecords(){
    this.nav.pop()
    this.nav.navigateRoot('/tabs/records', {replaceUrl: true})
    
  }
}
