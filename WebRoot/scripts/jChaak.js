var dbs=["db_Date","db_PatientId","db_lastModified","db_misName","db_Name","db_ApptNo","db_Age","db_Sex", "db_bedId", "db_modality", "db_rptId","db_appRoom",
"db_CInfo","db_Exam","db_Equip","db_refP","db_aDiag","db_misId","db_bDiag","db_cDiag","db_fromImg","db_result","db_purpose", "db_birth", "db_scanRoom","db_HisId"];

var jChaak={
    overlay: function(objId){ 
        setTimeout(function(){
            $("<div id='overlay_"+objId+"'></div>").css({
                "background-color":"white",
                position:"absolute",
                opacity:.2,
                "z-index":900,
                left:$("#"+objId).position().left+"px",
                top:$("#"+objId).position().top+"px",
                width:$("#"+objId).width()+"px",
                height:$("#"+objId).height()+"px"
            }).appendTo("body").fadeIn(200);
        },2000);
    },
    opens:  function (oj){
        oj.select();
        $("#"+oj.id).autocomplete( "search");
    },
    showhint: function (ele,msg,load){
        $("#tooltip").remove();
        if (msg!=null && msg!=""){
            showTooltip(getPos(ele).x, (getPos(ele).y+ele.offsetHeight),msg,load);
        }
    // alert(ele.parentNode.parentNode.childNodes[0].firstChild.data);
    },
    replacex:    function (str,reg,ch){
        if (str==undefined) return "";
        var tmp=str;
        str=tmp.replace(reg,ch);

        //alert(str);
        return str;
    },
    WindowSize:    function (inSize) {
        var myWidth = 0, myHeight = 0;
        if( typeof( window.innerWidth ) == 'number' ) {
            //Non-IE
            myWidth = window.innerWidth;
            myHeight = window.innerHeight;
        } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
            //IE 6+ in 'standards compliant mode'
            myWidth = document.documentElement.clientWidth;
            myHeight = document.documentElement.clientHeight;
        } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
            //IE 4 compatible
            myWidth = document.body.clientWidth;
            myHeight = document.body.clientHeight;
        }
        inSize=(inSize=="w")?myWidth:inSize;
        inSize=(inSize=="h")?myHeight:inSize;
        return inSize;
    },
    getCookie:    function (c_name){
        if (document.cookie.length>0)
        {
            c_start=document.cookie.indexOf(c_name + "=");//alert(document.cookie);
            if (c_start!=-1)
            {
                c_start=c_start + c_name.length+1;
                c_end=document.cookie.indexOf(";",c_start);
                if (c_end==-1) c_end=document.cookie.length;
                var mystr=unescape(document.cookie.substring(c_start,c_end));
                if ((mystr[0]=="\"")&&(mystr[mystr.length-1]=="\"")) {
                    mystr=mystr.substring(1,mystr.length-1);
                }
                return mystr;
            }
        }
        return "";
    },

    setCookie:function (name,value,days) {
        if (days) {
            var date = new Date();
            date.setTime(date.getTime()+(days*24*60*60*1000));
            var expires = "; expires="+date.toGMTString();
        }
        else var expires = "";
        document.cookie = name+"=\""+value+"\""+expires+"; path=/";
    },
    dateOut:    function (format,time){
        var today=new Date();
        var day1=60*60*24*1000;
        if (time!=null && time!=undefined && time>1){
            today.setTime(time);
        }
        month=formatDate0(today.getMonth()+1);
        year=formatDate0(today.getFullYear());
        //alert(year);
        //alert(Math.ceil(year/4));
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
            month=Today.getMonth();
            year=Today.getFullYear();
            if ((month-1)<0){
                month=12;                
                year=Today.getFullYear();
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
            year=Today.getFullYear();
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
            
//            if (country=="Malaysia"){
//                return day+"/"+month+"/"+year+" "+hour+":"+min+":"+sec+".0"; //for malaysia
//            } else {
                return year+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0";
//            }
            
        }
        
        
        
        if (!format) {
            return year+"-"+month+"-"+day;
        }
        if (format) {
            return hour+":"+min+":"+sec+".0";
        }
    },
    numbersonly:function (myfield,l, e, dec)
    { //l for limit
        
        var key;
        var keychar;
        if ($(myfield).val().length>(l-1)) {
            return false
        }
        if (window.event) {
            key = window.event.keyCode;
        }
        else if (e) {
            key = e.which;
        }
        else  return true;
        keychar = String.fromCharCode(key);
        //alert(keychar);
        // control keys
        if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) ){
            return true;
        }
        // numbers
        else if ((("0123456789").indexOf(keychar) > -1)){
            return true;
        }
        // decimal point jump
        else if (dec && (keychar == "."))   {
            myfield.form.elements[dec].focus();
            return false;
        }
        else
            return false;
    },
    //keypress event
    getUnicode:function (e){
        var unicode=e.keyCode? e.keyCode : e.charCode
        //return unicode;
        switch (unicode) {
            case 8:
                return "bkspace";
            case 37:
                return "lefta";
            case 38:
                return "upa";
            case 39:
                return "righta";
            case 40:
                return "downa";
            case 13:
                return "enter";    
            default:
                return unicode;
        }
    },
    msgPop: function (xs){
        $("#savingrcd").dialog({
            title:xs
        });
        $.fx.speeds._default = 500;
        //document.getElementById("savingrcd").title="鏁版嵁宸茬粡淇濆瓨 (Data Saved) "+xs;
        //$("#savingrcd").html(xs);
        $("#savingrcd").dialog("open");
        setTimeout(function(){
            $("#savingrcd").dialog("close");
        },1300);
    },
    defaultTxt: function (){
        $(".defaultText").focus(function(srcc)
        {
            if ($(this).val() == $(this)[0].title)
            {
                $(this).removeClass("defaultTextActive");
                $(this).val("");
            }
        });

        $(".defaultText").blur(function()
        {
            if ($(this).val() == "")
            {
                $(this).addClass("defaultTextActive");
                $(this).val($(this)[0].title);
            }
        });
        $(".defaultText").mouseout=$(".defaultText").blur;

        $(".defaultText").blur();
    },
    popXY:function (my){
        alert("X: "+my.style.left+"   Y: "+my.style.top);
    },
    chkBKspc: function (e,xt){
        if (this.getUnicode(e)=="bkspace"){
            xt.value="";
        }
        this.opens(xt);
    },
    cleanMsg: function(){
        try {
            $( "#tooltip" ).removeAttr( "style" ).hide().fadeOut();
        } catch (e) {}
        try {
            $("#msgBox" ).removeAttr( "style" ).hide().fadeOut(800);
        } catch (e) {}
    },
    showMsg: function (cs,sec,target) {
        
        var x=0;
        var y=0;
        if (sec==null || sec==undefined || !sec){
            sec=2000;
        } else {
            sec=(sec*1000);
        }
        if (target==null || target==undefined || !target){
            target=document;
            y=(window.innerHeight/2-200);
            x=(window.innerWidth/2-200);
        } else {
            y=(target.body.clientHeight/2-200);
            x=(target.body.clientWidth/2-200);
        }
        try {
            $(target.getElementById("msgBox") ).remove();
        } catch(er){}
        
        var bgx="";//<img src=\"../imgs/backtrans.png\" id=\"imgbg\" class=\"imgbgx\">";
        $('<div id="msgBox"><table width="100%" height="100%"><tr><td valign=center align=center>'+ bgx + unescape(cs) +'</td></tr></table></div>').css( {
            font: '14px Verdana, sans-serif,瀹嬩綋, Arial,浠垮畫_GB2312',
            position: 'absolute',
            display: 'none',
            top: y,
            left: x,
            border: '2px solid #eff',
            padding: '8px',
            width: '380px',
            height: '280px',
            'background-color': '#eff'
        }).appendTo(target.body).fadeIn(800);//effect("slide",{direction:"down"},500);

        if (sec>0){
            setTimeout(function() {
                try {
                    $(target.getElementById("msgBox") ).removeAttr( "style" ).hide().fadeOut(800);
                } catch(er){ }
            
            }, sec );
        }

    },
    buildForm: function (id,h){
        
        var tmp=unescape($("#"+id).html());
        
        tmp=tmp.replace(new RegExp("font-size: 14px; ","gm"),"");
        $("#"+id).html(tmp);
        $(".hasDatepicker").removeClass("hasDatepicker");
        $("#"+id).height(h);
        //$(".ui-resizable")
        if ($("#workarea")!=null){
            $("#workarea").removeAttr("class");
            $("#searchform").height($("#workarea").height());
        } 
        //.resizable("destroy");
        $("#sarea").show();
    //alert($("#"+id).html());
    },        
    validate: function (xform){
        for(i=0; i<xform.elements.length; i++){
            var xel=xform.elements[i];
            
            if (((xel.type=="password")||(xel.type=="text")||(xel.type=="textarea"))){  
                var mynode=$(xel).prev();
                //alert(mynode.attr("id"));
                if ((xel.value=="")&&(mynode.attr("id")!="noneed")){
                    ///alert($(mynode).html());
                    //alert(ci+"..."+mynode.childNodes.length);
                    //val=xel.parentNode.childNodes[0].firstChild.data;
                    
                    var st=$(mynode).text().replace(":","");
                    //alert(st);
                    if (st.length<3){
                        st=($("#radio2").is(":checked")?$("#radio2").val():$("#radio1").val())+"#";
                    }
                    $(mynode).css({
                        "text-Decoration":"blink"
                    });
                    setTimeout(function(){
                        $(mynode).css({
                            "text-Decoration":""
                        });
                    },5000);
                    //alert("婕忓～! 璇疯緭鍏�\""+st+"\""+xel.id+".."+mynode.id);
                    jChaak.showMsg(st+ "  Missing value! ");
                    xel.focus();
                    return false;
                }
            }
        }
        return true;
    }

};

function wl(xid, add){    
    $.post("core/central.jsp",{
        mode:12, 
        id:xid
    }, function(dat){
        if (add){
            $.post("xml/wlxml_wuhan.jsp",{
                appId:xid
            },function(data){
                data=$.trim(data);
                //alert(data);
            
                var msgl="Data saved "+xid+", posting to modality, please wait <img src='/backgrounds/imgs/loading2.gif' align=absmiddle />...";
                if (langBase=="chinese"){
                    msgl="鏁版嵁宸蹭繚瀛橈紝姝ｅ啓鍏ュ伐浣滃垪琛紝璇风◢绛夈�<img src='/backgrounds/imgs/loading2.gif' align=absmiddle />...";
                }
                if (langBase=="thai"){
                    msgl="喔氞副喔權笚喔多竵喔傕箟喔浮喔灌弗喙佮弗喙夃抚 喔佮福喔膏笓喔侧福喔釜喔编竵喔勦福喔灌箞<img src='/backgrounds/imgs/loading2.gif' align=absmiddle />...";
                }
                jChaak.showMsg(msgl,3);
                setTimeout(function(){
                    $.post("core/central.jsp",{
                        mode:13,
                        id:xid
                    },function(data){                       
                        //location="register.jsp";
                        try{
                            nId="";
                            uId="";
                            $(".killme,#contact_contactId, #appointment_appointmentId").val("");
                            colx(true);
                        }
                        catch(ex){}
                    //$("#reg_form").load("register.jsp");
 
                    });                    
                //displaySav("Remmoving Data.");
                },3000);                              
                       
            });
        }
    });        


}
    
function getPos(e){
    var e=gE(e);
    var a=e;
    var b=0;
    if(a.offsetParent){
        while(a.offsetParent){
            b+=a.offsetLeft;
            a=a.offsetParent
        }
    }else if(a.x)b+=a.x;
    var a=e;
    var c=0;
    if(a.offsetParent){
        while(a.offsetParent){
            c+=a.offsetTop;
            a=a.offsetParent
        }
    }else if(a.y)c+=a.y;
    return{
        x:b,
        y:c
    }
};

function gE(e){
    var t=typeof(e);
    if(t=="undefined")return 0;
    else if(t=="string"){
        var a=document.getElementById(e);
        if(!a)return 0;
        else if(typeof(a.appendChild)!="undefined")return a;else return 0
    }else if(typeof(e.appendChild)!="undefined")return e;else return 0
};
function one(oj){
    if (oj.value.search("@")==-1){            
        oj.value=(oj.checked)?"1":"0";
        if (oj.value=="1"){
            try{
                $(oj).next().next().attr("src","/backgrounds/imgs/bad.png");
                if (langBase=="chinese"){
                    $(oj).next().text("纭闃虫�");
                } else if (langBase=="thai"){
                    $(oj).next().text("喔溹腹喙夃笡喙堗抚喔⑧箘喔∴箞喔涏竵喔曕复");
                } else{
                    $(oj).next().text("Positive");
                    
                }
                
            } catch(ex){}
        } else {
            try{
                $(oj).next().next().attr("src","/backgrounds/imgs/happy.png");
                if (langBase=="chinese"){
                    $(oj).next().text("闃存�");
                } else if (langBase=="thai"){
                    $(oj).next().text("喔溹腹喙夃笡喙堗抚喔⑧笡喔佮笗喔�);
                } else {
                    $(oj).next().text("Negative");
                }
               
            } catch(ex){}
        }
    } else {
        try{
            chkbox()
        } catch(e){}
    }
}
function onex(oj){
    oj.checked=(oj.value=="1")?true:false;
}    
function showTooltip(x, y, contents,load) {
    $('<div id="tooltip">' + unescape(contents) + '</div>').css( {
        font: '11px Verdana, sans-serif,瀹嬩綋, Arial,浠垮畫_GB2312',
        position: 'absolute',
        display: 'none',
        top: y +5,
        left: x + 5,
        border: '1px solid #fdd',
        padding: '2px',
        'background-color': '#fee',
        opacity: 0.90
    }).appendTo("body").fadeIn(200);
    if (load){
        setTimeout(function() {
            $( "#tooltip" ).removeAttr( "style" ).hide().fadeOut();
            location=location;
        }, 1000 );
    } else {
        setTimeout(function() {
            $( "#tooltip" ).removeAttr( "style" ).hide().fadeOut();
        }, 8000 );
    }

}
$.fn.focusNextInputField = function() {
    return this.each(function() {
        var fields = $(this).parents('form:eq(0),body').find('button,input,textarea,select');
        var index = fields.index( this );
        if ( index > -1 && ( index + 1 ) < fields.length ) {
            fields.eq( index + 1 ).focus();
        }
        return false;
    });
};
function showhint(ele,msg,load){
    if (msg.length>28){
        jChaak.showMsg(msg,30);
    }
    else {
        $("#tooltip").remove();
        if (msg!=null && msg!=""){
            showTooltip(getPos(ele).x, (getPos(ele).y+ele.offsetHeight),msg,load);
        }
    // alert(ele.parentNode.parentNode.childNodes[0].firstChild.data);
    }
}


function replacecarriagereturn(textarea,replaceWith)
{ 
    textarea = escape(textarea);
    //encode all characters in text area
    //to find carriage return character
    //for(i=0; i < textarea.length; i++)
    //{ 
    //loop through string, replacing carriage return 
    //encoding with HTML break tag
    if(textarea.indexOf("%0D%0A") > -1)
    { 
        //Windows encodes returns as \r\n hex
        textarea=textarea.replace("%0D%0A",replaceWith);
    }
    else if(textarea.indexOf("%0A") > -1)
    { 
        //Unix encodes returns as \n hex
        textarea=textarea.replace("%0A",replaceWith);
    }
    else if(textarea.indexOf("%0D") > -1)
    { 
        //Macintosh encodes returns as \r hex
        textarea=textarea.replace("%0D",replaceWith);
    }
    //}
    return unescape(textarea);
//decode all characters in text area back
}

function timex(f){
    var today=new Date();        
    if (f==1){
        $("#from").val(jChaak.dateOut(false));
        $("#to").val(jChaak.dateOut(false));
    }
    if (f==2){
        $("#from").val(jChaak.dateOut(false,"-1"));
        $("#to").val(jChaak.dateOut(false,"-2"));
    }
    if (f==4){
        $("#from").val(jChaak.dateOut(false,"-3"));
        $("#to").val(jChaak.dateOut(false,"-4"));
    }
    if (f==3){
        $("#from").val(jChaak.dateOut(false,"-90"));
        $("#to").val(jChaak.dateOut(false));
    }
    if (f==5 ){
        var msgl="This may take some time to prepare the results, continue?";
        if (langBase=="chinese"){
            msgl="娌℃棩鏈熼檺鍒讹紝鏈夊彲鑳介渶瑕侀暱鏃堕棿鐨勬悳绱紝纭畾鍚楋紵";
        }
        if (langBase=="thai"){
            msgl="喔佮赋喔ム副喔囙笖喔赤箑喔權复喔權竵喔侧福 喔佮福喔膏笓喔侧福喔釜喔编竵喔勦福喔灌箞";
        }
        if ( confirm(msgl)) {
            $("#from").val("");
            $("#to").val("");
        } else {
            return;
        }
    }
    if (f==6){
        $("#from").val(jChaak.dateOut(false,"-7"));
        $("#to").val(jChaak.dateOut(false));
    }
    if (f==7){
        $("#from").val(jChaak.dateOut(false,"-5"));
        $("#to").val(jChaak.dateOut(false));
    }
    var day1=60*60*24*1000;
    if (f==8){
        var msgl="Back for how many days";
        if (langBase=="chinese"){
            msgl="鑷畾浠庝粖澶╁�鍥炲灏戝ぉ锛�;
        }
        if (langBase=="thai"){
            msgl="喔堗赋喔權抚喔權抚喔编笝喔椸傅喙堗竸喙夃笝喔覆";
        }
        var stir = window.prompt(msgl,"30");
        if (stir=="" || parseInt(stir)==NaN){
            return;
        }
        $("#to").val(jChaak.dateOut(false));
        var prevDay=today.getTime();
        prevDay-=(day1*stir);
        $("#from").val(jChaak.dateOut(false,prevDay));
    }
    colx(true);
}
function formatDate0(str){
    if (str<10) {
        str="0"+str;
    }
    return str;
}
function kill(all){
    var msgl="Empty All Fields, Continue?";
    if (langBase=="chinese"){
        msgl="纭畾娓呯┖鎵�湁鐨勬潯浠惰缃悧锛�;
    }
    if (langBase=="thai"){
        msgl="喔涏箟喔笝喔傕箟喔浮喔灌弗喙冟斧喔∴箞?";
    }
    if (confirm(msgl)){
        var fr=$("#from").val();
        var ft=$("#to").val();
        if (all){
            //alert("");
            document.getElementById("searchform").reset();
        } else {                
            
            try{
                $(".killme,#contact_contactId").val("");
            }
            catch(ex){}
        }
        $(".searchtext").val("");
        $("#from").val(fr);
        $("#to").val(ft);
        setDtxt();
        colx(true);
    }
}
function tabUp(force){
    if (force!=undefined && force!=null) {
        if (force){
            $("#sarea").animate({
                "height":0
            });
            $("#inimg").hide();
        } else {
            $("#sarea").animate({
                "height":fh
            });
            $("#inimg").show();
        }
    }
    else {
        //            $("#st").animate({top:"0"},500);
        //        } else { $("#sarea").is(":visible")
        if ($("#sarea").height()>0){
            $("#sarea").animate({
                "height":0
            });
            $("#inimg").hide();
        }
        else {
            $("#sarea").animate({
                "height":fh
            });
            $("#inimg").show();
        }
    }
}    
function insertAtCursor(myField, mV) {

    var myValue=$(mV).text();

    //IE support
    if (document.selection) {
        myField.focus();
        sel = document.selection.createRange();
        sel.text = myValue;
    }
    //MOZILLA/NETSCAPE support
    else if (myField.selectionStart || myField.selectionStart == '0') {
        var startPos = myField.selectionStart;
        var endPos = myField.selectionEnd;
        myField.value = myField.value.substring(0, startPos)
        + myValue
        + myField.value.substring(endPos, myField.value.length);
    } else {
        myField.value += myValue;
    }
}
function insertValAtCursor(myField, val) {

    var myValue=val;

    //IE support
    if (document.selection) {
        myField.focus();
        sel = document.selection.createRange();
        sel.text = myValue;
    }
    //MOZILLA/NETSCAPE support
    else if (myField.selectionStart || myField.selectionStart == '0') {
        var startPos = myField.selectionStart;
        var endPos = myField.selectionEnd;
        myField.value = myField.value.substring(0, startPos)
        + myValue
        + myField.value.substring(endPos, myField.value.length);
    } else {
        myField.value += myValue;
    }
    myField.focus();
}
function insertTxtAtCursor(myField, val) {

    var myValue=val;

    //IE support
    if (document.selection) {
        myField.focus();
        sel = document.selection.createRange();
        sel.text = myValue;
    }
    //MOZILLA/NETSCAPE support
    else if (myField.selectionStart || myField.selectionStart == '0') {
        var startPos = myField.selectionStart;
        var endPos = myField.selectionEnd;
        $(myField).text($(myField).text().substring(0, startPos)
        + myValue
        + $(myField).text().substring(endPos, $(myField).text().length));
    } else {
        $(myField).text($(myField).text() + myValue);
    }
    myField.focus();
}
var acceptObj=null;
var kdbedit=false;
function simpleTop(targetObj){
    acceptObj=targetObj;
    $("#resi").parent().show();
    $("#resi").css({
        "color":mypage[1].myValue
    });
///$("#knodb").dialog("open");
}
        
        
function passmatchx(){
    var enabl=false;

    if ($("#password3").val()!="" && $("#password4").val()!=""){
        enabl=($("#password3").val()==$("#password4").val());
        $("#newpassword").val($("#password3").val());
    }
    $(".mustR").each(function(){
        if ($(this).val()==""){
            enabl=false;
            //alert($(this).html());
        }
    });
    $(".musti").each(function(){
        if ($(this).val()==""){
            enabl=false;
        }
    });

    if ( $("#newpassword").val()==""){
        enabl=false;
    }
    if ($("#contact_contactId").val()!="" && $("#user_membershipx").val()==""){
        enabl=false;
    }
            
    if (enabl) {
        $("#savReg").show();
        
    } else {
        $("#savReg").hide();
        $("#newpassword").val("");                    

    }
}

function popDialog(msg,time){
    $("#progressModal").dialog("open");
    $("#progressModal").html("<p style='font:14px; width:100%; margin:auto; text-align:center'>"+msg+"</p>");
    $("#progressModal").animate({
        opacity: 0.99
    }, time, function() {
        $("#progressModal").dialog("close");
    });
}

try{
    window.onresize=posMain;
    posMain();               
} catch(e){}

var Today=new Date();

function getCursorXY(event){
    return [event.pageX,event.pageY];
}
var MouseX="",MouseY="";
var lastDownX,lastDownY="";
$(document).mousemove(function(event){
    MouseX=event.pageX;
    MouseY=event.pageY;
    try{
       resetTimer(); 
    } catch(e){}
    
    //$("#templateList").val(MouseX+".."+MouseY);
});

function moveObject(event)
{
    var delta = 0; 
    if (!event) event = window.event; 
    // normalize the delta
    if (event.wheelDelta) {
 
        // IE and Opera
        delta = event.wheelDelta / 60;
 
    } else if (event.detail) {
        delta = -event.detail / 2;
    }
   
    mxcroll(delta);
}