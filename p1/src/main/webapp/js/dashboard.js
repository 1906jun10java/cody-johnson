import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

let reimbursementTableHeaders = [
	"Type", "Status", "Amount", "Date", "Info"
];

let isManager = sessionStorage.getItem("level") > 1;

window.onload = () => {
	setHeading();
	setMyProfileLink();
	loadUserReimbursements();

	if (isManager) {
		addDirectoryNavItem();

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
		} else {
			populateUserReimbursementTable(json);
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
		} else {
			populateSubordinateReimbursementTable(json);
		}
	});
};

// Populate subordinate reimbursements table
let populateUserReimbursementTable = (json) => {
	let div = document.getElementById("userReimbursements");

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
	div.appendChild(table);
};

// Populate subordinate reimbursements table
let populateSubordinateReimbursementTable = (json) => {
	let managerLevel = sessionStorage.getItem("level");
	let div = document.getElementById("subordinateReimbursements");

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
			if (managerLevel && element["statusId"] === managerLevel - 1) {
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
			}
		});
	}

	table.appendChild(tableBody);
	div.appendChild(table);
};
