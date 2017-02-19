import { Component, OnInit, OnChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditUserHelpService } from './edit-userhelp.service';
import { TablesClass } from '../tables/tables-class';
import { TablesService } from '../tables/tables.service';
import { Message } from 'primeng/primeng';
import { SharedService } from '../shared/shared.service';

@Component({
  selector: 'app-edit-barman',
  templateUrl: './edit-barman.component.html',
  styleUrls: ['./edit-barman.component.css']
})
export class EditBarmanComponent implements OnInit, OnChanges {

  showEdit = false;

  noImageFound: string = "/assets/pictures/no_image_found.jpg";
  private user = {};
  private userUpdate = {username: "", id: ""};
  private msgs: Message[] = [];

  private displaySchedule: boolean = false;
   private formEditUser: FormGroup;



  //all tables
  tables: TablesClass[];

  constructor(
    private _editUserService: EditUserHelpService,
    private tablesService: TablesService,
    private _fb: FormBuilder,
    private _sharedService : SharedService,

  ) { }

  showSchedule(event, restaurant) {
    event.preventDefault();



    this.showEdit = true;
  }
  ngOnChanges() {
    alert("changes");
    /* if(this.isEdit)
        this.title = "Edit Profile";
     else
        this.title = "";*/
  }


  saveChanges(event, firstname, lastname, username, email) {
    {
      event.preventDefault();
      var data = { id: 1, firstname: firstname, lastname: lastname, username: username, email: email };
      this._editUserService.updateUser(data)
        .subscribe(
        res => {
          alert("update");
          this.user = data;
        }
        );
    }

  }

  ngOnInit() {

    this.formEditUser = this._fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required]
    });
    



    this._editUserService.getUserById(this._sharedService.userId)
      .subscribe(
      res => this.userUpdate = res
      );

    console.log(this.userUpdate);
  }

  showScheduleDialog() {

    this.tablesService.getTables().subscribe(
      res => {
        this.tables = res;
      }
    );

    this.displaySchedule = true;
  }

}
