import {logOut, validate} from "./validation.js";

// Check if user is valid
validate();

let reimbursementTableHeaders = [
    "Type", "Status", "Amount", "Date"
];

let reimbursementStatuses = {
    "-1": "Rejected",
    "0": "More Information Required",
    "1": "Initialized",
    "2": "Supervisor Approved",
    "3": "Department Head Approved",
    "4": "Accepted"
};

let reimbursementTypes = {
    "1": "Seminar",
    "2": "Preparation Classes",
    "3": "Certification",
    "4": "Technical Training",
    "5": "Other"
};

window.onload = () => {
    setHeading();
    loadUserReimbursements();

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
    let endpoint = "/reimbursement/employee/?eId=";
    endpoint += sessionStorage.getItem("id");

    fetch(endpoint).then((res) => res.json()).then((json) => {
        populateUserReimbursements(json);
    });
};

// Populate user reimbursements table
let populateUserReimbursements = (json) => {
    let userReimbursementsDiv = document.getElementById("userReimbursements");

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
                let td = document.createElement("td");
                if (key === "statusId") {
                    td.innerText = reimbursementStatuses[element[key]];
                } else if (key === "typeId") {
                    td.innerText = reimbursementTypes[element[key]];
                } else if (key === "receiptImgFile" || key === "id" ||
                key === "employeeId" || key === "description") {
                    continue;
                } else if (key === "amount") {
                    td.innerText = "$" + element[key].toFixed(2);
                } else if (key === "unixTs") {
                    let date = new Date(element[key]*1000);
                    td.innerText = `${date.getMonth()}-${date.getDate()}-${date.getFullYear()}`;
                } else {
                    td.innerText = element[key];
                }
                tr.appendChild(td);
            }
        }
        table.appendChild(tr);
    });
    userReimbursementsDiv.appendChild(table);
};
