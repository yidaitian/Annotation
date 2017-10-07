var jDate = {
	dateOut:    function (format,time){
	    var today=new Date();
	    var day1=60*60*24*1000;
	    /*if (time!=null && time!=undefined && time>1){
	        today.setTime(time);
	    }*/
	    month=formatDate0(today.getMonth()+1);
	    year=formatDate0(today.getFullYear());
	    day=formatDate0(today.getDate());
	    if (time==-1){
	         day="01";
	    }
	    if (time==-2){ 
	        switch (today.getMonth())
	        {
	            case 1:
	                 day="28";
	                 if(year%400 ==0 || (year%100 != 0 && year%4 == 0)){
	                     day="29";
	                 } 
	                 break;
	            default :
	                day="31"; 
	                break;
	            case 3:
	                day="30";
	                break;
	            case 5:
	                day="30";
	                break;
	            case 8:
	                day="30";
	                break;
	            case 10:
	                day="30";
	                break;
	        }
	    }
	    if (time==-3){
	        month="01";
	        day="01";
	    }
	    if (time==-4){
	        month="12";
	        day="31";
	    }
	    if (time<-4 || time==1) {            
	        if (time==-5){
	            time=-3;
	        }
	           
	       //var  prevDay=today.getTime()-(60*60*24*1000*365*44);
	        var  prevDay=today.getTime()-(day1*Math.abs(time));
	       // alert(today.getTime()+".."+prevDay);
	        today.setTime(prevDay);
	        month=formatDate0(today.getMonth()+1);
	        year=formatDate0(today.getFullYear());
	        
	        //alert(Math.ceil(year/4));
	        day=formatDate0(today.getDate());
	        hour=formatDate0(today.getHours());
	        min=formatDate0(today.getMinutes());
	        sec=formatDate0(today.getSeconds());
	        //
	        //+".."+today.getTime()+",,,"+dateOut(false, prevDay));
	        //return this.dateOut(false, Math.abs(prevDay));
	    }
	    if (time==20 || time==21){            
	        month=today.getMonth();
	        year=today.getFullYear();
	        if ((month-1)<0){
	            month=12;                
	            year=today.getFullYear();
	            year--;
	        }            
	        day="01";
	        if (time==21){  
	            var n=month;
	            switch (n)
	            {
	               case 2:
	                   day="28";
	                   if(year%400 ==0 || (year%100 != 0 && year%4 == 0)){
	                       day="29";
	                   } 
	                   break;
	                default :
	                    day="31"; 
	                    break;
	                case 4:
	                    day="30";
	                    break;
	                case 6:
	                    day="30";
	                    break;
	                case 9:
	                    day="30";
	                    break;
	                case 11:
	                    day="30";
	                    break;
	            }
	       }
	        month=formatDate0(month);
	    }
	    if (time==365 || time==366){  
	        month="01";
	        year=today.getFullYear();
	        year--;   
	        day="01";
	        if (time==366){  
	            month="12";
	            day="31";
	         }
	    }
	            
	    hour=formatDate0(today.getHours());
	    min=formatDate0(today.getMinutes());
	    sec=formatDate0(today.getSeconds());
	    if (format==null && time==null){
	            return year+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0";
	    }
	    if (!format) {
	        return year+"-"+month+"-"+day;
	    }
	    if (format) {
	        return hour+":"+min+":"+sec+".0";
	    }
	}
};

function formatDate0(str){
    if (str<10) {
        str="0"+str;
    }
    return str;
}