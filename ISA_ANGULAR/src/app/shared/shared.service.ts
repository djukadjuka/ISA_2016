import { Injectable } from '@angular/core';
import { CanActivate, Router }    from '@angular/router';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';
import { Auth } from '../auth.service';

@Injectable()
export class SharedService implements CanActivate {

    private _baseURL = "http://localhost:4400";


  constructor(private auth: Auth, private router : Router,  private _http : Http) {

    alert("shared");
    console.log(localStorage.getItem('profile'));
     this.getUserData();
  }
  

// MOZDA PODESITI DA CIM POGODI 4200 da mu se postavlja login aaaaaal msm.. hmm
  canActivate() {

    this.userProfile = localStorage.getItem('profile');

    //this.userProfile.email_verified true false
    console.log(this.userProfile);
    console.log(localStorage.getItem('token_id'));

    return this.auth.authenticated();
  }

   addNewUser(user)
  {
       var headers = new Headers({'Content-Type':'application/json'});
       var options = new RequestOptions({headers:headers});

       return this._http.post(this._baseURL+"/auth/addNewUser",JSON.stringify({first_name: user.given_name ,
                                                                                last_name : user.family_name, 
                                                                                email : user.email, 
                                                                                username : user.nickname,
                                                                                picture : user.picture,
                                                                                auth_id : user.identities[0].user_id}),options)
                        .map(res=>res.json().data);
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

