import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-barman',
  templateUrl: './edit-barman.component.html',
  styleUrls: ['./edit-barman.component.css']
})
export class EditBarmanComponent implements OnInit {

  showEdit=false;



  constructor() { }

   showSchedule(event,restaurant){
     event.preventDefault();
     
    

     this.showEdit = true;
   }

  ngOnInit() {
  }

}
