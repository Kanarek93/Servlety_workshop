document.addEventListener("DOMContentLoaded", function () {

    const form = document.querySelector("form")

    form.addEventListener("submit", handler)

});

function handler(ev) {
    ev.preventDefault();

    const inputs = ev.target.querySelectorAll("input")
    const newDiv = document.createElement("div")
    newDiv.classList.add("form-text")
    const divChildren = ev.target.querySelectorAll(".form-text")

    const emailErrors = [];
    const nameErrors = [];
    const passErrors = [];

    const emailReg = new RegExp(`^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,}$`)
    const passwordReg = new RegExp(`^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{7,60}$`)

    //usunięcie is-invalid z wszystkich okien
    inputs.forEach(val => {
        val.classList.remove("is-invalid")
    })
    if (divChildren) {
        for (let i = 0; i < divChildren.length; i++) {
            divChildren[i].parentElement.removeChild(divChildren[i])
        }
    }

    //poprawność emaila
    if (!emailReg.test(ev.target.email.value)) {
        inputs[1].classList.add("is-invalid");
        emailErrors.push("Email ma niepoprawną składnię.")
        console.log("Email ma niepoprawną składnię.")

    }
    if (ev.target.email.value.length >= 60) {
        inputs[1].classList.add("is-invalid");
        emailErrors.push("Email jest za długi.")
        console.log("Email jest za długi.")

    }
    //poprawność username
    if (ev.target.username.value.length < 4) {
        inputs[0].classList.add("is-invalid");
        nameErrors.push("Nazwa użytkownika jest za krótka.")
    }
    if (ev.target.username.value.length > 60) {
        inputs[0].classList.add("is-invalid");
        nameErrors.push("Nazwa użytkownika jest za długa.")
    }

    //poprawność hasła (mała, duża litera,cyfra, 8 znaków)
    if (!passwordReg.test(ev.target.password.value)) {
        inputs[2].classList.add("is-invalid")
        passErrors.push("Hasło musi zawierać co najmniej: jedną małą literę, jedną dużą literę, jedną cyfrę. Hasło musi mieć co najmniej 8 znaków.")
        console.log(ev.target.password.value)
    }

    if (emailErrors.length || nameErrors.length || passErrors.length) {
        if (emailErrors.length) {
            const emailChild = newDiv.cloneNode(true)
            inputs[1].parentElement.appendChild(emailChild)
            for (let i = 0; i < emailErrors.length; i++) {
                emailChild.innerText += emailErrors[i] + ' ';
            }
        }
        if (nameErrors.length) {
            const nameChild = newDiv.cloneNode(true)
            inputs[0].parentElement.appendChild(nameChild)
            for (let i = 0; i < nameErrors.length; i++) {
                nameChild.innerText += nameErrors[i] + ' ';
            }
        }
        if (passErrors.length) {
            const passChild = newDiv.cloneNode(true);
            inputs[2].parentElement.appendChild(passChild);
            for (let i = 0; i < passErrors.length; i++) {
                passChild.innerText = passErrors[i] + ' ';
            }
        }

        return;
    }

    $("#form").submit();

}
