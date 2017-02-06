import { Component, OnInit } from '@angular/core';
import {ViewRestaurantsService} from './view-restaurants.service';
import {NgForm} from '@angular/forms';
import {RestaurantClass} from '../view-restaurants/restaurant-class';
import {ProductService} from '../../products/products.service';
import {ProductClass} from '../../products/product-class';

@Component({
  selector: 'app-view-restaurants',
  templateUrl: './view-restaurants.component.html',
  styleUrls: ['./view-restaurants.component.css']
})
export class ViewRestaurantsComponent implements OnInit{

  //things for presentation
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

  constructor(private viewRestaurantsService : ViewRestaurantsService, private productService : ProductService) {
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

     console.log(this.editingRestaurant);
     for(let i=0; i<this.restaurants.length;  i++){
       if(this.restaurants[i].id == this.editingRestaurant.id){
         this.restaurants[i].name = this.editingRestaurant.name;
         this.restaurants[i].type = this.editingRestaurant.type;
           
         this.restaurants[i].drinksMenu = [];
         for(let j=0; j<this.editingRestaurant.drinksMenu.length; j++){
          this.restaurants[i].drinksMenu.push(this.editingRestaurant.drinksMenu[j]);  
         }

         this.restaurants[i].foodMenu = [];
         for(let j=0; j< this.editingRestaurant.foodMenu.length;  j++){
           this.restaurants[i].foodMenu.push(this.editingRestaurant.foodMenu[j]);
         }
         
       }
     }

     console.log(this.restaurants);
     this.editing = false;
   }

   cancelUpdate(){
     console.log(this.restaurants);

     this.editing = false;
   }

   //primed for deletion/edition
   /*
   saveChanges(event,name,type,id){
     event.preventDefault();
     var data = {id:parseInt(id),name:name, type:type};
     
     for(var i = 0;  i < this.restaurants.length;  i++){
       if(this.restaurants[i].id == data["id"]){
         //this.restaurants[i] = data;
         console.log("Found same.");
         break;
       }
     }
     console.log(this.restaurants);
     this.viewRestaurantsService.updateRestaurant(data).subscribe(
       res=>{}
     )

   }
   */
}
