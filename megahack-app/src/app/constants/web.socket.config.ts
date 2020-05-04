import { environment } from 'src/environments/environment';


export class WebSocketConfig {
   public static register: string = "/app/register";

   public static send: string = "/app/send";
   
   public static uri: string = `${environment.ws}/mega-hack/socket`;

   public static topic: string = "/user/queue/reply";
}