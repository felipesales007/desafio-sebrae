import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { environment } from 'src/environments/environment';
import { ChatState } from '../reducers/states';
import { AppHttp } from './auth/app-http';

@Injectable({
  providedIn: 'root'
})
export class EstablishmentService {
  
  api = environment.api
  
  
  constructor(private http: AppHttp, private store: Store<ChatState>) {
  }
  
  getAllByUUID(uuid: string){
    return this.http.get<any>(`${this.api.ESTABLISMENTS}/uuid/${uuid}`);
  }
  
  getAll(){
    return this.http.get<any>(`${this.api.ESTABLISMENTS}`);
  }

  getById(id){
    return this.http.get<any>(`${this.api.ESTABLISMENTS}/${id}`);
  }

  getByNeighborhood(neighborhood){
    return this.http.get<any>(`${this.api.ESTABLISMENTS}/neighborhood/${neighborhood}`);
  }
  
  
}
