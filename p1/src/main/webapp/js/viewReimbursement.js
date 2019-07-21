import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

window.onload = () => {
	// Hide manager actions
	if (sessionStorage.getItem("level") > 1) {
		document.getElementById("rejectBtn").style.visibility = "visible";
		document.getElementById("acceptBtn").style.visibility = "visible";
	} else {
		document.getElementById("rejectBtn").style.visibility = "hidden";
		document.getElementById("acceptBtn").style.visibility = "hidden";
	}

	getReimbursement();

	// Event handlers
	document.getElementById("logOutBtn").onclick = () => {
		logOut();
	};
	document.getElementById("rejectBtn").onclick = () => {
		rejectReimbursement();
	};
	document.getElementById("acceptBtn").onclick = () => {
		acceptReimbursement();
	};
};

// Populate data
let populateReimbursement = (json) => {
	for (let key in json) {
		if (json.hasOwnProperty(key)) {
			if (key === "id") {
				document.getElementById("id").innerText = "Reimbursement #" + json[key];
			} else if (key === "employeeId") {
				document.getElementById("employee").innerText = json[key];
			} else if (key === "typeName") {
				document.getElementById("type").innerText = json[key];
			} else if (key === "statusName") {
				document.getElementById("status").innerText = json[key];
			} else if (key === "amount") {
				document.getElementById("amount").innerText = `\$${json[key].toFixed(2)}`;
			} else if (key === "unixTs") {
				let date = new Date(json[key]*1000);
				let month = date.getMonth();
				let day = date.getDate();
				let year = date.getFullYear();
				document.getElementById("date").innerText = `${month}-${day}-${year}`;
			} else if (key === "description") {
				document.getElementById("description").innerText = json[key];
			} else if (key === "receiptImgFile") {
				let receiptImg = document.createElement("img");
				receiptImg.src = getBase64FileType(json[key]) + json[key];
				receiptImg.alt = "Receipt image";
				document.getElementById("receiptImgDiv").appendChild(receiptImg);
			}
		}
	}
};

// Get reimbursement from server
let getReimbursement = () => {
	let endpoint = "/reimbursement?id=" + getUrlParam("id");

	fetch(endpoint).then((res) => res.json()).then((json) => {
		populateReimbursement(json);
	});
};

// Reject reimbursement


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

// Get file type of base64 encoded image
let getBase64FileType = (b64Image) => {
	switch (b64Image.charAt(0)) {
		case '/': return "data:image/jpg;base64,";
		case 'i': return "data:image/png;base64,";
		case 'R': return "data:image/gif;base64,";
		case 'U': return "data:image/webp;base64,";
		default: return "";
	}
};
