window.onload = () => {
	// Event listeners
	document.getElementById("signInForm").onsubmit = (e) => {
		e.preventDefault();
		handleFormSubmit();
	};
	document.getElementById("email").oninput = (e) => {
		clearErrors();
	};
	document.getElementById("password").oninput = (e) => {
		clearErrors();
	};
};

let displayErrors = (error) => {
	let form = document.getElementById("signInForm");
	let div = document.createElement("div");
	div.id = "errors";
	let p = document.createElement("p");
	p.innerText = error;
	div.appendChild(p);
	form.appendChild(div);
};

let clearErrors = () => {
	let errors = document.getElementById("errors");
	if (errors) {
		errors.remove();
	}
};

let handleFormSubmit = () => {
	const form = document.getElementById("signInForm");
	let data = new FormData(form);

	fetch("/login", {
		method: "POST",
		body: data
	}).then((res) => res.json()).then((json) => {
		if (json.error) {
			displayErrors(json.error);
		} else {
			sessionStorage.setItem("id", `${json.id}`);
			sessionStorage.setItem("email", `${json.email}`);
			sessionStorage.setItem("firstName", `${json.firstName}`);
			sessionStorage.setItem("lastName", `${json.lastName}`);
			sessionStorage.setItem("level", `${json.level}`);
			sessionStorage.setItem("reportsTo", `${json.reportsTo}`);
			window.location.replace("/dashboard");
		}
	});
};
