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

  restaurantTypes;// String[] = ["Fine Dining","Fast Food","Bistro","Sports Bar","Diner"];
  restaurants : RestaurantClass[] ;
  editing = false;
  editingRestaurant : RestaurantClass = new RestaurantClass();
  editingDialogHeader : String;

  foodProducts : ProductClass[] = [];
  drinkProducts : ProductClass[] = [];

  availableFood : ProductClass[] = [];
  availableDrinks : ProductClass[] = [];
  selectedFood : ProductClass[] = [];
  selectedDrinks : ProductClass[] = [];

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
    this.editingRestaurant.name = restaurant.name;
    this.editingRestaurant.type = restaurant.type;
    this.editingRestaurant.id = restaurant.id;
    this.editingRestaurant.drinksMenu = restaurant.drinksMenu;
    this.editingRestaurant.foodMenu = restaurant.foodMenu;
    
    this.editingDialogHeader = restaurant.name;

    let selectedFoodMap = {};
    let selectedDrinksMap = {};

    this.availableFood = [];
    this.availableDrinks = [];
    this.selectedDrinks = [];
    this.selectedFood = [];

    //add food to selected food array and map
    for(let selectedFoodItem of this.editingRestaurant.foodMenu){
      selectedFoodMap[selectedFoodItem.id] = selectedFoodItem;
      this.selectedFood.push(selectedFoodItem);
    }

    //add drinks to selected drinks array and map
    for(let selectedDrinkItem of this.editingRestaurant.drinksMenu){
      selectedDrinksMap[selectedDrinkItem.id] = selectedDrinkItem;
      this.selectedDrinks.push(selectedDrinkItem);
    }

    //add all food to available food
    for(let availableFoodItem of this.foodProducts){
      if(!selectedFoodMap.hasOwnProperty(availableFoodItem.id)){
        this.availableFood.push(availableFoodItem);
      }
    } 

    //add all drinks to available drinks
    for(let availableDrinkItem of this.drinkProducts){
      if(!selectedDrinksMap.hasOwnProperty(availableDrinkItem.id)){
        this.availableDrinks.push(availableDrinkItem);
      }
    }

    this.editing = true;
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
