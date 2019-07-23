import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

// For sending reimbursement type by selection index
let reimbursementType = {
	"Seminar":1,
	"Preparation Classes":2,
	"Certification":3,
	"Technical Training":4,
	"Other":5
};

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

	// Use index of HTML select for reimbursement type
	for (let entry of formData.entries()) {
		if (entry[0] === "type") {
			formData.set(entry[0], reimbursementType[entry[1]]);
		}
	}

	// Append binary file and employee id to formData
	formData.append("imgFile", file);
	formData.append("eId", sessionStorage.getItem("id"));

	// POST to server
	fetch("/reimbursement/create", {
		method: "POST",
		body: formData
	}).then((res) => res.json()).then((json) => {
		if (json.error) {
			// Send error to view
			console.log(json);
		} else {
			// Redirect to dashboard
			window.location.replace("/dashboard");
		}
	});
};
