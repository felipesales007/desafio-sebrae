import { User } from './user.model';
export class Establishment {
   "id": number
   "name": string
   "address": any;
   "imageURL": any;
   "user": User;

   constructor(){
     this.id = null
     this.name = null
     this.address = null
     this.imageURL = null
     this.user = null
   }
 }
 