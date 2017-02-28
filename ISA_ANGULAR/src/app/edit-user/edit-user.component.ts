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

  //restaurant history and rating
  private showRateDialog = false;
  private restaurantVisitHistory = [];
  private restaurantVisitHistoryCols = [];
  private reservationBeingRatedId = "";

  private restaurantRate : number;
  private waiterRate : number;
  private foodRate : number;

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
    this._editUserService.getUserById(this._sharedService.userId).debounceTime(5000)
                         .subscribe(
                           res => {
                               this.user = res;
                               //vidi sta je user;;;;;;;
                                if(this._sharedService.isAdmin){
                                       this._restaurantRegistryService.getUnseenRegistersForAdmin().subscribe(
                                           res=>{
                                                    this.visible_registries_admin = res;
                                                    //this._restaurantRegistryService.getUnseenRegisterForManager(this._sharedService.userId).subscribe(
                                                    //    res=>{
                                                    //        this.visible_registries_manager = res;
                                                    //    }
                                                    //);
                                                }
                                        );
                                }else if(this._sharedService.isManager){
                                    this._restaurantRegistryService.getUnseenRegisterForManager(this._sharedService.userId).subscribe(
                                        res=>{
                                            this.visible_registries_manager = res;
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

    this.getRestaurantVisitHistoryData();

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
    
     this.restaurantVisitHistoryCols = [
            {field: 'id', header: 'Reservation ID'},
            {field: 'reservation.table_id.restaurant_zone_id.restaurant.name', header: 'Restaurant'},
            {field: 'reservation.table_id.restaurant_zone_id.name', header: 'Zone'},
            {field: 'reservation.table_id.id', header: 'Table ID'},
            {field: 'reservation.table_id.maxPeople', header: 'Table seats'},
            {field: 'food.name', header: 'Ordered food'},
            {field: 'drink.name', header: 'Ordered drink'}
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

  //Restaurant history and rates
  getRestaurantVisitHistoryData()
  {
      this._editUserService.getRestaurantVisitHistory(this._sharedService.userId)
                            .subscribe(
                                res => this.restaurantVisitHistory = res
                            );
  }

  rateClick(reservationCall)
  {
      this.showRateDialog = true;
      this.reservationBeingRatedId = reservationCall.id;
  }

  confirmRates()
  {
      this._editUserService.rateRestaurant(this.reservationBeingRatedId, this.restaurantRate, this.waiterRate, this.foodRate)
                            .subscribe(
                                res => 
                                {
                                    this.msgs = [];
                                    this.msgs.push({severity:'success', summary:'Thanks for rating!'});

                                    this.waiterRate = null;
                                    this.foodRate = null;
                                    this.restaurantRate = null;
                                }
                            )

      this.showRateDialog = false;
  }

  getDate(reservationCall)
  {
     let d = new Date(reservationCall.reservation.startDate);

     return d.toLocaleString();
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
                for(let idx in this.not_seen_bid_statuses){
                    let date = new Date(this.not_seen_bid_statuses[idx].date_of_delivery);
                    let date_pres = ""+date.getDate() + "/"+(date.getMonth()+1)+"/"+date.getFullYear();
                    this.not_seen_bid_statuses[idx].date_of_delivery = date_pres;
                }
                for(let idx in this.all_deliverer_bids){
                    let date = new Date(this.all_deliverer_bids[idx].date_of_delivery);
                    let date_pres = ""+date.getDate() + "/"+(date.getMonth()+1)+"/"+date.getFullYear();
                    this.all_deliverer_bids[idx].date_of_delivery = date_pres;
                }
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
                                          },
                                          err => {
                                              this.findNewPeopleData();
                                              this.msgs = [];
                                             this.msgs.push({severity:'error', summary:'Friend request already pending'});
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
                  
                  this.msgs = [];
                  this.msgs.push({severity:'success', summary:'Check your email.'});
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
        this._restaurantRegistryService.updateRegistration_ACCEPTED(id).subscribe(res=>{});
    }

    //called when admin denies a restaurant for registry
    //should update backend
    decline_restaurant(id){
        for(let i =0;   i<this.visible_registries_admin.length; i++){
            if(this.visible_registries_admin[i].id == id){
                this.visible_registries_admin.splice(i,1);
            }
        }
        this._restaurantRegistryService.updateRegistration_DECLINED(id).subscribe(res=>{});
    }

    //caled when manager sees an updated restaurant status
    //shoudl update backend
    disband_row(id){
        for(let i =0;   i<this.visible_registries_admin.length; i++){
            if(this.visible_registries_admin[i].id == id){
                this.visible_registries_admin.splice(i,1);
            }
        }
        this._restaurantRegistryService.updateRegistration_SEEN(id).subscribe(res=>{});
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

        this._restaurantRegistryService.registerNewRestaurant(this.new_restaurant_registry,+this._sharedService.userId).subscribe(   
            res=>{
                this.visible_registries_manager.push(res);
                this.new_restaurant_dialog_showing = false;
            }
        );
        
    }

    restaurant_name_changed(){
        let str = this.restaurant_name_input.trim();

        if(str === ""){
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

    date_of_delivery:Date;
    min_date_of_delivery = new Date();
    max_date_of_delivery;

    enter_bid_dollars(order){
        this.entering_for_order = order.belongs_to_order;

        var parts = order.to.split("/");
        var dt = new Date(parseInt(parts[2], 10),
                  parseInt(parts[1], 10) - 1,
                  parseInt(parts[0], 10));
        this.max_date_of_delivery = dt;

        this.entering_bid=true;
    }

    bid_entered_keyup(){
        let regx = new RegExp("^[1-9][0-9]*$");
        this.bid_valid = regx.test(this.bid_in_dollars);
    }

    bid_submitted(){
        let bid = {
            bid_in_dollars: this.bid_in_dollars,
            made_for_order:+this.entering_for_order,
            made_by_user: +this._sharedService.userId,
            made_for_date: this.date_of_delivery.getTime()
        };
        this._editUserService.send_new_bid(bid).subscribe(res=>{})
        this.entering_bid = false;
    }

    dismiss_order_notification(item){
        let payload = {bid_seen_id:item.id};
        this._editUserService.update_seen_status(payload).subscribe(
            res=>{}
        );
    }

    /**registering as deliverer */
    register_as_deliverer(){
        let user = {id:+this._sharedService.userId};
        this._editUserService.register_as_deliverer(user).subscribe(
            res=>{}
        )
    }


    /*=================================================
        SUBSCRIBE HERE
        UNSUBSCRIBE HERE
    =================================================
    */

    
    
    /*if(this._sharedService.isAdmin){
        Observable.timer(0,5000).subscribe(
            res=>{
              this.refresh_adminData();
              this.refresh_managerData(this._sharedService.userId);  
            } 
        );
    }
*/

    //friends notifications
    notification_subscription = null;
    //deliveries for deliverer
    delivery_subscription = null;
    //registering restaurants
    registry_subscription= null;

    accordion_opened(event){
        switch(event.index){
            //tab za notifikacije
            case 2:{
                //ako postoji subs onda unsubs
                if(this.notification_subscription != null){
                    this.notification_subscription.unsubscribe();
                }
                //napravi novi subs
                this.notification_subscription = Observable.timer(0, 1000).subscribe(
                    res => this.notificationsData()
                );
                break;
            }
            //tab za registar restorana
            case 3:{
                //ako postoji subs onda unsubs
                if(this.registry_subscription != null){
                    this.registry_subscription.unsubscribe();
                }
                //napravi novi subs
                if(this._sharedService.isAdmin){
                    this.registry_subscription =  Observable.timer(0,5000).subscribe(
                        res=>{
                            this.refresh_adminData();  
                        } 
                    );
                }else{
                    this.registry_subscription = Observable.timer(0,1000).subscribe(
                        res=>  this.refresh_managerData(this._sharedService.userId)
                    );
                }
                break;
            }
            //tab za porudzbine i stvari
            case 4:{
                //ako postoji subs onda unsubs
                if(this.delivery_subscription != null){
                    this.delivery_subscription.unsubscribe();
                }
                //napravi novi subs
                this.delivery_subscription = Observable.timer(0,1000).subscribe(
                    res=>this.refresh_deliverer_data()
                );
                break;
            }
        }
    }

    accordion_closed(event){
        switch(event.index){
            //tab za notifikacije
            case 2:{
                if(this.notification_subscription != null){
                    this.notification_subscription.unsubscribe();
                    this.notification_subscription = null;
                }
                break;
            }
            //tab za registar restorana
            case 3:{
                if(this.registry_subscription != null){
                    this.registry_subscription.unsubscribe();
                    this.registry_subscription = null;
                }
                break;
            }
            //tab za porudzbine i stvari
            case 4:{
                if(this.delivery_subscription != null){
                    this.delivery_subscription.unsubscribe();
                    this.delivery_subscription = null;
                }
                break;
            }
        }
    }

    ngOnDestroy() {
        if(this.notification_subscription != null){
                    this.notification_subscription.unsubscribe();
                    this.notification_subscription = null;
                }
        if(this.registry_subscription != null){
                    this.registry_subscription.unsubscribe();
                    this.registry_subscription = null;
                }
        if(this.delivery_subscription != null){
                    this.delivery_subscription.unsubscribe();
                    this.delivery_subscription = null;
                }
    } 

}