import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

// Table headers
let reimbursementTableHeaders = [
	"Type", "Status", "Amount", "Date", "Info"
];

// Lookup for employee role
let roles = {
	"1": "Engineer",
	"2": "Supervisor",
	"3": "Head of Department",
	"4": "Director"
};

let isManager = sessionStorage.getItem("level") > 1;

window.onload = () => {
	if (isManager) {
		addDirectoryNavItem();
	}
	if (isManager && getUrlParam("eId") !== sessionStorage.getItem("id")) {
		loadUserReimbursements();
	}

	setMyProfileLink();
	getEmployee();

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

// Get user's reimbursements
let loadUserReimbursements = () => {
	let endpoint = "/reimbursement/employee?eId=" + getUrlParam("eId");

	fetch(endpoint).then((res) => res.json()).then((json) => {
		if (json.error) {
			console.log(json.error);
		} else {
			populateUserReimbursementTable(json);
		}
	});
};

// Populate subordinate reimbursements table
let populateUserReimbursementTable = (json) => {
	let divRow = document.getElementById("reimbursements");

	let divCol = document.createElement("div");
	divCol.className = "col";
	let title = document.createElement("h5");
	title.innerText = "Reimbursements";
	divCol.append(title);

	let table = document.createElement("table");
	let tableBody = document.createElement("tbody");
	let tableHead = document.createElement("thead");
	let tableHeadRow = document.createElement("tr");

	table.className = "table table-sm";
	reimbursementTableHeaders.forEach(header => {
		let th = document.createElement("th");
		th.innerText = header;
		tableHeadRow.appendChild(th);
	});
	tableHead.appendChild(tableHeadRow);
	table.appendChild(tableHead);
	if (json.length > 0) {
		json.forEach(element => {
			let tr = document.createElement("tr");

			let statusTd = document.createElement("td");
			statusTd.innerText = element["statusName"];
			tr.appendChild(statusTd);

			let typeTd = document.createElement("td");
			typeTd.innerText = element["typeName"];
			tr.appendChild(typeTd);

			let amountTd = document.createElement("td");
			amountTd.innerText = `\$${element["amount"].toFixed(2)}`;
			tr.appendChild(amountTd);

			let dateTd = document.createElement("td");
			let date = new Date(element["unixTs"]*1000);
			let month = date.getMonth();
			let day = date.getDate();
			let year = date.getFullYear();
			dateTd.innerText = `${month}-${day}-${year}`;
			tr.appendChild(dateTd);

			let imgTd = document.createElement("td");
			let img = document.createElement("a");
			img.href = "/reimbursement/view?id=" + element["id"];
			img.innerText = "View";
			imgTd.appendChild(img);
			tr.appendChild(imgTd);

			tableBody.appendChild(tr);
		});
	}
	table.appendChild(tableBody);
	divCol.appendChild(table);
	divRow.appendChild(divCol);
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
