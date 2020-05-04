import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { OrderRequest } from 'src/app/models/dto/order.request';
import { environment } from 'src/environments/environment';
import { ChatState } from '../reducers/states';
import { AppHttp } from './auth/app-http';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  api = environment.api
 

  constructor(private http: AppHttp, private store: Store<ChatState>) {
  }

  newOrder(order: OrderRequest) {
    return this.http.post<any>(`${this.api.ORDER}`, order);
  }

  getOrder(id) {
    return this.http.get<any>(`${this.api.ORDER}/${id}`);
  }

  getValue(id) {
    return this.http.get<any>(`${this.api.ORDER}/${id}/value`);
  }

}
