import {Router, RouterModule} from '@angular/router';

import { TestComponent } from './test/test.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { HomeComponent } from './home/home.component';

//Router is providing our application with different routes and controls
//the rendering/instantianting of the components depending of the route you hit

export const routing = RouterModule.forRoot([
    { path: '', component: HomeComponent },
    { path: '**', component: NotFoundComponent }
]);