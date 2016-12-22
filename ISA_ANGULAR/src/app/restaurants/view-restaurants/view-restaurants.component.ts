import { Component, OnInit } from '@angular/core';
import {ViewRestaurantsService} from './view-restaurants.service';

@Component({
  selector: 'app-view-restaurants',
  templateUrl: './view-restaurants.component.html',
  styleUrls: ['./view-restaurants.component.css']
})
export class ViewRestaurantsComponent implements OnInit {

  restaurants = {};

  constructor(private viewRestaurantsService : ViewRestaurantsService) { }

  ngOnInit() {
    this.viewRestaurantsService.getRestaurants().subscribe(
      res=>
      {
        this.restaurants = res;
        console.log(this.restaurants);
      }
    )
  }

}
