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
			sessionStorage.setItem("firstName", `${json.firstName}`);
			window.location.replace("/dashboard");
		}
	});
};
