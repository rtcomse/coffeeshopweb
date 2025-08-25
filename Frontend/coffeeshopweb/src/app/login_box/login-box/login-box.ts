import projectData from '../../project/ProjectData';
import { Component, inject, signal } from '@angular/core';
import { DataFetch } from '../../services/data-fetch/data-fetch';
import { materialImportList } from '../../material/material-import-list';
import { MatDialog } from '@angular/material/dialog';
import { SignupModal } from '../signup-modal/signup-modal';
import { DeleteModal } from '../delete-modal/delete-modal';


@Component({
  selector: 'app-login-box',
  imports: [ ...materialImportList ],
  templateUrl: './login-box.html',
  styleUrl: './login-box.css'
})
export class LoginBox {

  loginUrl = projectData.urlPrefixServer1 + "/login" ;

  logoutUrl = projectData.urlPrefixServer1 + "/custom_logout" ;

  usernameList: any = signal( [] ) ;

  dataFetchService = inject( DataFetch ) ;

  signupModalDialog = inject( MatDialog ) ;

  deleteModalDialog = inject( MatDialog ) ;

  openSignupModalDialog () {

    const dialogRef = this.signupModalDialog.open( SignupModal ) ;
    
  }

  openDeleteModalDialog () {

    const dialogRef = this.deleteModalDialog.open( DeleteModal ) ;
    
  }


  ngOnInit () {

    this.dataFetchService.getUsername().subscribe({
      next: data => { this.usernameList.set( data ) ; },
      error: error => { console.error( error ) ; }
    }) ;

  }
  
  

}
