function createAccount(username, password){
	$.post('/createAccount', {"username": username, "password": password}, responseJSON => {
		const respObject = JSON.parse(responseJSON);
		if (respObject.exists === false){
			alert("username already exists");
		}
		else{
			signIn(username, password);
		}
	});
}

function signIn(username, password){
	$.post('/signIn', {"username": username, "password": password}, responseJSON => {
		const respObject = JSON.parse(responseJSON);
		console.log(respObject);
		if (respObject.signedIn === false){
			alert("invalid username or password");
		}
		else{
			//user successfully signed in
			console.log(respObject);
		}
	});
}

$(document).ready(() => {

	$("#signIn").click(event => {
		signIn(document.getElementById("username").value, document.getElementById("password").value);
	});
	
	$("#createAccount").click(event => {
		createAccount(document.getElementById("username").value, document.getElementById("password").value);
	});
	
	




 });