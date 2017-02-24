export class BarChartDaysMoney{
            data:any;
            constructor(labs : any,dta : any){
                     this.data = {
                                 labels:labs,
                                 datasets:[
                                             {
                                                         label: 'Dollars this Day',
                                                         backgroundColor: '#9CCC65',
                                                         borderColor: '#7CB342',
                                                         data : dta
                                             }
                                 ] 
                     }   
            }
}