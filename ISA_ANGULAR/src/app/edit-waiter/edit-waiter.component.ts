import { Component, OnInit } from '@angular/core';
import { TablesClass } from '../tables/tables-class';
import { TablesService } from '../tables/tables.service';
import { Message } from 'primeng/primeng';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EditUserService } from '../edit-user/edit-user.service';
import { SharedService } from '../shared/shared.service';
import {ViewRestaurantsService} from '../restaurants/view-restaurants/view-restaurants.service'

@Component({
  selector: 'app-edit-waiter',
  templateUrl: './edit-waiter.component.html',
  styleUrls: ['./edit-waiter.component.css']
})
export class EditWaiterComponent implements OnInit {

  private displayScheduleeButton: boolean = false;
   private displayScheduleButton: boolean = false;
   private displayEditButton: boolean = false ;
   private showTablesButton: boolean = false ;
   private creating_new_order: boolean = false ; 
   private selected_food_button: boolean = false;
   private selected_drink_button: boolean = false ;
   private oznaka =0;
   user ;
   schedule = [];

   selected_food : any;
  selected_drink : any;
    private userUpdate = {username: "", id: ""};
    private msgs: Message[] = [];
    private formEditUser: FormGroup;
    private formEditSchedule: FormGroup;
     reservation = { Date : new Date};
  formReservation : FormGroup;
   tables: TablesClass[];

   

  constructor(
    private viewRestaurantsService : ViewRestaurantsService,
 private _editUserService: EditUserService,
    private tablesService: TablesService,
    private _fb: FormBuilder,
    private _sharedService : SharedService,

  ) { }

  ngOnInit() {

       this.formEditUser = this._fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      password : ['', Validators.required],
    
      
    });
    


    

    this._editUserService.getUserById(this._sharedService.userId)
      .subscribe(
      res =>  this.userUpdate=res
     );

      
      
    
      
   

     //reservation form builder
       this.formReservation = this._fb.group({
        Date: ['', Validators.required]
        });
    
  
      }

      allFoodProduct;
      allDrinkProduct;
      help_table;

      creatingOrder(tablesId){

        console.log(tablesId);
         this.food_show=[];
         this.help_table=tablesId;
          this.viewRestaurantsService.getAllProducts().subscribe(
      res => {

         this.allFoodProduct = [];
          for(let item in res){
            let id = tablesId;
           let name = res[item].name;
           let price = res[item].price;
           let p_type = res[item].type;
          

           

            this.allFoodProduct.push(
             { food_name:""+name,food_price:""+price, table_id:""+id,prod_type:""+p_type}
             );
       // this.schedule =res;
       
      }
      
      }   
    );


 this.viewRestaurantsService.getAllDrinks().subscribe(
      res => {

         this.allDrinkProduct = [];
          for(let item in res){
            let waiter = this.userUpdate.id;
             let id = tablesId;
           let name = res[item].name;
           let price = res[item].price;
          

           console.log(res);

            this.allDrinkProduct.push(
             { drink_name:""+name,drink_price:""+price,table_id:""+id,served_by:""+waiter}
             );
       // this.schedule =res;
       
      }
      
      }   
    );



          this.creating_new_order = true;




      }

 showEditDialog(){

    this.displayEditButton = true ; 
  }


  send_order_test(){

    console.log(this.help_table);


     let order_api = {
         cook_c_status:"1",
         cook_i_status:"1",
         order_status:"1",
         price:20,
         waiter_status:"1",
         contains_items:this.food_show
       }
       let wrapper = {
         order:order_api,
         rest_id:this.help_table,
         user_id:1
         
       }
       console.log(wrapper);
       
        this.viewRestaurantsService.createNewOrder(wrapper).subscribe(
         res=>{
         //  this.order_with_items = [];
          // this.fake_key = 0;
         }
       );

  }

  send_order(){
       
       
           


           this.food_show.push({
              item_name:this.selected_food.food_name,item_price:""+this.selected_food.food_price,
              table_id:""+this.selected_food.table_id,item_type:this.selected_food.prod_type

           });
            console.log(this.food_show);

  }


   all_schedules_for_employee;
   all_users;
   all_tables;

  displaySchedule(){

    this.displayScheduleButton = true;


  }
  displaySchedulee(){


      

    this.viewRestaurantsService.getScheduleByDate(this.reservation.Date.getTime()).subscribe(
      res => {

         this.all_schedules_for_employee = [];
          for(let item in res){
           let start_time = new Date(res[item].from);
           let end_time = new Date(res[item].to);
           let date = new Date(res[item].date);
           let fName = res[item].for_employee.user.firstName;
           let lName = res[item].for_employee.user.lastName
          

          // console.log(res);

            this.all_schedules_for_employee.push(
             { first_name:""+fName, last_name:""+lName,  from:""+start_time.getHours() + " : " + start_time.getMinutes(),
               to:""+end_time.getHours() + " : " + end_time.getMinutes(),
               date:""+date.getDate()+" : "+ (date.getMonth()+1) + " : " + date.getFullYear()}
             );
       // this.schedule =res;
       
      }
      
      }   
    );
       // console.log(this.schedule);
    this.displayScheduleeButton = true;



  }
  showTables(){
    this.tablesService.getTables(1).subscribe(
      res => {
        this.all_tables=[];
          console.log(res);
         for(let item in res){
              let servedBy = res[item].id;
              let image = res[item].image;
             

                

                this.all_tables.push(
                  { waiter:"" +servedBy , tImage:""+image

                  }
                )
          console.log(this.all_tables);


         }
           this.tables = res;

           
      }
    );

    this.showTablesButton = true;

  }

    updateUser()
  {
      this._editUserService.checkUsername(this.userUpdate.username, this.userUpdate.id)
                            .subscribe(
                              res => 
                              {
                                if(res) {
                                  this.msgs = [];
                                  this.msgs.push({severity:'error', summary:'That username is taken!', detail:'Choose another username.'});
                                  }
                                else
                                  this._editUserService.updateUser(this.userUpdate)
                                                        .subscribe(
                                                                 res => 
                                                                  {
                                                                    this.displayEditButton = false;
                                                                    this.user = this.userUpdate;
                                                                  }
                                                                );  
                                }
                            )           
  }

  food_show ; 

  selected_food_dialog(event){
       
      this.food_show=[];
       
           


           this.food_show.push({
              item_name:""+this.selected_food.food_name,item_price:""+this.selected_food.food_price,
              food_id:""+this.selected_food.table_id

           });
            console.log(this.food_show);
       

       this.selected_food_button = true;
     }

    drink_show;

     selected_drink_dialog(event){
       
      this.drink_show=[];
       
           


           this.drink_show.push({
              drink_name:""+this.selected_drink.drink_name,drink_price:""+this.selected_drink.drink_price,
              drink_id:""+this.selected_drink.table_id

           });
            console.log(this.drink_show);
       

       this.selected_drink_button = true;
     }

order;

      acceptShowOrderDialog()
  {
    this.oznaka= this.oznaka +1 ;
      
    console.log(this.selected_food.food_id);
   
      this.viewRestaurantsService.updateOrder(this.selected_food.food_name, this.selected_food.food_price, this.selected_food.table_id,1)
                            .subscribe(
                                res =>
                                {
                                    

                                    
                                    
                                      this.selected_food_button = false;
                                     
                                }
                            );
  }


}
