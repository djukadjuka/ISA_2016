import { Component, OnInit } from '@angular/core';
import { Message } from 'primeng/primeng';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditUserService } from '../edit-user/edit-user.service';
import { SharedService } from '../shared/shared.service';
import {ViewRestaurantsService} from '../restaurants/view-restaurants/view-restaurants.service';

@Component({
  selector: 'app-edit-chef',
  templateUrl: './edit-chef.component.html',
  styleUrls: ['./edit-chef.component.css']
})
export class EditChefComponent implements OnInit {

private displayScheduleeButton: boolean = false;
  private displayScheduleButton: boolean =false;
  private displayEditButton :boolean = false ;
   private user = {};
    private userUpdate = {username: "", id: ""};
    private msgs: Message[] = [];
    private formEditUser: FormGroup;
      reservation = { Date : new Date};
  formReservation : FormGroup;
  constructor(

     private _editUserService: EditUserService,
     private _restaurantService : ViewRestaurantsService,
    
    private _fb: FormBuilder,
    private _sharedService : SharedService,
  ) { }

  ngOnInit() {

       this.formEditUser = this._fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    



    this._editUserService.getUserById(this._sharedService.userId)
      .subscribe(
      res => this.userUpdate = res
      );

    console.log(this.userUpdate);
/*
     this._restaurantService.getWorkersNOMANAGERSForRestaurant(1).subscribe(
          res=>this.userUpdate = res
      );

    console.log(this.userUpdate);

    */

    this.formReservation = this._fb.group({
        Date: ['', Validators.required]
        });
  }

  showEditDialog(){

    this.displayEditButton = true ; 
  }




   all_schedules_for_employee;
   all_users;



  displaySchedule(){
    this.displayScheduleButton = true;

  }

  displaySchedulee(){
        console.log(this.reservation.Date.getTime());
    this._restaurantService.getCookScheduleByDate(this.reservation.Date.getTime()).subscribe(
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
                                                                    this.displayEditButton = false;
                                                                    this.user = this.userUpdate;
                                                                  }
                                                                );  
                                }
                            )           
  }

}
