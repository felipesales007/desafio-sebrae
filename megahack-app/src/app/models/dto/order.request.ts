import { Establishment } from '../establishment.model';
import { OrderItemRequest } from './order.item.request';

export class OrderRequest {
	"establishment": number
	"addressDelivery": string
	"latitudeDelivery": number
	"longitudeDelivery": number
   "orderItems": Array<OrderItemRequest>
   
   constructor(){
      this.establishment = null
      this.addressDelivery = null
      this.latitudeDelivery = null;
      this.longitudeDelivery = null
      this.orderItems = []
   }
}