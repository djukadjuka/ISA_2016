import { Injectable } from '@angular/core';
import { CanActivate, Router }    from '@angular/router';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import 'rxjs/Rx';
import { Auth } from '../auth.service';

@Injectable()
export class SharedService implements CanActivate {

    private _baseURL = "http://localhost:4400";


  constructor(private auth: Auth, private router : Router,  private _http : Http) {

     this.getUserData();
  }
  

// MOZDA PODESITI DA CIM POGODI 4200 da mu se postavlja login aaaaaal msm.. hmm
  canActivate() {

    if(this.pullUserData)
    {
        this.userProfile = JSON.parse(localStorage.getItem('profile'));
        
        //ako nije verifikovao mail, saljem ga napolje
        if(!this.userProfile.email_verified)
        {
            this.message = "Please verify your account over email."
            this.auth.logout();
        }
        else
            this.message = "";

        this.addNewUser(this.userProfile)
              .subscribe(
                res => {
                    this.userId = res.id;
                    this.
                }
                ); 
                //Vratiti ovde id novog registrovanog
        //this.userProfile.email_verified true false
        console.log(this.userProfile);
    }

    return this.auth.authenticated();
  }

   addNewUser(user)
  {
       var headers = new Headers({'Content-Type':'application/json'});
       var options = new RequestOptions({headers:headers});
       var userValue;
       
       if(user.user_id.substring(0, 5) != "auth0")
       {
            userValue = JSON.stringify({first_name: user.given_name ,
                                        last_name : user.family_name, 
                                        email : user.email, 
                                        username : user.nickname,
                                        picture : user.picture,
                                        auth_id : user.user_id})
       }
       else
       {
            userValue = JSON.stringify({first_name: user.user_metadata.name ,
                                        last_name : user.user_metadata.lastname, 
                                        email : user.email, 
                                        username : user.nickname,
                                        picture : user.picture,
                                        auth_id : user.user_id})
       }

       return this._http.post(this._baseURL+"/auth/addNewUser",userValue,options)
                        .map(res=>res.json());
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
  public isSocialAccount : boolean = false;
  public isManager : boolean = true;
  public isDeliverer : boolean = true;

  public userId : String = "1";
  public userEmail : String = "";

  public userProfile : any;

  public pullUserData = true;

  public message = "";

}

