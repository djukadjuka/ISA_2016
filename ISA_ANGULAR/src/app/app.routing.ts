import {Router, RouterModule} from '@angular/router';

import { TestComponent } from './test/test.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { ViewRestaurantsComponent } from './restaurants/view-restaurants/view-restaurants.component';
import { EditChefComponent } from './edit-chef/edit-chef.component';
import { EditWaiterComponent } from './edit-waiter/edit-waiter.component';
import { EditBarmanComponent } from './edit-barman/edit-barman.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { InviteComponent } from './invite/invite.component';
import { SharedService } from './shared/shared.service';
//Router is providing our application with different routes and controls
//the rendering/instantianting of the components depending of the route you hit

export const routing = RouterModule.forRoot([
    { path: '', component: HomeComponent},
    { path: 'account', component: EditUserComponent, canActivate: [SharedService] },
    { path: 'reservations', component: ReservationsComponent, canActivate: [SharedService] },
    { path: 'invite/:keygen', component: InviteComponent, canActivate: [SharedService] },
    { path: 'restaurants', component: ViewRestaurantsComponent, canActivate: [SharedService] },
    { path: 'chef',component : EditChefComponent, canActivate: [SharedService] },
    { path: 'waiter',component : EditWaiterComponent, canActivate: [SharedService] },
    { path: 'barman',component : EditBarmanComponent, canActivate: [SharedService] },
    { path: '**', component: NotFoundComponent }
]);