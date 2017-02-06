import { Component, OnInit, OnChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditUserService } from './edit-user.service';
import { ConfirmationService } from 'primeng/primeng';
import { Message } from 'primeng/primeng';
import { SharedService } from '../shared/shared.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  private formEditUser: FormGroup;
  private user = {};
  private userUpdate = {username: "", id: ""};
  private msgs: Message[] = [];

  //modal dialog for editing profile
  private displayEditProfile: boolean = false;

  constructor(
    private _editUserService : EditUserService,
    private _confirmationService: ConfirmationService,
    private _sharedService : SharedService,
    private _fb: FormBuilder
    ) { }

  ngOnInit() 
  {
    this.formEditUser = this._fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required]
    });
    
    //TO-DO
    //Uvezati Auth0 korisnike sa DB korisnicima i vuci korisnika po tom ID-u
    this._editUserService.getUserById(1)
                         .subscribe(
                           res => this.user = res
                         );
  }

  fillForm()
  {
      this.displayEditProfile = true;
      this._editUserService.getUserById(1)
                         .subscribe(
                           res => this.userUpdate = res
                         );
  }

  updateUser()
  {
    //TO-DO
    //Prvo proveriti da li postoji taj username - ako postoji ne sme proci promena
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
                                                                    this.displayEditProfile = false;
                                                                    this.user = this.userUpdate;
                                                                  }
                                                                );  
                                }
                            )           
  }

  showEditProfileDialog() {
        this.fillForm();
        this.displayEditProfile = true;
    }

  showChangePasswordDialog()
  {
     //TO-DO
     //Prvo cemo proveriti da li je Username-Password-Authentication
     //zatim dozvoliti prikaz dijaloga ili obavestiti korisnika da nije moguce
     if(!this._sharedService.isSocialAccount)

        this._confirmationService.confirm({
              message: 'Email with instructions on how to change your password will be sent to you. Are you sure you want this?',
              accept: () => {
                  this._editUserService.updateUserPassword();
              }
          });
      else
      {
          this.msgs = [];
          this.msgs.push({severity:'warn', summary:'Sorry! Can\'t change password.', detail:'Facebook/Google logged users can\'t change passwords'});
      }
  }
}
