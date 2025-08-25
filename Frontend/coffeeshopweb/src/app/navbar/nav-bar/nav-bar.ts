import { Component } from '@angular/core';

import { navbarImportList } from '../navbar-import-list';
import { LoginBox } from '../../login_box/login-box/login-box';

@Component({
  selector: 'app-nav-bar',
  imports: [ ...navbarImportList, LoginBox ],
  templateUrl: './nav-bar.html',
  styleUrl: './nav-bar.css'
})
export class NavBar {

}
