import { Component, OnInit } from '@angular/core';
import { DeliveryService } from 'src/app/provider/services/delivery.service';
import { NavController } from '@ionic/angular';
import { ActivatedRoute, Router } from '@angular/router';
import { DeliveryOrder } from 'src/app/models/dto/deliveryOrder';

@Component({
  selector: 'order-delivey',
  templateUrl: './order-delivey.component.html',
  styleUrls: ['./order-delivey.component.scss'],
})
export class OrderDeliveyComponent implements OnInit {
  deliveryOrders: Array<DeliveryOrder> = new Array<DeliveryOrder>();

  constructor(private deliveryService: DeliveryService,
     private nav: NavController,
    private activeRouter: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.getAll();
    console.log(this.deliveryOrders);
  }

  getAll(){
    this.deliveryService.getAll().subscribe(data => { 
      this.deliveryOrders = data;
      console.log(this.deliveryOrders);
    });
  }

  accepted(deliery:DeliveryOrder){
    this.router.navigate(['/tabs/home/delivery',deliery.order]);
  }

}
