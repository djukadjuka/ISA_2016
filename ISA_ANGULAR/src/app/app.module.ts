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
import { ViewRestaurantsComponent } from './restaurants/view-restaurants/view-restaurants.component';
import { EditChefComponent } from './edit-chef/edit-chef.component';
import { EditBarmanComponent} from './edit-barman/edit-barman.component';
import { EditWaiterComponent} from './edit-waiter/edit-waiter.component';

//PrimeNG imports

import {CalendarModule} from 'primeng/primeng';
import {DataGridModule} from 'primeng/primeng';
import {PanelModule} from 'primeng/primeng';
import {Header} from 'primeng/primeng';
import {Footer} from 'primeng/primeng';
import {ToolbarModule} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
import {InputTextModule} from 'primeng/primeng';
import {SelectButtonModule} from 'primeng/primeng';

//Service imports

import {ViewRestaurantsService} from './restaurants/view-restaurants/view-restaurants.service';
import { TestService } from './test/test.service';
import { EditUserService } from './edit-user/edit-user.service';
import { EditUserHelpService } from './edit-barman/edit-userhelp.service';
  

@NgModule({
  declarations: [
    AppComponent,
    TestComponent,
    NotFoundComponent,
    NavbarComponent,
    HomeComponent,
    EditChefComponent,
    EditBarmanComponent,
    EditUserComponent,
    EditWaiterComponent,
    ViewRestaurantsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,
    ReactiveFormsModule,
    CalendarModule,
    DataGridModule,
    PanelModule,
    ToolbarModule,
    ButtonModule,
    InputTextModule,
    SelectButtonModule,
    routing
  ],
  providers: [
    TestService,
    ViewRestaurantsService,
    EditUserService,
    EditUserHelpService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
