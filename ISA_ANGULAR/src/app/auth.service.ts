import { Injectable }      from '@angular/core';
import { tokenNotExpired, JwtHelper } from 'angular2-jwt';
import { myConfig }        from './auth.config';
import { Router } from '@angular/router';
import { PleaseService } from './shared/please.service';

//import { Auth0 } from 'auth0-lock';

// only for HashLocationStrategy
// import { Router } from '@angular/router';
// import 'rxjs/add/operator/filter';

// constructor(public router: Router) {
//   this
//     .router
//     .events
//     .filter(event => event.constructor.name === 'NavigationStart')
//     .filter(event => (/access_token|id_token|error/).test(event.url))
//     .subscribe(() => {
//       this.lock.resumeAuth(window.location.hash, (error, authResult) => {
//         if (error) return console.log(error);
//         localStorage.setItem('id_token', authResult.idToken);
//         this.router.navigate(['/']);
//       });
//   });


// Avoid name not found warnings
declare var Auth0Lock: any;

@Injectable()
export class Auth {

  // Configure Auth0
  lock = new Auth0Lock(myConfig.clientID, myConfig.domain, {
       theme: {
          logo: "https://trello-logos.s3.amazonaws.com/876cba81a8058ae357c7c8ee7a886184/170.png"
       },
      languageDictionary: {
          title: "SoulFoodApp"
      },
      additionalSignUpFields: [{
      name: "name",                              // required
      placeholder: "enter your name",            // required
      // icon: "https://example.com/address_icon.png", // optional
      // validator: function(value) {                  // optional
        // only accept addresses with more than 10 characters
        // return value.length > 10;
      // }
    },{
      name: "lastname",
      placeholder: "enter your last name"
    }]
  });

  public userProfile : any;

  role : String = new String();

  jwtHelper : JwtHelper = new JwtHelper();
  //alert("TOKEN " + this.jwtHelper.decodeToken(localStorage.getItem('id_token')));
  //console.log(this.jwtHelper.decodeToken(localStorage.getItem('id_token')));

  constructor(public router: Router) {

    this.userProfile = JSON.parse(localStorage.getItem('profile'));

    // Add callback for lock `authenticated` event
    this.lock.on('authenticated', (authResult) => {
      localStorage.setItem('id_token', authResult.idToken);
      
      this.lock.getProfile(authResult.idToken, function (err, profile) {
        if(err) {
          // handle error
          return;
        }

        localStorage.setItem('profile', JSON.stringify(profile));
        //PleaseService.userInfo = this.userProfile;
      });
        });
  }
  

  public login() {
    // Call the show method to display the widget.
    this.lock.show();
  };

  public authenticated() {
    // Check if there's an unexpired JWT
    // It searches for an item in localStorage with key == 'id_token'
    return tokenNotExpired();
  };

  public logout() {
    // Remove token from localStorage
    localStorage.removeItem('id_token');
    localStorage.removeItem('profile');
    this.router.navigateByUrl("/");
  };
}
