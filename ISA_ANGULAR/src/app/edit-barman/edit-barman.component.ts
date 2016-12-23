import { Component, OnInit,OnChanges } from '@angular/core';
import { EditUserHelpService } from './edit-userhelp.service';


@Component({
  selector: 'app-edit-barman',
  templateUrl: './edit-barman.component.html',
  styleUrls: ['./edit-barman.component.css']
})
export class EditBarmanComponent implements OnInit,OnChanges {

  showEdit=false;

  private user = {};
  private userUpdate = {};

  constructor(
     private _editUserService : EditUserHelpService,
  ) { }

   showSchedule(event,restaurant){
     event.preventDefault();
     
    

     this.showEdit = true;
   }
    ngOnChanges()
  {
    alert("changes");
      /* if(this.isEdit)
          this.title = "Edit Profile";
       else
          this.title = "";*/
  }


   saveChanges(event,firstname,lastname,username,email){
{
  event.preventDefault();
  var data ={id:1,firstname:firstname,lastname:lastname,username:username,email:email};
      this._editUserService.updateUser(data)
                            .subscribe(
                                res => 
                                  {
                                    alert("update");
                                    this.user = data;
                                  }
                            );             
  }

   }

  ngOnInit(

  ) {


 this._editUserService.getUserById(1)
                         .subscribe(
                           res => this.user = res
                         );

    console.log(this.user);
  }

   

}
