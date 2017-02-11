import { Injectable } from '@angular/core';

@Injectable()
export class SharedService {

  //Data about currently logged user
  public isAdmin : boolean = true;
  public isChef : boolean = false;
  public isCustomer : boolean = true;
  public isBartender : boolean = false;
  public isWaiter : boolean = false;
  public isSocialAccount : boolean = true;

  public userId : String = "4";
  public userEmail : String = "";


  constructor() { }

}
