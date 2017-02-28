import { Injectable } from '@angular/core';
import { CanActivate, Router }    from '@angular/router';
import { Auth } from '../auth.service';

@Injectable()
export class PleaseService{

  constructor(private router : Router) {

   
  }

  public userInfo : any;

}
