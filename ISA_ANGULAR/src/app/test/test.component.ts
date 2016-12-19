import { Component, OnInit } from '@angular/core';
import { TestService } from './test.service'

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  users = {};

  constructor(private testService : TestService ) { }

  ngOnInit() {
    
    this.testService.getUsers().subscribe(
                            res =>
                            {
                                this.users = res;
                                console.log(this.users);
                            }
                        );
  }

}
