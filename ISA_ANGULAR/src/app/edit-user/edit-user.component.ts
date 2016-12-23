import { Component, OnInit, OnChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditUserService } from './edit-user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit, OnChanges {

  private form: FormGroup;
  private title = "";
  private isEdit = false;
  private user = {};
  private userUpdate = {};

  constructor(
    private _editUserService : EditUserService,
    private _fb: FormBuilder
    ) { }

  ngOnInit() 
  {
    this.form = this._fb.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      username: [''],
      password: [''],
      confirmPassword: ['']
    });
    
     this._editUserService.getUserById(1)
                         .subscribe(
                           res => this.user = res
                         );

    console.log(this.form);
  }

  ngOnChanges()
  {
    alert("changes");
       if(this.isEdit)
          this.title = "Edit Profile";
       else
          this.title = "";
  }

  fillForm(event)
  {
      this.isEdit = true;
      //this.userUpdate = this.user;
      //kako kopirati objekte u js a ne dodeliti referencu ?????
      //event.class = "active";
      //event.srcElement.setAttribute("class", "active");

      this._editUserService.getUserById(1)
                         .subscribe(
                           res => this.userUpdate = res
                         );
  }

  updateUser()
  {
      this._editUserService.updateUser(this.userUpdate)
                            .subscribe(
                                res => 
                                  {
                                    alert("update");
                                    this.isEdit = false;
                                    this.user = this.userUpdate;
                                  }
                            );             
  }

  editProfile()
  {
      this.isEdit = true;
  }

}
