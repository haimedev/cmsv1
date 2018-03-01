<style>
    body
    {
        margin:0px;
    }
    #moduleDiv
    {
        font-family: arial;
        color: white;
        overflow: hidden;
        background-color: rgb(59,89,152);
        padding: 20px 150px 18px 150px;
    }
    #companyName
    {
        position: absolute;
    }
    
    #logout_lbl
    {
        position: absolute;
        left: 90%;
        text-decoration: none;
        color: white;
        font-weight: bold;
    }
    
    #logout_lbl:hover
    {
        color: red;
    }
</style>

<div id="moduleDiv">
    <h1 od="companyName">Cafe Management System</h1>
    <a href="${pageContext.request.contextPath}/LoginPage" id="logout_lbl">Logout...</a>
</div>