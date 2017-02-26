import { Injectable } from '@angular/core';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';
import {TablesClass} from '../tables/tables-class'

@Injectable()
export class TablesService {

    private _baseURL = "http://localhost:4400"

constructor(private _http : Http) { }




getTables(res_id)
{

return this._http.get(this._baseURL + "/tableController/getTablesForRestaurant/" + res_id)
            .map(res =><TablesClass[]> res.json());

}


}