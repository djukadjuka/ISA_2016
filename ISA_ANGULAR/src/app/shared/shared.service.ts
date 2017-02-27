import { Injectable } from '@angular/core';
import { CanActivate }    from '@angular/router';
import { Auth } from '../auth.service';

@Injectable()
export class SharedService implements CanActivate {

  constructor(private auth: Auth) {}

   canActivate() {
    
    this.userId = "5";

    return this.auth.authenticated();
  }

  //Data about currently logged user
  public isAdmin : boolean = true;
  public isChef : boolean = false;
  public isCustomer : boolean = true;
  public isBartender : boolean = false;
  public isWaiter : boolean = false;
  public isSocialAccount : boolean = true;
  public isManager : boolean = true;
  public isDeliverer : boolean = true;

  public userId : String = "";
  public userEmail : String = "";

}

