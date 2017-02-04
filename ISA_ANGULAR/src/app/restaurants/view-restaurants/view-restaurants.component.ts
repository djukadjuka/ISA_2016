import { Component, OnInit } from '@angular/core';
import {ViewRestaurantsService} from './view-restaurants.service';
import {NgForm} from '@angular/forms';
import {Restaurant} from '../view-restaurants/restaurant-interface';


@Component({
  selector: 'app-view-restaurants',
  templateUrl: './view-restaurants.component.html',
  styleUrls: ['./view-restaurants.component.css']
})
export class ViewRestaurantsComponent implements OnInit{

  restaurantTypes;// String[] = ["Fine Dining","Fast Food","Bistro","Sports Bar","Diner"];
  restaurants : Restaurant[] ;
  editing = false;
  editingName : String;
  editingType : String;
  editingId;

  constructor(private viewRestaurantsService : ViewRestaurantsService) {
      this.restaurantTypes = [];
      this.restaurantTypes.push({label:'Fine Dining', value: 'Fine Dining'});
      this.restaurantTypes.push({label:'Fast Food', value: 'Fast Food'});
      this.restaurantTypes.push({label:'Bistro', value: 'Bistro'});
      this.restaurantTypes.push({label:'Sports Bar', value: 'Sports Bar'});
      this.restaurantTypes.push({label:'Diner', value: 'Diner'});
  }

   ngOnInit(){
     
     this.viewRestaurantsService.getRestaurants().subscribe(
        res =>{
          //console.log(res);
          this.restaurants = res;
          console.log(this.restaurants);
        }
      );
   }

   editRestaurant(restaurant){
    this.editingName = restaurant.name;
    this.editingType = restaurant.type;
    this.editingId = restaurant.id;
    this.editing = true;

   }

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

}
