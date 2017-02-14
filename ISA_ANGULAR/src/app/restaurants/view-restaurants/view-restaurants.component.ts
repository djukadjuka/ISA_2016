import { Component, OnInit } from '@angular/core';
import {ViewRestaurantsService} from './view-restaurants.service';
import {NgForm, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {RestaurantClass} from '../view-restaurants/restaurant-class';
import {RestaurantZone} from '../view-restaurants/zone-class';
import {ProductService} from '../../products/products.service';
import {ProductClass} from '../../products/product-class';
import {SelectItem} from 'primeng/primeng';
import {Message} from 'primeng/primeng';
import {Http,Headers,RequestOptions,RequestMethod,Request,Response} from '@angular/http';

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

  constructor(
    private viewRestaurantsService : ViewRestaurantsService, 
    private productService : ProductService,
    private _fb: FormBuilder) 
    {

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

   saveUpdate(){
     this.editingRestaurant.drinksMenu = this.selectedDrinks;
     this.editingRestaurant.foodMenu = this.selectedFood;
    
     let k;

     for(let i=0; i<this.restaurants.length;  i++){
       if(this.restaurants[i].id == this.editingRestaurant.id){
         k = i;
         this.restaurants[i].name = this.editingRestaurant.name;
         this.restaurants[i].type = this.editingRestaurant.type;

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

         for(let j=0; j<this.editingRestaurant.zones.length;  j++){
           if(this.editingRestaurant.zones[j].hasOwnProperty("_$visited")){
             console.log("has visited property.");
             delete this.editingRestaurant.zones[j]['_$visited'];
             console.log(this.editingRestaurant.zones[j]);
           }else{
             console.log(this.editingRestaurant.zones[j]);
           }
           this.restaurants[i].zones.push(this.editingRestaurant.zones[j]);
         }

         break;
       }
     }

     this.viewRestaurantsService.updateRestaurant(this.restaurants[k]).subscribe(
       res=>{}
     );

     this.editing = false;
   }

   cancelUpdate(){
     this.editing = false;
   }

   showChange(){
     console.log(this.selectedCuisines);
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
     this.viewRestaurantsService.updateZone(this.zoneCrudModel).subscribe(
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
          console.log(res);
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

   ///////////////////////////////////////
   // IMAGE UPLOADING
   ///////////////////////////////////////
   uploadingPicture = false;
   showUploadImageDialog(id){
     this.uploadingPicture = true;
     this.uploadingId = id;
   }
   uploadImageBaby(event){
     //show the file information
     // could do with just event.files[0] since it's only one file ....
     //console.log(event.files);
   }

   beforeSend(event){
     console.log(event.xhr);
     console.log(event.formData);
   }

   uploadingId;

   beforeUpload(event){
     this.viewRestaurantsService.prepForUpload(this.uploadingId).subscribe(res=>res);
    //READONLY //event.files[0].name = this.uploadingId + ".jpg";
     //console.log(event.files[0].name);
   }
}