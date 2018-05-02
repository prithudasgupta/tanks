// TODO:
// Enemy tanks
// Load in screen and process to get to the first campaign levels
// a system to load each game individually
// A start screen for the game
// a way to pause and end the game.

// let canvas;
let TILE_SIZE = 45;
let BOARD_WIDTH = 24;
let BOARD_HEIGHT = 16;
let user, uCannon;
let aKey, sKey, wKey, dKey, space;
let ready = false;
let mousX, mousY, rot;
let bullets = [];
let nonTrav = [];
let treads = [];
let USER_SPEED = 2.5;
let BULLET_SPEED = 5;
let USER_ROT = 0.02;
let lastFire = Date.now();
let gameTime = false;


let isGameOver;
let winner = false;


let explosions = [];
let startTime;
let currentTime = "00:00";
let pauseTime = 0;
let enemyObj;
let pause = false;
let pauseStart;
let pauseSprite;
let statEnemies = [];
let dumbEnemies = [];
// dumb enemies start location
let dumbStart = [];


let kills = 0;

let enemyLoc = [];


let movingEnemyX;
let movingEnemyY;



class Explosion {
    constructor(sprite) {
        this.sprite = sprite;
        this.stage = 1;
        this.count = 0;
    }
}

class Coordinate {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }
}

class Enemy {
    constructor(sprite) {
        this.sprite = sprite;
        this.alive = true;
    }
}



// TEMP variables for testing
let placedEnemy = false;
let enemy;

let placedMovingEnemy = false;
let movingEnemy;

let one,two,three, timerSprite;

// set up canvas


// create the Scene object and create the map
let scene = sjs.Scene({w:1080, h:720});
let canvasbg = scene.Layer('background', {useCanvas:false, autoClear:true});


// setup the map
let map = [];
let walls = [];

for (let row = 0; row < 16; row++) {
    map[row] = [];
    for (let col = 0; col < 24; col++) {
        map[row][col] = 0;
    }
}

function visitPage(whereTo){
	const urlArr = document.URL.split("/");
	let newUrl = "";
	switch(whereTo){
		case "Main":
			newUrl = "/home";
		break;
		case "Next":
			const nextLevel = parseInt(urlArr[urlArr.length -1])+1;
			if(nextLevel > 20){
				alert("Congratulations! you finished all campaign levels");
				return;
			}
			newUrl = nextLevel;
		
		break;
		
	}
	window.location.href = newUrl;
	
}

function getMap () {
    $.post('/map', {"url": window.location.href}, responseJSON => {
        const respObject = JSON.parse(responseJSON);
        for (let i in respObject.enemies) {
            if (respObject.enemies[i].type === "s") {
                enemyLoc.push(respObject.enemies[i].location.coordinates);
            }
            if (respObject.enemies[i].type === "d") {
                dumbStart.push(respObject.enemies[i].location.coordinates);
            }
        }
        let mapLoc = respObject.map;
        for (let row = 0; row < 16; row++) {
            for (let col = 0; col < 24; col++) {
                populateMap(row, col, mapLoc[row][col]);
            }
        }
        setStatTankMap(enemyLoc);
        startX = (respObject.game.user.location.coordinates[0] * 45) + 5;
        startY = (respObject.game.user.location.coordinates[1] * 45) + 5;
        loadMap();
    });
}


function populateMap(row, col, type) {
    map[row][col] = type;
}

function setStatTankMap(list) {
    // console.log(list);
    for (let i in list) {
        let row = (list[i])[1];
        let col = (list[i])[0];
        map[row][col] = "st";
    }
}

let startX, startY;
let collideable = [];

// // load in the map
function loadMap() {
    scene.loadImages(["/sprites/wall.png", "/sprites/freeSpace.png", "/sprites/pothole.png", "/sprites/breakable.png",
        "/sprites/imm_tank.png", "/sprites/tank_space.png"], function()  {

        let next;
        //console.log(map);
        for (let row = 0; row < 16; row++) {
            for (let col = 0; col < 24; col++) {

                if (map[row][col] === "u" || map[row][col] === "x") {
                    next = canvasbg.Sprite("/sprites/wall.png");
                    walls.push(next);
                    nonTrav.push(next);
                } else if (map[row][col] === "b") {
                    next = canvasbg.Sprite("/sprites/freeSpace.png");
                    next.size(45, 45);

                    let breakable = canvasbg.Sprite("/sprites/breakable.png");
                    breakable.size(45, 45);
                    // put in location
                    breakable.move(col * TILE_SIZE, row * TILE_SIZE);
                    // update it
                    breakable.update();
                    collideable.push(breakable);
                    nonTrav.push(breakable);

                } else if (map[row][col] === "p") {
                    next = canvasbg.Sprite("/sprites/pothole.png");
                    nonTrav.push(next);
                } else if (map[row][col] === "st") {
                    next = canvasbg.Sprite("/sprites/tank_space.png");
                } else {
                    next = canvasbg.Sprite("/sprites/freeSpace.png");
                }
                if (map[row][col] !== "b") {

                    next.size(45, 45);
                    // put in location
                    next.move(col * TILE_SIZE, row * TILE_SIZE);
                    // update it
                    next.update();

                }
            }
        }

    });
    // load in the player
    scene.loadImages(["/sprites/one.png", "/sprites/two.png", "/sprites/three.png", "/sprites/tank.png",
        "/sprites/tank_cannon.png", "/sprites/bullet.png", "/sprites/tTreads.png",
        "/sprites/explo2.png", "/sprites/explo1.png", "/sprites/explo3.png", "/sprites/pause.png",
        "/sprites/pause_blank.png", "/sprites/dumbTank.png"], function() {
        let userTank = canvasbg.Sprite("/sprites/tank.png");
        let cannon = canvasbg.Sprite("/sprites/tank_cannon.png")
        // put in location
        userTank.move(startX, startY);
        cannon.move(startX, startY);
        // update it
        userTank.scale(1.25);
        cannon.scale(1.25);

        userTank.update();
        cannon.update();
        user = userTank;
        user.lastFire = Date.now();
        uCannon = cannon;
        ready = true;

        // // create moving enemy
        // movingEnemy = canvasbg.Sprite("/sprites/imm_tank.png");
        // movingEnemy.move(movingEnemyX, movingEnemyY);
        // movingEnemy.update();
        // movingEnemy.lastFire = Date.now();
        // collideable.push(movingEnemy);
        // nonTrav.push(movingEnemy);
        // enemyObj = new Enemy(movingEnemy);
        // enemyObj.alive = false;

        // placedEnemy = true;

        for(let i in enemyLoc) {
            //console.log(i);
            let cur = enemyLoc[i];
            createStationaryTank(cur[1], cur[0]);
        }

        for (let i in dumbStart) {
            let cur = dumbStart[i];
            createDumbTank(cur[1], cur[0]);
        }

        // now loading screen
        three = canvasbg.Sprite("/sprites/one.png");
        two = canvasbg.Sprite("/sprites/two.png");
        one = canvasbg.Sprite("/sprites/three.png");

        // load pause
        pauseSprite = canvasbg.Sprite("/sprites/pause_blank.png");
        pauseSprite.move(375, 275);
        pauseSprite.update();

        window.requestAnimationFrame(oneM);

    });

}

function createStationaryTank(row, col) {
    //let space = canvasbg.Sprite("/sprites/tank_space.png");
    let tank = canvasbg.Sprite("/sprites/imm_tank.png");
    //space.move(col*TILE_SIZE, row*TILE_SIZE);
    tank.move(col*TILE_SIZE, row*TILE_SIZE);
    //space.update();
    tank.update();
    tank.lastFire = Date.now();
    collideable.push(tank);
    nonTrav.push(tank);
    statEnemies.push(tank);

}

function createDumbTank(row, col) {
    //let space = canvasbg.Sprite("/sprites/tank_space.png");
    let tank = canvasbg.Sprite("/sprites/dumbTank.png");
    //space.move(col*TILE_SIZE, row*TILE_SIZE);
    tank.move(col*TILE_SIZE, row*TILE_SIZE);
    //space.update();
    tank.update();
    tank.startX = tank.x;
    tank.startY = tank.y;
    tank.lastFire = Date.now();
    collideable.push(tank);
    nonTrav.push(tank);
    dumbEnemies.push(tank);
}

function oneM() {
    one.move(500,250);
    one.update();
    window.requestAnimationFrame(twoM);
}

function twoM() {
    let now = Date.now();
    while (Date.now() - now < 1000) {
    }
    one.remove();
    two.move(500,250);
    two.update();
    window.requestAnimationFrame(threeM);
}

function threeM() {
    let now = Date.now();
    while (Date.now() - now < 1000) {
    }
    two.remove();
    three.move(500,250);
    three.update();
    window.requestAnimationFrame(main);
}

// retrieve the map then load it.
getMap();


// game loop
let count = 0;
let lastTime;

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

class Bullet {
    constructor(sprite) {
        this.sprite = sprite;
        this.bounces = 0;
        this.type = 0;
    }
}

function fire(sprite) {
    if (ready && (Date.now() - sprite.lastFire) > 400) {
        let b = canvasbg.Sprite("/sprites/bullet.png");

        // make sure that it is to size
        // put in location
        let direction;
        if (sprite === user) {
            direction = forwardByAngle(uCannon.angle, 25);
            b.move(sprite.x + direction[0], sprite.y + direction[1]);
        } else {
            direction = forwardByAngle(sprite.angle, 25);
            b.move((sprite.x + 22) + (direction[0] * 1.1), (sprite.y + 22) + (direction[1] * 1.1));
        }


        if (user === sprite) {
            b.rotate(uCannon.angle);
        } else {
            b.rotate(sprite.angle);
        }
        b.scale(1.7);
        // update it
        b.update();
        // create an object for storage with bullet trajectory
        let bullet = new Bullet(b);
        if (user === sprite) {
            bullet.movDir = forwardByAngle(uCannon.angle, BULLET_SPEED);
            bullet.type = 1;
        } else {
            bullet.movDir = forwardByAngle(sprite.angle, BULLET_SPEED);
        }
        bullets.push(bullet);
        sprite.lastFire = Date.now();
    }
}

function findCollisionDirection(x1, y1, x2, y2){
    let left = x2;
    let bottom = y2;
    let right = x2 + TILE_SIZE;
    let top = y2 + TILE_SIZE;
    console.log("coord " + x1 + ", " + y1);
    console.log("X from: " + left + " to " + right + "and Y from: " + bottom + " to " + top);
    
    /*left = Math.abs(left - x1);
    bottom = Math.abs(bottom - y1);
    right = Math.abs(right - x1);
    top = Math.abs(top - y1);*/
    
    /*if(x1 >= left && x1 <= right){
        console.log("vert");
        return 0;
    }else{
        console.log("hori");

        return 1;
    }*/
    left = x1 - left;
    bottom = y1 - bottom;
    right = right - x1;
    top = top - y1;


    let min = Math.min(left, bottom, right, top);

    if(min == bottom || min == top){
         console.log("vert");

        return 0;

    }else{
        console.log("hori");

        return 1;
    }
}



function updateBullet() {
    // if game map is loaded
    if (ready) {
        // go through all bullets and check for collisions
        for(let b in bullets) {
            // retrieve bullet from array
            let bullet = bullets[b];

            // get previous (non collided cords)
            let prevX = bullet.sprite.x;
            let prevY = bullet.sprite.y;

            // direction the bullet is moving
            let cord = bullet.movDir;
            bullet.sprite.move(cord[0],cord[1]);
            // if the bullet collides with a wall, remove it
            if (bullet.sprite.collidesWithArray(walls)) {

                let wall = bullet.sprite.collidesWithArray(walls);

                if(bullet.type == 1 && bullet.bounces < 3){
                    let bulletAng = angleBetweenVectors(bullet.movDir, [1,0]);
                    if(bulletAng < 0){
                        bulletAng += 2*Math.PI;
                    }
                    
                    console.log("start");
                    console.log("bull ang " + (180*bulletAng/Math.PI));
                    console.log("actual " + bullet.sprite.x + ", " + bullet.sprite.y)
                    const diff = forwardByAngle(bulletAng, 2);
                    console.log("diff is " + diff);
                    const newVector = shortenVector(bullet.movDir, 4);
                    let direction = findCollisionDirection(bullet.sprite.x - diff[0], bullet.sprite.y + diff[1], wall.x, wall.y);
                    //let direction = findCollisionDirection(newVector[0], newVector[1], wall.x, wall.y);
                    bullet.bounces++;

                    
                   // console.log("Prev: " + bullet.movDir);
                    let old = [];
                    old[0] =  bullet.movDir[0];
                    old[1] =  bullet.movDir[1];

                    if(direction == 0){
                        bullet.movDir[1] = -bullet.movDir[1];
                    } else {
                        bullet.movDir[0] = -bullet.movDir[0];
                    }
                    bullet.sprite.rotate(angleBetweenVectors(old,bullet.movDir));
                    
                } else {
                    console.log("removed");
                    bullet.sprite.remove();
                    bullets.splice(b, 1);
                }
            }
            // otherwise check for collision with other entities (tanks, breakable walls)
            else {
                // may not collide with anything
                let collided = false;
                for (let i in collideable) {
                    // for everything on map that needs handling if bullet hits it
                    if (bullet.sprite.collidesWith(collideable[i])) {
                        // if collides with enemy
                        if (collideable[i] === enemy) {
                            placedEnemy = false;
                        }

                        if (statEnemies.includes(collideable[i])) {
                            statEnemies.splice(statEnemies.indexOf(collideable[i]), 1);
                            kills++;
                            let ind = nonTrav.indexOf(collideable[i]);
                            if (ind >= 0) {
                                nonTrav.splice(ind, 1);
                            }
                            // remove from collideable

                            // we dont want to remove bullet, we want to change the sprite... and set up some type of
                            // timeline to change from different parts of the explosion
                            let explosion = new Explosion(collideable[i]);
                            explosion.sprite.loadImg("/sprites/explo1.png");
                            explosion.sprite.update();
                            explosions.push(explosion);
                            //collideable[i].remove();
                            collideable.splice(i, 1);
                            // ABOVE

                            bullet.sprite.remove();
                            bullets.splice(b,1);
                            collided = true;
                            break;
                        } else if (dumbEnemies.includes(collideable[i])) {
                                dumbEnemies.splice(dumbEnemies.indexOf(collideable[i]), 1);
                                kills++;
                                let ind = nonTrav.indexOf(collideable[i]);
                                if (ind >= 0) {
                                    nonTrav.splice(ind, 1);
                                }

                                let explosion = new Explosion(collideable[i]);
                                explosion.sprite.loadImg("/sprites/explo1.png");
                                explosion.sprite.update();
                                explosions.push(explosion);
                                //collideable[i].remove();
                                collideable.splice(i, 1);
                                // ABOVE

                                bullet.sprite.remove();
                                bullets.splice(b,1);
                                collided = true;
                                break;
                        } else {
                            let ind = nonTrav.indexOf(collideable[i]);
                            if (ind >= 0) {
                                nonTrav.splice(ind, 1);
                            }
                            collideable[i].loadImg("/sprites/freeSpace.png");
                            collideable[i].update();

                            collideable.splice(i, 1);

                            bullet.sprite.remove();
                            bullets.splice(b,1);
                            collided = true;
                            break;
                        }

                    }
                }
                if (bullet.sprite.collidesWith(user)) {
                    isGameOver = true;
                    //window.alert("Game OVER!");
                }
                if (!collided) {
                    bullet.sprite.update();
                }
            }

        }
    }

}

function shortenVector(v1, shortenValue){
    const x1 = v1[0];
    const y1 = v1[1];
    
    const length = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
    let output = [];
    output[0] = x1 - ((x1/length)*shortenValue);
    output[1] = y1 - ((y1/length)*shortenValue);
    return output;

    
}

function angleBetweenVectors(v1, v2){
    const x1 = v1[0];
    const x2 = v2[0];
    const y1 = v1[1];
    const y2 = v2[1];
    return Math.atan2(x1*y2-y1*x2,x1*x2+y1*y2);

}


function checkForWalls(x,y) {
    let row = Math.floor(y/45);
    let col = Math.floor(x/45);
    let uRow = Math.floor(user.y/45);
    let uCol = Math.floor(user.x/45);
    // above the enemy
    if (uRow < row) {
        if (map[row-1][col] !== "w") {
            return true;
        }
    }
    if (uCol < col) {
        if (map[row][col-1] !== "w") {
            return true;
        }
    }
    if (uRow > row) {
        if (map[row+1][col] !== "w") {
            return true;
        }
    }
    if (uCol > col) {
        if (map[row][col+1] !== "w") {
            return true;
        }
    }
}

function enemyLogic(enemy) {
    if (ready) {
        // let dx = mousX - user.x;
        // let dy = mousY - user.y;
        // rot = Math.atan2(dy, dx);
        // uCannon.setAngle(0);
        if (user !== undefined && withinSight(enemy.x, enemy.y)) {
            // let dx = user.x - enemy.x;
            // let dy = user.y - enemy.y;
            let dx = enemy.x - user.x;
            let dy = enemy.y - user.y;
            rot = Math.atan2(-dy, -dx);
            enemy.setAngle(0);
            enemy.rotate(rot);
            enemy.update();
            fire(enemy);
        } else {
            enemy.rotate(0.02);
            enemy.update();
        }
    }
}

function getBorderingLandTiles(xCoord, yCoord){

    const yTile = Math.floor(yCoord / 45);
    const xTile = Math.floor(xCoord / 45);
    let landSpots = [];

    if (map[yTile - 1][xTile] === "l"){
        landSpots.push(new Coordinate(xTile, yTile - 1))
    }
    if (map[yTile + 1][xTile] === "l"){
        landSpots.push(new Coordinate( xTile, yTile + 1))
    }
    if (map[yTile][xTile - 1] === "l"){
        landSpots.push(new Coordinate( xTile - 1,yTile))
    }
    if (map[yTile][xTile + 1] === "l"){
        landSpots.push(new Coordinate(xTile + 1, yTile))
    }
    return landSpots;
}

function checkEndGame() {
    return (statEnemies.length === 0 && dumbEnemies.length === 0);
}

function movingEnemyLogic(movingEnemy) {

    if (ready) {
        if (user !== undefined && withinSight(movingEnemy.x, movingEnemy.y)) {
            let dx = movingEnemy.x - user.x;
            let dy = movingEnemy.y - user.y;
            rot = Math.atan2(-dy, -dx);
            movingEnemy.setAngle(0);
            movingEnemy.rotate(rot);
            movingEnemy.update();
            fire(movingEnemy);
        }
        else {
            let movedSoFar = euclidDist(movingEnemy.startX, movingEnemy.startY, movingEnemy.x, movingEnemy.y);

            if (movingEnemy.nextRow === undefined || movedSoFar >= 45) {
                let landSpots = getBorderingLandTiles(movingEnemy.x, movingEnemy.y);
                const rand = Math.floor(Math.random() * landSpots.length);
                const nextMove = landSpots[rand];
                movingEnemy.nextRow = nextMove.y;
                movingEnemy.nextCol = nextMove.x;
                let curRow = Math.floor(movingEnemy.y / 45);
                let curCol = Math.floor(movingEnemy.x / 45);

                if (movingEnemy.nextRow - curRow == 0){
                    if (movingEnemy.nextCol - curCol == 1){
                        movingEnemy.nextAngle = 0;
                    }
                    else{
                        movingEnemy.nextAngle = 3.1415;
                    }
                }
                else if(movingEnemy.nextRow - curRow == 1){
                    movingEnemy.nextAngle = 1.5707;
                }
                else{
                    movingEnemy.nextAngle = 4.712;
                }

                movingEnemy.startX = movingEnemy.x;
                movingEnemy.startY = movingEnemy.y;
            }

            moveBetween(movingEnemy);
        }
    }
}


function placeTread(x , y, ang) {
    // first create tread and place it
    let tread = canvasbg.Sprite("/sprites/tTreads.png");
    // make sure that it is to size
    // put in location
    tread.move(x, y);
    tread.rotate(ang);
    // update it
    tread.update();
    //console.log(tread);
    //console.log(user);
    treads.push(tread);
    // remove treads if too many

    if (treads.length > 8) {
        let temp = treads.slice(Math.max(treads.length - 8 , 0));
        for (let i in treads) {
            if (i < treads.length - 8) {
                let tred = treads[i];
                tred.remove();
            }
        }
        for (let i in temp) {
            let tred = temp[i];
            tred.setOpacity(0.20 * i);
            tred.update();
        }
        treads = temp;
    }
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

function updateExplosions() {
    for(let i in explosions) {
        let explo = explosions[i];
        explo.count += 1;
        if (explo.count > 5 && explo.count <= 10) {
            explo.stage = 2;
            explo.sprite.loadImg("/sprites/explo2.png");
            explo.sprite.update();
        } else if (explo.count > 15 && explo.count <= 20) {
            explo.stage = 3;
            explo.sprite.loadImg("/sprites/explo3.png");
            explo.sprite.update();
        } else if (explo.count > 30) {
            explo.sprite.remove();
            explosions.splice(i, 1);
        }
    }
}


let firstIteration = true;

function displayEndGame() {

    $('#next').toggle();
    document.getElementById("result").innerHTML = "GAME OVER!";
    $('#endGame').toggle();
}


function displayWinGame() {

    document.getElementById("result").innerHTML = "GAME WON!";
    $('#endGame').toggle();
}

function main() {
    if (isGameOver) {
        displayEndGame();
    }
    else if (winner) {
        displayWinGame();
    } else {
        if (firstIteration) {
            let now1 = Date.now();
            while (Date.now() - now1 < 1000) {
            }
            three.remove();
            startTime = Date.now();
            firstIteration = false;
        }
        let now = Date.now();
        let timeChange = (now - lastTime) / 1000.0;

        // // update all the entities on the screen
        // update(timeChange);
        // // redraw all the objects
        // render();
        //console.log(pause);
        if (!pause) {
            userMove();
            // (Date.now() - lastFire) > 800
            if (space) {
                fire(user);
            }
            updateBullet();
            // check to see if the enemy is alive
            for (let i in statEnemies) {
                enemyLogic(statEnemies[i]);
            }

            for (let i in dumbEnemies) {
                movingEnemyLogic(dumbEnemies[i]);
            }

            updateExplosions();

            lastTime = now;

            currentTime = updateTime((Date.now() - startTime) - pauseTime);
            count++;
        }
        if(checkEndGame()) {
            winner = true;
        }
        window.requestAnimationFrame(main);



    }
}

$(document).ready(() => {

    let urlArr = document.URL.split("/");
    let level = parseInt(urlArr[urlArr.length -1]) + 1;
    document.getElementById("levNumber").innerHTML = "Game Level : " + level.toString();
    // add event listeners for movement of the user tank

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
            case "p":
                if (pause) {
                    pause = false;
                    let timePaused = (Date.now() - pauseStart);
                    console.log(timePaused);
					pauseTime += timePaused;
                     
                     pauseSprite.loadImg("/sprites/pause_blank.png");
                     pauseSprite.update();
                    
                } else {
                    pause = true;
                    pauseStart = Date.now();
                    pauseSprite.loadImg("/sprites/pause.png");
                    pauseSprite.update();
                }
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

    let adWidth = (screen.width - 1080).toString();
    console.log(screen.width);
    document.getElementById('sideMenu').setAttribute("style","width:" + adWidth + "px");
});


function updateTime(time){
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
    timeString = "Time : " + timeString;
    // console.log(timeString);
    if (timeString !== document.getElementById("timer").innerHTML) {
        document.getElementById("timer").innerHTML = timeString;
        document.getElementById("time").innerHTML = timeString;

    }

    let killString = "Kills : " + kills.toString();
    if (killString != document.getElementById("kills").innerHTML) {
        document.getElementById("kills").innerHTML = killString;
        document.getElementById("enemyKill").innerHTML = killString;
    }


    //gets time just need to display to user
    return timeString;
}

function enemyDetector(x, y) {
    // get row and col
    let dist = Math.sqrt((user.x - x)*(user.x - x) + (user.y - y)*(user.y - y));
    return dist;
}

function euclidDist(x1, y1, x2, y2) {
    //console.log(x1 + ' ' + y1 + ' ' + x2 + ' ' + y2);
    let dist = Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
    //console.log(dist);
    return dist;
}

function withinSight(cpuX, cpuY){
    const deltaY = user.y - cpuY;
    const deltaX = user.x - cpuX;
    const distance = enemyDetector(cpuX, cpuY);
    const epsilon = 5;

    const userXTile = Math.floor(parseInt(user.x) / TILE_SIZE);
    const userYTile = Math.floor(parseInt(user.y) / TILE_SIZE);

    let currX = cpuX;
    let currY = cpuY;

    for (let i = 0; i < distance; i = i + epsilon){
        currX += deltaX / (distance / epsilon);
        currY += deltaY / (distance / epsilon);
        const currXTile = Math.floor(currX / TILE_SIZE);
        const currYTile = Math.floor(currY / TILE_SIZE);
        const currTile = map[currYTile][currXTile];

        if (userXTile === currXTile && userYTile === currYTile){
            return true;
        }
        else if(currTile === "u"){
            return false;
        }
    }

    return false;
}

