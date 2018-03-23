var reportTitle;
var reprotId;

Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

$(".rows").click(function (event){ 
    reportTitle = $(this).children("td").html();
    reportId = $(this).children("td").attr('id');
    $("#reportTitle_lbl").val(reportTitle);
    $("#reportId_lbl").val(reportId);
    //document.location.href = "ReportController?lbl=" + reportTitle + "&" + "reportLabelId=" + reportId;
    $("#reportLabel_frm").submit();
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

$("#generateReport_frm").submit(function()
{
    alert($("#").children("td").attr('id'));
});

$( document ).ready(function() {
    $('#from_cal').val(new Date().toDateInputValue());
    $('#to_cal').val(new Date().toDateInputValue());
    $("#"+getParameterByName("reportLabelId")).css("background-color","yellow");
});