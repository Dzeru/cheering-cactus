var cactus =
{
    uuid: '12345',
    birthday: '000',
    age: 0,
    hp: 201,
    cactusColor: '#669900',
    potColor: '#663300',
    evilMode: false
};

var userSessionUuid = ""

var getUrlParameter = function getUrlParameter(sParam)
{
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++)
    {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam)
        {
            return sParameterName[0] === undefined ? false : sParameterName[1];
        }
    }
};

function getUserSessionUuid()
{
    console.log("get user session uuid");
    userSessionUuid = document.getElementById("userUuid").value;
};

function getCactus()
{
    getUserSessionUuid();
    var url = "/loadcactus?uuid=" + userSessionUuid;
    console.log("getCactus");

    $.ajax(
    {
        url: url,
        type: 'GET',
        success: function(res)
        {
          cactus.uuid = res.uuid;
          cactus.age = res.age;
          cactus.hp = res.hp;
          cactus.cactusColor = res.cactusColor;
          cactus.potColor = res.potColor;
          cactus.evilMode = res.evilMode;

          console.log("Load cactus");
          console.log(res.uuid);
          init();
        },
        error: function(err)
        {
          console.log("Fail to load cactus");
          console.log(err);
        }
      })
}

function init()
{
    console.log("init");
    document.getElementById('hp').innerHTML = cactus.hp;
}

$(document).ready(function()
{
    getCactus();
});