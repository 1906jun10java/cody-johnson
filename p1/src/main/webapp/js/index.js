window.onload = () => {
	document.getElementById("signInForm").onsubmit = (e) => {
		e.preventDefault();
		handleFormSubmit();
	};
};

let handleFormSubmit = () => {
	const form = document.getElementById("signInForm");
	let data = new FormData(form);

	fetch("/login", {
		method: "POST",
		body: data
	}).then((res) => res.json()).then((json) => {
		if (json.error) {
			console.log(json.error);
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
