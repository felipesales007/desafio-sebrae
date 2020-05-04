import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Chat } from 'src/app/models/chat.model';
import { ChatService } from 'src/app/provider/services/chat.service';
import { EstablishmentService } from 'src/app/provider/services/establishment.service';
import { AuthService } from 'src/app/provider/services/auth/auth.service';

@Component({
  selector: 'chat-list',
  templateUrl: './chat-list.component.html',
  styleUrls: ['./chat-list.component.scss'],
})
export class ChatListComponent implements OnInit {
  latestContacts:Array<Chat> = new Array<Chat>();
  showSpinner: boolean = false;

  constructor(private chatService: ChatService, private router: Router, private auth: AuthService) { }
  
  ngOnInit() {
    this.checkProfile();
  }

  checkProfile(event?){
    this.showSpinner = true;
    if(this.auth.hasPermission("ROLE_USER")){
      this.getLatestContactsEstablhishment(event)
    }else if (this.auth.hasPermission("ROLE_VENDEDOR")){
      this.getLatestContactsUser(event)
    }
    
  }

  hasRole(role) {
    return this.auth.hasPermission(role)
  }
  
  doRefresh(event) {
    this.checkProfile(event);
  }

  openPageChat(uuid){
    if(this.auth.hasPermission("ROLE_USER")){
      this.router.navigate(['/tabs/chat', uuid, false])
    }else if (this.auth.hasPermission("ROLE_VENDEDOR")){
      this.router.navigate(['/tabs/chat', uuid, true])
    }
   
  }
  
  getLatestContactsUser(event?){
    this.chatService.findLatestContactsUser(0, 100).subscribe(chats => {
      this.latestContacts = chats
      if(event)
        setTimeout(()=> event.target.complete(), 500)
        this.showSpinner = false
    }, () => this.showSpinner = true)
  }

  getLatestContactsEstablhishment(event?){
    this.chatService.findLatestContactsEstablishment(0, 100).subscribe(chats => {
      this.latestContacts = chats
      if(event)
        setTimeout(()=> event.target.complete(), 500)
      this.showSpinner = false;
    }, () => this.showSpinner = false)
  }
  
  
}
