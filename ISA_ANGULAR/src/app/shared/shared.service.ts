import { Injectable } from '@angular/core';
import { CanActivate, Router }    from '@angular/router';
import { Auth } from '../auth.service';

@Injectable()
export class SharedService implements CanActivate {

  constructor(private auth: Auth, private router : Router) {

    alert("shared");
    console.log(localStorage.getItem('profile'));
     this.getUserData();
  }

// MOZDA PODESITI DA CIM POGODI 4200 da mu se postavlja login aaaaaal msm.. hmm
  canActivate() {

    this.userProfile = localStorage.getItem('profile');
    console.log(this.userProfile);

    return this.auth.authenticated();
  }

  getUserData()
  {

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
  public userEmail : String = "1";

  public userProfile : any;

  public refresh = false;

}

