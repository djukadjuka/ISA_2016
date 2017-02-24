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

  getDeliveryBidsForDeliveryId(delivery_id){
    return this._http.get(this._baseURL + "/delivery_controller/getBidsForDeliveryId/" + delivery_id)
    .map(res=>res.json());
  }

  getDeliveryOrdersForRestaurant(rest_id){
    return this._http.get(this._baseURL + "/delivery_controller/getStartingData/" + rest_id)
    .map(res=>res.json());
  }

  getPendingDeliverers(){
    return this._http.get(this._baseURL + "/userRepo/getPendingDeliverers").map(res=>res.json());
  }

  getWaitersForRestaurant(rest_id){
    return this._http.get(this._baseURL + "/EmployeeControler/getWaitersForRestaurant/" + rest_id)
    .map(res=>res.json());
  }

  getTablesForRestaurant(rest_id){
    return this._http.get(this._baseURL + "/tableController/getTablesForRestaurant/" + rest_id)
    .map(res=>res.json());
  }

  getWorkersForRestaurant(rest_id){
    return this._http.get(this._baseURL + "/EmployeeControler/getEmployeesForRestaurant/" + rest_id)
    .map(res=>res.json());
  }

  getWorkersNOMANAGERSForRestaurant(rest_id){
    return this._http.get(this._baseURL + "/schedz/getEmployeesForRestaurant/"+rest_id)
      .map(res=>res.json());
  }

  getSpecificWorkersSchedules(worker_id){
    return this._http.get(this._baseURL + "/schedz/getForEmployee/" + worker_id)
      .map(res => res.json());
  }

  getWorkersAndUsersForEmployeeManagement(rest_id){
    return this._http.get(this._baseURL + "/userRepo/getEmployeeDataForRestaurant/" + rest_id)
      .map(res=>res.json());
  }

  getScheduleByDate(){
    return this._http.get(this._baseURL + "/schedz/getScheduleByDate")
          .map(res=>res.json());

  }

  getFreeManagers_AndUserManagers(manager_id,restaurant_id){
    return this._http.get(this._baseURL + "/userRepo/getManagers/" + manager_id + "/forRestaurant/"+restaurant_id)
          .map(res=>res.json());
  }

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

  getAllTables(zone_id, startDate, endDate)
  {
      return this._http.get(this._baseURL+"/getAllTables/"+zone_id+"/"+startDate+"/"+endDate)
                        .map(res => res.json());
  }

  makeReservation(reservation)
  {
      var headers = new Headers({'Content-Type':'application/json'});
      var options = new RequestOptions({headers:headers});
      return this._http.post(this._baseURL + "/makeReservation",JSON.stringify(reservation),options)
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


  getAllRestaurantGrades(rest_id){
    return this._http.get(this._baseURL + "/restaurant_statistics/getAllRestaurantGrades/" + rest_id)
      .map(res=>res.json());
  }

  getGradesForProductInRestaurant(rest_id,prod_id){
    return this._http.get(this._baseURL + "/restaurant_statistics/gradesForProduct/"+prod_id+"/inRestaurant/" + rest_id)
      .map(res=>res.json());
  }

  getGradesForEmployee(emp_id){
    return this._http.get(this._baseURL + "/restaurant_statistics/getGradesForEmployee/"+emp_id)
      .map(res=>res.json());
  }

  getBillsForRestaurant(rest_id){
    return this._http.get(this._baseURL + "/restaurant_statistics/getAllRestaurantBills/"+rest_id)
      .map(res=>res.json());
  }

  getBillsForEmployee(emp_id){
    return this._http.get(this._baseURL + "/restaurant_statistics/getBillsForAnEmployee/"+emp_id)
      .map(res=>res.json());
  }

  getBillsForRestaurantTimePeriod(rest_id,date_from,date_to){
    return this._http.get(this._baseURL + "/restaurant_statistics/getBills/"+rest_id+"/from/"+date_from+"/to/"+date_to)
      .map(res=>res.json());
  }
}