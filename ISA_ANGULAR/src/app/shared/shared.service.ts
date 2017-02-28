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
       var emailValue;
       //ako je email prazan slucajno
       if(user.email == null)
            emailValue = "Hidden email."
        else
            emailValue = user.email;
       
       if(user.user_id.substring(0, 5) != "auth0")
       {
            userValue = JSON.stringify({first_name: user.given_name ,
                                        last_name : user.family_name, 
                                        email : emailValue, 
                                        username : user.nickname,
                                        picture : user.picture,
                                        auth_id : user.user_id})
       }
       else
       {
            userValue = JSON.stringify({first_name: user.user_metadata.name ,
                                        last_name : user.user_metadata.lastname, 
                                        email : emailValue, 
                                        username : user.nickname,
                                        picture : user.picture,
                                        auth_id : user.user_id})
       }

       return this._http.post(this._baseURL+"/auth/addNewUser",userValue,options)
                        .map(res=>res.json());
  }

  setRoles(res)
  {
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
  }

  getUserData()
  {
       if(localStorage.getItem('profile') != null)
    {
        this.userProfile = JSON.parse(localStorage.getItem('profile'));
        
        //ako nije verifikovao mail, saljem ga napolje
        if(!this.userProfile.email_verified)
        {
            this.message = "Please verify your account over email."
            this.auth.logout();
            return false;
        }
        else
            this.message = "";

        this.addNewUser(this.userProfile)
              .subscribe(
                res => {
                    this.userId = res.user_id;

                    if(this.userProfile.email == null)
                        this.userEmail = "Hidden email";
                    else
                        this.userEmail = this.userProfile.email;

                    this.managesRestaurants = res.manages_restaurants;
                   
                    this.isSocialAccount = this.userProfile.identities[0].isSocial;

                    this.setRoles(res);
                    
                    //if res.password = 0, logout uz poruku da promeni pass i saljemo menjanje pass-a
                    if(res.password == 0 && this.isSocialAccount == false)
                    {
                        this.message = "First login. Please change your password!"
                        this.updateUserPassword();
                    }

                }
                ); 
                //Vratiti ovde id novog registrovanog
        //this.userProfile.email_verified true false
        console.log(this.userProfile);
    }
  }

  updateUserPassword()
  {
      
    var request = require("request");

    var options = { method: 'POST',
    url: 'https://stkosijer.eu.auth0.com/dbconnections/change_password',
    headers: { 'content-type': 'application/json' },
    body: 
    { client_id: '8WD2ZYu8u0Y5ZcfhTqgANX7Gt8FBEHpU',
        email: this.userEmail,
        connection: 'Username-Password-Authentication' },
    json: true };

    request(options, function (error, response, body) {
        
    });

    this.auth.logout();
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

