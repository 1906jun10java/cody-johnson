window.onload = () => {
    validate();
};

function validate() {
    let eId = sessionStorage.getItem("id");
    if (!eId) {
        window.location.replace("/login");
    } else {
        setHeading();
    }
}

function setHeading() {
    let eFirstName = sessionStorage.getItem("firstName");
    let heading = document.getElementById("heading");
    heading.innerText = `Welcome, ${eFirstName}`;
}
