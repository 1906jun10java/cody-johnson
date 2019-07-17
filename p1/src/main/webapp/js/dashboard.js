window.onload = () => {
    validate();

    // Event handlers
    let logOutBtn = document.getElementById("logOutBtn").onclick = () => {
        logOut();
    };
};

// Validate user
function validate() {
    let eId = sessionStorage.getItem("id");
    if (eId) {
        setHeading();
    } else {
        window.location.replace("/login");
    }
}

// Set welcome message in heading
function setHeading() {
    let eFirstName = sessionStorage.getItem("firstName");
    let heading = document.getElementById("heading");
    heading.innerText = `Welcome, ${eFirstName}`;
}

// Clear session and forward to log in page
function logOut() {
    sessionStorage.clear();
    window.location.replace("/login");
}
