import { Component, OnInit, OnChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditUserService } from '../edit-user/edit-user.service';
import { TablesClass } from '../tables/tables-class';
import { TablesService } from '../tables/tables.service';
import { Message } from 'primeng/primeng';
import { SharedService } from '../shared/shared.service';
import {ViewRestaurantsService} from '../restaurants/view-restaurants/view-restaurants.service';

@Component({
  selector: 'app-edit-barman',
  templateUrl: './edit-barman.component.html',
  styleUrls: ['./edit-barman.component.css']
})
export class EditBarmanComponent implements OnInit, OnChanges {

  showEdit = false;

  noImageFound: string = "/assets/pictures/no_image_found.jpg";
   user ;
  private userUpdate = {username: "", id: ""};
  private msgs: Message[] = [];

  private displayScheduleeButton : boolean = false;
  private displaySchedule: boolean = false;
  private displayEditButton : boolean = false ; 
   private formEditUser: FormGroup;
     reservation = { Date : new Date};
  formReservation : FormGroup;



  //all tables
  tables: TablesClass[];

  constructor(
    private _editUserService: EditUserService,
    private tablesService: TablesService,
    private _fb: FormBuilder,
    private _sharedService : SharedService,
     private _restaurantService : ViewRestaurantsService,

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


  ngOnInit() {

    this.formEditUser = this._fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required]
    });
    



    this._restaurantService.getEmployeeById(1)
      .subscribe(
      res => { this.userUpdate=res;

         this.user = [];
          for(let item in res){
           let Eusername =res[item].user_id.username;
           let Efirstname = res[item].user_id.first_name;
           let Elastname = res[item].user_id.last_name;
           let Eshoesize = res[item].shoesize;
           let Epassword = res[item].user_id.password;
          

           console.log(res);

            this.user.push(
             { first_name:""+Efirstname, last_name:""+Elastname,  user_name:""+ Eusername,shoesize:""+Eshoesize,password:""+Epassword}
             );
       // this.schedule =res;
       
      }
      
      }   
    );

    


    this.formReservation = this._fb.group({
        Date: ['', Validators.required]
        });
  }

  showScheduleDialog() {

   
    

    this.displaySchedule = true;
  }

  showEditDialog(){

    this.displayEditButton = true ; 
  }

 all_schedules_for_employee;
   all_users;



  displaySchedulee(){

 console.log(this.reservation.Date.getTime());
    this._restaurantService.getBarmanScheduleByDate(this.reservation.Date.getTime()).subscribe(
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



    this.displayScheduleeButton = true;

  }

}
