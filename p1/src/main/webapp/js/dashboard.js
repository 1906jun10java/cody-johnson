import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

let reimbursementTableHeaders = [
	"Type", "Status", "Amount", "Date", "Info"
];

let isManager = sessionStorage.getItem("level") > 1;

window.onload = () => {
	setHeading();
	loadUserReimbursements();
	if (isManager) {
		let div = document.getElementById("subordinateReimbursements");
		let tableTitle = document.createElement("h5");
		tableTitle.innerText = "Subordinate Reimbursements";
		div.appendChild(tableTitle);
		loadSubordinateReimbursements();
	}

	// Event handlers
	document.getElementById("logOutBtn").onclick = () => {
		logOut();
	};
};

// Set welcome message in heading
let setHeading = () => {
	let eFirstName = sessionStorage.getItem("firstName");
	let heading = document.getElementById("heading");
	heading.innerText = `Welcome, ${eFirstName}`;
};

// Get user's reimbursements
let loadUserReimbursements = () => {
	let endpoint = "/reimbursement/employee?eId=";
	endpoint += sessionStorage.getItem("id");

	fetch(endpoint).then((res) => res.json()).then((json) => {
		if (json.error) {
			console.log(json.error);
		} else if (json.status) {
			let status = document.createElement("p");
			status.innerText = json.status;
			document.getElementById("userReimbursements").appendChild(status);
		} else {
			populateReimbursementTable(json, "user", null);
		}
	});
};

// Get subordinate reimbursements
let loadSubordinateReimbursements = () => {
	let endpoint = "/reimbursement/subordinatesof?eId=";
	endpoint += sessionStorage.getItem("id");

	fetch(endpoint).then((res) => res.json()).then((json) => {
		if (json.error) {
			console.log(json.error);
		} else if (json.status) {
			let status = document.createElement("p");
			status.innerText = json.status;
			document.getElementById("subordinateReimbursements").appendChild(status);
		} else {
			let managerLevel = sessionStorage.getItem("level");
			populateReimbursementTable(json, "subordinates", managerLevel);
		}
	});
};

// Populate reimbursements table
let populateReimbursementTable = (json, flag, managerLevel) => {
	let div = null;
	if (flag === "user") {
		div = document.getElementById("userReimbursements");
	} else if (flag === "subordinates") {
		div = document.getElementById("subordinateReimbursements");
	} else {
		return;
	}

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

	json.forEach(element => {
		let tr = document.createElement("tr");
		for (let key in element) {
			if (element.hasOwnProperty(key)) {
				if (managerLevel && element["statusId"] !== managerLevel - 1) {
					continue;
				}
				let td = document.createElement("td");
				if (key === "statusName") {
					td.innerText = element[key];
				} else if (key === "typeName") {
					td.innerText = element[key];
				} else if (key === "amount") {
					td.innerText = "$" + element[key].toFixed(2);
				} else if (key === "unixTs") {
					let date = new Date(element[key]*1000);
					let month = date.getMonth();
					let day = date.getDate();
					let year = date.getFullYear();
					td.innerText = `${month}-${day}-${year}`;
				} else {
					continue;
				}
				tr.appendChild(td);
			}
			tableBody.appendChild(tr);
		}
		let td = document.createElement("td");
		let link = document.createElement("a");
		link.href = "/reimbursement/view?id=" + element["id"];
		link.innerText = "View";
		td.appendChild(link);
		tr.appendChild(td);
		tableBody.appendChild(tr);
	});
	table.appendChild(tableBody);
	div.appendChild(table);
};
