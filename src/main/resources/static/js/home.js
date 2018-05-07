

let TILE_SIZE = 45;
let numRows = 10;
let numCols = 12;

let USER_SPEED = 2.5;
let BULLET_SPEED = 5;
let USER_ROT = 0.04;

let sKey, aKey, wKey, dKey, space;
let mousX, mousY;

let user;
let nonTrav = [];

let id;
let username;

// create the Scene object and create the map
let scene = sjs.Scene({w:numCols * TILE_SIZE, h:numRows * TILE_SIZE});
let game = scene.Layer('background', {useCanvas:false, autoClear:true});

class Tile {
    constructor(sprite, type) {
        this.sprite = sprite;
        this.type = type;
        this.perWall = false;
    }

}

let map = [];

for (let row = 0; row < numRows; row++) {
    map[row] = [];
    for (let col = 0; col < numCols; col++) {
        if (row === 0 || row === (numRows - 1) || col === 0 || col === (numCols - 1)) {
            map[row][col] = "u";
        } else {
            map[row][col] = "t";
        }
    }
}

function loadMap() {
    scene.loadImages(["/sprites/wall.png", "/sprites/freeSpace.png", "/sprites/pothole.png", "/sprites/breakable.png",
        "/sprites/imm_tank.png", "/sprites/tank_space.png", "/sprites/selected.png", "/sprites/menu.png", "/sprites/select.png"], function () {
        // loading map into view
        for (let row = 0; row < numRows; row++) {
            for (let col = 0; col < numCols; col++) {
                let next;
                if (map[row][col] === "u") {
                    next = game.Sprite("/sprites/wall.png");
                    map[row][col] = new Tile(next, "u");
                    map[row][col].perWall = true;
                    nonTrav.push(map[row][col].sprite);
                } else {
                    next = game.Sprite("/sprites/freeSpace.png");
                    map[row][col] = new Tile(next, "t");
                }
                next.move(col * TILE_SIZE, row * TILE_SIZE);
                next.update();
            }
        }
    });

    scene.loadImages(["/sprites/one.png", "/sprites/two.png", "/sprites/three.png", "/sprites/tank.png",
        "/sprites/tank_cannon.png", "/sprites/bullet.png", "/sprites/tTreads.png",
        "/sprites/explo2.png", "/sprites/explo1.png", "/sprites/explo3.png"], function() {
        let userTank = game.Sprite("/sprites/tank.png");
        let cannon = game.Sprite("/sprites/tank_cannon.png")
        // put in location
        userTank.move(50, 50);
        cannon.move(50, 50);
        // update it
        userTank.scale(1.25);
        cannon.scale(1.25);

        userTank.update();
        cannon.update();
        user = userTank;
        user.lastFire = Date.now();
        uCannon = cannon;
        ready = true;

        window.requestAnimationFrame(main);

    });
}

function visitPage(whereTo){
    const urlArr = document.URL.split("/");
    let newUrl = "";
    switch(whereTo){
        case "Main":
            newUrl = "/home";
            break;
        case "Next":
            if(survival){
                $.post('/nextRound', {}, responseJSON => {
                    location.reload();
                });
            }
            const nextLevel = parseInt(urlArr[urlArr.length -1])+1;
            if(nextLevel > 20 && survival !== true){
                alert("Congratulations! you finished all campaign levels");
                return;
            }
            newUrl = nextLevel;

            break;
        case "survival":
            newUrl = "/tank/game/survival";
    }
    window.location.href = newUrl;
}


function forwardByAngle(angRads, speed) {
    let x = speed * Math.cos(angRads);
    let y = speed * Math.sin(angRads);
    return [x,y];
}

function backwardByAngle(angRads, speed) {
    let x = -(speed * Math.cos(angRads));
    let y = -(speed * Math.sin(angRads));
    return [x,y];
}

function userMove() {
    if (wKey) {
        let mov = forwardByAngle(user.angle, USER_SPEED);
        user.move(mov[0], mov[1]);
        uCannon.move(mov[0], mov[1]);
        if (user.collidesWithArray(nonTrav)) {
            // if there is a collision revert back to old location
            user.move(-mov[0], -mov[1]);
            uCannon.move(-mov[0], -mov[1]);
        } else {
            uCannon.update();
            user.update();
            //placeTread(user.x-mov[0], user.y-mov[1], user.angle);
        }
    }
    if (sKey) {
        let mov = backwardByAngle(user.angle, USER_SPEED);
        user.move(mov[0], mov[1]);
        uCannon.move(mov[0], mov[1]);
        if (user.collidesWithArray(nonTrav)) {
            user.move(-mov[0], -mov[1]);
            uCannon.move(-mov[0], -mov[1]);
        } else {
            uCannon.update();
            user.update();
            //placeTread(user.x-mov[0], user.y-mov[1], user.angle);
        }
    }
    if (aKey) {
        user.rotate(-USER_ROT);
        if (user.collidesWithArray(nonTrav)) {
            user.rotate(USER_ROT);
        }
        user.update();
    }
    if (dKey) {
        user.rotate(USER_ROT);
        if (user.collidesWithArray(nonTrav)) {
            user.rotate(-USER_ROT);
        }
        user.update();
    }
    if (ready) {
        let dx = mousX - user.x;
        let dy = mousY - user.y;
        rot = Math.atan2(dy, dx);
        uCannon.setAngle(0);
        uCannon.rotate(rot);
        uCannon.update();
    }
}

function generateFriendsList(friendsList) {

    let table = document.getElementById("friendsTable");
    let tableBody = document.createElement("tbody");

    let firstRow  = document.createElement("tr");
    let textTitle1 = document.createTextNode("Username");
    let textTitle2 = document.createTextNode("Status");
    let title1 = document.createElement("th");
    let title2 = document.createElement("th");
    title1.appendChild(textTitle1);
    title2.appendChild(textTitle2);
    firstRow.appendChild(title1);
    firstRow.appendChild(title2);
    tableBody.appendChild(firstRow);

    for(let curRow = 0; curRow < friendsList.length; curRow++) {
        let row = document.createElement("tr");
        for (let c = 0; c < 2; c++) {
            let text;
            let cell = document.createElement("td");
            if (c === 0) {
                text = document.createTextNode(friendsList[curRow].friendName);
                cell.appendChild(text);
            } else {
                let status = friendsList[curRow].status;
                if (status === 0) {
                    text = document.createTextNode("Friend");
                    cell.appendChild(text);
                } else if (status === 1) {
                    text = document.createTextNode("Pending");
                    cell.appendChild(text);
                } else {
                    let but = document.createElement("button");
                    but.onclick = function () { friendRequest(friendsList[curRow].friend, but) };
                    text = document.createTextNode("Accept");
                    but.appendChild(text);
                    cell.appendChild(but);
                }
            }

            row.appendChild(cell);
        }
        tableBody.appendChild(row);
    }

    table.appendChild(tableBody);

}

function friendRequest(friendId, but) {

    $.post('/friendAccept', {"friendId": friendId}, responseJSON => {
        const respObject = JSON.parse(responseJSON);
    });
    but.disabled = true;
    but.innerHTML = "accepted";
}

// variables keeping track of the next friend game
let nextFriendId = -1;
let selected;

function selectFriend(button, id) {
    if (selected === button) {

    } else if (selected === undefined) {
        nextFriendId = id;
        selected = button;
        selected.innnerHTML = "CHOSEN";
    } else {
        selected.innnerHTML = "select";
        nextFriendId = id;
        selected = button;
        selected.innnerHTML = "CHOSEN";
    }
}

function generateFriendsMult(friendsList) {

    let table = document.getElementById("friendsTable2");
    let tableBody = document.createElement("tbody");

    let firstRow  = document.createElement("tr");
    let textTitle1 = document.createTextNode("Username");
    let title1 = document.createElement("th");
    title1.appendChild(textTitle1);
    firstRow.appendChild(title1);
    tableBody.appendChild(firstRow);

    for(let curRow = 0; curRow < friendsList.length; curRow++) {
        let row = document.createElement("tr");
        for (let c = 0; c < 2; c++) {
            let text;
            let status = friendsList[curRow].status;
            let cell = document.createElement("td");
            if (status === 0) {
                if (c === 0) {
                    text = document.createTextNode(friendsList[curRow].friendName);
                    cell.appendChild(text);
                } else {
                    let select = document.createElement("button");
                    select.setAttribute("id", "friendSelect");
                    select.onclick = function() { selectFriend(select, friendsList[curRow].friend) };
                    select.appendChild(document.createTextNode("select"));
                    cell.appendChild(select);
                }
                row.appendChild(cell);
            }
        }
        tableBody.appendChild(row);
    }

    table.appendChild(tableBody);

}

$("friendSelect").on("click",function(){
	$("tr").each(function(){
		$(this).removeClass("marked");
	});
	$(this).closest("tr").addClass("marked");
});

function generateMapsList(gameList) {

    let table = document.getElementById("mapsTable");
    let tableBody = document.createElement("tbody");

    let firstRow  = document.createElement("tr");
    let textTitle1 = document.createTextNode("Map ID");
    let textTitle2 = document.createTextNode("Options");
    let title1 = document.createElement("th");
    let title2 = document.createElement("th");
    title1.appendChild(textTitle1);
    title2.appendChild(textTitle2);
    firstRow.appendChild(title1);
    firstRow.appendChild(title2);
    tableBody.appendChild(firstRow);

    for(let curRow = 0; curRow < gameList.length; curRow++) {
        let row = document.createElement("tr");
        for (let c = 0; c < 2; c++) {
            let text;
            let cell = document.createElement("td");
            // id of the game
            if (c === 0) {
                text = document.createTextNode(gameList[curRow]);
                cell.appendChild(text);
            } else {
                // options
                let play = document.createElement("button");
                play.onclick = function() { loadCampLevel(gameList[curRow]); };
                play.appendChild(document.createTextNode("play"));
                cell.appendChild(play);
            }

            row.appendChild(cell);
        }
        tableBody.appendChild(row);
    }

    table.appendChild(tableBody);

}


function generateMapsMult(gameList, campaign) {
    let table = document.getElementById("gamesTable");
    let tableBody = document.createElement("tbody");
    let firstRow  = document.createElement("tr");
    let textTitle1 = document.createTextNode("Game ID");
    let textTitle2 = document.createTextNode("Options");
    let title1 = document.createElement("th");
    let title2 = document.createElement("th");
    title1.appendChild(textTitle1);
    title2.appendChild(textTitle2);
    firstRow.appendChild(title1);
    firstRow.appendChild(title2);
    tableBody.appendChild(firstRow);


    for(let curRow = 0; curRow < campaign; curRow++) {
        let row = document.createElement("tr");
        for (let c = 0; c < 2; c++) {
            let text;
            let cell = document.createElement("td");
            // id of the game
            if (c === 0) {
                text = document.createTextNode(curRow);
                cell.appendChild(text);
            } else {
                // options
                let play = document.createElement("button");
                play.onclick = function() { loadMultiplayer(curRow, -1)};
                play.appendChild(document.createTextNode("play"));
                cell.appendChild(play);
            }

            row.appendChild(cell);
        }
        tableBody.appendChild(row);
    }

    for(let curRow = 0; curRow < gameList.length; curRow++) {
        let row = document.createElement("tr");
        for (let c = 0; c < 2; c++) {
            let text;
            let cell = document.createElement("td");
            // id of the game
            if (c === 0) {
                text = document.createTextNode(gameList[curRow]);
                cell.appendChild(text);
            } else {
                // options
                let play = document.createElement("button");
                play.onclick = function() { loadMultiplayer(gameList[curRow], -1)};
                play.appendChild(document.createTextNode("play"));
                cell.appendChild(play);
            }

            row.appendChild(cell);
        }
        tableBody.appendChild(row);
    }
    table.appendChild(tableBody);

}

function addFriend(){
	 $.post('/friendRequest', {"username": document.getElementById("friendUse").value}, responseJSON => {
		//addToTable
	});
}

function parseTime(time) {
    const totalSeconds = parseInt(time / 1000);
    const seconds = totalSeconds % 60;
    const minutes = parseInt(totalSeconds / 60);
    let timeString = "";
    if (minutes < 10){
        timeString += "0";
    }
    timeString += minutes.toString();
    timeString += ":";
    if (seconds < 10){
        timeString += "0";
    }
    timeString += seconds.toString();
    return timeString;
}

function updateProfilePage(profile) {
    document.getElementById("user-heading1").innerHTML = "Profile : " + profile.username;
    document.getElementById("killstat").innerHTML = "Total Kills : " + profile.kills;
    let time = profile.time;
    let timeString = parseTime(time);
    timeString = "Total Time Played : " + timeString;
    document.getElementById("timestat").innerHTML = timeString;
    document.getElementById("campstat").innerHTML = "Campaign : " + profile.campaign;
    document.getElementById("survivalstat").innerHTML = "Survival : " + profile.bestSurvival;


}

function userData() {
    $.post('/profileData', {}, responseJSON => {
        const respObject = JSON.parse(responseJSON);
        if(respObject !== "none") {
            console.log(respObject);
            let friendsList = respObject.friends;
            let mapsList = respObject.games;
            let profile = respObject.profile;
            let campaign = profile.campaign;
            let inbox = respObject.inbox;
            username = profile.username;
            id = profile.id;
            generateFriendsList(friendsList);
            updateProfilePage(profile);
            generateMapsList(mapsList);
            getLeaderboardLists();
            setupCampaign(campaign);
            generateMapsMult(mapsList, campaign);
            generateFriendsMult(friendsList);
            generateInbox(inbox);
        }
    });
}

function generateInbox(list) {
    let table = document.getElementById("inboxTable");
    let tableBody = document.createElement("tbody");

    let firstRow  = document.createElement("tr");
    let textTitle1 = document.createTextNode("Opponent");
    let textTitle2 = document.createTextNode("Game ID");
    let textTitle3 = document.createTextNode("Result/Pending");
    let title1 = document.createElement("th");
    let title2 = document.createElement("th");
    let title3 = document.createElement("th");
    title1.appendChild(textTitle1);
    title2.appendChild(textTitle2);
    title3.appendChild(textTitle3);
    firstRow.appendChild(title1);
    firstRow.appendChild(title2);
    firstRow.appendChild(title3);
    tableBody.appendChild(firstRow);

    for(let curRow = 0; curRow < list.length; curRow++) {
        let row = document.createElement("tr");
        for (let c = 0; c < 3; c++) {
            let text;
            let cell = document.createElement("td");
            if (c === 0) {
                text = document.createTextNode(list[curRow].username2);
                cell.appendChild(text);
            }else if (c === 1) {
                text = document.createTextNode(list[curRow].gameId);
                cell.appendChild(text);
            }  else {
                let state = list[curRow].isOver;
                let text1;
                switch (state) {
                    case 1:
                        let play = document.createElement("button");
                        play.onclick = function() {loadMultiplayer(list[curRow].gameId, list[curRow].id2) };
                        play.appendChild(document.createTextNode("Play"));
                        cell.appendChild(play);
                        break;
                    case 0:
                        if (list[curRow].winner === true) {
                            text1 = document.createTextNode("Won!");
                        } else {
                            text1 = document.createTextNode("Lost...");
                        }
                        cell.appendChild(text1);
                        break;
                    case -1:
                        text1 = document.createTextNode("Pending");
                        cell.appendChild(text1);
                        break;

                }
            }
            row.appendChild(cell);
        }
        tableBody.appendChild(row);
    }

    table.appendChild(tableBody);

}

function getLeaderboardLists() {
    $.post('/leaderboard', {}, responseJSON => {
        const respObject = JSON.parse(responseJSON);
        let killsGlobal = respObject.kills.first;
        let killsFriends = respObject.kills.second;
        let survivalGlobal = respObject.survival.first;
        let survivalFriends = respObject.survival.second;
        let timeGlobal = respObject.time.first;
        let timeFriends = respObject.time.second;
        generateLeaderboard("timeGlobal", timeGlobal, true);
        generateLeaderboard("timeFriends", timeFriends, true);
        generateLeaderboard("survivalGlobal", survivalGlobal, false);
        generateLeaderboard("survivalFriends", survivalFriends, false);
        generateLeaderboard("killsGlobal", killsGlobal, false);
        generateLeaderboard("killsFriends", killsFriends, false);

    });
}

function setupCampaign(level) {
    for(let i = 0; i < 20; i++){
        if (i < level) {
            $('div#levels').append("<img id='level' onclick='loadCampLevel(" + i + ")' src='images/mapIcons/mapIcon" + i + ".png'/> </img>");
        } else {
            $('div#levels').append("<img id='level' src='images/mapIcon_lock.png'/> </img>");
        }
    }
}

function generateLeaderboard(id, list, time) {
    let table = document.getElementById(id);
    let tableBody = document.createElement("tbody");

    let firstRow  = document.createElement("tr");
    let textTitle1 = document.createTextNode("Rank");
    let textTitle2 = document.createTextNode("User ID");
    let textTitle3 = document.createTextNode("Kills/Time/Survival");
    let title1 = document.createElement("th");
    let title2 = document.createElement("th");
    let title3 = document.createElement("th");
    title1.appendChild(textTitle1);
    title2.appendChild(textTitle2);
    title3.appendChild(textTitle3);
    firstRow.appendChild(title1);
    firstRow.appendChild(title2);
    firstRow.appendChild(title3);
    tableBody.appendChild(firstRow);

    for(let curRow = 0; curRow < list.length; curRow++) {
        let row = document.createElement("tr");
        for (let c = 0; c < 3; c++) {
            let text;
            let cell = document.createElement("td");
            if (c === 0) {
                text = document.createTextNode(curRow+1);
                cell.appendChild(text);
            }else if (c === 1) {
                text = document.createTextNode(list[curRow].first);
                cell.appendChild(text);
            }  else {
                if (time) {
                    text = document.createTextNode(parseTime(list[curRow].second));
                } else {
                    text = document.createTextNode(list[curRow].second);
                }
                cell.appendChild(text);
            }
            row.appendChild(cell);
            row.setAttribute("width", "100%");
        }
        tableBody.appendChild(row);
    }
    tableBody.setAttribute("width", "100%");
    tableBody.setAttribute("board-collapse", "collapse");
    table.appendChild(tableBody);

}


function switchFilter(current, switchTo, type, next) {
    let cur = document.getElementById(current);
    if (cur.style.display !== "none") {
        cur.style.display = "none";
        document.getElementById(switchTo).style.display = "block";
        document.getElementById(switchTo).style.width = "100%";
        document.getElementById(switchTo).style.borderCollapse = "collapse";
        console.log(document.getElementById(switchTo).style);
    } else {
    }
    document.getElementById(next).innerHTML = type;

}


$(document).ready(() => {

    userData();


	document.getElementById("login").style.display = "none";
    //generate_table();

    document.addEventListener('keydown', function (e) {
        switch (e.key) {
            case "a":
                aKey = true;
                break;
            case "s":
                sKey = true;
                break;
            case "w":
                wKey = true;
                break;
            case "d":
                dKey = true;
                break;
            case " ":
                space = true;
                break;
        }
    });

    document.addEventListener('keyup', function (e) {
        switch (e.key) {
            case "a":
                aKey = false;
                break;
            case "s":
                sKey = false;
                break;
            case "w":
                wKey = false;
                break;
            case " ":
                space = false;
                break;
            case "d":
                dKey = false;
                break;
        }
    });

    document.addEventListener("mousemove", function(e) {
        mousX = e.clientX;
        mousY = e.clientY;
    });

    document.getElementById("sjs0").style.position = "absolute";
    document.getElementById("sjs0").style.top = "25%";
    document.getElementById("sjs0").style.left = "5%";

    $('#campBut').on('click', function () {
    $.post('/authenticate', {}, responseJSON => {
		const respObject = JSON.parse(responseJSON);
		if (respObject.id === -1){
			console.log("invalid");
			document.getElementById("login").style.display = "block";
		}
		else{
			$('#campaign, #main').fadeIn(250);
		}
	});
    });

    $('#exitCamp').on('click', function () {
        $('#campaign').toggle();
    });
    
    $('#logout').on('click', function () {
        logout();
        location.reload();
    });

    $('#profileBut').on('click', function () {
    		$.post('/authenticate', {}, responseJSON => {
		const respObject = JSON.parse(responseJSON);
		if (respObject.id === -1){
			console.log("here");
			$('#signin, #main').fadeIn(250);
		}
		else{
			$('#profile, #main').fadeIn(250);
            document.getElementById("profile").style.display = "flex";
		}
	});
    });

    $('#survivalBut').on('click', function () {
        $.post('/authenticate', {}, responseJSON => {
            const respObject = JSON.parse(responseJSON);
            if (respObject.id === -1){
                console.log("invalid");
                document.getElementById("login").style.display = "block";
            }
            else{
                visitPage("survival");
            }
        });
    });

    $('#multiBut').on('click', function () {
        $.post('/authenticate', {}, responseJSON => {
            const respObject = JSON.parse(responseJSON);
            if (respObject.id === -1){
                console.log("invalid");
                document.getElementById("login").style.display = "block";
            }
            else{
            	$('#multiplayer').toggle();
            }
        });
    });
    
    $('#leader').on('click', function () {
        $.post('/authenticate', {}, responseJSON => {
            const respObject = JSON.parse(responseJSON);
            if (respObject.id === -1){
                console.log("invalid");
                document.getElementById("login").style.display = "block";
            }
            else{
                $('#leaderboard').toggle();
            }
        });
    });

    $('#exitProf').on('click', function () {
        $('#signin').toggle();
    });

    $('#exitLeader').on('click', function () {
        $('#leaderboard').toggle();
    });
    
    $('#exitMulti').on('click', function () {
        $('#multiplayer').toggle();
    });
    
    $('#exitProfile').on('click', function () {
        $('#profile').toggle();
    });

    $('#exitLogin').on('click', function () {
        $('#login').toggle();
    });

   


    $('#mapBuild').on('click', function () {
    		$.post('/authenticate', {}, responseJSON => {
		const respObject = JSON.parse(responseJSON);
		if (respObject.id === -1){
			console.log("invalid");
			document.getElementById("login").style.display = "block";
		}
		else{
			let url = window.location.href;
	        let next = url.lastIndexOf("/");
	        let newUrl = url.substr(0, next) + "/mapbuilder";
	        window.location.replace(newUrl);
		}
	});
 });

    loadMap();

});

function loadCampLevel(level) {
    let url = window.location.href;
    let next = url.lastIndexOf("/");
    let newUrl = url.substr(0, next) + "/tank/game/" + level;
    window.location.replace(newUrl);
}

function loadMultiplayer(level, friendId) {
    if (nextFriendId !== 0) {
        let url = window.location.href;
        let next = url.lastIndexOf("/");
        let newUrl;
        if (friendId !== -1) {
            newUrl = url.substr(0, next) + "/tank/game/" + level + "#" + (-friendId);
        } else {
            newUrl = url.substr(0, next) + "/tank/game/" + level + "#" + nextFriendId;
        }

        window.location.replace(newUrl);
    } else if (friendId !== -1) {
        let url = window.location.href;
        let next = url.lastIndexOf("/");
        let newUrl = url.substr(0, next) + "/tank/game/" + level + "#" + (-friendId);
        window.location.replace(newUrl);
    } else {
        alert("Please select a friend to challenge to a game.");
    }
}

function matchmaker(){
	console.log("here");
	$.post('/matchmaker', {}, responseJSON => {
        let url = window.location.href;
        let next = url.lastIndexOf("/");
        let newUrl;
        const respObject = JSON.parse(responseJSON);
        
      	newUrl = url.substr(0, next) + "/tank/game/" + respObject.game + "#" + respObject.opponent;

        window.location.replace(newUrl);
    });
}

function main() {

        userMove();

        // lastTime = now;
        window.requestAnimationFrame(main);

}

function openTab(evt, tabname) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tabname).style.display = "block";
    evt.currentTarget.className += " active";

}
