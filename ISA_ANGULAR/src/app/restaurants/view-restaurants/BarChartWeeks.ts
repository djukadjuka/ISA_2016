export class BarChartWeeks{
            data:any;
            constructor(labs : any,dta : any, year : any){
                        this.data = {
                                    labels:labs,
                                    datasets:[
                                                {
                                                            label: 'Number of customers per Week in ' + year,
                                                            backgroundColor: '#42A5F5',
                                                            borderColor: '#1E88E5',
                                                            data : dta
                                                }
                                    ] 
                        }   
            }
}