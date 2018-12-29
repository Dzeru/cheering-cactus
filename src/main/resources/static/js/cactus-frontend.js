var cactus =
{
    id: -1,
    uuid: "12345",
    birthday: "000",
    age: 0,
    level: 0,
    maxHp: 201,
    currentHp: 201,
    cactusColor: "#669900",
    potColor: "#663300",
    evilMode: false
};

var userSessionUuid = ""

var getUrlParameter = function getUrlParameter(sParam)
{
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split("&"),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++)
    {
        sParameterName = sURLVariables[i].split("=");

        if (sParameterName[0] === sParam)
        {
            return sParameterName[0] === undefined ? false : sParameterName[1];
        }
    }
};

function getUserSessionUuid()
{
    console.log("Get user session uuid");
    userSessionUuid = document.getElementById("userUuid").value;
    console.log(userSessionUuid);
};

function getCactus()
{
    getUserSessionUuid();
    var url = "/loadcactus?uuid=" + userSessionUuid;
    console.log("Get cactus");

    $.ajax(
    {
        url: url,
        type: "GET",
        success: function(res)
        {
          cactus.id = res.id;
          cactus.uuid = res.uuid;
          cactus.birthday = res.birthday;
          cactus.age = res.age;
          cactus.level = res.level;
          cactus.maxHp = res.maxHp;
          cactus.currentHp = res.currentHp;
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

function increaseCurrentHp(incValue)
{
    cactus.currentHp = cactus.currentHp + incValue;
    if(cactus.currentHp > cactus.maxHp)
    {
        cactus.currentHp = cactus.maxHp;
    }
    document.getElementById("currentHp").innerHTML = cactus.currentHp;
    updateCactus();
}

function init()
{
    console.log("init");
    document.getElementById("maxHp").innerHTML = cactus.maxHp;
    document.getElementById("currentHp").innerHTML = cactus.currentHp;
    document.getElementById("age").innerHTML = cactus.age;
    document.getElementById("level").innerHTML = cactus.level;
    $("#cactus").css("background", cactus.cactusColor);
    $("#pot").css("border-top-color", cactus.potColor);

    $("#watering").on("click", function()
    {
        console.log("Watering");
        increaseCurrentHp(10);
    });

    $("#caress").on("click", function()
    {
        console.log("Caress");
        increaseCurrentHp(5);
    });

    $("#hugs").on("click", function()
    {
        console.log("Hugs");
        increaseCurrentHp(15);
    });
}

function updateCactus()
{
    var postCactus =
    {
        "id": cactus.id,
        "uuid": cactus.uuid,
        "cactusColor": cactus.cactusColor,
        "potColor": cactus.potColor,
        "birthday": cactus.birthday,
        "age": cactus.age,
        "level": cactus.level,
        "currentHp": cactus.currentHp,
        "maxHp": cactus.maxHp,
        "evilMode": cactus.evilMode
    };

    $.ajax(
    {
        url: "/updatecactus",
        method: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(postCactus),
        headers:
        {
            "X-CSRF-TOKEN": $('meta[name="_csrf"]').attr("content")
        }
    })
    .done(function (res)
    {
        console.log("Send post request of cactus");
        console.log(res);
    })
    .fail(function (res)
    {
        console.log("Fail to send post request of cactus");
        console.log(res);
    });
}

$(document).ready(function()
{
    getCactus();
});