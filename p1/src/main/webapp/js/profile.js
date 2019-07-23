import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

// Lookup for employee role
let roles = {
	"1": "Engineer",
	"2": "Supervisor",
	"3": "Head of Department",
	"4": "Director"
};

window.onload = () => {
	getEmployee();

	// Event handlers
	document.getElementById("logOutBtn").onclick = () => {
		logOut();
	};
};

// Get employee data from server
let getEmployee = () => {
	let endpoint = "/employee?eId=" + getUrlParam("eId");

	fetch(endpoint).then((res) => res.json()).then((json) => {
		populateEmployee(json);
	});
};

// Populate employee data on page
let populateEmployee = (json) => {
	let header = document.getElementById("name");
	header.innerText = json["firstName"] + " " + json["lastName"];
	document.getElementById("id").innerText = json["id"];
	document.getElementById("email").innerText = json["email"];
	document.getElementById("role").innerText = roles[json["level"]];
	if (json["reportsTo"] === 0) {
		document.getElementById("manager").innerText = "None";
	} else {
		let link = document.createElement("a");
		link.href = "/employee/profile?eId=" + json["reportsTo"];
		link.innerText = json["reportsTo"];
		document.getElementById("manager").appendChild(link);
	}
};

// Get ID parameter from URL
let getUrlParam = (param) => {
	let query = window.location.search.substring(1);
	let vars = query.split("&");

	for (let i=0; i<vars.length; i++) {
		let pair = vars[i].split("=");
		if (pair[0] === param) {
			return pair[1];
		}
	}

	return false;
};
