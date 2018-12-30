function checkLogin()
{
    var url = document.location.search.substring(1);

    if(url == "loginerror")
    {
        $("#login-error").css("visibility", "visible");
    }
}

$(document).ready(function()
{
    checkLogin();
});