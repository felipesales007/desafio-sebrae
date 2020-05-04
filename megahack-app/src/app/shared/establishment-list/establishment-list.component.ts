import { Router } from '@angular/router';
import { Establishment } from './../../models/establishment.model';
import { EstablishmentService } from 'src/app/provider/services/establishment.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'establishment-list',
  templateUrl: './establishment-list.component.html',
  styleUrls: ['./establishment-list.component.scss'],
})
export class EstablishmentListComponent implements OnInit {
  showSpinner: boolean = false
  establishments: Array<Establishment> = new Array<Establishment>();
  router: Router;

  constructor(establishmentService: EstablishmentService, router: Router) { 
    this.showSpinner = true;
    establishmentService.getByNeighborhood('Pernambues').subscribe(data => {
      this.establishments = data;
      this.showSpinner = false
    }, () => this.showSpinner = false);
      this.router = router;
  }

  ngOnInit() {}

  viewEstablishmentProduct(uuid) {
    console.log(uuid);
    this.router.navigate(['/tabs/chat', uuid, false]);
  }

}
