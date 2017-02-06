import { Injectable } from '@angular/core';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';
import {ProductClass} from '../products/product-class';


@Injectable()
export class ProductService {

    private _baseURL = "http://localhost:4400";

    constructor(private _http : Http) { }

    getAllProducts()
    {
        return this._http.get(this._baseURL + "/getAllProducts")
        .map(res => res.json());
    }

    getAllFood(){
        return this._http.get(this._baseURL + "/getAllFood")
        .map(res => <ProductClass[]> res.json());
    }

    getAllDrinks(){
        return this._http.get(this._baseURL + "/getAllDrinks")
        .map(res => <ProductClass[]> res.json());
    }
}