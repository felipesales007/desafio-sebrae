import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { UserRequest } from 'src/app/models/dto/user.request';
import { AppInfo } from 'src/app/models/app.info.model';
import * as storage from 'src/app/utils/storage';
import { environment } from 'src/environments/environment';
import { Http } from './auth/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  api = environment.api
  private appInfo: AppInfo = new AppInfo()

  constructor(private http: Http) { }

  existsEmail(email: string) {
    return this.http.get(`${this.api.USERS}/exists/${email}`)
  }

  getByUserUUID(uuid: string) {
    return this.http.get(`${this.api.USERS}/uuid/${uuid}`)
  }

  postUser(userRequest: UserRequest){
    return this.http.post(`${this.api.USERS}`, userRequest)
  }

  async getAppInfo() {

    await storage.getObject("user-info")
    .then(async data => {
      if(data.value){
        this.appInfo =  JSON.parse(data.value);
      }else{
        await this.getUserInfo().subscribe(data => this.appInfo = data)  
      }
    })
    return this.appInfo
  }

  getUserInfo(){
    return this.http.get<any>(`${this.api.USERS}/user-info`)
      .pipe(catchError(this.http.handleError(HttpErrorResponse)))
      .pipe(tap(data =>  storage.setObject("user-info", JSON.stringify(data))))
      
  }
}
