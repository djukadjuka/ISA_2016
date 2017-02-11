import { Component, OnInit } from '@angular/core';
import {ViewRestaurantsService} from './view-restaurants.service';
import {NgForm} from '@angular/forms';
import {RestaurantClass} from '../view-restaurants/restaurant-class';
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
    
    //zone editing
      //dialog visibity
      zoneDialogVisible = false;
      addingNewZone = false;
      showingZones : RestaurantZone[];

      //new zone
      newZone : RestaurantZone = new RestaurantZone();
      
      //seating zones
      zoneNames : SelectItem[];

      //all zones
      availableZones : {};

      //validation things
      amountOfTablesValid = false;
      tableForXValid = false;
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

  constructor(private viewRestaurantsService : ViewRestaurantsService, private productService : ProductService) {
      this.availableZones = {"Garden":{"id":1,"name":"Garden"},
        "Garden (Closed)":{"id":2,"name":"Garden (Closed)"},
        "Smoking":{"id":3,"name":"Smoking"},
        "No Smoking":{"id":4,"name":"No Smoking"},
        "Patio":{"id":5,"name":"Patio"},
        "Kids":{"id":6,"name":"Kids"},
        "Main Hall":{"id":7,"name":"Main Hall"}};
      this.zoneNames = [{label:"Garden",value:this.availableZones["Garden"]},
        {label:"Garden (Closed)",value:this.availableZones["Garden (Closed)"]},
        {label:"Smoking",value:this.availableZones["Smoking"]},
        {label:"No Smoking",value:this.availableZones["No Smoking"]},
        {label:"Patio",value:this.availableZones["Patio"]},
        {label:"Kids",value:this.availableZones["Kids"]},
        {label:"Main Hall",value:this.availableZones["Main Hall"]}];
      
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
     this.editingRestaurant.zones = restaurant.zones;

     this.showingZones = [];
     for(let i =0;  i<this.editingRestaurant.zones.length;  i++){
       if(this.editingRestaurant.zones[i].deleted == 0){
         this.showingZones.push(this.editingRestaurant.zones[i]);
       }
     }

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

   //opening dialog houskeeping
    //new zone
    newZoneDialog(){
      this.newZone = new RestaurantZone();
      this.newZone.zone_id.name = "Garden";
      this.newZone.zone_id.id = 1;
      this.amountOfTablesValid = false;
      this.tableForXValid = false;
      this.newZone.deleted = 0;

      this.addingNewZone = true;
      this.zoneDialogVisible = true;
    }

    //existing zone
    editZone(event){
      this.newZone = this.cloneZone(event.data);
      this.amountOfTablesValid = true;
      this.tableForXValid = true;

      this.addingNewZone = false;
      this.zoneDialogVisible = true;
    }

    //save newly created zone.
    addZone(){
      this.editingRestaurant.zones.push(this.newZone);
      this.showingZones = [];
      for(let i =0;  i<this.editingRestaurant.zones.length;  i++){
        if(this.editingRestaurant.zones[i].deleted == 0){
          this.showingZones.push(this.editingRestaurant.zones[i]);
        }
      }
      this.zoneDialogVisible=false;
    }

    //save exisiting edited zone
    saveZone(){
      for(let i = 0;  i<this.editingRestaurant.zones.length;  i++){
        if(this.newZone.id == this.editingRestaurant.zones[i].id){
          this.editingRestaurant.zones[i].zone_id.id = +this.newZone.zone_id.id;
          this.editingRestaurant.zones[i].zone_id.name = this.newZone.zone_id.name;
          this.editingRestaurant.zones[i].tableForX = +this.newZone.tableForX;
          this.editingRestaurant.zones[i].amountOfTables = +this.newZone.amountOfTables;
          break;
        }
      }
      this.showingZones = [];
      for(let i =0;  i<this.editingRestaurant.zones.length;  i++){
        if(this.editingRestaurant.zones[i].deleted == 0){
          this.showingZones.push(this.editingRestaurant.zones[i]);
        }
      }
      this.printZones();
      this.zoneDialogVisible=false;
    }
    //delete exisiting zone
    deleteZone(){
      for(let i = 0;  i<this.editingRestaurant.zones.length;  i++){
        
        if(this.newZone.id == null && this.editingRestaurant.zones[i].id == null){
          if( this.newZone.amountOfTables == this.editingRestaurant.zones[i].amountOfTables &&
              this.newZone.deleted == this.editingRestaurant.zones[i].deleted && 
              this.newZone.tableForX == this.editingRestaurant.zones[i].tableForX &&
              this.newZone.zone_id.id == this.editingRestaurant.zones[i].zone_id.id &&
              this.newZone.zone_id.name == this.editingRestaurant.zones[i].zone_id.id){
                this.editingRestaurant.zones[i].deleted = 1;
                break;
              }
        }
        
        if(this.newZone.id == this.editingRestaurant.zones[i].id){
          this.editingRestaurant.zones[i].deleted = 1;
          break;
        }
      }
      this.showingZones = [];
      for(let i =0;  i<this.editingRestaurant.zones.length;  i++){
        if(this.editingRestaurant.zones[i].deleted == 0){
          this.showingZones.push(this.editingRestaurant.zones[i]);
        }
      }
      this.printZones();
      this.zoneDialogVisible=false;
    }

   //validations
   tableForXChanged(){
     let reg = new RegExp('^[0-9]+$');
     this.tableForXValid = reg.test(this.newZone.tableForX);
   }

   amountOfTablesChanged(){
     let reg = new RegExp('^[0-9]+$');
     this.amountOfTablesValid = reg.test(this.newZone.amountOfTables);
   }

   //print helper
   printZones(){
     for(let item of this.editingRestaurant.zones){
       console.log("-------------------");
       console.log("Entity id : " + item.id);
       console.log("Zone id : " + item.zone_id.id);
       console.log("\tZone name : " + item.zone_id.name);
       console.log("\tZone table for : " + item.tableForX);
       console.log("\tAmount of tables : " + item.amountOfTables);
       console.log("\tTO DELETE : " + item.deleted);
     }
   }

   //clone helper
   cloneZone(zone : RestaurantZone){
     let clone = new RestaurantZone();
     clone.amountOfTables = zone.amountOfTables;
     clone.id = zone.id;
     clone.tableForX = zone.tableForX;

     clone.zone_id.name = zone.zone_id.name;
     clone.zone_id.id = zone.zone_id.id;
     clone.deleted = zone.deleted;

     return clone;
   }
}


class ZoneId{
  id;name;
}
class RestaurantZone{
  id;
  zone_id:ZoneId;
  tableForX;
  amountOfTables;
  deleted;
  constructor(){
    this.zone_id = new ZoneId();
  }
}