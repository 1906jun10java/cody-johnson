let books = [
	{
		title:"Spring MVC: Beginner's Guide",
		author:"Amuthan G",
		genre:"Programming",
		cover:"https://images-na.ssl-images-amazon.com/images/I/41ScMbxmwGL._SX404_BO1,204,203,200_.jpg"
	},
	{
		title:"Core Java Volume I - Fundamentals",
		author:"Cay S. Horstmann",
		genre:"Programming",
		cover:"https://images-na.ssl-images-amazon.com/images/I/41DrpvyFkoL._SX384_BO1,204,203,200_.jpg"
	}
];

window.onload = () => {
	let addBookForm = document.getElementById("addBookForm");
	addBookForm.onsubmit = (e) => {
		e.preventDefault();

		let data = {};
		let formData = new FormData(addBookForm);
		for (let entry of formData.entries()) {
			data[entry[0]] = entry[1];
		}

		addBook(data);

		let inputs = document.getElementsByTagName("input");
		for (let i=0; i<inputs.length; i++) {
			inputs[i].value = "";
		}
	}

	loadBooks();
}

function loadBooks() {
	let bookListElement = document.getElementById("bookList");

	books.forEach((bookObj) => {
		let book = document.createElement('div');
		book.className = "book";

		let title = document.createElement('div');
		title.innerText = bookObj.title;
		book.append(title);

		let author = document.createElement('div');
		author.innerText = bookObj.author;
		book.append(author);

		let genre = document.createElement('div');
		genre.innerText = bookObj.genre;
		book.append(genre);

		let cover = document.createElement('img');
		cover.src = bookObj.cover;
		cover.alt = "Cover image";
		book.append(cover);

		bookListElement.append(book);
		bookListElement.append(document.createElement("br"));
	});
}

function addBook(data) {
	let bookListElement = document.getElementById("bookList");

	let book = document.createElement('div');
	book.className = "book";

	let title = document.createElement('div');
	title.innerText = data.title;
	book.append(title);

	let author = document.createElement('div');
	author.innerText = data.author;
	book.append(author);

	let genre = document.createElement('div');
	genre.innerText = data.genre;
	book.append(genre);

	let cover = document.createElement('img');
	cover.src = data.cover;
	cover.alt = "Cover image";
	book.append(cover);

	bookListElement.append(book);
	bookListElement.append(document.createElement("br"));
}
