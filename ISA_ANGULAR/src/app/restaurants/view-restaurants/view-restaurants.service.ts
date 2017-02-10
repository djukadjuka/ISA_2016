import { Injectable } from '@angular/core';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';
import {RestaurantClass} from '../view-restaurants/restaurant-class';
import {RestaurantsProductsClass} from '../restaurants-products-class';
import {Observable} from 'rxjs/Rx';


@Injectable()
export class ViewRestaurantsService {

  private _baseURL = "http://localhost:4400";

  constructor(private _http : Http) { }

  getRestaurants()
    {
        return this._http.get(this._baseURL + "/getAllRestaurants")
            .map(res =><RestaurantClass[]> res.json());
    }

  updateRestaurant(data){
      var headers = new Headers({'Content-Type':'application/json'});
      var options = new RequestOptions({headers:headers});
      return this._http.put(this._baseURL+"/updateRestaurant",JSON.stringify(data),options)
      .map(res=>res.json().data);
  }

  uploadPicture(event){
    let fileList: FileList = event.files;
    if(fileList.length > 0){
      let file: File = fileList[0];
      let formData:FormData = new FormData();
      formData.append('file',file,file.name);
      let headers = new Headers();
      headers.append('Content-type','multiplart/form-data');
      headers.append('Accept','application/json');
      let options = new RequestOptions({headers:headers});
      this._http.post('http://localhost:4400/restaurantPictureUpload',formData,options)
        .map(res=>res.json())
        .catch(error=>Observable.throw(error))
        .subscribe(data=>console.log('success'),error=>console.log(error));
    }
  }

}