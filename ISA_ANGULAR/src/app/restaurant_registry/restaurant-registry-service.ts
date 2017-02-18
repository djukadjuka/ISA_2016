import { Injectable } from '@angular/core';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';
import {RestaurantRegistry} from './restaurant-registry-class';


@Injectable()
export class RestaurantRegistryService {

private _baseURL = "http://localhost:4400";
private _url_postfix = "/restaurantRegistry/";

constructor(private _http : Http) { }

	/**
	 * Registers that the admin hasn't yet seen
	 */
	getUnseenRegisterForManager(manager_id){
		return this._http.get(this._baseURL + this._url_postfix+ "getUnseenByManager/"+ manager_id)
		.map(res=><RestaurantRegistry[]>res.json());
	}

	/**
	 * Register that the manager hasn't yet seen
	 */
	getUnseenRegistersForAdmin(){
		return this._http.get(this._baseURL + this._url_postfix + "getPendingForAdmin")
		.map(res=><RestaurantRegistry[]>res.json());
	}

	/**
	* Admin accepted registration
	*/
	updateRegistration_ACCEPTED(reg_id){
		var headers = new Headers({'Content-Type':'application/json'});
		var options = new RequestOptions({headers:headers});
		return this._http.put(this._baseURL+this._url_postfix+"adminAccepted/"+reg_id,null,options)
		.map(res=>res.json());
	}

	/**
	 * Admin declined registration
	 */
	 updateRegistration_DECLINED(reg_id){
		var headers = new Headers({'Content-Type':'application/json'});
		var options = new RequestOptions({headers:headers});
		return this._http.put(this._baseURL+this._url_postfix+"adminDeclined/"+reg_id,null,options)
		.map(res=>res.json());
	 }

	/**
	 * Manager saw update
	 */
	 updateRegistration_SEEN(reg_id){
		var headers = new Headers({'Content-Type':'application/json'});
		var options = new RequestOptions({headers:headers});
		return this._http.put(this._baseURL+this._url_postfix+"managerSawStatus/"+reg_id,null,options)
		.map(res=>res.json());
	 }

	 /**
	  * Register new restaurant by manager
	  */
	  registerNewRestaurant(register,mgr_id){
		var headers = new Headers({'Content-Type':'application/json'});
		var options = new RequestOptions({headers:headers});
		return this._http.post(this._baseURL+this._url_postfix+"newRegistry/"+mgr_id,JSON.stringify(register),options)
		.map(res=><RestaurantRegistry>res.json());

	  }
}
