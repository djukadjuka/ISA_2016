import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import {ViewRestaurantsService} from './view-restaurants.service';
import {NgForm, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {RestaurantClass} from '../view-restaurants/restaurant-class';
import {RestaurantZone} from '../view-restaurants/zone-class';
import {ProductService} from '../../products/products.service';
import {ProductClass} from '../../products/product-class';
import {SelectItem} from 'primeng/primeng';
import {Message} from 'primeng/primeng';
import {MenuItem} from 'primeng/primeng';
import {OverlayPanel} from 'primeng/primeng';
import { SharedService } from '../../shared/shared.service';
import {ConfirmationService} from 'primeng/primeng';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';
import {BarChartDays} from './BarChartDays';
import {BarChartWeeks} from './BarChartWeeks';
import {BarChartDaysMoney} from './BarChartDaysMoney';
import {UIChart} from 'primeng/primeng'
import {GMapOptions} from './GMapOptions'
import { Observable } from 'rxjs/Rx';
//import {Observable} from 'rxjs/Observable';

declare var google: any;

@Component({
  selector: 'app-view-restaurants',
  templateUrl: './view-restaurants.component.html',
  styleUrls: ['./view-restaurants.component.css']
})
export class ViewRestaurantsComponent implements OnInit{

  //things for presentation

    //images
    noImageFound : string = "/assets/pictures/no_image_found.jpg";
    uploadedPicture;
    
    //food types - cuisines
    selectedCuisines = [];
    availableCuisines : SelectItem[];

    //save update buttons
    splitButtonCommands;
    
    //restaurant types [fine dining,sports bar ...]
    restaurantTypes;

    //show editing panel
    editing = false;

    //editing panel constants
    editingDialogHeader : String;
    editingDialogName : String;
    editingDialogType : String;

  //all restaurants
  restaurants : RestaurantClass[];

  //filtering restaurants
  filteredRestaurants : RestaurantClass[];
  filterData = { restaurantName : "", restaurantType: ""};
  showFilterDialog = false;
  showFilterCancelButton = false;
  formFilter : FormGroup;

  //reservation for restaurant
  showReservationDialog = false;
  reservationTables = [];
  reservationSelectedTables = [];
  reservationSteps: MenuItem[] = [{label: "Step"}, {label: "Step"}, {label: "Step"}];
  reservation = { startDate : new Date, endDate : new Date, table_id : "", restaurantZone : "" };
  reservationZoneBackgroundImage = "";
  reservationZoneName = "";
  reservationActiveStep = 0;
  reservationLoadingBar = 10;
  formReservation : FormGroup;

  //Editing things
  editingRestaurant : RestaurantClass = new RestaurantClass();
  availableFood : any[] = [];
  availableDrinks : any[] = [];
  selectedFood : any[] = [];
  selectedDrinks : any[] = [];

  //all food and drinks
  foodProducts : ProductClass[] = [];
  drinkProducts : ProductClass[] = [];

  allFoodTypes : {};

  //restaurant ratings
  showRatingsDialog = false;
  ratingsTitle = "";
  @ViewChild('chart') chart : UIChart;

  ratings = {
            labels: ['Restaurant', 'Food', 'Service'],
            datasets: [
                {
                    label: 'Me',
                    backgroundColor: '#42A5F5',
                    borderColor: '#1E88E5',
                    data: [1, 5, 3]
                },
                {
                    label: 'Friends',
                    backgroundColor: '#9CCC65',
                    borderColor: '#7CB342',
                    data: [5, 5, 4]
                },
                 {
                    label: 'Others',
                    backgroundColor: '#FFCE56',
                    borderColor: '#FFCE56',
                    data: [2, 3, 4]
                }
            ]
        }

  constructor(
    private viewRestaurantsService : ViewRestaurantsService, 
    private productService : ProductService,
    private _sharedService : SharedService,
    private _fb: FormBuilder,
    private _confirmationService : ConfirmationService)
    {
      this.viewRestaurantsService.getRestaurantAverageRateAll(1)
                                .subscribe(
                                  res => res
                                );
      this.allFoodTypes = {"Serbian":{"id":1,"name":"Serbian"},
        "Spanish":{"id":2,"name":"Spanish"},
        "Mexian":{"id":3,"name":"Mexian"},
        "Jamaican":{"id":4,"name":"Jamaican"},
        "Italian":{"id":5,"name":"Italian"},
        "Chinese":{"id":6,"name":"Chinese"},
        "Japanese":{"id":7,"name":"Japanese"},
        "Indian":{"id":8,"name":"Indian"},
        "US":{"id":9,"name":"US"},
        "British":{"id":10,"name":"British"},
        "Vietnamese":{"id":11,"name":"Vietnamese"},
        "All Seafood":{"id":12,"name":"All Seafood"},
        "All Oriental":{"id":13,"name":"All Oriental"}}
      this.availableCuisines = [  {label:'Serbian',value:'Serbian'},
                                  {label:'Spanish',value:'Spanish'},
                                  {label:'Mexian',value:'Mexian'},
                                  {label:'Jamaican',value:'Jamaican'},
                                  {label:'Italian',value:'Italian'},
                                  {label:'Chinese',value:'Chinese'},
                                  {label:'Japanese',value:'Japanese'},
                                  {label:'Indian',value:'Indian'},
                                  {label:'US',value:'US'},
                                  {label:'British',value:'British'},
                                  {label:'Vietnamese',value:'Vietnamese'},
                                  {label:'All Seafood',value:'All Seafood'},
                                  {label:'All Oriental',value:'All Oriental'}];

      this.splitButtonCommands = [{label:'Cancel Update',icon:'fa-close',command:()=>{
          this.cancelUpdate();
        }}];

      this.restaurantTypes = [{label:'Fine Dining', value: 'Fine Dining'},
                              {label:'Fast Food', value: 'Fast Food'},
                              {label:'Bistro', value: 'Bistro'},
                              {label:'Sports Bar', value: 'Sports Bar'},
                              {label:'Diner', value: 'Diner'}];
  }


   //get all restaurants from the database
   ngOnInit(){
     
     this.viewRestaurantsService.getRestaurants().subscribe(
        res =>{
          this.restaurants = res;
        }
      );
      this.productService.getAllProducts().subscribe(
        res =>{
          let allProducts = res;
          for(let i = 0;  i<allProducts.length; i++){

            if(allProducts[i].food) {
              this.foodProducts.push(allProducts[i]);
            }else{
              this.drinkProducts.push(allProducts[i]);
            }
          
          }
        }
      );

      //filter form builder
      this.formFilter = this._fb.group({
        restaurantName: [''],
        restaurantType: ['']
        });

      //reservation form builder
       this.formReservation = this._fb.group({
        startDate: ['', Validators.required],
        endDate: ['', Validators.required],
        restaurantZone: ['', Validators.required]
        });
   }

   google_maps_overlays : any[];
   handleDragEnd(event) {
      this.editingRestaurant.lng = this.google_maps_overlays[0].position.lng();
      this.editingRestaurant.lat = this.google_maps_overlays[0].position.lat();
    }
   //show dialog to edit a single restaurant
   editRestaurant(restaurant){

     //copy the selected restaurant for editing so i have it everywhere
     this.editingRestaurant.name = restaurant.name;
     this.editingDialogHeader = restaurant.name;
     this.editingRestaurant.id = restaurant.id;
     this.editingRestaurant.type = restaurant.type;
     this.editingRestaurant.foodTypes = restaurant.foodTypes;
     this.editingRestaurant.image = restaurant.image;
     this.editingRestaurant.lng = restaurant.lng;
     this.editingRestaurant.lat = restaurant.lat;

     //GOOGLE MAPS CONFIG
     this.google_map_options = new GMapOptions(this.editingRestaurant.lng, this.editingRestaurant.lat).options;
     this.google_maps_overlays =[
      new google.maps.Marker({position:{lat:this.editingRestaurant.lat,lng:this.editingRestaurant.lng},title:this.editingRestaurant.name,draggable:true})
     ];
     for(let item of this.editingRestaurant.foodTypes){
       this.selectedCuisines.push(item.name);
     }

     let foodMap = {};
     let drinksMap = {};
     this.availableFood = [];
     this.availableDrinks = [];
     this.selectedDrinks = [];
     this.selectedFood = [];
     
     //store all selected food into a map
     for(let item of restaurant.foodMenu){
       foodMap[item.id] = item;
     }
     //store all selected drinks into a map
     for(let item of restaurant.drinksMenu){
       drinksMap[item.id] = item;
     }

     //for every possible food item
     for(let item of this.foodProducts){
       //if it's in the map it goes in the selected items
       if(foodMap.hasOwnProperty(item.id)){
         this.selectedFood.push(item);
       //otherwise it goes into the available items
       }else{
         this.availableFood.push(item);
       }
     }
     //same for drinks
     for(let item of this.drinkProducts){
       if(drinksMap.hasOwnProperty(item.id)){
         this.selectedDrinks.push(item);
       }else{
         this.availableDrinks.push(item);
       }
     }

    this.editing = true;
   }

   google_map_options;

   saveUpdate(){
     this.editingRestaurant.drinksMenu = this.selectedDrinks;
     this.editingRestaurant.foodMenu = this.selectedFood;
    
     let k;

     for(let i=0; i<this.restaurants.length;  i++){
       if(this.restaurants[i].id == this.editingRestaurant.id){
         k = i;
         this.restaurants[i].name = this.editingRestaurant.name;
         this.restaurants[i].type = this.editingRestaurant.type;
         this.restaurants[i].lat = this.editingRestaurant.lat;
         this.restaurants[i].lng = this.editingRestaurant.lng;

         this.restaurants[i].drinksMenu = [];
         this.restaurants[i].foodMenu =[];
         this.restaurants[i].foodTypes = [];
         this.restaurants[i].zones = [];

         for(let j=0; j<this.editingRestaurant.drinksMenu.length;  j++){
           this.restaurants[i].drinksMenu.push(this.editingRestaurant.drinksMenu[j]);
         }

         for(let j=0; j<this.selectedCuisines.length; j++){
           this.restaurants[i].foodTypes.push(this.allFoodTypes[this.selectedCuisines[j]]);
         }

         for(let j=0; j<this.editingRestaurant.foodMenu.length;  j++){
           this.restaurants[i].foodMenu.push(this.editingRestaurant.foodMenu[j]);
         }

         break;
       }
     }

     //this.viewRestaurantsService.updateRestaurant(this.restaurants[k]).subscribe(
       //res=>{}
     //);
     this.viewRestaurantsService.basicRestaurantUpdate(this.restaurants[k]).subscribe(
       res=>{
         this.editing = false
       }
     );
   }

   cancelUpdate(){
     this.editing = false;
   }

   showChange(){
     
   }

   //zone functions and params
   editingZones = false;
   restaurantZones : RestaurantZone[];
   zonesRestaurant : RestaurantClass;

   zoneCrud = false;
   zoneCrudModel : RestaurantZone;

   zoneNames : SelectItem[];
   selectedZoneName : string;
   numberOfTables;
   tablesForX;

   editingZone = false;
   addingZone = false;

   tablesForXValid = false;
   numberOfTablesValid = false;

   growl: Message[] = [];

   editZones(restaurant : RestaurantClass){
     this.zoneNames = [{label:'Garden',value:'Garden'},{label:'Garden (Closed)',value:'Garden (Closed)'},
                       {label:'Smoking',value:'Smoking'},{label:'No Smoking',value:'No Smoking'},
                       {label:'Main Hall',value:'Main Hall'},{label:'Kids',value:'Kids'}];
     this.viewRestaurantsService.getZonesForRestaurant(restaurant).subscribe(res=>{
       this.restaurantZones = [];
       for(let item of res){
         if(item.deleted == 0){
           this.restaurantZones.push(item);
         }
       }
       
       this.editingDialogHeader = restaurant.name;
       this.zonesRestaurant = restaurant;

       this.editingZones = true;
     });
   }

   zoneSelected(event){
     if(this.addingZone==true){
       this.growl = [];
       this.growl.push({severity:'error',
                        summary:'Finish Adding',
                        detail:'You must first finish adding a new zone or cancel adding a new zone if you wish to edit an existing zone.'});
     }else if(this.editingZone==true){
       this.growl = [];
        this.growl.push({severity:'error',
                          summary:'Finish Editing',
                          detail:'You must first finish editing an existing zone or cancel editing if you wish to edit a different zone.'});
     
     }else{
      this.zoneCrudModel = event.data;
      this.selectedZoneName = this.zoneCrudModel.name;
      this.numberOfTables = this.zoneCrudModel.number_of_tables;
      this.tablesForX = this.zoneCrudModel.tables_for_x;

      this.numberOfTablesValid = true;
      this.tablesForXValid = true;
      this.editingZone = true;
      this.zoneCrud = true;
     }
   }
   confirmEdit(){
     this.zoneCrudModel.name = this.selectedZoneName;
     this.zoneCrudModel.number_of_tables = this.numberOfTables;
     this.zoneCrudModel.tables_for_x = this.tablesForX;
     this.viewRestaurantsService.updateZoneFIX(this.zoneCrudModel).subscribe(
       res=>{
          this.editingZone = false;
          this.zoneCrud = false;
       }
     )
   }

   addNewZone(){
     if(this.editingZone ==true){
        this.growl = [];
        this.growl.push({severity:'error',
                          summary:'Finish Editing',
                          detail:'You must first finish editing an existing zone or cancel editing if you wish to add a new zone.'});
     }else{
      let newZone = new RestaurantZone();

      newZone.restaurant = this.zonesRestaurant;
      this.zoneCrudModel = newZone;
      this.selectedZoneName = this.zoneNames[0].value;
      this.zoneCrudModel.name =this.zoneNames[0].value;
      this.numberOfTables = "";
      this.tablesForX = "";


      this.numberOfTablesValid=false;
      this.tablesForXValid=false;
      this.addingZone = true;
      this.zoneCrud = true;
     }
   }
   confirmAdd(){
     this.zoneCrudModel.name = this.selectedZoneName;
     this.zoneCrudModel.number_of_tables = this.numberOfTables;
     this.zoneCrudModel.tables_for_x = this.tablesForX;
     this.viewRestaurantsService.createZone(this.zoneCrudModel).subscribe(
       res=>{
          this.restaurantZones = [];
          for(let item of res){
            if(item.deleted == 0){
              this.restaurantZones.push(item);
            }
          }
          this.addingZone = false;
          this.zoneCrud = false;
       }
     );
   }

   deleteZone(){
     this.zoneCrudModel.deleted = 1;
     this.viewRestaurantsService.deleteZone(this.zoneCrudModel).subscribe(
       res=>{
         
          this.restaurantZones = [];
          for(let item of res){
            if(item.deleted == 0){
              this.restaurantZones.push(item);
            }
          }
          this.editingZone = false;
          this.zoneCrud = false;
       }
     )
   }

   cancelCrud(){
     this.addingZone = false;
     this.editingZone = false;
     this.zoneCrud = false;
   }

   numberRegex : RegExp = RegExp('^[0-9]+$');
   enteredNumberOfTables(){
     this.numberOfTablesValid = this.numberRegex.test(this.numberOfTables);
   }

   enteredTablesForX(){
     this.tablesForXValid = this.numberRegex.test(this.tablesForX);
   }
   closeZonePanel(){
     this.editingZones = false;
   }

   //ratings restaurant fuctions ************************************************************************

   restaurantRatings(restaurant)
   {

         Observable.forkJoin([
           this.viewRestaurantsService.getRestaurantAverageRateMe(restaurant.id, this._sharedService.userId),
           this.viewRestaurantsService.getFoodAverageRateMe(restaurant.id, this._sharedService.userId),
           this.viewRestaurantsService.getWaiterAverageRateMe(restaurant.id, this._sharedService.userId),

           this.viewRestaurantsService.getRestaurantAverageRateFriends(restaurant.id, this._sharedService.userId),
           this.viewRestaurantsService.getFoodAverageRateFriends(restaurant.id, this._sharedService.userId),
           this.viewRestaurantsService.getWaiterAverageRateFriends(restaurant.id, this._sharedService.userId),

           this.viewRestaurantsService.getRestaurantAverageRateAll(restaurant.id),
           this.viewRestaurantsService.getFoodAverageRateAll(restaurant.id),
           this.viewRestaurantsService.getWaiterAverageRateAll(restaurant.id),
           ])
        .subscribe(data => {

              this.ratings.datasets[0].data[0] = data[0];
              this.ratings.datasets[0].data[1] = data[1];
              this.ratings.datasets[0].data[2] = data[2];

              this.ratings.datasets[1].data[0] = data[3];
              this.ratings.datasets[1].data[1] = data[4];
              this.ratings.datasets[1].data[2] = data[5];

              this.ratings.datasets[2].data[0] = data[6];
              this.ratings.datasets[2].data[1] = data[7];
              this.ratings.datasets[2].data[2] = data[8];

              this.chart.refresh();

              this.ratingsTitle = restaurant.name;

              this.showRatingsDialog = true;
        });
   }

   //filtering restaurants functions *********************************************************************

   filterRestaurants()
   {
     if(this.formFilter.value.restaurantName.trim() == "" && this.formFilter.value.restaurantType == "")
     {
        this.growl = [];
        this.growl.push({severity:'error',
                          summary:'Can\'t apply an empty filter!',
                          detail:'Please choose some filtering options.'});
     }
     else
        this.viewRestaurantsService.filterRestaurants(this.filterData)
                                .subscribe(
                                  res => {
                                      this.showFilterDialog = false;
                                      this.showFilterCancelButton = true;
                                      this.restaurants = res;
                                  }
                                );
   }

   cancelFilter()
   {
      this.viewRestaurantsService.getRestaurants()
                                .subscribe(
                                  res => {
                                      this.showFilterCancelButton = false;
                                      this.restaurants = res;
                                  }
                                );
   }

   restaurantReservation(restaurant)
   {
        this.viewRestaurantsService.getZonesForRestaurant(restaurant)
                                    .subscribe(
                                        res=>
                                        {
                                            this.restaurantZones = [];
                                                for(let item of res){
                                                  if(item.deleted == 0){
                                                    this.restaurantZones.push(item);
                                                  }
                                            
                                            this.showReservationDialog=true;
                                        }
                                    });
   }

   reservationReset()
   {
        this.reservationTables = [];
        this.reservationSelectedTables = [];
        this.reservationZoneBackgroundImage = "";
        this.reservationZoneName = "";
        this.reservationActiveStep = 0;
        this.reservationLoadingBar = 10;
   }

   //reservation steps functions

   reservationStep1()
   {
        this.reservation.endDate.setFullYear(this.reservation.startDate.getFullYear());
        this.reservation.endDate.setMonth(this.reservation.startDate.getMonth());
        this.reservation.endDate.setDate(this.reservation.startDate.getDate());

        //ako si odabrao datum u proslosti
        let date = new Date();
        let time = date.getTime() + 1800000;

        if(this.reservation.startDate.getTime() < time)
        {
              this.growl = [];
              this.growl.push({severity:'error',
                                summary:'Wrong start time!',
                                detail:'Reservation has to be made at least 30 minutes earlier'});
              return;
        }

        if(this.reservation.endDate.getTime() - this.reservation.startDate.getTime() < 1800000)
        {
              this.growl = [];
              this.growl.push({severity:'error',
                                summary:'Wrong end time!',
                                detail:'Your reservation can\'t end before it started and has to last at least 30 minutes.'});
        }
        else
        {
              this.reservationLoadingBar = 45;

             //za sad za zonu jedan, posle za zonu trenutno kliknutog restorana
            this.viewRestaurantsService.getAllTables(this.reservation.restaurantZone, this.reservation.startDate.getTime(), this.reservation.endDate.getTime())
                                  .subscribe(
                                    res => 
                                    {
                                      this.reservationTables = res;
                                      
                                      //set the proper background-image for selected restaurant zone
                                      for(let zone of this.restaurantZones)
                                      {
                                          if(zone.id == this.reservation.restaurantZone)
                                          {
                                              switch(zone.name)
                                              {
                                                  case "Garden":
                                                      this.reservationZoneName = "Garden";
                                                      this.reservationZoneBackgroundImage = "url(../../assets/pictures/restaurant_pictures/gardenGray.jpg)";
                                                      break;
                                                  case "Garden (Closed)":
                                                      this.reservationZoneName = "Garden (Closed)";
                                                      this.reservationZoneBackgroundImage = "url(../../assets/pictures/restaurant_pictures/garden.jpg)";
                                                       break;
                                                  case "Smoking":
                                                      this.reservationZoneName = "Smoking Area";
                                                      this.reservationZoneBackgroundImage = "url(../../assets/pictures/restaurant_pictures/smokingArea.png)";
                                                       break;
                                                  case "No Smoking":
                                                      this.reservationZoneName = "No Smoking Area";
                                                      this.reservationZoneBackgroundImage = "url(../../assets/pictures/restaurant_pictures/nonSmokingArea.jpg)";
                                                       break;
                                                  case "Main Hall":
                                                      this.reservationZoneName = "Main Hall";
                                                      this.reservationZoneBackgroundImage = "url(../../assets/pictures/restaurant_pictures/blackAndWhiteFloor.png)";
                                                       break;
                                                  case "Kids":
                                                      this.reservationZoneName = "Kids Area";
                                                      this.reservationZoneBackgroundImage = "url(../../assets/pictures/restaurant_pictures/kids.jpg)";
                                                       break;   
                                              }
                                              break;
                                          }
                                      }
                                      //change to next step (Step 2)
                                      this.reservationActiveStep = 1;
                                    }
                                  );
        }
   }

   reservationBackStep1()
   {
        this.reservationActiveStep = 0;
        this.reservationSelectedTables = [];
        this.reservationLoadingBar = 10;
   }

   reservationStep2()
   {
      this._confirmationService.confirm({
              header: 'Confirm reservation',
              message: 'Are you sure you want to reserve these tables? Reservation will be made after confirming this step.',
              accept: () => {
                  

                  this.growl = [];

                  let atLeastOneSelected = false;

                  //uzmi reservationSelectedTables i prodji kroz njih i dodaj id-eve stolova u listu sa startDate endDate
                  for(let i = 0; i < this.reservationSelectedTables.length; i++)
                  {
                      if(this.reservationSelectedTables[i].selected == true)
                      {
                          atLeastOneSelected = true;

                          this.reservation.table_id = this.reservationSelectedTables[i].id;

                          let temp = {startDate : this.reservation.startDate.getTime(), endDate : this.reservation.endDate.getTime(), table_id : this.reservation.table_id, originator : this._sharedService.userId};

                           this.viewRestaurantsService.makeReservation(temp)
                                      .subscribe(
                                          res => {
                                             
                                             if(res == true)
                                             {
                                                  this.growl.push({severity:'success', summary:'Successful reservation for table '+ temp.table_id, detail:''});
                                             }
                                             else
                                             {
                                                  this.growl.push({severity:'error', summary:'Sorry, table '+ temp.table_id + ' is already taken!', detail:''});
                                             }
                                         }
                                      );
                      }
                  }

                  // ni jedan sto nije selektovan, a korisnik pokusava da nastavi rezervaciju
                  if(!atLeastOneSelected)
                  {
                      this.growl = [];
                      this.growl.push({severity:'error', summary:'You haven\'t selected any tables!'});
                      return ;
                  }

                  this.reservationLoadingBar = 100;
                  this.reservationActiveStep = 2;
              }
          });
   }

   reservationBackStep2()
   {
        this.reservationSelectedTables = [];
        this.reservationLoadingBar = 45;
        this.reservationStep1();
        this.reservationActiveStep = 1;
   }

   reservationFinish()
   {
      this.showReservationDialog = false;
      this.reservationReset();
   }

   //restaurant table reservation
   tableClicked(event)
   {
     
      if(event.target.style.borderColor === "red")
      {
            this.growl = [];
            this.growl.push({severity:'error',
                              summary:'Sorry, that table is taken!',
                              detail:'Try choosing the free ones.'});
            return;
      }

      if(event.target.style.borderColor === "black")
      {
          event.target.style.borderColor = "green";
          // add to list if doesn't exist already
          let tableJson = {"id" : event.target.id, "selected" : true};
          let exists = false;

          for(let i=0; i < this.reservationSelectedTables.length; i++)
          {
              if(this.reservationSelectedTables[i].id == event.target.id)
              {
                  this.reservationSelectedTables[i].selected = true;
                  
                  exists = true;
                  break;
              }
          }

          if(!exists)
            this.reservationSelectedTables.push(tableJson);
      }
      else
      {
          event.target.style.borderColor = "black";
          // remove from list 
          for(let i=0; i < this.reservationSelectedTables.length; i++)
          {
              if(this.reservationSelectedTables[i].id == event.target.id)
              {
                  this.reservationSelectedTables[i].selected = false;
                  break;
              }
          }
      }

      
   }

   tableBorderColor(table)
   {
       if(table.status == "FREE")
          return "black";
       else
          return "red";
   }

   ///////////////////////////////////////
   // IMAGE UPLOADING
   ///////////////////////////////////////
   uploadingPicture = false;
   showUploadImageDialog(id){
     this.uploadingPicture = true;
     this.uploadingId = id;
   }
   uploadImageBaby(event){
     this.uploadingPicture = false;
   }

   beforeSend(event){
     //console.log(event.xhr);
     //console.log(event.formData);
   }

   uploadingId;

   beforeUpload(event){
     this.viewRestaurantsService.prepForUpload(this.uploadingId).subscribe(res=>res);
   }

/**********************-------------------------------------------------------
 * 2.3 NEW CONFIG
 */

    //global editing flag
    editing_something = false;
    //single editing flags
    creating_new_manager_open = false;
    creating_new_employee_open = false;
    creating_employee_schedule_open = false;
    creating_employee_region_open = false;
    creating_new_deliverer_open = false;
    creating_new_delivery_open = false;
    checking_delivery_notifications_open = false;
    checking_restaurant_statistics = false;
    restaurant_23 : RestaurantClass;
    /**VIEW FLAGS CONFIG*/
    check_visibility(){
      if( this.creating_employee_region_open == false &&
          this.creating_employee_schedule_open == false &&
          this.creating_new_deliverer_open == false &&
          this.creating_new_delivery_open == false &&
          this.creating_new_employee_open == false &&
          this.creating_new_manager_open == false &&
          this.checking_delivery_notifications_open == false &&
          this.checking_restaurant_statistics == false){
            this.editing_something = false;
          }else{
            this.editing_something = true;
          }
    }

    /**=============================
     * ADDING NEW MANAGER CONFIG
     * =============================*/
     //should be user list ...

     possible_managers; assigned_managers;
     create_new_manager_clicked(restaurant : RestaurantClass){
        this.restaurant_23 = restaurant;

        this.viewRestaurantsService.getFreeManagers_AndUserManagers(this._sharedService.userId,this.restaurant_23.id)
        .subscribe(res=>{
            this.possible_managers = res.free_users;
            this.assigned_managers = res.managers;
            this.creating_new_manager_open = true;
            this.check_visibility();
        });

     }

     moved_new_manager_to_target(event){
       let payload = this.create_manager_payload(event.items);
       this.viewRestaurantsService.createNewManagers(payload,this.restaurant_23.id).subscribe(
        res=>{}
      );
     }

     create_manager_payload(items){
      let payload = [];
      for(let idx in items){
        payload.push({id:items[idx].id,value:items[idx]});
      }
      return payload;
     }

     moved_existing_manager_to_source(event){
      let payload = this.create_manager_payload(event.items);
      this.viewRestaurantsService.fireSomeManagers(payload,this.restaurant_23.id).subscribe(
        res=>{}
      )
     }

     close_new_manager_panel(){

       this.creating_new_manager_open = false;
       this.check_visibility();
     }

    /**=======================================
     * ADDING NEW EMPLOYEE CONFIG
     =======================================*/

     //for pickboxes
     restaurant_workers;  free_workers; 
     //for data table
     free_users; 
     //selected dude for registration
     selected_user_for_work;
     //new employee object
     registering_employee;
     //dialog flag
     registering_new_employee; 
     //pick box options
     employee_roles : SelectItem[];
     registering_employee_dob : Date;

     //date things
     min_employee_date_of_birth : Date;
     max_employee_date_of_birth : Date;

     add_new_employee_clicked(restaurant : RestaurantClass){
        this.restaurant_23 = restaurant;
        this.employee_roles = [{label:'Waiter',value:'WAITER'},{label:'Cook',value:'COOK'},{label:'Bartender',value:'BARTENDER'}];
        
        this.min_employee_date_of_birth = new Date(0);
        this.max_employee_date_of_birth = new Date(new Date().getTime() - 1000*60*60*24*365*18);
        
        this.get_employee_information_packed();
     }

     selected_a_user_for_work(event){
      
       this.registering_employee = {};
       this.registering_employee.dateOfBirth = 0;
       this.registering_employee_dob = new Date(0);
       this.registering_employee.has_registered = null;
       this.registering_employee.id = this.selected_user_for_work.id;
       this.registering_employee.role = "WAITER";
       this.registering_employee.shoeSize = null;
       this.registering_employee.suitSize = null;
       this.registering_employee.user = this.selected_user_for_work;

       this.registering_new_employee = true;
     }

     fire_employee(worker){
       this.viewRestaurantsService.fireAnEmployee(worker.id).subscribe(
         res=>{
           
           this.get_employee_information_packed();
         }
       );
     }
     
     close_new_employee_dialog(){

       this.registering_employee.date_of_birth = this.registering_employee_dob.getTime();
     
       this.viewRestaurantsService.registerNewEmployee(this.registering_employee,this.restaurant_23.id).subscribe(
         res=>{
           
           this.get_employee_information_packed();
         }
       );
       this.registering_new_employee = false;
     }

/**CONTAINS REST CALL
 *  ----------------
 */
     get_employee_information_packed(){
      this.viewRestaurantsService.getWorkersAndUsersForEmployeeManagement(this.restaurant_23.id)
          .subscribe(res=>{
            //console.log(res);
            this.restaurant_workers = []; this.free_workers = []; this.free_users = [];
            //console.log("Not employed users : ");
            for(let person_id in res.not_employed_users){
              this.free_users.push(res.not_employed_users[person_id]);
            }
            //console.log("Your workers :");
            for(let person_id in res.employed_workers){
              this.restaurant_workers.push(res.employed_workers[person_id]);
            }
            //console.log("Not employed workers :");
            for(let person_id in res.not_employed_workers){
              this.free_workers.push(res.not_employed_workers[person_id]);
            }

            this.registering_employee = {};
            this.registering_employee.dateOfBirth = 0;
            this.registering_employee_dob = new Date(0);
            this.registering_employee.has_registered = null;
            this.registering_employee.id = null;
            this.registering_employee.role = "WAITER";
            this.registering_employee.shoeSize = 10;
            this.registering_employee.suitSize = 10;
            this.registering_employee.user = null;

            this.creating_new_employee_open = true;
            this.check_visibility();
          });
     }

     close_new_employee_panel(){

       this.creating_new_employee_open = false;
        this.check_visibility();
     }

    /**============================================
     * ADDING EMPLOYEE SCHEDULE CONFIG
     =========================================*/

     employee_usernames_schedule : SelectItem[]; schedule_employee;
     

     all_schedules_for_employee;

     //for dialog
     adding_new_shedule = false;

     //for schedz day
     min_schedz_day;
     max_schedz_day;
     new_schedz_day;
     schedz_time_start;
     schedz_time_end;
     min_time_end;

     chosen_table_id;

     available_tables_for_schedz : SelectItem[];

     edit_employee_schedule_clicked(restaurant : RestaurantClass){
        this.restaurant_23 = restaurant;
        this.viewRestaurantsService.getWorkersNOMANAGERSForRestaurant(this.restaurant_23.id).subscribe(
          res=>{
            this.employee_usernames_schedule = [];
            for(let item in res){
              this.employee_usernames_schedule.push({label:res[item].user.username,value:res[item]})
            }
            this.creating_employee_schedule_open = true;
            this.check_visibility();
          }
        );
     }

     schedule_selected(event){
       this.viewRestaurantsService.getSpecificWorkersSchedules(this.schedule_employee.id).subscribe(res=>{
         //console.log(res);
         this.all_schedules_for_employee = [];
         for(let item in res){
           let start_time = new Date(res[item].from);
           let end_time = new Date(res[item].to);
           let date = new Date(res[item].date);
           this.all_schedules_for_employee.push(
             {from:""+start_time.getHours() + " : " + start_time.getMinutes(),
               to:""+end_time.getHours() + " : " + end_time.getMinutes(),
               date:""+date.getDate()+" : "+ (date.getMonth()+1) + " : " + date.getFullYear(),
               id:res[item].id,
               table_id:res[item].table.id}
             );
         }
       });
     }
     remove_schedule(schedz){
        this.viewRestaurantsService.delete_schedule(schedz.id).subscribe(
          res=>{
            this.viewRestaurantsService.getSpecificWorkersSchedules(this.schedule_employee.id).subscribe(res=>{
              this.all_schedules_for_employee = [];
              for(let item in res){
                let start_time = new Date(res[item].from);
                let end_time = new Date(res[item].to);
                let date = new Date(res[item].date);
                this.all_schedules_for_employee.push(
                      {from:""+start_time.getHours() + " : " + start_time.getMinutes(),
                        to:""+end_time.getHours() + " : " + end_time.getMinutes(),
                        date:""+date.getDate()+" : "+ (date.getMonth()+1) + " : " + date.getFullYear(),
                        id:res[item].id}
                      );
                  }
                this.adding_new_shedule = false;
            });
          }
        )
     }

     /**when creating a new schedule in the schedz dialog */
     add_new_schedule_clicked(){
        //set current time for schedz start
        this.viewRestaurantsService.getTablesForRestaurant(this.restaurant_23.id).subscribe(res=>{
            this.available_tables_for_schedz = [];
            for(let idx in res){
              let item = res[idx];
              this.available_tables_for_schedz.push({label:item.id,value:item.id});
            }
            this.chosen_table_id = this.available_tables_for_schedz[0].value;
            this.schedz_time_start = new Date();
            
            //set current time + 1h for schedz end and min schedz
            this.schedz_time_end = new Date(this.schedz_time_start.getTime() + 1000*60*60);
            this.min_time_end = new Date(this.schedz_time_end.getTime());
            
            //set current date for the new schedz day
            this.new_schedz_day = new Date();
            //min max days one year apart
            this.min_schedz_day = new Date();
            this.max_schedz_day = new Date(new Date().getTime() + 1000*60*60*24*365);

            this.adding_new_shedule = true;
        });
     }
     changed_shedz_start(){
       this.schedz_time_end = new Date(this.schedz_time_start.getTime() + 1000*60*60);
     }
     changed_shedz_end(){
       let start = this.schedz_time_start.getTime();
       let end = this.schedz_time_end.getTime();
       if(end <= start){
         this.schedz_time_end = new Date(start + 1000*60*60);
       }
     }

     close_new_schedule_dialog(){
        let post_schedz = {
          from:this.schedz_time_start.getTime(),
          to:this.schedz_time_end.getTime(),
          date:this.new_schedz_day.getTime(),
          for_employee:this.schedule_employee,
          table:{id:this.chosen_table_id}
        };

        this.viewRestaurantsService.create_new_schedule(post_schedz).subscribe(
          res=>{
            this.viewRestaurantsService.getSpecificWorkersSchedules(this.schedule_employee.id).subscribe(res=>{
              this.all_schedules_for_employee = [];
              for(let item in res){
                let start_time = new Date(res[item].from);
                let end_time = new Date(res[item].to);
                let date = new Date(res[item].date);
                this.all_schedules_for_employee.push(
                      {from:""+start_time.getHours() + " : " + start_time.getMinutes(),
                        to:""+end_time.getHours() + " : " + end_time.getMinutes(),
                        date:""+date.getDate()+" : "+ (date.getMonth()+1) + " : " + date.getFullYear(),
                        id:res[item].id,
                        table_id:res[item].table.id}
                      );
                  }
                this.adding_new_shedule = false;
            });
          }
        );
     }

     close_new_employee_schedule(){

        this.creating_employee_schedule_open = false;
        this.check_visibility();
     }
    /**
     * REGISTER DELIVERER CONFIG
     */
    //posible deliverers
    idle_users_for_deliverer;

    //for the progress bar
    upgrading_to_deliverer_status : number = 0;

     register_new_deliverer_clicked(restaurant : RestaurantClass){
        this.restaurant_23 = restaurant;

        this.viewRestaurantsService.getPendingDeliverers().subscribe(
          res=>{
            this.idle_users_for_deliverer = res;
            this.creating_new_deliverer_open = true;
            this.check_visibility();
          }
        )

     }

     upgrade_to_deliverer(user){
       this.viewRestaurantsService.upgrade_to_deliverer(user).subscribe(res=>{
         this.viewRestaurantsService.getPendingDeliverers().subscribe(
          res=>{
            this.idle_users_for_deliverer = res;
          }
        )
       })
     }

     close_register_new_deliverer(){

       this.creating_new_deliverer_open = false;
       this.check_visibility();
     }

    /**
     * CREATE DELIVERY CONFIG
     */

    order_item_input_name;
    order_item_input_amount;
    order_amount_greater_than_zero = false;
    
    //order things
    order_with_items = [];
    fake_key = 0;

    //dates of the order
    order_date_from : Date; order_date_to : Date; order_dates_invalid;
    min_order_date : Date;

     create_new_delivery_clicked(restaurant : RestaurantClass){
        this.restaurant_23 = restaurant;
        this.fake_key = 0;
        this.order_item_input_name = "";
        this.order_item_input_amount = "";
        this.order_amount_greater_than_zero = false;
        this.creating_new_delivery_open = true;
        this.order_dates_invalid = true;
        this.order_with_items = [];
        this.check_visibility();
     }

     regexp = new RegExp('[1-9][0-9]*$');
     item_amount_changed(){
        this.order_amount_greater_than_zero = this.regexp.test(this.order_item_input_amount);
     }

     add_item_to_order_list(){
       this.order_with_items.push({item_name:this.order_item_input_name,item_amount:this.order_item_input_amount,key:this.fake_key+1});
       this.fake_key = this.fake_key+1;
       this.order_item_input_amount = "";
       this.order_item_input_name = "";
       this.order_amount_greater_than_zero = false;
     }
     remove_item(item){
       for(let it in this.order_with_items){
         if(this.order_with_items[it].key == item.key){
           this.order_with_items.splice(+it,1);
         }
       }
     }
     send_order(){
       //made by se salje u http zahtevu
       let order_api = {
         date_from:this.order_date_from.getTime(),
         date_to:this.order_date_to.getTime(),
         contains_items:this.order_with_items
       }
       let wrapper = {
         order:order_api,
         rest_id:this.restaurant_23.id,
         user_id:+this._sharedService.userId
       }
       //console.log(order_api);
       this.viewRestaurantsService.sendNewDelivery(wrapper).subscribe(
         res=>{
           this.order_with_items = [];
           this.fake_key = 0;
         }
       );
     }

     check_order_dates(){
        this.order_date_from.setHours(0);
        this.order_date_from.setMinutes(0);
        this.order_date_from.setSeconds(0);
        this.order_date_to = new Date(this.order_date_from.getTime() + 1000*60*60*24);
        this.min_order_date = new Date(this.order_date_to.getTime());
     }

     close_create_new_delivery(){

       this.creating_new_delivery_open = false;
       this.check_visibility();
     }

     /**
      * CHECK DELIVERY NOTIFICATIONS CONFIG
      */
      
      //all possible restaurant orders
      restaurant_orders;

      //restaurant orders for presentation
      order_presentation = [];

      //all bids for the other data table
      order_bids = [];

      //subscription to delivery orders;
      delivery_bid_subscription;

      check_delivery_notifications_clicked(restaurant : RestaurantClass){
        this.restaurant_23 = restaurant;

        this.viewRestaurantsService.getDeliveryOrdersForRestaurant(this.restaurant_23.id).subscribe(
          res=>{
            this.restaurant_orders = res;
            let curr_date = new Date();

            for(let order in this.restaurant_orders){ 
                let from = new Date(this.restaurant_orders[order].date_from);
                let to = new Date(this.restaurant_orders[order].date_to);
                let from_milis = from.getTime()
                let to_milis = to.getTime();

                for(let item in this.restaurant_orders[order].contains_items){

                  if(from_milis < curr_date.getTime() && to_milis > curr_date.getTime()){  
                    this.order_presentation.push({
                        belongs_to_order:this.restaurant_orders[order].id,
                        item_name:this.restaurant_orders[order].contains_items[item].item_name,
                        item_amount:this.restaurant_orders[order].contains_items[item].item_amount,
                        from: from.getDate() + "/" + (from.getMonth() + 1) + "/" + from.getFullYear(),
                        to: to.getDate() + "/" + (to.getMonth() + 1) + "/" + to.getFullYear()
                    });
                  }
                }
                //console.log(this.order_presentation);

            }

            this.checking_delivery_notifications_open = true;
            this.check_visibility();
          }
        )

      }

      /////////////////////////////////////
      /**REST CALL TO GET DELIVERY ORDERS */
      /////////////////////////////////////
      get_all_bids_for_order(data){
        this.viewRestaurantsService.getDeliveryBidsForDeliveryId(data).subscribe(
          res=>{
            this.order_bids = res;
          }
        )
      }

      check_bids(data){
        if(this.delivery_bid_subscription != null){
          this.delivery_bid_subscription.unsubscribe();
        }
        this.delivery_bid_subscription = Observable.timer(0,1000).subscribe(
          res=> this.get_all_bids_for_order(data) 
        )
      }

      accept_bid(bid){
       // console.log(bid);
        let payload = {
          bid_id:bid.id,
          order_id:bid.made_for_order.id,
          cash_accepted_id:bid.bidding_price
        };
        if(this.delivery_bid_subscription != null){
          this.delivery_bid_subscription.unsubscribe();
        }
        this.viewRestaurantsService.acceptBid(payload).subscribe(res=>{
          this.viewRestaurantsService.getDeliveryOrdersForRestaurant(this.restaurant_23.id).subscribe(
            res=>{
              this.restaurant_orders = res;
              let curr_date = new Date();
              this.order_presentation = [];
              this.order_bids = [];
              for(let order in this.restaurant_orders){ 
                  let from = new Date(this.restaurant_orders[order].date_from);
                  let to = new Date(this.restaurant_orders[order].date_to);
                  let from_milis = from.getTime()
                  let to_milis = to.getTime();

                  for(let item in this.restaurant_orders[order].contains_items){

                    if(from_milis < curr_date.getTime() && to_milis > curr_date.getTime()){  
                      this.order_presentation.push({
                          belongs_to_order:this.restaurant_orders[order].id,
                          item_name:this.restaurant_orders[order].contains_items[item].item_name,
                          item_amount:this.restaurant_orders[order].contains_items[item].item_amount,
                          from: from.getDate() + "/" + (from.getMonth() + 1) + "/" + from.getFullYear(),
                          to: to.getDate() + "/" + (to.getMonth() + 1) + "/" + to.getFullYear()
                      });
                    }
                  }
              }
            }
          )
        }
        )
      }

      close_check_delivery_notifications(){
        if(this.delivery_bid_subscription != null){
          this.delivery_bid_subscription.unsubscribe();
        }
        this.checking_delivery_notifications_open = false;
        this.check_visibility();
      }

      //================================================
      /**RESTAURANT STATISTICS */
      //================================================
      
      //all restaurant reviews
      all_restaurant_reviews;
      //restaurant grade
      restaurant_avg_grade;
      //restaurant food list;
      restaurant_food_list_items : SelectItem[];
      selected_23_food_item;
      //food item grades and reviews
      food_item_reviews;
      //all waiters
      restaurant_23_waiters : SelectItem[];
      //selected waiter for review
      selected_23_waiter;
      //waiter reviews
      selected_waiter_23_reviews;

      //for charts
      attendance_weeks : BarChartWeeks; attendance_days;
      //chart dates
      start_year = new Date().getFullYear();
      //dates for day to day attendance:
      date_day_from : Date = null; date_day_to : Date;
      min_day_to : Date;

      //constants:
      one_day_in_millis = 86400000;
      one_week_in_millis = 86400000 * 7;

      //when the panel is opened
      check_restaurant_statistics_clicked(restaurant){

        this.restaurant_23 = restaurant;
        this.restaurant_23_waiters = [];
        for(let item in this.restaurant_23.workers){
          if(this.restaurant_23.workers[item].role == "WAITER"){
            this.restaurant_23_waiters.push({label:this.restaurant_23.workers[item].user.username,
                                             value:this.restaurant_23.workers[item].user.id});
          }
        }

        this.restaurant_food_list_items = [];
        for(let item in this.restaurant_23.foodMenu){
          this.restaurant_food_list_items.push({label:this.restaurant_23.foodMenu[item].name,value:this.restaurant_23.foodMenu[item].id});
        }

        this.viewRestaurantsService.getAllRestaurantGrades(this.restaurant_23.id).subscribe(
          res=>{
           // console.log(res);
            this.all_restaurant_reviews = res;
            this.restaurant_avg_grade = 0;
            for(let item in this.all_restaurant_reviews){
              this.restaurant_avg_grade += this.all_restaurant_reviews[item].grade;
              if(this.all_restaurant_reviews[item].short_description == null){
                this.all_restaurant_reviews[item].short_description = "No Comment Available.";
              }
            }
            this.restaurant_avg_grade = this.restaurant_avg_grade / this.all_restaurant_reviews.length;
            this.checking_restaurant_statistics = true;
            this.check_visibility();
          }
        )
      }

      //when you select a different tab
      statistic_tab_change(event){
        let idx = event.index;
        if(idx == 0){
          this.viewRestaurantsService.getAllRestaurantGrades(this.restaurant_23.id).subscribe(
            res=>{
              this.all_restaurant_reviews = res;
              this.checking_restaurant_statistics = true;
              for(let item in this.all_restaurant_reviews){
                if(this.all_restaurant_reviews[item].short_description == null){
                  this.all_restaurant_reviews[item].short_description = "No Comment Available.";
                }
              }
              this.check_visibility();
            }
          );
        }else if(idx == 3){
          this.call_rest_for_week_attendance();
        }
      }

      //when selected different food item to show grade
      selected_food_list_item(event){
        this.viewRestaurantsService.getGradesForProductInRestaurant(this.restaurant_23.id,this.selected_23_food_item).subscribe(
          res=>{
          //  console.log(res);
            this.food_item_reviews = res;
            for(let item in this.food_item_reviews){
                if(this.food_item_reviews[item].short_description == null){
                  this.food_item_reviews[item].short_description = "No Comment Available.";
                }
              }
          }
        )
      }

      //when selecting different waiter to show grades
      selected_23_waiter_changed(event){
        this.viewRestaurantsService.getGradesForEmployee(this.selected_23_waiter).subscribe(
          res=>{
            this.selected_waiter_23_reviews = res;
            for(let item in this.selected_waiter_23_reviews){
                if(this.selected_waiter_23_reviews[item].short_description == null){
                  this.selected_waiter_23_reviews[item].short_description = "No Comment Available.";
                }
              }
          }
        )
      }

      //==============================================
      //DATE CHARTS CONFIG
      //==============================================
      //selecting the starting week deletes the end week, sets the minimum end week
      //and enables it for input
      year_weeks_changed(){
        this.call_rest_for_week_attendance();
      }

      call_rest_for_week_attendance(){
        this.attendance_weeks = null;
        this.viewRestaurantsService.getAttendanceForYear(this.start_year,this.restaurant_23.id).subscribe(
          res=>{
            let labs = [];
            let dta = [];
            for(let item in res){
              labs.push(item);
              dta.push(res[item]);
            }
            //console.log(dta);
            this.attendance_weeks = new BarChartWeeks(labs,dta,this.start_year).data;
            
          }
        )
      }

      //when a day from has been selected from the first datepicker
      selected_day_from(){
        this.date_day_from.setHours(0);
        this.date_day_from.setMinutes(0);
        this.date_day_from.setSeconds(0);

        this.date_day_to = null;
        this.min_day_to = new Date(this.date_day_from.getTime() + this.one_day_in_millis);
      }

      //when a day to has been selected from the second datepicker
      selected_day_to(){
        this.date_day_to.setMinutes(59);
        this.date_day_to.setHours(23);
        this.date_day_to.setSeconds(59);

        this.attendance_days = null;
        this.viewRestaurantsService.getAttendanceForDayPeriod(this.date_day_from.getTime(),this.date_day_to.getTime(),this.restaurant_23.id)
        .subscribe(res=>{
          //console.log(res);
          let labs = [];
          let dta = [];
          for(let item in res){
            labs.push(item);
          }
          labs = labs.sort((n1,n2)=>n1-n2);
          let labs2 = [];
          for(let item in labs){
            let tem = labs[item];
            let d = new Date(parseInt(tem)).getDate();
            let m = new Date(parseInt(tem)).getMonth() +1;
            let y = new Date(parseInt(tem)).getFullYear();
            let str = d + "/" + m + "/" + y;
            labs2.push(str);
            dta.push(res[tem]);
          }
          this.attendance_days = new BarChartDays(labs2,dta).data;
        });
      }

      //restaurant revinue;
      date_day_from_revinue : Date;
      date_day_to_revinue : Date;
      min_day_to_revinue : Date;
      revinue_chart;

      selected_day_from_revinue(){
        this.date_day_from_revinue.setHours(0);
        this.date_day_from_revinue.setMinutes(0);
        this.date_day_from_revinue.setSeconds(0);

        this.date_day_to_revinue = null;
        this.min_day_to_revinue = new Date(this.date_day_from_revinue.getTime() + this.one_day_in_millis);
      }

      selected_day_to_revinue(){
        this.date_day_to_revinue.setMinutes(59);
        this.date_day_to_revinue.setHours(23);
        this.date_day_to_revinue.setSeconds(59);

        this.revinue_chart = null;
        this.viewRestaurantsService.getRevinueForRestarurant(this.date_day_from_revinue.getTime(),this.date_day_to_revinue.getTime(),this.restaurant_23.id)
        .subscribe(res=>{
         // console.log(res);
          let labs = [];
          let dta = [];
          
          for(let day in res){
            let dd = parseInt(day);
            let d = new Date(dd).getDate();
            let m = new Date(dd).getMonth() + 1;
            let y = new Date(dd).getFullYear();
            let str = d+"/"+m+"/"+y;
            labs.push(str);
            dta.push(res[day]);
          }
          
          this.revinue_chart = new BarChartDaysMoney(labs,dta).data;
        });
      }

      selected_23_waiter_revinue;
      this_waiters_revinues;
      selected_23_waiter_changed_revinue(event){
        this.this_waiters_revinues = [];
        this.viewRestaurantsService.getBillsForEmployee(this.selected_23_waiter_revinue).subscribe(
          res=>{
            for(let item in res){
              let obj = res[item];
              let date = new Date(parseInt(obj.date_of_transaction));
              let d = date.getDate();
              let m = date.getMonth()+1;
              let y = date.getFullYear();
              this.this_waiters_revinues.push({
                bill_id:obj.id,
                on_date:d+"/"+m+"/"+y,
                money:obj.cash
              })
            }
          }
        )
      }

      //when panel is ultimatelly closed
      close_check_restaurant_statistics(){

        this.checking_restaurant_statistics = false;
        this.check_visibility();
      }

      ngOnDestroy() {
        if(this.delivery_bid_subscription!=null){
          this.delivery_bid_subscription.unsubscribe();
          this.delivery_bid_subscription = null;
        }
    }

    show_global_map;
    global_map_options;
    global_map_overlays : any[];
    show_location(restaurant){
      this.global_map_options = new GMapOptions(+restaurant.lng,+restaurant.lat).options;
      this.global_map_overlays = [
        new google.maps.Marker({position:{lat:restaurant.lat,lng:restaurant.lng},title:"Restaurants Position",draggable:false})
      ];
      this.show_global_map  = true;
    }

    handle_drag_end($event){

    }

}


    /**
     * ADDING EMPLOYEE REGION CONFIG
     */
    /*
     //all restaurants tables
     selected_restaurant_tables;
     //employee selected to view the data;
     employee_serving_data;
     //table selected to change server;

     //dialog about the server serving concrete table
     checking_table_server = false;
     //dialog about changing the employee serving a table
     changing_table_server = false;

     //employees that are not serving the selected table
     all_servers; all_servers_select_item:SelectItem[];selected_server_id;

     edit_employee_region_clicked(restaurant : RestaurantClass){
        this.restaurant_23 = restaurant;

        this.viewRestaurantsService.getTablesForRestaurant(this.restaurant_23.id).subscribe(
          res=>{
            this.selected_restaurant_tables = res;
            this.creating_employee_region_open = true;
            this.check_visibility();
          }
        )

     }

     //view details about employee serving this table
     view_table_serving(table){
        this.employee_serving_data = table.served_by;
        this.checking_table_server = true;
     }

     configuring_for_table;
     //change employee serving table
     change_table_serving(table){
       this.configuring_for_table = table;
      this.viewRestaurantsService.getWaitersForRestaurant(this.restaurant_23.id).subscribe(
        res=>{
          this.all_servers = res;
          this.all_servers_select_item = [];
          for(let server in this.all_servers){
            this.all_servers_select_item.push({label:this.all_servers[server].user.username,value:this.all_servers[server].id});
          }
          if(table.served_by == null){
            this.selected_server_id = null;
          }else{
            this.selected_server_id = table.served_by.id;
          }
          this.changing_table_server  = true;
        }
      )
     }

     //called after button is clicked changing the employee who serves table;
     //dialog should be closed after Save is clicked
     changed_table_server(){
      let selected_guy;
      for(let server in this.all_servers){
        if(this.all_servers[server].id == this.selected_server_id){
          selected_guy = this.all_servers[server];
        }
      }

      //who is selected to be the new server for this table...
      console.log("------------------------");
      console.log(this.configuring_for_table);
      console.log(selected_guy);
      this.viewRestaurantsService.change_served_by(selected_guy.id,this.configuring_for_table).subscribe(
        res=>{
          this.viewRestaurantsService.getTablesForRestaurant(this.restaurant_23.id).subscribe(
            res=>{
              this.selected_restaurant_tables = res;
              this.changing_table_server = false;
            }
          )
        }
      )
     }

     close_edit_employee_region(){

       this.creating_employee_region_open = false;
       this.check_visibility();
     }
*/