import { Component, OnInit, OnChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditUserService } from './edit-user.service';
import { ConfirmationService } from 'primeng/primeng';
import { Message } from 'primeng/primeng';
import { SharedService } from '../shared/shared.service';
import { SelectItem } from 'primeng/primeng';
import { Observable } from 'rxjs/Rx';
import { RestaurantRegistry} from '../restaurant_registry/restaurant-registry-class';
import { RestaurantRegistryService} from '../restaurant_registry/restaurant-registry-service';

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
    private _fb: FormBuilder,
    private _restaurantRegistryService : RestaurantRegistryService
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
                           res => {
                               this.user = res;
                               //vidi sta je user;;;;;;;
                                if(this._sharedService.isAdmin){
                                       this._restaurantRegistryService.getUnseenRegistersForAdmin().subscribe(
                                           res=>{
                                                    this.visible_registries_admin = res;
                                                    //console.log(this.visible_registries_admin);
                                                    this._restaurantRegistryService.getUnseenRegisterForManager(this._sharedService.userId).subscribe(
                                                        res=>{
                                                            this.visible_registries_manager = res;
                                                            //console.log(this.visible_registries_manager);
                                                        }
                                                    );
                                                }
                                        );
                                }else if(this._sharedService.isManager){
                                    this._restaurantRegistryService.getUnseenRegisterForManager(this._sharedService.userId).subscribe(
                                        res=>{
                                            this.visible_registries_manager = res;
                                            //console.log(this.visible_registries_manager);
                                        }
                                    );
                                }else if(this._sharedService.isDeliverer){
                                    let current_date = new Date().getTime();
                                    this._editUserService.getDelivererStartingData(current_date,this._sharedService.userId).subscribe(
                                        res=>{
                                            this.all_deliverer_bids = res.all_bids;
                                            this.free_orders = res.free_orders;
                                            this.not_seen_bid_statuses = res.status_bids;

                                            for(let idx in this.free_orders){
                                                let order = this.free_orders[idx];
                                                let order_from = new Date(order.date_from);
                                                let order_to = new Date(order.date_to);
                                                for(let edx in order.contains_items){
                                                    let item = order.contains_items[edx];
                                                    this.free_orders_presentation.push({
                                                        belongs_to_order:order.id,
                                                        from: order_from.getDate() +"/"+ (order_from.getMonth()+1) + "/" + order_from.getFullYear(),
                                                        to: order_to.getDate() +"/"+ (order_to.getMonth()+1) + "/" + order_to.getFullYear(),
                                                        item_id:item.id,
                                                        item_name:item.item_name,
                                                        item_amount:item.item_amount
                                                    });
                                                }
                                            }
                                        }
                                    );
                                }
                           }
                         );
    
    this.findNewPeopleData();

    this.myFriendsData();

    //svakih 15 sekundi povlacenje notifikacija
    Observable.timer(0, 15000).subscribe(
               res => this.notificationsData()
    );
    
    if(this._sharedService.isAdmin){
        Observable.timer(0,5000).subscribe(
            res=>{
              this.refresh_adminData();
              this.refresh_managerData(this._sharedService.userId);  
            } 
        );
    }

    if(this._sharedService.isManager){
        Observable.timer(0,5000).subscribe(
            res=> this.refresh_managerData(this._sharedService.userId)
        );
    }

    if(this._sharedService.isDeliverer){
        Observable.timer(0,5000).subscribe(
            res=>this.refresh_deliverer_data()
        );
    }

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

    this.restaurant_types = [{label:'Fine Dining', value: 'Fine Dining'},
                              {label:'Fast Food', value: 'Fast Food'},
                              {label:'Bistro', value: 'Bistro'},
                              {label:'Sports Bar', value: 'Sports Bar'},
                              {label:'Diner', value: 'Diner'}];

    this.selected_restaurant_type = this.restaurant_types[0].value;

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

  refresh_adminData(){
      this._restaurantRegistryService.getUnseenRegistersForAdmin().subscribe(res=>this.visible_registries_admin = res);
  }

  refresh_managerData(id){
      this._restaurantRegistryService.getUnseenRegisterForManager(id).subscribe(res=>this.visible_registries_manager = res);
  }

  refresh_deliverer_data(){
        let current_date = new Date().getTime();
        this._editUserService.getDelivererStartingData(current_date,this._sharedService.userId).subscribe(
            res=>{
                this.all_deliverer_bids = res.all_bids;
                this.free_orders = res.free_orders;
                this.not_seen_bid_statuses = res.status_bids;
                this.free_orders_presentation = [];
                for(let idx in this.free_orders){
                    let order = this.free_orders[idx];
                    let order_from = new Date(order.date_from);
                    let order_to = new Date(order.date_to);
                    for(let edx in order.contains_items){
                        let item = order.contains_items[edx];
                        this.free_orders_presentation.push({
                            belongs_to_order:order.id,
                            from: order_from.getDate() +"/"+ (order_from.getMonth()+1) + "/" + order_from.getFullYear(),
                            to: order_to.getDate() +"/"+ (order_to.getMonth()+1) + "/" + order_to.getFullYear(),
                            item_id:item.id,
                            item_name:item.item_name,
                            item_amount:item.item_amount
                        });
                    }
                }
            }
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

  /*\
  |#|   RESTAURANT REGISTRY CONFIG
  \*/

    //all registries that are unseen
    visible_registries_admin : RestaurantRegistry[];
    visible_registries_manager : RestaurantRegistry[];
    new_restaurant_registry : RestaurantRegistry;
    new_restaurant_dialog_showing = false;
    restaurant_name_input : string;
    selected_restaurant_type : string;
    restaurant_types : SelectItem[];
    restaurant_name_valid = false;

    //called when admin accepts a restaurant for registry
    //should update backend
    accept_restaurant(id){
        for(let i =0;   i<this.visible_registries_admin.length; i++){
            if(this.visible_registries_admin[i].id == id){
                this.visible_registries_admin.splice(i,1);
            }
        }
        this._restaurantRegistryService.updateRegistration_ACCEPTED(id).subscribe(res=>{console.log(res);});
        //console.log("Accepted restaurant : " + id);
    }

    //called when admin denies a restaurant for registry
    //should update backend
    decline_restaurant(id){
        for(let i =0;   i<this.visible_registries_admin.length; i++){
            if(this.visible_registries_admin[i].id == id){
                this.visible_registries_admin.splice(i,1);
            }
        }
        this._restaurantRegistryService.updateRegistration_DECLINED(id).subscribe(res=>console.log(res));
        //console.log("Declined restaurant : " + id);
    }

    //caled when manager sees an updated restaurant status
    //shoudl update backend
    disband_row(id){
        for(let i =0;   i<this.visible_registries_admin.length; i++){
            if(this.visible_registries_admin[i].id == id){
                this.visible_registries_admin.splice(i,1);
            }
        }
        this._restaurantRegistryService.updateRegistration_SEEN(id).subscribe(res=>console.log(res));
        //console.log("Disbanded row : " + id);
    }

    //when a manager wants to register a new restaurant
    //open dialog and show options
    new_restaurant_dialog(){
        this.new_restaurant_registry = new RestaurantRegistry();
        this.selected_restaurant_type = this.restaurant_types[0].value;
        this.restaurant_name_input = "";
        this.restaurant_name_valid = false;

        this.new_restaurant_dialog_showing = true;
    }

    //when the manager accepts the new restaurant
    register_new_restaurant(){
        this.new_restaurant_registry.deleted = 0;
        this.new_restaurant_registry.restaurant_name = this.restaurant_name_input;
        this.new_restaurant_registry.seen = 0;
        this.new_restaurant_registry.status = 'PENDING';
        this.new_restaurant_registry.type = this.selected_restaurant_type;
        this.new_restaurant_registry.registered_by=null;

        //console.log(this.new_restaurant_registry);

        this._restaurantRegistryService.registerNewRestaurant(this.new_restaurant_registry,+this._sharedService.userId).subscribe(   
            res=>{
                //console.log(res);
                this.visible_registries_manager.push(res);
                this.new_restaurant_dialog_showing = false;
            }
        );
        
    }

    restaurant_name_changed(){
        let str = this.restaurant_name_input.trim();

        if(str === ""){
            //console.log("String is empty!");
            this.restaurant_name_valid = false;
        }else{
            this.restaurant_name_valid = true;
        }
    }

    //======================================
    //============DELIVERY PART ============
    //======================================
    all_deliverer_bids;
    not_seen_bid_statuses;
    free_orders;

    free_orders_presentation = [];

    //for what order is the bid being entered
    entering_for_order;

    //entering bid dialog visibility
    entering_bid = false;

    //is bid over 0
    bid_valid=false;

    //the entered bid in dollars from the dialog;
    bid_in_dollars;

    enter_bid_dollars(belongs_to_order){
        this.entering_for_order = belongs_to_order;

        this.entering_bid=true;
    }

    bid_entered_keyup(){
        let regx = new RegExp("^[1-9][0-9]*$");
        this.bid_valid = regx.test(this.bid_in_dollars);
    }

    bid_submitted(){
        console.log("Bid in dollars : " + this.bid_in_dollars);
        console.log("Made for order with id : " + this.entering_for_order);
        console.log("Made by user : " + this._sharedService.userId);
        let bid = {
            bid_in_dollars: this.bid_in_dollars,
            made_for_order:+this.entering_for_order,
            made_by_user: +this._sharedService.userId
        };
        this._editUserService.send_new_bid(bid).subscribe(res=>{
            console.log(res);
        })
        this.entering_bid = false;
    }

    dismiss_order_notification(item){
        console.log(item);
        let payload = {bid_seen_id:item.id};
        this._editUserService.update_seen_status(payload).subscribe(
            res=>{}
        );
    }

    /**registering as deliverer */
    register_as_deliverer(){
        let user = {id:+this._sharedService.userId};
        this._editUserService.register_as_deliverer(user).subscribe(
            res=>{
                console.log(res);
            }
        )
    }
}   
