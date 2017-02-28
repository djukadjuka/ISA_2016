import { Injectable }      from '@angular/core';
import { tokenNotExpired, JwtHelper } from 'angular2-jwt';
import { myConfig }        from './auth.config';
import { Router } from '@angular/router';

//import { Auth0 } from 'auth0-lock';

//only for HashLocationStrategy
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
  lock = new Auth0Lock(myConfig.clientID, myConfig.domain, {});

  role : String = new String();

  jwtHelper : JwtHelper = new JwtHelper();

  constructor(public router: Router) {
    // Add callback for lock `authenticated` event
    this.lock.on('authenticated', (authResult) => {
      localStorage.setItem('id_token', authResult.idToken);
      
      this.lock.getProfile(authResult.idToken, function (err, profile) {
        if(err) {
          // handle error
          return;
        }

        alert('hello ' + profile.role);
        //Proveri zasto se ne prosledjuje XD, zasto je role 
        this.role = new String();
        this.role = "admin";
        alert(this.role);
        console.log(profile);
      });
          //alert("TOKEN " + this.jwtHelper.decodeToken(localStorage.getItem('id_token')));
          console.log(this.jwtHelper.decodeToken(localStorage.getItem('id_token')));
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
    this.router.navigateByUrl("/");
  };
}
