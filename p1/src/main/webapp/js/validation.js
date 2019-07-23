const eId = sessionStorage.getItem("id");

// Validate user
const validate = () => {
	if (eId == null) {
		window.location.replace("/login");
	}
};

// Clear session and forward to log in page
const logOut = () => {
	sessionStorage.clear();
	window.location.replace("/login");
};

export {validate, logOut};
