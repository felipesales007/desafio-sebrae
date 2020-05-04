import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/provider/reducers/states';
import { AuthService } from 'src/app/provider/services/auth/auth.service';

@Component({
  selector: 'dashboard',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  segment: string;
  value: string = 'chat'

  constructor(private auth: AuthService) {
    this.segment = 'chat';
  }

  ngOnInit() {
    this.checkProfile()
  }

  checkProfile(){
    if(this.auth.hasPermission("ROLE_USER")){
      this.value = 'chat'
      this.segment = 'chat'
    }else if (this.auth.hasPermission("ROLE_ENTREGADOR")){
      this.value = 'acompanhar'
      this.segment = 'acompanhar'
    }
    
  }

  segmentChanged(event){
    this.segment = event.detail.value
  }

  hasRole(role) {
    return this.auth.hasPermission(role)
  }
}
