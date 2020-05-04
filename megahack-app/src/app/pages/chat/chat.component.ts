import { KeyValue } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavController } from '@ionic/angular';
import { Store } from '@ngrx/store';
import { Chat } from 'src/app/models/chat.model';
import { ChatRequest } from 'src/app/models/dto/chat.request';
import { TypeChat } from 'src/app/models/enums/type.chat';
import { Establishment } from 'src/app/models/establishment.model';
import { User } from 'src/app/models/user.model';
import { chatSelect } from 'src/app/provider/reducers/reducer';
import { ChatState } from 'src/app/provider/reducers/states';
import { AuthService } from 'src/app/provider/services/auth/auth.service';
import { ChatService } from 'src/app/provider/services/chat.service';
import { EstablishmentService } from 'src/app/provider/services/establishment.service';
import { OrderService } from 'src/app/provider/services/order.service';
import { UserService } from 'src/app/provider/services/user.service';
import { groupMessages } from 'src/app/utils/transform.messages';

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
})
export class ChatComponent implements OnInit, OnDestroy {
  message = null;
  messages: Map<string, Array<Chat>> = new Map<string, Array<Chat>>()
  chat: ChatRequest = new ChatRequest();
  toUserUUID: string;
  chats = []
  establishment: Establishment = new Establishment()
  user: User = new User()
  isEstablishment: boolean
  
  constructor(
    private nav: NavController,
    private router: Router,
    private chatStore: Store<ChatState>,
    private chatService: ChatService,
    private activeRouter: ActivatedRoute,
    private establishmentService: EstablishmentService,
    private auth: AuthService,
    private userService: UserService,
    private orderService: OrderService) { 
      
    }
    ngOnDestroy(): void {
    }

    hasRole(role) {
      return this.auth.hasPermission(role)
    }
    
    
    ngOnInit() {
      this.chatStore.select(chatSelect).subscribe(this.treateEvent)
      this.activeRouter.params.subscribe(params => {
        this.toUserUUID = params['to_user'];
        this.isEstablishment = params['establishment'];
        this.chechProfile()
      });
      
      
      this.getAllChats();
    }

    getAllChats(){
      this.chatService.findAll(this.toUserUUID).subscribe();
    }


    
    chechProfile(){
      if(this.auth.hasPermission("ROLE_USER")){
        this.establishmentService.getAllByUUID(this.toUserUUID).subscribe((data: any) => {
          this.establishment = data
      })
      }else if (this.auth.hasPermission("ROLE_VENDEDOR")){
        this.userService.getByUserUUID(this.toUserUUID).subscribe((data: any) => {
          this.user = data
        })
      }
     
    }

    findUser(){

    }

    openOrder(id) {
      this.router.navigate(['/tabs/orders', id]);
    }
    
    send(){
      this.chat.to = this.toUserUUID
      this.chat.message = this.message
      this.chat.type = TypeChat.CHAT
      if(this.message!=null && this.message!=""){
        this.chatService.sendMessage(this.chat).subscribe((chat: Chat) => {
          this.message = null
        });
      }
      
    }
    
    keyDescOrder = (a: KeyValue<number,any>, b: KeyValue<number,any>): number => {
      return a.key > b.key ? -1 : (b.key > a.key ? 1 : 0);
    }

    originalOrder = (a: KeyValue<number,any>, b: KeyValue<number,any>): number => {
      return 0;
    }
    
    back() {
      this.nav.back() 
    }
    
    async openPageProducts(establishmentId){
      console.log("openPageProducts ", establishmentId);
      
      this.router.navigate(['/tabs/products/product-list', establishmentId]);
    }
    
    public treateEvent = (event: ChatState) => {    
      this.messages = groupMessages(event.messages)       
    }
  }
  