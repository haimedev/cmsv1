var reportTitle;
var reprotId;

Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});
$( document ).ready(function() {
    $('#from_cal').val(new Date().toDateInputValue());
    $('#to_cal').val(new Date().toDateInputValue());
    $("#"+getParameterByName("reportLabelId")).css("background-color","yellow");
});

$(".rows").click(function (event){ 
    reportTitle = $(this).children("td").html();
    reportId = $(this).children("td").attr('id');
    if(reportTitle == "Total Customers")
    {
        document.location.href = "ReportController?lbl=" + reportTitle + "&" + "reportLabelId=" + reportId;
    }
});

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

$("#generateReport_btn").click(function()
{
    alert(window.location.pathname);
    alert(window.location.href + "&idid=123123bbbabc"); 
    document.location.href = "ReportController?lbl=" + reportTitle + "&" + "reportLabelId=" + reportId
        ;
});