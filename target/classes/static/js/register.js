var start = 1900;
var end = new Date().getFullYear();
var options = "";
var optionsPromotion = "";
for(var year = start ; year <=end; year++){
    options += "<option>"+ year +"</option>";
}

optionsPromotion = "<option selected disabled hidden>Promotion La Ramée<option>";
optionsPromotion += options;

document.getElementById("year").innerHTML = options;
document.getElementById("promotion").innerHTML = optionsPromotion;

$(document).ready(function () {
    $("#passwordcheck").keyup(checkPasswordMatch);
});

function checkPasswordMatch() {
    var password = $("#password").val();
    var confirmPassword = $("#passwordcheck").val();

    if (password != confirmPassword){
        $("#divCheckPasswordMatch").show();
        $("#divCheckPasswordMatch").html("Le mot de passe ne correspond pas");
    }
    else {
        $("#divCheckPasswordMatch").hide();
    }
}

// $(function() {
//     var temp="Promotion La Ramée";
//     $("#promotion").val(temp);
// });