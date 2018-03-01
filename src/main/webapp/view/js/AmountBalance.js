var globalVar = 5;
var t;
var url = window.location.href;
var type = getParameterByName('timeLineType') || "unpaid";
$(".amountButton").click(function()
{
    $('#amount_txt').val($('#'+this.id).val().substr(1,$('#'+this.id).val().indexOf('.')-1));
});

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
                        var tempComment = row.getElementsByTagName("td")[1];
                        var tempName = row.getElementsByTagName("td")[2];
                        var tempValue = row.getElementsByTagName("td")[3];
                        var tempType = row.getElementsByTagName("td")[5];
                        var id = tempId.innerHTML;
                        var name = tempName.innerHTML;
                        var value = tempValue.innerHTML;
                        var comment = tempComment.innerHTML;
                        if((type == "all" && tempType.innerHTML == "Unpaid") || type == "unpaid")
                        {
                           var result = confirm("Pay this balance?\n" + name + ": " + value + "\n\nComment: \"" + comment + ".\"");
                            if(result)
                            {
                                document.location.href = "AmountBalanceController?del=1&" + "id=" + id;
                            } 
                        }
                    };
                };  

            currentRow.onclick = createClickHandler(currentRow);
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
    
    else if(targetIH == "Timeline(Unpaid)")
    {
        t = "unpaid";
    }
    
    else if(targetIH == "Timeline(Paid)")
    {
        t = "paid";
    }
    
    else if(targetIH == "Reports")
    {
        
    }
    document.location.href = "AmountBalanceController?timeLineType=" + t;
    
};

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

//onload function
$(function ()
{
    
});

window.onload = addRowHandlers();







