import projectData from '../../project/ProjectData';
import { Component } from '@angular/core';
import { materialImportList } from '../../material/material-import-list';

@Component({
  selector: 'app-delete-modal',
  imports: [ ...materialImportList ],
  templateUrl: './delete-modal.html',
  styleUrl: './delete-modal.css'
})
export class DeleteModal {

  deleteUrl = projectData.urlPrefixServer1 + "/accounts/delete_account/current_user" ;

}
