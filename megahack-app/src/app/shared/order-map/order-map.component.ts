import { Component, OnInit } from '@angular/core';
import { DeliveryOrder } from 'src/app/models/dto/deliveryOrder';
import { DeliveryService } from 'src/app/provider/services/delivery.service';

@Component({
  selector: 'order-map',
  templateUrl: './order-map.component.html',
  styleUrls: ['./order-map.component.scss'],
})
export class OrderMapComponent implements OnInit {

  deliveryOrders: Array<DeliveryOrder> = new Array<DeliveryOrder>();

  constructor(private deliveryService: DeliveryService) { }

  ngOnInit() {
    this.getAll();
  }

  getAll(){
    this.deliveryService.getAllDeliveriesMade().subscribe(data => { 
      this.deliveryOrders = data;
    });
  }
}