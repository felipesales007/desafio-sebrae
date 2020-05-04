import { Product } from './product.model';
import { Establishment } from './establishment.model';

export class Order {
   "user": number
	"establishment": Establishment
	"addressDelivery": string
	"latitudeDelivery": number
	"longitudeDelivery": number
   "orderItems": Array<Product>
   
   constructor(){
      this.user = null
      this.establishment = new Establishment();
      this.addressDelivery = null
      this.latitudeDelivery = null;
      this.longitudeDelivery = null
      this.orderItems = []
   }
}