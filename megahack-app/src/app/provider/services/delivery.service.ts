import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { environment } from 'src/environments/environment';
import { ChatState } from '../reducers/states';
import { AppHttp } from './auth/app-http';

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {
  
  api = environment.api
  
  
  constructor(private http: AppHttp) {
  }
  
  getAll() {
    return this.http.get<any>(`${this.api.DELIVERY}`);
  }

  putAccomplished(id){
    return this.http.put<any>(`${this.api.DELIVERY}/accomplished/order/${id}`,{});
  } 

  getAllDeliveriesMade() {
    return this.http.get<any>(`${this.api.DELIVERY}/deliveries/made`);
  }
}
