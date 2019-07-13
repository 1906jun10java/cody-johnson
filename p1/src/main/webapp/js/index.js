window.onload = () => {
	let form = document.forms.signInForm;
	form.onSubmit = (e) => {
		let formData = new FormData(form);
		fetch("/login", {
			method: "POST",
			mode: "cors",
			headers: {
				"Content-Type": "application/json"
			},
			body: data
		});
	}
}
