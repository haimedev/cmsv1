var globalVar = 5;
var t;
var url = window.location.href;
var type = getParameterByName('timeLineType') || "unused";
function setTime(button)
{
    var btnValue = button.value.substr(0,button.value.indexOf(' '));
    var btnHour = Math.floor(btnValue/60);
    var btnMinute = btnValue % 60;
    var timeHour = document.getElementsByName("timeHour_txt")[0];
    var timeMinute = document.getElementsByName("timeMinute_txt")[0];
    timeHour.value = "";
    timeMinute.value = "";
    if(btnHour != 0)
    {
        timeHour.value = btnHour;
    }
    
    else
    {
        timeHour.value = "0";
    }
    
    if(btnMinute != 0)
    {
        timeMinute.value = btnMinute;
    }
    
    else
    {
        timeMinute.value = "0";
    }
}

function validation()
{
    var timeHour = document.getElementsByName("timeHour_txt")[0];
    var timeMinute = document.getElementsByName("timeMinute_txt")[0];
    
    if(timeHour.value == "0" && timeMinute.value == "0")
    {
        return false;
    }
    
    else
    {
        return true;
    }
}

//Clicked row in table. Confirm box will appear
function addRowHandlers() {
    if(type != "used")
    {
        var table = document.getElementById("timeTable");
        var rows = table.getElementsByTagName("tr");
        for (i = 0; i < rows.length; i++) {
            var currentRow = table.rows[i];
            var createClickHandler = 
                function(row) 
                {
                    return function() 
                    { 
                        var tempId = row.getElementsByTagName("td")[0];
                        var tempComments = row.getElementsByTagName("td")[1];
                        var tempName = row.getElementsByTagName("td")[2];
                        var tempTime = row.getElementsByTagName("td")[3];
                        var tempType = row.getElementsByTagName("td")[5];
                        var id = tempId.innerHTML;
                        var comments = tempComments.innerHTML;
                        var name = tempName.innerHTML;
                        var time = tempTime.innerHTML;
                        if((type == "all" && tempType.innerHTML == "unused") || type == "unused")
                        {
                           var result = confirm("Use this time?\n" + name + ": " + time
                                   + "\n\nComments: \"" + comments + ".\"");
                            if(result)
                            {
                                document.location.href = "TimelineController?del=1&" + "id=" + id;
                            } 
                        }
                    };
                };  

            currentRow.onclick = createClickHandler(currentRow);
        }
    }
    
}

//When focus leave on time hour/minute, if not blank the other's value will be "0"
function onBlur(timeBox)
{
   if(timeBox.value != "" || timeBox.value != "0")
   {
       var time = null;
       if(timeBox.name == "timeHour_txt")
       {
            time = document.getElementsByName("timeMinute_txt")[0];
            if(time.value == "")
            {
                time.value = "0";  
            }
       }
       
       else
       {
           
           time = document.getElementsByName("timeHour_txt")[0];
           if(time.value == "")
           {
               time.value = "0";
           }
       }
   }
}

function getTimelineTarget(e)
{
    e = e || window.event;
    return e.target || e.srcElement;
}

var ul = document.getElementById("timeline_ul");
ul.onclick = function(event)
{
    var target = getTimelineTarget(event);
    var targetIH = target.innerHTML;
    if(targetIH == "Timeline(ALL)")
    {
        t = "all";
    }
    
    else if(targetIH == "Timeline(Unused)")
    {
        t = "unused";
    }
    
    else if(targetIH == "Timeline(Used)")
    {
        t = "used";
    }
    document.location.href = "TimelineController?timeLineType=" + t;
}

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

window.onload = addRowHandlers();

function toTitleCase(str)
{
    return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}

$("#add_frm").submit(function()
{
    var customerNames = document.getElementById("customer_lst");
    var customerName_txt = $("#custName_txt").val();
    var custId = $('#customer_lst').find('option[value="' + customerName_txt + '"]').attr('id');
    var tempCustomerNames = [];
    for(var i =0; i < customerNames.options.length; i++)
    {
        tempCustomerNames.push(customerNames.options[i].value);
    }
    
    if(tempCustomerNames.includes(customerName_txt))
    {
        $("#custId_txt").val(custId);
        return true;
    }
    
    else
    {
        alert("Please enter a valid name.");
        return false;
    }
    
    
});







