import { Injectable } from '@angular/core';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';
import {RestaurantClass} from '../view-restaurants/restaurant-class';

import {RestaurantsProductsClass} from '../restaurants-products-class';
import {Observable} from 'rxjs/Rx';
import {RestaurantZone} from '../view-restaurants/zone-class';
import {EmployeeClass} from '../../edit-barman/employee-class';


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

  getScheduleByDate(date){
    return this._http.get(this._baseURL + "/schedz/getScheduleByDate/" + date)
          .map(res=>res.json());

  }

  getCookScheduleByDate(date){
    return this._http.get(this._baseURL + "/schedz/getCookScheduleByDate/" + date)
          .map(res=>res.json());

  }

 getBarmanScheduleByDate(date){
    return this._http.get(this._baseURL + "/schedz/getBarmanScheduleByDate/" + date)
          .map(res=>res.json());

  }

  getEmployeeById(worker_id){
    return this._http.get(this._baseURL + "/EmployeeControler/getEmployeeById/" + worker_id)
          .map(res=><EmployeeClass[]>res.json());

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

   updateOrder(food_name, food_price, table_id,waiter_id)

  {
       var headers = new Headers({'Content-Type':'application/json'});
      var options = new RequestOptions({headers:headers});
      return this._http.post(this._baseURL+"/order/updateOrder/"+food_name+"/"+food_price+"/"+table_id+"/"+waiter_id,null,options)
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

  basicRestaurantUpdate(data){
    var headers = new Headers({'Content-Type':'application/json'});
      var options = new RequestOptions({headers:headers});
      return this._http.post(this._baseURL+"/basicRestaurantUpdate",JSON.stringify(data),options)
      .map(res=>res.json()); 
  }

  getZonesForRestaurant(restaurant){
      return this._http.get(this._baseURL+"/getZoneByRestaurantId/"+restaurant.id).map(res => <RestaurantZone[]>res.json());
  }

  updateZoneFIX(zone){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.post(this._baseURL+"/editZoneFIX",JSON.stringify(zone),options).map(
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

  getAttendanceForYear(year,rest_id){
    return this._http.get(this._baseURL + "/restaurant_statistics/getAttendanceForYear/"+year+"/for_restaurant/"+rest_id)
      .map(res=>res.json());  
  }
  
  getAttendanceForDayPeriod(start_day,end_day,rest_id){
    return this._http.get(this._baseURL + "/restaurant_statistics/getAttendanceFromDay/"+start_day+"/toDay/"+end_day+"/for_restaurant/"+rest_id)
      .map(res=>res.json());  
  }

  getRevinueForRestarurant(start_day,end_day,rest_id){
    return this._http.get(this._baseURL + "/restaurant_statistics/getBills/"+rest_id+"/from/"+start_day+"/to/"+end_day)
      .map(res=>res.json());  
  }

  //////////////////////////////////////////////////////////////////
  createNewManagers(manager_payload,rest_id){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.post(this._baseURL+"/EmployeeControler/sendNewManagerPack/"+rest_id,JSON.stringify(manager_payload),options).map(
      res=>"finished..."
    );
  }
  fireSomeManagers(manager_payload,rest_id){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.post(this._baseURL+"/EmployeeControler/sendRemovingManagerPack/"+rest_id,JSON.stringify(manager_payload),options).map(
      res=>"finished..."
    );
  }

  registerNewEmployee(employee,rest_id){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.post(this._baseURL+"/EmployeeController/registerNewEmployee/"+rest_id,JSON.stringify(employee),options).map(
      res=>"finished..."
    );
  }

  fireAnEmployee(emp_id){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.post(this._baseURL+"/EmployeeController/fireEmployee/"+emp_id,null,options).map(
      res=>"finished..."
    );
  }
/////////////////////////////////////////////////////////////SCHEDULES SERVICES
  create_new_schedule(schedz){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.post(this._baseURL+"/schedz/createNewSchedule",JSON.stringify(schedz),options).map(
      res=>"finished..."
    );
  }
  delete_schedule(sc_id){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.post(this._baseURL+"/schedz/deleteSchedule/"+sc_id,null,options).map(
      res=>"finished..."
    );
  }
  //////////////////////////////////////////////////////////////REGION SERVICES
  change_served_by(empl_id,table){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.post(this._baseURL+"/tableController/setTableForEmployee/"+empl_id,JSON.stringify(table),options).map(
      res=>"finished..."
    );
  }
  ///////////////////////////////////////////////////////MANAGER DELIVERY STUFF
  upgrade_to_deliverer(user){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.post(this._baseURL+"/delivery_controller/upgradeToDeliverer",JSON.stringify(user),options).map(
      res=>"finished..."
    );
  }
  sendNewDelivery(deliveryOrder){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.post(this._baseURL+"/delivery_controller/sendNewDelivery",JSON.stringify(deliveryOrder),options).map(
      res=>"finished..."
    );
  }
  acceptBid(payload){
    var headers = new Headers({'Content-Type':'application/json'});
    var options = new RequestOptions({headers:headers});
    return this._http.post(this._baseURL+"/delivery_controller/acceptBid",JSON.stringify(payload),options).map(
      res=>"finished..."
    );
  }

  //*********************************** SERVICES FOR ALL RESTAURANT RATES ********************************************** */

  //RESTAURANT
  
  getRestaurantAverageRateAll(restaurant_id)
  {
       return this._http.get(this._baseURL + "/getRestaurantAverageRateAll/" + restaurant_id)
      .map(res=>res.json());  
  }

  getRestaurantAverageRateFriends(restaurant_id, user_id)
  {
       return this._http.get(this._baseURL + "/getRestaurantAverageRateFriends/" + restaurant_id + "/" + user_id)
      .map(res=>res.json());  
  }

  getRestaurantAverageRateMe(restaurant_id, user_id)
  {
       return this._http.get(this._baseURL + "/getRestaurantAverageRateMe/" + restaurant_id + "/" + user_id)
      .map(res=>res.json());  
  }

  //FOOD
  
  getFoodAverageRateAll(restaurant_id)
  {
       return this._http.get(this._baseURL + "/getFoodAverageRateAll/" + restaurant_id)
      .map(res=>res.json());  
  }

  getFoodAverageRateFriends(restaurant_id, user_id)
  {
       return this._http.get(this._baseURL + "/getFoodAverageRateFriends/" + restaurant_id + "/" + user_id)
      .map(res=>res.json());  
  }

  getFoodAverageRateMe(restaurant_id, user_id)
  {
       return this._http.get(this._baseURL + "/getFoodAverageRateMe/" + restaurant_id + "/" + user_id)
      .map(res=>res.json());  
  }

  //WAITER
  
  getWaiterAverageRateAll(restaurant_id)
  {
       return this._http.get(this._baseURL + "/getWaiterAverageRateAll/" + restaurant_id)
      .map(res=>res.json());  
  }

  getWaiterAverageRateFriends(restaurant_id, user_id)
  {
       return this._http.get(this._baseURL + "/getWaiterAverageRateFriends/" + restaurant_id + "/" + user_id)
      .map(res=>res.json());  
  }

  getWaiterAverageRateMe(restaurant_id, user_id)
  {
       return this._http.get(this._baseURL + "/getWaiterAverageRateMe/" + restaurant_id + "/" + user_id)
      .map(res=>res.json());  
  }

  getAllFood()
  {
    return this._http.get(this._baseURL + "/getAllFood" )
      .map(res=>res.json()); 

  }

  getAllDrinks()
  {
    return this._http.get(this._baseURL + "/getAllDrinks" )
      .map(res=>res.json()); 

  }

}