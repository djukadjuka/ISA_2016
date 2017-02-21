import { Component, OnInit } from '@angular/core';
import { TablesClass } from '../tables/tables-class';
import { TablesService } from '../tables/tables.service';
import { Message } from 'primeng/primeng';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditUserService } from '../edit-user/edit-user.service';
import { SharedService } from '../shared/shared.service';

@Component({
  selector: 'app-edit-waiter',
  templateUrl: './edit-waiter.component.html',
  styleUrls: ['./edit-waiter.component.css']
})
export class EditWaiterComponent implements OnInit {

  private displayScheduleeButton: boolean = false;
   private displayScheduleButton: boolean = false;
   private showTablesButton: boolean = false ;
   private user = {};
    private userUpdate = {username: "", id: ""};
    private msgs: Message[] = [];
    private formEditUser: FormGroup;

   tables: TablesClass[];

  constructor(
 private _editUserService: EditUserService,
    private tablesService: TablesService,
    private _fb: FormBuilder,
    private _sharedService : SharedService,

  ) { }

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

  displaySchedule(){

    this.displayScheduleButton = true;


  }
  displaySchedulee(){

    this.displayScheduleeButton = true;

  }
  showTables(){
    this.tablesService.getTables(1).subscribe(
      res => {
        this.tables = res;
      }
    );

    this.showTablesButton = true;

  }

    updateUser()
  {
      this._editUserService.checkUsername(this.userUpdate.username, this.userUpdate.id)
                            .subscribe(
                              res => 
                              {
                                if(res) {
                                  this.msgs = [];
                                  this.msgs.push({severity:'error', summary:'That username is taken!', detail:'Choose another username.'});
                                  }
                                else
                                  this._editUserService.updateUser(this.userUpdate)
                                                        .subscribe(
                                                                 res => 
                                                                  {
                                                                  
                                                                    this.user = this.userUpdate;
                                                                  }
                                                                );  
                                }
                            )           
  }

}
