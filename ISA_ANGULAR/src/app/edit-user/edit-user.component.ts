import { Component, OnInit, OnChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditUserService } from './edit-user.service';
import { ConfirmationService } from 'primeng/primeng';
import { Message } from 'primeng/primeng';
import { SharedService } from '../shared/shared.service';
import { SelectItem } from 'primeng/primeng';
import { Observable } from 'rxjs/Rx';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  //variables for new people user can add
  private allUsers = [];
  private allUsersCols = [];
  private stackedFindNewPeople = false;

  //variables for user's notifications
  private allFriendRequests = [];
  private allFriendRequestsCols = [];

  //variables for user's friends
  private allUsersFriendships = [];
  private allUsersFriendshipsCols = [];
  private statusFilters : SelectItem[];
  private stackedMyFriends = false;

  //variables for editing user profile information
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
    this._editUserService.getUserById(this._sharedService.userId)
                         .subscribe(
                           res => this.user = res
                         );
    
    this.findNewPeopleData();

    this.myFriendsData();

    //svakih 15 sekundi povlacenje notifikacija
    Observable.timer(0, 15000).subscribe(
               res => this.notificationsData()
    );
    
    this.setColumnsForDataLists();
    
  }

  setColumnsForDataLists()
  {
      this.allUsersCols = [
            {field: 'firstName', header: 'First name'},
            {field: 'lastName', header: 'Last name'},
            {field: 'username', header: 'Username'},
            {field: 'email', header: 'Email'}
        ];
    
    this.allFriendRequestsCols = [
            {field: 'originator.firstName', header: 'First name'},
            {field: 'originator.lastName', header: 'Last name'},
            {field: 'originator.username', header: 'Username'}
        ];

    this.allUsersFriendshipsCols = [
            {field: 'recipient.firstName', header: 'First name'},
            {field: 'recipient.lastName', header: 'Last name'},
            {field: 'recipient.username', header: 'Username'},
            {field: 'recipient.email', header: 'Email'}
        ];
    
    // this.statusFilters = [
    //     {label: 'Any Status', value: null},
    //      {label: 'Friends', value: "ACCEPTED"},
    //       {label: 'Pending', value: "PENDING"}
    // ];
  }

  myFriendsData()
  {
       this._editUserService.getFriendships(this._sharedService.userId)
                          .subscribe(
                            res => this.allUsersFriendships = res
                          );
  }

  findNewPeopleData()
  {
      //all users for Find new people tab
      this._editUserService.getUsers(this._sharedService.userId)
                              .subscribe(
                                res => this.allUsers = res
                              );
  }

  notificationsData()
  {
      this._editUserService.getFriendRequests(this._sharedService.userId)
                              .subscribe(
                                res => this.allFriendRequests = res
                              );
  }

  fillForm()
  {
      this.displayEditProfile = true;
      this._editUserService.getUserById(this._sharedService.userId)
                         .subscribe(
                           res => this.userUpdate = res
                         );
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
                                                                    this.displayEditProfile = false;
                                                                    this.user = this.userUpdate;
                                                                  }
                                                                );  
                                }
                            )           
  }

  showEditProfileDialog() 
  {
        this.fillForm();
        this.displayEditProfile = true;
  }

  sendFriendRequest(user)
  {
      this._confirmationService.confirm({
              header: 'Add a friend',
              message: 'Send a friend request to '+user.firstName + ' ' + user.lastName + ' ?',
              accept: () => {
                  
                  this._editUserService.sendFriendRequest(this.user,user)
                                      .subscribe(
                                          res => {
                                             this.findNewPeopleData();
                                             
                                             this.msgs = [];
                                             this.msgs.push({severity:'success', summary:'Friend request sent.', detail:'Please wait for '+user.firstName + ' ' + user.lastName + ' to respond.'});  
                                          }
                                      );
              }
          });
  }

  respondFriendRequest(friendship, status)
  {
      let statusMessage = status==1 ? "accept" : "decline";
      
      friendship.status = status==1 ? "ACCEPTED" : "DECLINED";

      this._confirmationService.confirm({
              header: 'Respond to friend request',
              message: 'Are you sure you want to '+ statusMessage + " friendship from " + friendship.originator.firstName + ' ' + friendship.originator.lastName + ' ?',
              accept: () => {
                  
                  this._editUserService.respondFriendRequest(friendship)
                                      .subscribe(
                                          res => {
                                             this.notificationsData();
                                             this.myFriendsData();

                                             this.msgs = [];
                                             if(statusMessage = "accept")
                                                this.msgs.push({severity:'success', summary:'Friend request accepted!'});
                                             else
                                                this.msgs.push({severity:'success', summary:'Friend request declined!'});  
                                          }
                                      );
              }
          });
  }

  removeFromFriends(friendship)
  {
      this._confirmationService.confirm({
              header: 'Remove from friends',
              message: 'Are you sure about this?',
              accept: () => {

                    this._editUserService.removeFromFriends(friendship.id)
                                        .subscribe(
                                            res => {
                                                this.myFriendsData();

                                                this.msgs = [];
                                                if(res)
                                                    this.msgs.push({severity:'success', summary:'Successfully unfriended!'});
                                                else
                                                    this.msgs.push({severity:'error', summary:'Can\'t unfriend now! Sorry.'});
                                            }
                                        );
              }
      });
  }

  showChangePasswordDialog()
  {
     //TO-DO
     //Prvo cemo proveriti da li je Username-Password-Authentication
     //zatim dozvoliti prikaz dijaloga ili obavestiti korisnika da nije moguce
     if(!this._sharedService.isSocialAccount)

        this._confirmationService.confirm({
              header: 'Change password',
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
