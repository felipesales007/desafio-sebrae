import { User } from './user.model';

export class AppInfo {
   user?: User

   constructor() {
     this.user = new User()
   }
}
 