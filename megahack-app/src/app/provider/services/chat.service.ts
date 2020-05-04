import { Injectable } from '@angular/core';
import { ChatRequest } from 'src/app/models/dto/chat.request';
import { environment } from 'src/environments/environment';
import { AppHttp } from './auth/app-http';
import { Store } from '@ngrx/store';
import { ChatState } from '../reducers/states';
import { chatSelect } from '../reducers/reducer';
import { ActionChat } from '../reducers/actions';
import { TypeMessage } from '../reducers/type.message';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  api = environment.api
 

  constructor(private http: AppHttp, private store: Store<ChatState>) {
  }

  sendMessage(message: ChatRequest) {
    return this.http.post(`${this.api.CHAT}`, message)
  }

  findAll(toUserUUID: string) {
    return this.http.get<any>(`${this.api.CHAT}/${toUserUUID}`)
    .pipe(tap(data => this.store.dispatch(new ActionChat(TypeMessage.CHATS, data))))
  }

  findLatestContactsUser(page, size) {
    return this.http.get<any>(`${this.api.CHAT}/latest-contacts/users?page=${page}&size=${size}`)
  }

  findLatestContactsEstablishment(page, size) {
    return this.http.get<any>(`${this.api.CHAT}/latest-contacts/establishment?page=${page}&size=${size}`)
  }

}
