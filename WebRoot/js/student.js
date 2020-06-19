function getRadioBoxValue(radioName){
     var obj = document.getElementsByName(radioName);
           for(i=0; i<obj.length;i++)    {
           if(obj[i].checked)    {
               return   obj[i].value;
           }
       }
      return "undefined";
}

function register(radioName){
    var type = getRadioBoxValue(radioName);
    location.href="<%=basePath%>servlet/action?action=add_"+type;
}

function search(type, element, basePath){
    var name = document.getElementById(element).value;
    //window.open(<%=basePath%>servlet/page?pageNo=1&pageSize=5&type="+type+"&name="+name,"main");
    //window.parent.main.location.href="<%=basePath%>servlet/page?pageNo=1&pageSize=5&type="+type+"&name="+name;
    window.top.main.location.href=basePath+"servlet/page?pageNo=1&pageSize=5&type="+type+"&name="+name;
}