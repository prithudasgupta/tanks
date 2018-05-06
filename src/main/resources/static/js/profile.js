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

function getUser(){
	$.post('/authenticate', {}, responseJSON => {
		const respObject = JSON.parse(responseJSON);
		console.log(respObject);
		if (respObject.id === -1){
			console.log("invalid");
			document.getElementById("login").style.display = "block";
		}
			console.log(respObject.id);
			return respObject.id;
	});
}

function signIn(username, password){
	$.post('/signIn', {"username": username, "password": password}, responseJSON => {
	
		const respObject = JSON.parse(responseJSON);
		if (respObject === undefined){
			alert("invalid username or password");
		}
		else{
			//user successfully signed in
			$('#signin').toggle();
			 $('#profile').toggle();
			 userData();
			 new_send(getUserPayload());
			 
		}
	});
}

function logout(){
	$.post('/logout', {}, responseJSON => {
		
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