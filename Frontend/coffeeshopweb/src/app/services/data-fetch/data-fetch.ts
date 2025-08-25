import projectData from '../../project/ProjectData';
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class DataFetch {

  http = inject( HttpClient ) ;

  constructor() { }


  getIngredients () {

    return this.http.get( projectData.urlPrefixServer1 + "/ingredients" ) ;

  }

  getOrderHistory () {

    return this.http.get( projectData.urlPrefixServer1 + "/coffee_orders/order_history" ) ;

  }

  getUsername () {

    return this.http.get( projectData.urlPrefixServer1 + "/user_details/current_user/username" ) ;

  }



}
