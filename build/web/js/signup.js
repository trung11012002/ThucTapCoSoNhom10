let checkName = (userName) => {
    if (userName.value != "") {
        return true;
    } else
        return false;
}
let checkEmail = (email) => {
    var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (regex.test(email.value)) {
        return true;
    } else {
        return false;
    }
}
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
let checkPass = (password) => {
    if (password.value.length >= 8) {
        return true;
    } else {
        return false;
    }
}
let checkAddress = (address) => {
    if (address.value != "") {
        return true;
    } else {
        return false;
    }
}
let checkRepeatpass = (repeatpass, password) => {
    if (password.value == repeatpass.value) {
        return true;
    }
    return false;
}
let checkCode = (code, checkForm2) => {
    if (code.value == checkForm2.value) {
        return true;
    }
    return false;
}
let check = () => {
    let userName = document.getElementById("user-name");
    let email = document.getElementById("inputEmail2");
    let password = document.getElementById("user-pass");
    let address = document.getElementById("Address");
    let repeatpass = document.getElementById("user-repeatpass");
    let checkForm = document.getElementById("checkForm");

    if (!checkName(userName)) {
        let error1 = document.getElementById("error1");
        error1.innerHTML = "not be empty";
    } else {
        error1.innerHTML = "";
    }
    if (!checkEmail(email)) {
        let error2 = document.getElementById("error2");
        error2.innerHTML = "please enter the correct format";
    } else {
//        alert(checkEmailExist(email));
        error2.innerHTML = "";
        if (!checkEmailExist(email)) {

            error2.innerHTML = "gmail already exists";
        } else {
            error2.innerHTML = "";
        }
    }
    if (!checkPass(password)) {
        let error3 = document.getElementById("error3");
        error3.innerHTML = "password must be at least 8 characters";
    } else {
        error3.innerHTML = "";
    }
    if (!checkAddress(address)) {
        let error4 = document.getElementById("error4");
        error4.innerHTML = "not be empty";
    } else {
        error4.innerHTML = "";
    }
    if (!checkRepeatpass(repeatpass, password)) {
        let error5 = document.getElementById("error5");
        error5.innerHTML = "repeat password is not correct";
    } else {
        error5.innerHTML = "";
    }
}
let signUp = () => {
    let userName = document.getElementById("user-name");
    let email = document.getElementById("inputEmail2");
    let password = document.getElementById("user-pass");
    let address = document.getElementById("Address");
    let repeatpass = document.getElementById("user-repeatpass");
    let code = document.getElementById("code");
    let checkForm2 = document.getElementById("checkForm2");
    if (checkName(userName) && checkEmail(email) && checkPass(password) && checkAddress(address) && checkRepeatpass(repeatpass, password)) {
        if (checkCode(code, checkForm2)) {
            let Action = document.getElementById("form_signUp");
            Action.action = "signup";
        } else {
            alert("error code");
        }
    } else {
        alert("error");
    }
}