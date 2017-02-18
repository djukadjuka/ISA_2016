import { Injectable } from '@angular/core';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';
import {RestaurantClass} from '../view-restaurants/restaurant-class';
import {RestaurantsProductsClass} from '../restaurants-products-class';
import {Observable} from 'rxjs/Rx';
import {RestaurantZone} from '../view-restaurants/zone-class';


@Injectable()
export class ViewRestaurantsService {

  private _baseURL = "http://localhost:4400";

  constructor(private _http : Http) { }

  getRestaurants()
    {
        return this._http.get(this._baseURL + "/getAllRestaurants")
            .map(res =><RestaurantClass[]> res.json());
    }

    prepForUpload(id){
        return this._http.get(this._baseURL + "/prepForUpload/" + id).map(res=>console.log(res));
    }

  filterRestaurants(filterData)
  {
      var headers = new Headers({'Content-Type':'application/json'});
      var options = new RequestOptions({headers:headers});
      return this._http.post(this._baseURL + "/filterRestaurants",JSON.stringify(filterData),options)
            .map(res =><RestaurantClass[]> res.json());
  }

  getAllTables(zone_id)
  {
      return this._http.get(this._baseURL+"/getAllTables/"+zone_id)
                        .map(res => res.json());
  }

  updateRestaurant(data){
      var headers = new Headers({'Content-Type':'application/json'});
      var options = new RequestOptions({headers:headers});
      return this._http.put(this._baseURL+"/updateRestaurant",JSON.stringify(data),options)
      .map(res=>res.json().data);
  }

  getZonesForRestaurant(restaurant){
      return this._http.get(this._baseURL+"/getZoneByRestaurantId/"+restaurant.id).map(res => <RestaurantZone[]>res.json());
  }

  updateZone(zone){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.put(this._baseURL+"/editZone",JSON.stringify(zone),options).map(
      res=>res.json()
    );
  }

  deleteZone(zone){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.delete(this._baseURL+"/deleteZone/"+zone.id).map(
      res=>res.json()
    );
  }

  createZone(zone){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.put(this._baseURL+"/addZone",JSON.stringify(zone),options).map(
      res=>res.json()
    );
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