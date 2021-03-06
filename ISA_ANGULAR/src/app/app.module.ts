//Module imports 

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router'
import { routing } from './app.routing'
import { AgmCoreModule } from 'angular2-google-maps/core';

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
import { ReservationsComponent } from './reservations/reservations.component';

//PrimeNG imports

import {CalendarModule} from 'primeng/primeng';
import {DataGridModule} from 'primeng/primeng';
import {PanelModule} from 'primeng/primeng';
import {Header} from 'primeng/primeng';
import {Footer} from 'primeng/primeng';
import {TabViewModule} from 'primeng/primeng';
import {DialogModule} from 'primeng/primeng';
import {PasswordModule} from 'primeng/primeng';
import {ToolbarModule} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
import {SplitButtonModule} from 'primeng/primeng';
import {InputTextModule} from 'primeng/primeng';
import {SelectButtonModule} from 'primeng/primeng';
import {PickListModule} from 'primeng/primeng';
import {ConfirmDialogModule, ConfirmationService} from 'primeng/primeng';
import {GrowlModule} from 'primeng/primeng';
import {Message} from 'primeng/primeng';
import {AccordionModule} from 'primeng/primeng';
import {SharedModule} from 'primeng/primeng';
import {DataTableModule} from 'primeng/primeng';
import {ListboxModule} from 'primeng/primeng';
import {FileUploadModule} from 'primeng/primeng';
import {DropdownModule} from 'primeng/primeng';
import {TooltipModule} from 'primeng/primeng';
import {StepsModule} from 'primeng/primeng';
import {ProgressBarModule} from 'primeng/primeng';
import {MessagesModule} from 'primeng/primeng';
import {OverlayPanelModule} from 'primeng/primeng';
import {SliderModule} from 'primeng/primeng';
import {ChartModule} from 'primeng/primeng';
import {SpinnerModule} from 'primeng/primeng';
import {CheckboxModule} from 'primeng/primeng';
import {GMapModule} from 'primeng/primeng';
import {RatingModule} from 'primeng/primeng';
import {LightboxModule} from 'primeng/primeng';

//Service imports
import { Auth } from './auth.service';

import {ViewRestaurantsService} from './restaurants/view-restaurants/view-restaurants.service';
import { TestService } from './test/test.service';
import { EditUserService } from './edit-user/edit-user.service';
import { EditUserHelpService } from './edit-barman/edit-userhelp.service';
import { ProductService} from './products/products.service';
import { SharedService } from './shared/shared.service'; 
import { TablesService } from './tables/tables.service';
import {RestaurantRegistryService} from './restaurant_registry/restaurant-registry-service';
import { ReservationService } from './reservations/reservation.service';
import { InviteComponent } from './invite/invite.component';

//Auth0



import { PleaseService } from './shared/please.service';

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
    ViewRestaurantsComponent,
    ReservationsComponent,
    InviteComponent
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
    TabViewModule,
    DialogModule,
    PasswordModule,
    ToolbarModule,
    ButtonModule,
    InputTextModule,
    SelectButtonModule,
    PickListModule,
    ConfirmDialogModule,
    GrowlModule,
    AccordionModule,
    SharedModule,
    DataTableModule,
    SplitButtonModule,
    ListboxModule,
    FileUploadModule,
    DropdownModule,
    TooltipModule,
    StepsModule,
    ProgressBarModule,
    MessagesModule,
    OverlayPanelModule,
    SliderModule,
    ChartModule,
    SpinnerModule,
    CheckboxModule,
    GMapModule,
    AgmCoreModule.forRoot({
      apiKey:'AIzaSyBBKgIXEhpHGY3tdQIkKx0edit3QLMyZBw'
    }),
    RatingModule,
    LightboxModule,
    routing
  ],
  providers: [
      Auth,
    TestService,
    ViewRestaurantsService,
    EditUserService,
    EditUserHelpService,
    ProductService,
    ConfirmationService,
    SharedService,
    RestaurantRegistryService,
    TablesService,
    ReservationService,
    PleaseService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private _sharedService : SharedService){}

 }