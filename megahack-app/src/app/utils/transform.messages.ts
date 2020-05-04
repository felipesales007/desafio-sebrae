import { Chat } from '../models/chat.model';
import * as moment from 'moment';
import { Order } from '../models/order.model';
import { OrderRequest } from '../models/dto/order.request';

export const groupMessages = (chats: Array<Chat>):Map<string, Array<Chat>> => {
   let map = new Map<string, Array<Chat>>();
   chats.forEach(c => {     
      const values = map.get(moment(c.dateTime).format('DDMMMMYYYY'));
      const key = moment(c.dateTime).format('DDMMMMYYYY');
      
      if (!values) {
         map.set(key, [c]);
      } else {
         var v = [...values, c]
         v.sort((a, b) => moment(a.dateTime).toDate().getTime() - moment(b.dateTime).toDate().getTime())
         map.set(key, v);
      }
   })
   return map;
}

export const groupMessagesByChat = (map: Map<string, Array<Chat>>, chat: Chat):Map<string, Array<Chat>> => {   
   const values = map.get(moment(chat.dateTime).format('DDMMMMYYYY'));
   const key = moment(chat.dateTime).format('DDMMMMYYYY');
   
   if (!values) {
      map.set(key, [chat]);
   } else {
      map.set(key, [...values, chat]);
   }
   return map;
}

export const generateOrder = (order: Order):OrderRequest => {   
   let orderRequest =  new  OrderRequest()
   orderRequest.establishment =  order.establishment.id
   order.orderItems.forEach(element => {
      orderRequest.orderItems.push(
         {
            product: element.id,
            amount: 1
         }
         )
      })
      orderRequest.addressDelivery = "Teste"
      
      return orderRequest;
   }