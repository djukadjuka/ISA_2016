import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReservationService } from '../reservations/reservation.service';
import { SharedService } from '../shared/shared.service';
import { Message } from 'primeng/primeng';

@Component({
  selector: 'app-invite',
  templateUrl: './invite.component.html',
  styleUrls: ['./invite.component.css']
})
export class InviteComponent implements OnInit, OnDestroy {

  private keygen;
  private sub: any;
  private inviteData: any;
  private showInvite = false;
  private showOrderDialog = false;
  private msgs: Message[] = [];

  foodMenu = [];
  drinkMenu = [];

  makeOrderReady : boolean = false;

  foodMenuCols = [];
  drinkMenuCols = [];

  selectedFood = {};
  selectedDrink = {};

  message = "";

  constructor(private route: ActivatedRoute,
              private _reservationService : ReservationService,
              private _sharedService : SharedService) { }

  ngOnInit() {
      this.sub = this.route.params.subscribe(params => {
        this.keygen = params['keygen']; 

        this._reservationService.inviteData(this.keygen)
                                .subscribe(
                                  res => {
                                    this.inviteData = res;
                                    this.message = "";
                                    this.showInvite = true;
                                    this.foodMenu = this.inviteData.reservation.table_id.restaurant_zone_id.restaurant.foodMenu;
                                    this.drinkMenu = this.inviteData.reservation.table_id.restaurant_zone_id.restaurant.drinksMenu;
                                  },
                                  err => {
                                      this.msgs = [];
                                      this.msgs.push({severity:'error', summary:'Reservation is canceled.'});
                                      this.message = "We are so sorry! Reservation invite is no longer active."
                                  }
                                );
    });

        this.foodMenuCols = [
            {field: 'name', header: 'Name'},
            {field: 'price', header: 'Product price $'},
            {field: 'description', header: 'Description'}
        ];
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  declineShowOrderDialog()
  {
      this.showOrderDialog = false;
      this.selectedFood = {};
      this.selectedDrink = {};
  }

    getDate(date)
  {
      let d = new Date(date);

      return d.toLocaleString();
  }

  declineInvite()
  {
      //update the invite to decline it
      this._reservationService.declineInvite(this.inviteData.id)
                              .subscribe(
                                res => {
                                      if(res)
                                      {
                                           this.msgs = [];
                                           this.msgs.push({severity:'success', summary:'Invite succesfully declined!'});
                                      }
                                  }
                              )
  }

  acceptInvite()
  {
      this._reservationService.acceptInvite(this.inviteData.id)
                              .subscribe(
                                  res => 
                                  {
                                      if(res)
                                      {
                                          this.msgs = [];
                                          this.msgs.push({severity:'success', summary:'Invite succesfully accepted!'});
                                      }
                                  }
                              )
  }

}
