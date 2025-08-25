import { Component, inject, signal } from '@angular/core';
import { DataFetch } from '../../services/data-fetch/data-fetch';
import { materialImportList } from '../../material/material-import-list';

@Component({
  selector: 'app-history-page',
  imports: [ ...materialImportList ],
  templateUrl: './history-page.html',
  styleUrl: './history-page.css'
})
export class HistoryPage {

  coffeeOrderList: any = signal( [] ) ;

  dataFetchService = inject( DataFetch ) ;


  ngOnInit () {

    this.dataFetchService.getOrderHistory().subscribe({
      next: data => { this.coffeeOrderList.set( data ) ; },
      error: error => { console.error( error ) ; }
    }) ;

  }
  
  

}
