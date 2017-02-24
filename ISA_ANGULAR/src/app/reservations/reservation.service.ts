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
}
