import { Component, OnInit } from '@angular/core';
import {ViewRestaurantsService} from './view-restaurants.service';
import {NgForm} from '@angular/forms';
import {RestaurantClass} from '../view-restaurants/restaurant-class';
import {ProductService} from '../../products/products.service';
import {ProductClass} from '../../products/product-class';
import {SelectItem} from 'primeng/primeng';

@Component({
  selector: 'app-view-restaurants',
  templateUrl: './view-restaurants.component.html',
  styleUrls: ['./view-restaurants.component.css']
})
export class ViewRestaurantsComponent implements OnInit{

  //things for presentation
  selectedCuisines = [];
  availableCuisines : SelectItem[];
  splitButtonCommands;
  restaurantTypes;
  editing = false;
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
      this.allFoodTypes = {
        "Serbian":{"id":1,"name":"Serbian"},
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
        "All Oriental":{"id":13,"name":"All Oriental"},
      }
      this.availableCuisines = [];
      this.availableCuisines.push({label:'Serbian',value:'Serbian'});
      this.availableCuisines.push({label:'Spanish',value:'Spanish'});
      this.availableCuisines.push({label:'Mexian',value:'Mexian'});
      this.availableCuisines.push({label:'Jamaican',value:'Jamaican'});
      this.availableCuisines.push({label:'Italian',value:'Italian'});
      this.availableCuisines.push({label:'Chinese',value:'Chinese'});
      this.availableCuisines.push({label:'Japanese',value:'Japanese'});
      this.availableCuisines.push({label:'Indian',value:'Indian'});
      this.availableCuisines.push({label:'US',value:'US'});
      this.availableCuisines.push({label:'British',value:'British'});
      this.availableCuisines.push({label:'Vietnamese',value:'Vietnamese'});
      this.availableCuisines.push({label:'All Seafood',value:'All Seafood'});
      this.availableCuisines.push({label:'All Oriental',value:'All Oriental'});

      this.splitButtonCommands = [
        {label:'Cancel Update',icon:'fa-close',command:()=>{
          this.cancelUpdate();
        }}
      ];

      this.restaurantTypes = [];
      this.restaurantTypes.push({label:'Fine Dining', value: 'Fine Dining'});
      this.restaurantTypes.push({label:'Fast Food', value: 'Fast Food'});
      this.restaurantTypes.push({label:'Bistro', value: 'Bistro'});
      this.restaurantTypes.push({label:'Sports Bar', value: 'Sports Bar'});
      this.restaurantTypes.push({label:'Diner', value: 'Diner'});
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

}