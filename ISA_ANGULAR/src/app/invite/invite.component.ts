import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReservationService } from '../reservations/reservation.service';
import { SharedService } from '../shared/shared.service';

@Component({
  selector: 'app-invite',
  templateUrl: './invite.component.html',
  styleUrls: ['./invite.component.css']
})
export class InviteComponent implements OnInit, OnDestroy {

  private keygen;
  private sub: any;
  private inviteData = {};
  private showInvite = false;
  private showOrderDialog = false;

  foodMenu = [];
  drinkMenu = [];

  makeOrderReady : boolean = false;

  foodMenuCols = [];
  drinkMenuCols = [];

  selectedFood = {};
  selectedDrink = {};

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
                                    this.showInvite = true;
                                    this.foodMenu = this.inviteData.reservation.table_id.restaurant_zone_id.restaurant.foodMenu;
                                    this.drinkMenu = this.inviteData.reservation.table_id.restaurant_zone_id.restaurant.drinksMenu;
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

  }

}
