Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});
$( document ).ready(function() {
    $('#dateFrom').val(new Date().toDateInputValue());
    $('#dateTo').val(new Date().toDateInputValue());
});

$(".rows").click(function (event){ 
    var reportTitle = $(this).children("td").html();
    var reportId = $(this).children("td").attr('id');
    if(reportTitle == "Total Customers")
    {
        document.location.href = "ReportController?lbl=" + reportTitle + "&" + "reportLabelId=" + reportId;
    }
});