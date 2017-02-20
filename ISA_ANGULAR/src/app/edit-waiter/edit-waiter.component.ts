import { Component, OnInit } from '@angular/core';
import { TablesClass } from '../tables/tables-class';
import { TablesService } from '../tables/tables.service';

@Component({
  selector: 'app-edit-waiter',
  templateUrl: './edit-waiter.component.html',
  styleUrls: ['./edit-waiter.component.css']
})
export class EditWaiterComponent implements OnInit {


   private displayScheduleButton: boolean = false;
   private showTablesButton: boolean = false ;

   tables: TablesClass[];

  constructor(
 private tablesService: TablesService

  ) { }

  ngOnInit() {
    
  }

  displaySchedule(){

    this.displayScheduleButton = true;


  }
  showTables(){
    this.tablesService.getTables(1).subscribe(
      res => {
        this.tables = res;
      }
    );

    this.showTablesButton = true;

  }

}
