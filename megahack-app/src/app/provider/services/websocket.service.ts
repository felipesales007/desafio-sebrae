import { Injectable, OnInit } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { IMessage, Message } from '@stomp/stompjs';
import { map } from 'rxjs/operators';
import { StompService, StompState } from '@stomp/ng2-stompjs';
import { Store } from '@ngrx/store';
import { ChatState } from '../reducers/states';
import { WsData } from 'src/app/models/dto/ws.data';
import { WebSocketConfig } from 'src/app/constants/web.socket.config';
import { ActionChat } from '../reducers/actions';
import { Http } from './auth/http';
import { environment } from 'src/environments/environment';
import { TypeMessage } from '../reducers/type.message';
import { ProductService } from './product.service';
import { OrderService } from './order.service';

@Injectable({ providedIn: 'root' })
export class WebsocketService implements OnInit{
   private datasubscription: Subscription;
   private statesubscription: Subscription;
   public message: Observable<Message>;
   public wsstate: Observable<any>;
   private _wsData: WsData;
   
   constructor(
      private stompService: StompService,
      private store: Store<ChatState>) {
      //this.connectWebSocket();
      
   }
   ngOnInit(): void {
      this.keepHeroku()
   }
   
   public connectWebSocket() {
      console.log("chamou coonect");
      if(!this.stompService.active)
         this.stompService.activate()
      this.wsstate = this.stompService.state.pipe(map((state: number) => state));
      this.message = this.stompService.subscribe(WebSocketConfig.topic);
      this.datasubscription = this.getSocketDataObservable().subscribe(this.receiverMessage);
      this.statesubscription = this.getSocketStateObservable().subscribe(this.onStateChange);
   }
   
   publishEvent(wsData: WsData, type) {
      if(type == 'CHAT'){
         this.store.dispatch(new ActionChat(TypeMessage.CHAT, wsData.payload));
      } else {
         this.store.dispatch(new ActionChat(TypeMessage.CHAT_ORDER, wsData.payload));
      } 
   }
   
   
   public send(chat: any) {
      this.stompService.publish(WebSocketConfig.send, JSON.stringify(chat));
   }
   
   public receiverMessage = (message: IMessage) => {
      console.log("receiverMessage ", message.body);
      
      this._wsData = JSON.parse(message.body);
      this.publishEvent(this._wsData, message.body['type'])
   }
   
   public getSocketDataObservable() {
      return this.message;
   }
   
   private onStateChange = (state: number) => {
      if (state == 2) {
         //fazer algo quando conectar
      } else if ( state == 0) {
         window.location.reload();
      }
      console.log('WS connection state changed ' + StompState[state]);
   }
   
   public getSocketStateObservable() {
      return this.wsstate;
   }
   
   public disconnect() {
      this.stompService.deactivate()
      this.datasubscription.unsubscribe();
      this.statesubscription.unsubscribe();
   }
   
   //prevent Heroku free from disconnecting the websocket
   public keepHeroku() {
      setTimeout(() => this.stompService.publish("", ""), 50000);
   }
}