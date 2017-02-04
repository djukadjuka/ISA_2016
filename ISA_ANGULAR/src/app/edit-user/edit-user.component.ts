import { Component, OnInit, OnChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditUserService } from './edit-user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  private form: FormGroup;
  private title = "";
  private user = {};
  private userUpdate = {};

  //modal dialog for editing profile
  private displayEditProfile: boolean = false;


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

  fillForm()
  {
      this.displayEditProfile = true;
      //this.userUpdate = this.user;
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
                                    this.displayEditProfile = false;
                                    this.user = this.userUpdate;
                                  }
                            );             
  }

  showEditProfileDialog() {
        this.fillForm();
        this.displayEditProfile = true;
    }
}
