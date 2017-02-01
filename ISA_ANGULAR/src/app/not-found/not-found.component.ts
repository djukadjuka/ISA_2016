import { Component, OnInit } from '@angular/core';
import {CalendarModule} from 'primeng/primeng';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.css']
})
export class NotFoundComponent implements OnInit {

  value: Date;

  constructor() { }

  ngOnInit() {
  }

}
