import { Order } from 'src/app/models/order.model';
import { AppInfo } from '../../models/app.info.model';
import { OrderRequest } from 'src/app/models/dto/order.request';


export class AppState {
  cart: Order;
  order: OrderRequest
  info: AppInfo

  constructor(){
    this.order = new OrderRequest()
    this.cart = new Order()
    this.info = new AppInfo()
  }
}

export class ChatState {
  type: string
  messages;

  constructor(messages = []){
    this.type = null;
    this.messages = messages
  }

}

export class SpinnerState {
  isLoading: Boolean

  constructor(){
    this.isLoading = false
  }
}



