import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { environment } from 'src/environments/environment';
import { ChatState } from '../reducers/states';
import { AppHttp } from './auth/app-http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  
  api = environment.api
  
  
  constructor(private http: AppHttp, private store: Store<ChatState>) {
  }
  
  getAllByEstablishment(id) {
    return this.http.get<any>(`${this.api.PRODUCT}/establishment/${id}`);
  }

  enableDisableProduct(id, active) {
    return this.http.put(`${this.api.PRODUCT}/${id}/active/${active}`, {});
  }
  
}
