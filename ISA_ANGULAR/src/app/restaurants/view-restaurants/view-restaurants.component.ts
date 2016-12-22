import { Component, OnInit } from '@angular/core';
import {ViewRestaurantsService} from './view-restaurants.service';
import {NgForm} from '@angular/forms';
@Component({
  selector: 'app-view-restaurants',
  templateUrl: './view-restaurants.component.html',
  styleUrls: ['./view-restaurants.component.css']
})
export class ViewRestaurantsComponent{

  restaurants;
  showEdit = false;
  selectedName = "";
  selectedType = "";
  selectedId = 0;


  constructor(private viewRestaurantsService : ViewRestaurantsService) {
      viewRestaurantsService.getRestaurants().subscribe(
        res =>{
          this.restaurants = res;
          console.log(this.restaurants);
        }
      );
   }


   changeRestaurantProfile(event,restaurant){
     event.preventDefault();
     
     this.selectedId = restaurant.id;
     this.selectedName = restaurant.name;
     this.selectedType = restaurant.type;

     this.showEdit = true;
   }

   saveChanges(event,name,type,id){
     event.preventDefault();
     var data = {name:name, type:type,id:id};
     console.log(data);
     this.viewRestaurantsService.updateRestaurant2(data).subscribe(
       res=>{
         console.log(res);
       }
     )
   }

}
