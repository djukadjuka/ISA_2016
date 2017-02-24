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
  public isManager : boolean = true;
  public isDeliverer : boolean = false;

  public userId : String = "1";
  public userEmail : String = "";


  constructor() { }

}
