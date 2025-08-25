import projectData from '../../project/ProjectData';
import { Component } from '@angular/core';
import { materialImportList } from '../../material/material-import-list';

@Component({
  selector: 'app-signup-modal',
  imports: [ ...materialImportList ],
  templateUrl: './signup-modal.html',
  styleUrl: './signup-modal.css'
})
export class SignupModal {

  signupUrl = projectData.urlPrefixServer1 + "/accounts/create_account" ;

}
