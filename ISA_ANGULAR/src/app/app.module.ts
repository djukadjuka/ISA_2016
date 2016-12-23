//Module imports 

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router'
import { routing } from './app.routing'

//Component imports

import { AppComponent } from './app.component';
import { TestComponent } from './test/test.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { ViewRestaurantsComponent } from './restaurants/view-restaurants/view-restaurants.component'

//Service imports

import {ViewRestaurantsService} from './restaurants/view-restaurants/view-restaurants.service';
import { TestService } from './test/test.service';
import { EditUserService } from './edit-user/edit-user.service'

@NgModule({
  declarations: [
    AppComponent,
    TestComponent,
    NotFoundComponent,
    NavbarComponent,
    HomeComponent,
    EditUserComponent,
    ViewRestaurantsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,
    ReactiveFormsModule,
    routing
  ],
  providers: [
    TestService,
    ViewRestaurantsService,
    EditUserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
