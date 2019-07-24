import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

let isManager = sessionStorage.getItem("level") > 1;

window.onload = () => {
	// Redirect to dashboard if employee is not a manager
	if (isManager) {
		addDirectoryNavItem();
	} else {
		window.location.replace("/dashboard");
	}

	getEmployees();
	setMyProfileLink();

	// Event handlers
	document.getElementById("logOutBtn").onclick = () => {
		logOut();
	};
};

// Add directory link in nav
let addDirectoryNavItem = () => {
	let itemLi = document.getElementById("directoryNavItem");

	let link = document.createElement("a");
	link.className = "nav-link";
	link.href = "/employee/directory";
	link.innerText = "Directory";

	itemLi.appendChild(link);
};

// Set My Profile link
let setMyProfileLink = () => {
	let link = document.getElementById("profileLink");
	link.href = "/employee/profile?eId=" + sessionStorage.getItem("id");
};

// Get employees from server
let getEmployees = () => {
	fetch("/employee/all").then((res) => res.json()).then((json) => {
		populateEmployees(json);
	});
};

// Populate employees on page
let populateEmployees = (json) => {
	json.forEach(e => {
		let directoryDiv = document.getElementById("directory");

		let divRow = document.createElement("div");
		divRow.className = "row";

		let divIdCol = document.createElement("div");
		divIdCol.className = "col";
		let idLink = document.createElement("a");
		idLink.href = "/employee/profile?eId=" + e["id"];
		idLink.innerText = e["id"];
		divIdCol.appendChild(idLink);

		let divNameCol = document.createElement("div");
		divNameCol.className = "col";
		let pName = document.createElement("p");
		pName.innerText = e["firstName"] + " " + e["lastName"];
		divNameCol.appendChild(pName);

		divRow.appendChild(divIdCol);
		divRow.appendChild(divNameCol);
		directoryDiv.appendChild(divRow);
	});
};
