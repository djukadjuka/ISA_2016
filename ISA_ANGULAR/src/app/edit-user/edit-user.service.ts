import { Injectable } from '@angular/core';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';
import { SharedService } from '../shared/shared.service';

@Injectable()
export class EditUserService {

  private _baseURL = "http://localhost:4400";

  constructor(
        private _http : Http,
        private _sharedService : SharedService ) { }

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

   updateUserPassword()
  {
    //TO-DO     
    //Iz deljenog servisa saznati email od usera i proslediti ga dole
    //this._sharedService.userEmail // kada se poveze sa auth0

    var request = require("request");

    var options = { method: 'POST',
    url: 'https://stkosijer.eu.auth0.com/dbconnections/change_password',
    headers: { 'content-type': 'application/json' },
    body: 
    { client_id: '8WD2ZYu8u0Y5ZcfhTqgANX7Gt8FBEHpU',
        email: 'stkosijer@gmail.com',
        connection: 'Username-Password-Authentication' },
    json: true };

    request(options, function (error, response, body) {
        if (error) throw new Error(error);
    });
  }
}
