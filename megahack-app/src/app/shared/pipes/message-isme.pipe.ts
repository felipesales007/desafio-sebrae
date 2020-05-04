import { Pipe, PipeTransform } from '@angular/core';
import * as _moment from 'moment';
import { AuthService } from 'src/app/provider/services/auth/auth.service';
const moment = _moment;

@Pipe({
  name: 'isMe',
  pure: true
})
export class MessageIsmePipe implements PipeTransform {
  
  constructor(private auth: AuthService) {
    
  }
  transform(value: any, ...args: any[]): any {
    var myUUID = this.auth.getIUUID();
   
    switch (args[0]) {
      case 'message':
      if(value.from == myUUID){
        return `user personal`
      }else {
        return `client  `
      }
      case 'view':
      if(value.from == myUUID){
        return true
      }else {
        return false
      }
    }

  }
  
}
