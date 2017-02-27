import { Component, OnInit } from '@angular/core';
import { ReservationService } from './reservation.service';
import { SharedService } from '../shared/shared.service';
import { EditUserService } from '../edit-user/edit-user.service';
import { Observable } from 'rxjs/Rx';
import { Message } from 'primeng/primeng';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  selectedReservation = {};
  originatorReservations = [];
  originatorReservationsCols = [];

  friends = [];
  friendsCols = [];

  recipientReservations = [];
  recipientReservationsCols = [];

  //food and drink choosing
  showOrderDialog = false;
  selectedReservationCallId: any;

  foodMenu = [];
  drinkMenu = [];

  makeOrderFast : boolean = false;

  foodMenuCols = [];
  drinkMenuCols = [];

  selectedFood : any;
  selectedDrink : any;

  msgs: Message[] = [];


  constructor(
              private _reservationService : ReservationService,
               private _editUserService : EditUserService,
              private _sharedService : SharedService,
              ) { }

  ngOnInit() {

      this.selectedReservation = {};

      this.getOriginatorReservationData();

      this.getRecipientReservationData();
      
      this._editUserService.getFriendships(this._sharedService.userId)
                          .subscribe(
                            res => {this.friends = res;
                                    console.log(res);
                            }
                          );
    
      this.originatorReservationsCols = [
            {field: 'id', header: 'Reservation ID'},
            {field: 'reservation.table_id.restaurant_zone_id.restaurant.name', header: 'Restaurant'},
            {field: 'reservation.table_id.restaurant_zone_id.name', header: 'Zone'},
            {field: 'reservation.table_id.id', header: 'Table ID'},
            {field: 'reservation.table_id.maxPeople', header: 'Table seats'}
        ];

        this.recipientReservationsCols = [
            {field: 'id', header: 'Reservation ID'},
            {field: 'reservation.table_id.restaurant_zone_id.restaurant.name', header: 'Restaurant'},
            {field: 'reservation.table_id.restaurant_zone_id.name', header: 'Zone'},
            {field: 'reservation.table_id.id', header: 'Table ID'},
            {field: 'reservation.table_id.maxPeople', header: 'Table seats'},
            {field: 'food.name', header: 'Ordered food'},
            {field: 'drink.name', header: 'Ordered drink'}
        ];

       this.friendsCols = [
            {field: 'recipient.firstName', header: 'First name'},
            {field: 'recipient.lastName', header: 'Last name'},
            {field: 'recipient.username', header: 'Username'},
            {field: 'recipient.email', header: 'Email'}
        ];

         this.foodMenuCols = [
            {field: 'name', header: 'Name'},
            {field: 'price', header: 'Product price $'},
            {field: 'description', header: 'Description'}
        ];
  }

  getDate(reservationCall)
  {
     let d = new Date(reservationCall.reservation.startDate);

     return d.toLocaleString();
  }

  getOriginatorReservationData()
  {
      this._reservationService.getOriginatorReservationCalls(this._sharedService.userId)
                            .subscribe(
                              res => this.originatorReservations = res
                            );
  }

  getRecipientReservationData()
  {
       this._reservationService.getRecipientReservationCalls(this._sharedService.userId)
                            .subscribe(
                              res => this.recipientReservations = res
                            );
  }
  

  cancelReservation(reservationCall)
  {
      this._reservationService.cancelReservation(reservationCall)
                              .subscribe(
                                  res => 
                                  {
                                        this.msgs = [];
                                        this.msgs.push({severity:'success', summary:'Reservation canceled!'});
                                        this.getOriginatorReservationData();
                                        this.getRecipientReservationData();
                                  }
                              );
  }

  reservationInvite(friendship)
  {
      if(Object.keys(this.selectedReservation).length === 0)
      {
             this.msgs = [];
             this.msgs.push({severity:'warn', summary:'Please select one reservation.'});
      }
      else
      {
             this._reservationService.reservationInvite(this.selectedReservation, friendship.recipient)
                                    .subscribe(
                                        res => {
                                                this.msgs = [];
                                                this.msgs.push({severity:'success', summary:'Invite sent over e-mail.'});
                                            },
                                        err => {
                                                this.msgs = [];
                                                this.msgs.push({severity:'error', summary:'Invitation is already sent.'});
                                        }
                                    )
      }
  }

  chooseFoodAndDrink(reservationCall)
  {
        this.selectedReservationCallId = reservationCall.id;
        this.foodMenu = reservationCall.reservation.table_id.restaurant_zone_id.restaurant.foodMenu;
        this.drinkMenu = reservationCall.reservation.table_id.restaurant_zone_id.restaurant.drinksMenu;

        this.showOrderDialog = true;
  }

  declineShowOrderDialog()
  {
      this.selectedReservationCallId = "";
      this.selectedFood = {};
      this.selectedDrink = {};
      this.makeOrderFast = false;

      this.showOrderDialog = false;
  }

  acceptShowOrderDialog()
  {
      this._reservationService.updateFoodAndDrink(+this.selectedReservationCallId, +this.selectedFood.id, +this.selectedDrink.id, this.makeOrderFast)
                            .subscribe(
                                res =>
                                {
                                     this.msgs = [];
                                     this.msgs.push({severity:'success', summary:'Your food and drink order is set succesfully!'});

                                     this.selectedFood = {};
                                     this.selectedDrink = {};
                                     this.makeOrderFast = false;
                                     this.selectedReservationCallId = "";

                                     this.showOrderDialog = false;

                                     this.getRecipientReservationData();
                                }
                            );
  }

  cancelFoodAndDrinkOrder(reservationCall)
  {
      this._reservationService.cancelFoodAndDrink(reservationCall.id)
                            .subscribe(
                                res => 
                                {
                                    this.msgs = [];
                                     this.msgs.push({severity:'success', summary:'Your food and drink order is canceled succesfully!'});
                                     
                                      this.getRecipientReservationData();
                                }
                            );
  }

}
