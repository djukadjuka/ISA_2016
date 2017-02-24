import { Component, OnInit } from '@angular/core';
import { TablesClass } from '../tables/tables-class';
import { TablesService } from '../tables/tables.service';
import { Message } from 'primeng/primeng';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditUserService } from '../edit-user/edit-user.service';
import { SharedService } from '../shared/shared.service';
import {ViewRestaurantsService} from '../restaurants/view-restaurants/view-restaurants.service'

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
   schedule = [];
    private userUpdate = {username: "", id: ""};
    private msgs: Message[] = [];
    private formEditUser: FormGroup;
    private formEditSchedule: FormGroup;

   tables: TablesClass[];

  constructor(
    private viewRestaurantsService : ViewRestaurantsService,
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

    console.log(JSON.stringify(this.userUpdate));
    
  }

   all_schedules_for_employee;
   all_users;

  displaySchedule(){

    this.displayScheduleButton = true;


  }
  displaySchedulee(){


      

    this.viewRestaurantsService.getScheduleByDate().subscribe(
      res => {

         this.all_schedules_for_employee = [];
          for(let item in res){
           let start_time = new Date(res[item].from);
           let end_time = new Date(res[item].to);
           let date = new Date(res[item].date);
           let fName = res[item].for_employee.user.firstName;
           let lName = res[item].for_employee.user.lastName
          

           console.log(res);

            this.all_schedules_for_employee.push(
             { first_name:""+fName, last_name:""+lName,  from:""+start_time.getHours() + " : " + start_time.getMinutes(),
               to:""+end_time.getHours() + " : " + end_time.getMinutes(),
               date:""+date.getDate()+" : "+ (date.getMonth()+1) + " : " + date.getFullYear()}
             );
       // this.schedule =res;
       
      }
      
      }   
    );
       // console.log(this.schedule);
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
