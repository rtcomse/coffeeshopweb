import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-order-success-page',
  imports: [],
  templateUrl: './order-success-page.html',
  styleUrl: './order-success-page.css'
})
export class OrderSuccessPage {

  orderNumber = signal( -1 ) ;

  activatedRoute = inject( ActivatedRoute ) ;


  ngOnInit () {

    this.activatedRoute.params.subscribe({
      next: params => { this.orderNumber.set( parseInt( params[ "order_number" ] ) ) ; },
      error: error => { console.error( error ) ; }
    }) ;

  }

}
