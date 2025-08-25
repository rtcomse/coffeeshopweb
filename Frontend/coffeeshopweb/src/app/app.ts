import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { pagesImportList } from './pages/pages-import-list';
import { navbarImportList } from './navbar/navbar-import-list';

@Component({
  selector: 'app-root',
  imports: [ RouterOutlet, ...pagesImportList, ...navbarImportList ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'coffeeshopweb';
}
