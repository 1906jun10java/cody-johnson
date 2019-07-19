import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

// For sending reimbursement type by selection index
let reimbursementType = {
	"Seminar":0,
	"Preparation Classes":1,
	"Certification":2,
	"Technical Training":3,
	"Other":4
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

	// Append binary file to formData
	formData.append("imgFile", file);

	// POST to server
	fetch("/reimbursement/create", {
		method: "POST",
		body: formData
	}).then(res => {
		console.log(res);
	});
};
