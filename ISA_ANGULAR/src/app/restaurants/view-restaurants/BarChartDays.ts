export class BarChartDays{
            data:any;
            constructor(labs : any,dta : any){
                     this.data = {
                                 labels:labs,
                                 datasets:[
                                             {
                                                         label: 'Attendance this Day',
                                                         backgroundColor: '#9CCC65',
                                                         borderColor: '#7CB342',
                                                         data : dta
                                             }
                                 ] 
                     }   
            }
}