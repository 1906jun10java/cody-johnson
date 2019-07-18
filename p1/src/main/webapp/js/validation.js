// Validate user
const validate = () => {
	if (sessionStorage.getItem("id") == null) {
		window.location.replace("/login");
	}
};

// Clear session and forward to log in page
const logOut = () => {
	sessionStorage.clear();
	window.location.replace("/login");
};

export {validate, logOut};
