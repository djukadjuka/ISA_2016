import { Component, OnInit } from '@angular/core';
import { ReservationService } from './reservation.service';
import { SharedService } from '../shared/shared.service';
import { EditUserService } from '../edit-user/edit-user.service';
import { Observable } from 'rxjs/Rx';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  originatorReservations = [];
  originatorReservationsCols = [];

  friends = [];
  friendsCols = [];


  constructor(
              private _reservationService : ReservationService,
               private _editUserService : EditUserService,
              private _sharedService : SharedService,
              ) { }

  ngOnInit() {

      this._reservationService.getOriginatorReservationCalls(this._sharedService.userId)
                            .subscribe(
                              res => this.originatorReservations = res
                            );
      
      this._editUserService.getFriendships(this._sharedService.userId)
                          .subscribe(
                            res => this.friends = res
                          );
    
      this.originatorReservationsCols = [
            {field: 'id', header: 'Reservation ID'},
            {field: 'originator.username', header: 'Originator username'},
            {field: 'reservations[0].table_id', header: 'Table ID'}
        ];

       this.friendsCols = [
            {field: 'recipient.firstName', header: 'First name'},
            {field: 'recipient.lastName', header: 'Last name'},
            {field: 'recipient.username', header: 'Username'},
            {field: 'recipient.email', header: 'Email'}
        ];
  }

}
