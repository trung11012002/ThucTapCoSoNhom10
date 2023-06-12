let checkEmailExist = (email) => {
    let ok;
    let txt;
    let checkForm = document.getElementById("checkForm");
    $.ajax({
        url: "/websell/emailexist",
        type: "get", //send it through get method
        data: {
            Email: email.value
        },
        success: function (data) {
//          txt = $('#result').text(data);          
            txt = data;
            if (data === "0") {
                ok = 0;
                checkForm.value = "0";
            } else if (data === "1") {
                ok = 1;
                checkForm.value = "1";
            }
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
//    alert(typeof(txt) , txt);


    if (checkForm.value == "1") {
        return false;
    } else {
        return true;
    }
}
let checkCode = (code, checkForm2) => {
    if (code.value == checkForm2.value) {
        return true;
    }
    return false;
}
let check = () => {
    let error = document.getElementById("error");
    let email = document.getElementById("email");
    error.style.display = "none";
    if (!checkEmailExist(email)) {
        error.style.display = "block";
        error.style.color = "green";
    } else {
        error.style.color = "red";
        error.style.display = "block";
    }
}
let checkExist = ()=>{
    let email = document.getElementById("email");
    if(!checkEmailExist(email)){
        return true;
    }else {
        return false;
    }
}