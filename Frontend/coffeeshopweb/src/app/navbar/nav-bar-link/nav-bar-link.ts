import { Component, input } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-nav-bar-link',
  imports: [ RouterLink, RouterLinkActive ],
  templateUrl: './nav-bar-link.html',
  styleUrl: './nav-bar-link.css'
})
export class NavBarLink {

  linkTitle = input( "Loading..." ) ;
  linkPath = input( "Loading..." ) ;

}
