import { Injectable } from '@angular/core';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';
import {TablesClass} from '../tables/tables-class'

@Injectable()
export class TablesService {

    private _baseURL = "http://localhost:4400"

constructor(private _http : Http) { }




getTables()
{

return this._http.get(this._baseURL + "/getAllTables")
            .map(res =><TablesClass[]> res.json());

}


}