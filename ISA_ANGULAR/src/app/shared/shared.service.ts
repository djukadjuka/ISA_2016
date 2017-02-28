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
  

  canActivate() {

    let canActivate = this.auth.authenticated();

    return canActivate;
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
       if(this.pullUserData && localStorage.getItem('profile') != null)
    {
        this.userProfile = JSON.parse(localStorage.getItem('profile'));

        this.isSocialAccount = this.userProfile.identities[0].isSocial;
        
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
                    this.userId = res.user_id;
                    this.userEmail = this.userProfile.email;

                    this.managesRestaurants = res.manages_restaurants;
                    console.log(this.managesRestaurants);

                    if(res.user_role == "MANAGER")
                    {
                       this.isAdmin = false;
                       this.isChef = false;
                       this.isBartender = false;
                       this.isWaiter = false;
                       this.isManager = true;
                       this.isDeliverer = false;
                    }
                    if(res.user_role == "WAITER")
                    {
                       this.isAdmin = false;
                       this.isChef = false;
                       this.isBartender = false;
                       this.isWaiter = true;
                       this.isManager = false;
                       this.isDeliverer = false;
                    }
                    if(res.user_role == "BARTENDER")
                    {
                       this.isAdmin = false;
                       this.isChef = false;
                       this.isBartender = true;
                       this.isWaiter = false;
                       this.isManager = false;
                       this.isDeliverer = false;
                    }
                    if(res.user_role == "COOK")
                    {
                       this.isAdmin = false;
                       this.isChef = true;
                       this.isBartender = false;
                       this.isWaiter = false;
                       this.isManager = false;
                       this.isDeliverer = false;
                    }
                    if(res.user_role == "DELIVERER")
                    {
                       this.isAdmin = false;
                       this.isChef = false;
                       this.isBartender = false;
                       this.isWaiter = false;
                       this.isManager = false;
                       this.isDeliverer = true;
                    }

                    if(res.user_role == "ADMIN")
                    {
                       this.isAdmin = true;
                       this.isChef = false;
                       this.isBartender = false;
                       this.isWaiter = false;
                       this.isManager = false;
                       this.isDeliverer = false;
                    }

                    if(res.user_role == "USER")
                    {
                       this.isAdmin = true;
                       this.isChef = false;
                       this.isBartender = false;
                       this.isWaiter = false;
                       this.isManager = false;
                       this.isDeliverer = false;
                    }

                    
                    this.pullUserData = false;
                    
                    //if res.password = 0, logout uz poruku da promeni pass i saljemo menjanje pass-a
                }
                ); 
                //Vratiti ovde id novog registrovanog
        //this.userProfile.email_verified true false
        console.log(this.userProfile);
    }
  }

  //Data about currently logged user
  public isAdmin : boolean;
  public isChef : boolean;
  public isCustomer : boolean;
  public isBartender : boolean;
  public isWaiter : boolean;
  public isSocialAccount : boolean;
  public isManager : boolean;
  public isDeliverer : boolean;
  public managesRestaurants : any;

  public userId : String = "";
  public userEmail : String = "";

  public userProfile : any;

  public pullUserData = true;

  public message = "";

}

