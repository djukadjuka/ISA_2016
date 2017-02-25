import { Injectable } from '@angular/core';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class ReservationService {

  private _baseURL = "http://localhost:4400";

  constructor(private _http : Http) { }

    getOriginatorReservationCalls(id)
    {
        return this._http.get(this._baseURL + "/getReservationsForOriginator/" + id)
            .map(res => res.json());
    }

     getRecipientReservationCalls(id)
    {
        return this._http.get(this._baseURL + "/getReservationsForRecipient/" + id)
            .map(res => res.json());
    }

    cancelReservation(reservationCall)
    {
        return this._http.delete(this._baseURL+"/cancelReservation/" + reservationCall.reservation.id + "/" + reservationCall.id)
                            .map(res => res.json());
    }

     reservationInvite(reservationCall, recipient)
    {
        reservationCall.recipient = recipient;

        console.log(reservationCall);

        var headers = new Headers({'Content-Type':'application/json'});
        var options = new RequestOptions({headers:headers});
        // {"reservationCall": reservationCall,"recipient" : recipient}

        return this._http.post(this._baseURL+"/reservationInvite",JSON.stringify(reservationCall),options)
                            .map(res=>res.json().data);
    }

    //************* SERVICES FOR RESERVATION TAB AND INVITES OVER EMAIL *******************

    inviteData(keygen)
    {
        return this._http.get(this._baseURL + "/inviteData/" + keygen)
            .map(res => res.json());
    }

    declineInvite(call_id)
    {
        var headers = new Headers({'Content-Type':'application/json'});
        var options = new RequestOptions({headers:headers});

        return this._http.put(this._baseURL+"/declineInvite",JSON.stringify(call_id),options)
                        .map(res=>res.json().data);
    }

    acceptInvite(call_id)
    {
        var headers = new Headers({'Content-Type':'application/json'});
        var options = new RequestOptions({headers:headers});

        return this._http.put(this._baseURL+"/acceptInvite",JSON.stringify(call_id),options)
                        .map(res=>res.json().data);
    }

    updateFoodAndDrink(call_id, food, drink, makeOrderReady)
    {
        var headers = new Headers({'Content-Type':'application/json'});
        var options = new RequestOptions({headers:headers});

        return this._http.put(this._baseURL+"/updateFoodAndDrink",JSON.stringify({reservation_call_id : call_id, food : food, drink : drink, makeOrderReady : makeOrderReady}),options)
                        .map(res=>res.json().data);
    }

    cancelFoodAndDrink(call_id)
    {
        var headers = new Headers({'Content-Type':'application/json'});
        var options = new RequestOptions({headers:headers});

        return this._http.put(this._baseURL+"/cancelFoodAndDrink",JSON.stringify(call_id),options)
                        .map(res=>res.json().data);
    }
    
}
