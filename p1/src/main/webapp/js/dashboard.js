import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

window.onload = () => {
    setHeading();

    // Event handlers
    let logOutBtn = document.getElementById("logOutBtn").onclick = () => {
        logOut();
    };
};

// Set welcome message in heading
let setHeading = () => {
    let eFirstName = sessionStorage.getItem("firstName");
    let heading = document.getElementById("heading");
    heading.innerText = `Welcome, ${eFirstName}`;
};
