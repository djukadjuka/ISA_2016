import { Component, OnInit } from '@angular/core';
import {ViewRestaurantsService} from './view-restaurants.service';
import {NgForm} from '@angular/forms';
@Component({
  selector: 'app-view-restaurants',
  templateUrl: './view-restaurants.component.html',
  styleUrls: ['./view-restaurants.component.css']
})
export class ViewRestaurantsComponent implements OnInit{

  restaurants;
  showEdit = false;
  selectedName = "";
  selectedType = "";
  selectedId = 0;


  constructor(private viewRestaurantsService : ViewRestaurantsService) {
      
   }

   ngOnInit(){
     this.viewRestaurantsService.getRestaurants().subscribe(
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

     this.showEdit = !this.showEdit;
   }

   saveChanges(event,name,type,id){
     event.preventDefault();
     var data = {id:parseInt(id),name:name, type:type};
     
     for(var i = 0;  i < this.restaurants.length;  i++){
       if(this.restaurants[i].id == data["id"]){
         this.restaurants[i] = data;
         console.log("Found same.");
         break;
       }
     }
     console.log(this.restaurants);
     this.viewRestaurantsService.updateRestaurant(data).subscribe(
       res=>{}
     )

     this.showEdit = !this.showEdit;
   }

}
