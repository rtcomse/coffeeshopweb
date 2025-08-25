import { Routes } from '@angular/router';

import { HomePage } from './pages/home-page/home-page';
import { OrderPage } from './pages/order-page/order-page';
import { HistoryPage } from './pages/history-page/history-page';
import { AboutPage } from './pages/about-page/about-page';
import { OrderSuccessPage } from './pages/order-success-page/order-success-page';
import { OrderFailurePage } from './pages/order-failure-page/order-failure-page';



export const routes: Routes = [ 

    { path: "", redirectTo: "home_page", pathMatch: "full" },
    { path: "home_page", component: HomePage, title: "Home Page" },
    { path: "order_page", component: OrderPage, title: "Order Page" },
    { path: "history_page", component: HistoryPage, title: "History Page" },
    { path: "about_page", component: AboutPage, title: "About Page" },
    { path: "order_success_page/:order_number", component: OrderSuccessPage, title: "Order Success Page" },
    { path: "order_failure_page", component: OrderFailurePage, title: "Order Failure Page" }

] ;
