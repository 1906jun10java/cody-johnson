import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

window.onload = () => {
	// Event handlers
	document.getElementById("logOutBtn").onclick = () => {
		logOut();
	};
	document.getElementById("reimbursementForm").onsubmit = (e) => {
		e.preventDefault();
		handleFormSubmit();
	};
};

let handleFormSubmit = () => {
	const form = document.getElementById("reimbursementForm");
	const file = document.querySelector('[type=file]').files;
	let formData = new FormData(form);
	formData.append("imgFile", file);

	fetch("/reimbursement/create", {
		method: "POST",
		body: formData
	}).then(res => {
		console.log(res);
	});
};
