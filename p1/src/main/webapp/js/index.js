let form = document.getElementById("signInForm");

window.onload = () => {
	form.onsubmit = (e) => {
		e.preventDefault();

		let data = {};
		let formData = new FormData(form);
		for (let entry of formData.entries()) {
			data[entry[0]] = entry[1];
		}

		const dataString = JSON.stringify(data);
		handleFormSubmit(dataString);
	};
};

function handleFormSubmit(dataString) {
	fetch("/login", {
		method: "post",
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		},
		body: dataString
	}).then((res) => res.json()).then((json) => {
		if (json.error) {
			console.log(json.error);
		} else {
			sessionStorage.setItem("id", `${json.id}`);
			sessionStorage.setItem("firstName", `${json.firstName}`);
			window.location.replace("/dashboard");
		}
	});
}
