import { Component, OnInit } from '@angular/core';
import { ModalController, NavController } from "@ionic/angular";
import { Store } from '@ngrx/store';
import { Order } from 'src/app/models/order.model';
import { AppAction, ActionChat } from 'src/app/provider/reducers/actions';
import { appSelect } from 'src/app/provider/reducers/reducer';
import { AppState, ChatState } from 'src/app/provider/reducers/states';
import { TypeMessage } from 'src/app/provider/reducers/type.message';
import { OrderService } from 'src/app/provider/services/order.service';
import { generateOrder } from 'src/app/utils/transform.messages';
import { ChangePaymentComponent } from '../../change-payment/change-payment.component';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/provider/services/auth/auth.service';

@Component({
  selector: 'order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss'],
})
export class OrderlComponent implements OnInit {
  order: Order = new Order()
  orderItems = []
  componentProps: any = {}
  total = 0
  subTotal = 0
  orderId: null
  showSpinner: boolean = false;

  
  constructor(private modalController: ModalController,
    private appStore: Store<AppState>,
    private chatStore: Store<ChatState>,
    private orderService: OrderService,
    private nav: NavController,
    private activeRouter: ActivatedRoute,
    private auth: AuthService) {
      this.appStore.select(appSelect).subscribe(this.treateEvent)
    }
    
    ngOnInit() {
    
      this.activeRouter.params.subscribe(params => {
        this.orderId = params['id'];
      });
      
      if(this.orderId==null){  
        this.modalController.getTop().then(data=> {
          if(data.componentProps){
            this.componentProps = data.componentProps
            this.createOrder()
          } 
        });  
      }else {
        this.showSpinner  = true;
        this.orderService.getOrder(this.orderId).subscribe(data => {
          this.order = data
          this.order.orderItems = data.orderItems
          this.showSpinner  = false
        }, () => this.showSpinner  = false)        
      }
      
    }
    
    hasRole(role) {
      return this.auth.hasPermission(role)
    }
    
    async changePayment(){
      const modal = await this.modalController.create({
        component: ChangePaymentComponent,
        
      });
      return await modal.present();
    }
    
    createOrder(){   
      this.order.establishment = this.componentProps.establishment
      this.order.orderItems = [...this.orderItems, this.componentProps.product]
      this.setTotal(this.order.orderItems)
      this.appStore.dispatch(new AppAction(TypeMessage.CART_ADD, this.order))
      
    }

    setTotal(items){
      this.total = 0;
      this.subTotal = 0;
      items.forEach(element => {
        this.subTotal = this.subTotal + element.price
      });
      this.total = this.subTotal + 5.99
    }
    
    removeItem(id){
      this.order = new Order()
      this.order.establishment = this.componentProps.establishment
      this.orderItems = this.orderItems.filter(i => i.id != id)
      this.order.orderItems = [...this.orderItems]
      this.setTotal(this.order.orderItems)
      this.appStore.dispatch(new AppAction(TypeMessage.CART_ADD, this.order))
    }
    
    send() {
      this.showSpinner  = true;
      let or = generateOrder(this.order)
      this.orderService.newOrder(or).subscribe(data => {
        this.chatStore.dispatch(new ActionChat(TypeMessage.CART, data.id))
        this.closeModal()
        this.nav.back()
        this.showSpinner  = false;
      }, () => this.showSpinner  = false)
    }
    
    async closeModal() {
      if(this.orderId==null) {
        await this.modalController.dismiss();
        this.modalController = null;
      } else {
        this.nav.back()
      }
      
    }
    
    treateEvent = (event: AppState) => {
      this.orderItems = event.cart.orderItems 
    }
    
  }
  