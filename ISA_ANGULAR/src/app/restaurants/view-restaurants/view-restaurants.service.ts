import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class ViewRestaurantsService {

  private _baseURL = "http://localhost:4400";

  constructor(private _http : Http) { }

  getRestaurants()
    {
        return this._http.get(this._baseURL + "/getAllRestaurants")
            .map(res => res.json());
    }

}