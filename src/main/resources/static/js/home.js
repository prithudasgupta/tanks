

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

$(document).ready(() => {

	document.getElementById("login").style.display = "none";
	
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
        $('#campaign, #main').fadeIn(250);
    });

    $('#exitCamp').on('click', function () {
        $('#campaign').toggle();
    });

    $('#profileBut').on('click', function () {
        $('#profile, #main').fadeIn(250);
    });

    $('#exitProf').on('click', function () {
        $('#profile').toggle();
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


    for(let i = 0; i < 20; i++){
        if (i < 10) {
            $('div#levels').append("<img id='level' onclick='loadCampLevel(" + i + ")' src='images/mapIcons/mapIcon" + i + ".png'/> </img>");
        } else {
            $('div#levels').append("<img id='level' src='images/mapIcon_lock.png'/> </img>");
        }


    }

    loadMap();


});

function loadCampLevel(level) {
    let url = window.location.href;
    let next = url.lastIndexOf("/");
    let newUrl = url.substr(0, next) + "/tank/game/" + level;
    window.location.replace(newUrl);
}

function main() {

        userMove();

        // lastTime = now;
        window.requestAnimationFrame(main);

}