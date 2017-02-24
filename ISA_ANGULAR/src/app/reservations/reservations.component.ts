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

  msgs: Message[] = [];


  constructor(
              private _reservationService : ReservationService,
               private _editUserService : EditUserService,
              private _sharedService : SharedService,
              ) { }

  ngOnInit() {

      this.selectedReservation = {};

      this.getOriginatorReservationData();
      
      this._editUserService.getFriendships(this._sharedService.userId)
                          .subscribe(
                            res => this.friends = res
                          );
    
      this.originatorReservationsCols = [
            {field: 'id', header: 'Reservation ID'},
            {field: 'originator.username', header: 'Made by'},
            {field: 'reservation.table_id.restaurant_zone_id.restaurant.name', header: 'Restaurant'},
            {field: 'reservation.table_id.restaurant_zone_id.name', header: 'Zone'},
            {field: 'reservation.table_id.id', header: 'Table ID'},
            {field: 'reservation.table_id.maxPeople', header: 'Table seats'}
        ];

       this.friendsCols = [
            {field: 'recipient.firstName', header: 'First name'},
            {field: 'recipient.lastName', header: 'Last name'},
            {field: 'recipient.username', header: 'Username'},
            {field: 'recipient.email', header: 'Email'}
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

  cancelReservation(reservationCall)
  {
      this._reservationService.cancelReservation(reservationCall)
                              .subscribe(
                                  res => 
                                  {
                                        this.msgs = [];
                                        this.msgs.push({severity:'success', summary:'Reservation canceled!'});
                                        this.getOriginatorReservationData();
                                  }
                              );
  }

  reservationInvite(friend)
  {
      if(Object.keys(this.selectedReservation).length === 0)
      {
             this.msgs = [];
             this.msgs.push({severity:'warn', summary:'Please select one reservation.'});
      }
      else
      {
             this._reservationService.reservationInvite(this.selectedReservation, friend)
                                    .subscribe(
                                        res =>
                                        {
                                             this.msgs = [];
                                             this.msgs.push({severity:'success', summary:'Invite sent over e-mail.'});
                                        }
                                    )
      }
  }

}
