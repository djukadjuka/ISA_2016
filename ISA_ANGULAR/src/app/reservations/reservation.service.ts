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

    //FOR EMAIL INVITE
    inviteData(keygen)
    {
        return this._http.get(this._baseURL + "/inviteData/" + keygen)
            .map(res => res.json());
    }
    
}
