let teams = {};

window.onload = () => {
	let teamIdForm = document.getElementById("teamIdForm");
	teamIdForm.onsubmit = (e) => {
		e.preventDefault();

		let data = {};
		let formData = new FormData(teamIdForm);
		for (let entry of formData.entries()) {
			data[entry[0]] = entry[1];
		}

		getTeam(data);
	}
}

function getTeam(data) {
	let endpoint = "https://statsapi.web.nhl.com/api/v1/teams/";
	endpoint += data.id;

	fetch(endpoint, {
		method: "get"
	}).then((res) => res.json()).then((json) => {
		displayData(json);
	});
}

function displayData(json) {
	let dataDiv = document.getElementById("dataDiv");

	let team = document.createElement("h3");
	team.innerText = json.teams[0].name;

	let website = document.createElement("a");
	website.innerText = "Official Website";
	website.href = json.teams[0].officialSiteUrl;

	dataDiv.innerHTML = "";
	dataDiv.append(team);
	dataDiv.append(website);
}
