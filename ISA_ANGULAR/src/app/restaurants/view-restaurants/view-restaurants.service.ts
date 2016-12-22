import { Injectable } from '@angular/core';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
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

  updateRestaurant2(data){
      return this._http.put(this._baseURL+"/updateRestaurant",JSON.stringify(data))
      .map(res=>res.json());
  }

  updateRestaurant(data){
      var headers = new Headers(), authtoken = localStorage.getItem('authtoken');
      headers.append("Content-Type",'application/json');

      if(authtoken){
          headers.append("Authorization",'Token '+authtoken);
      }
      headers.append("Accept",'application/json');
      var requestOptions = new RequestOptions({
          method: RequestMethod.Post,
          url:this._baseURL + "/updateRestaurant/",
          headers: headers,
          body: data
      })

      return this._http.request(new Request(requestOptions))
      .map((res: Response)=>{
          if(res){
              return {status: res.status, json: res.json()}
          }
      })
  }

}