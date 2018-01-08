var globalVar = 5;

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

//$(document).ready(function(){
//    $("#rowClick > tr").click(function(){
//        $(this).toggleClass("active");
//    });
//});

function addRowHandlers() {
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
                    var tempName = row.getElementsByTagName("td")[1];
                    var tempTime = row.getElementsByTagName("td")[2];
                    var id = tempId.innerHTML;
                    var name = tempName.innerHTML;
                    var time = tempTime.innerHTML;
                    var result = confirm("Use this time?\n" + name + ": " + time);
                    if(result)
                    {
//                        location.reload();
                        document.location.href = "TimelineController?del=1&" + "id=" + id;
//                        document.getElementById("rowName").value= name + " : " + time;
                    }   
                };
            };  

        currentRow.onclick = createClickHandler(currentRow);
    }
}

window.onload = addRowHandlers();






