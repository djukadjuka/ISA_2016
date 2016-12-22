//Module imports 

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router'
import { routing } from './app.routing'

//Component imports

import { AppComponent } from './app.component';
import { TestComponent } from './test/test.component';

//Service imports

import { TestService } from './test/test.service';
import { NotFoundComponent } from './not-found/not-found.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { EditUserComponent } from './edit-user/edit-user.component'

@NgModule({
  declarations: [
    AppComponent,
    TestComponent,
    NotFoundComponent,
    NavbarComponent,
    HomeComponent,
    EditUserComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,
    routing
  ],
  providers: [
    TestService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
