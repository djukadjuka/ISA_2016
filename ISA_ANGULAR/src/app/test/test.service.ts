import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class TestService {

  private _baseURL = "http://localhost:4400";

  constructor(private _http : Http) { }

  getUsers()
    {
        return this._http.get(this._baseURL + "/users/all")
            .map(res => res.json());
    }

}
