import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-chef',
  templateUrl: './edit-chef.component.html',
  styleUrls: ['./edit-chef.component.css']
})
export class EditChefComponent implements OnInit {


  private displayScheduleButton: boolean =false;
  constructor() { }

  ngOnInit() {
  }


  displaySchedule(){
    this.displayScheduleButton = true;

  }
}
