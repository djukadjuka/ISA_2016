import { Injectable } from '@angular/core';

@Injectable()
export class SharedService {

  //Data about currently logged user
  public isAdmin : boolean = false;
  public isChef : boolean = false;
  public isCustomer : boolean = true;
  public isBartender : boolean = false;
  public isWaiter : boolean = false;
  public isSocialAccount : boolean = true;
  public isManager : boolean = false;
  public isDeliverer : boolean = true;

  public userId : String = "18";
  public userEmail : String = "";


  constructor() { }

}
