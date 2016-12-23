import { Injectable } from '@angular/core';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class EditUserHelpService {

  private _baseURL = "http://localhost:4400";

  constructor(private _http : Http) { }

  getUserById(id)
    {
        return this._http.get(this._baseURL + "/getUser/" + id)
            .map(res => res.json());
    }

  updateUser(data)
  {
      var headers = new Headers({'Content-Type':'application/json'});
      var options = new RequestOptions({headers:headers});
      return this._http.put(this._baseURL+"/updateUser",JSON.stringify(data),options)
      .map(res=>res.json().data);
  }
}
