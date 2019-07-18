import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

window.onload = () => {
	// Event handlers
	let logOutBtn = document.getElementById("logOutBtn").onclick = () => {
		logOut();
	};
};
