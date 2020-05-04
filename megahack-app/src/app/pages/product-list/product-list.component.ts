import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ModalController, NavController } from "@ionic/angular";
import { Establishment } from 'src/app/models/establishment.model';
import { Product } from 'src/app/models/product.model';
import { EstablishmentService } from 'src/app/provider/services/establishment.service';
import { ProductService } from 'src/app/provider/services/product.service';
import { OrderlComponent } from 'src/app/shared/modals/order/order.component';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
})
export class ProductListComponent implements OnInit {
  products: Array<Product> = new Array<Product>();
  establishmentId: number
  establishment: Establishment = new Establishment()
  showSpinner: boolean = false;

  constructor(private modalController: ModalController,
    private nav: NavController,
    private productService: ProductService,
    private activeRouter: ActivatedRoute,
    private establishmentService: EstablishmentService) {}

  ngOnInit() {
    this.activeRouter.params.subscribe(params => {
      this.establishmentId = params['establishmentId'];
    });
    this.getAllProducts();
    this.establishmentService.getById(this.establishmentId).subscribe(data => {
      this.establishment = data;
    })
  }


  back() {
    this.nav.back() 
  }
  
  getAllProducts() {
    this.showSpinner = true;
    this.productService.getAllByEstablishment(this.establishmentId).subscribe(data => {
      this.products = data
      this.showSpinner = false
    }, () => this.showSpinner = false)
  }

  async openModalDetail(product){
    const modal = await this.modalController.create({
      component: OrderlComponent,
      componentProps: {
        product: product,
        establishment: this.establishment
      }
    });
    return await modal.present();
  }

}
