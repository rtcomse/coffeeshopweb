import projectData from '../../project/ProjectData';
import { Component, inject, signal } from '@angular/core';
import { DataFetch } from '../../services/data-fetch/data-fetch';
import { materialImportList } from '../../material/material-import-list';

@Component({
  selector: 'app-order-page',
  imports: [ ...materialImportList ],
  templateUrl: './order-page.html',
  styleUrl: './order-page.css'
})
export class OrderPage {

  ingredientList: any = signal( [] ) ;

  dataFetchService = inject( DataFetch ) ;

  formSubmitUrl = projectData.urlPrefixServer1 + "/coffee_orders/order_placement" ;


  ngOnInit () {

    this.dataFetchService.getIngredients().subscribe({
      next: data => { this.ingredientList.set( data ) ; },
      error: error => { console.error( error ) ; }
    }) ;

  }
  
  



}
