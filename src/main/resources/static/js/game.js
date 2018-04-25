// GOKUL TODO:
// make enemy class
// make alive, variable, stage variable
//




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
let explosions = [];

class Explosion {
    constructor(sprite) {
        this.sprite = sprite;
        this.stage = 1;
        this.count = 0;
    }
}

// TEMP variables for testing
let placedEnemy = false;
let enemy;

let one,two,three;

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
function getMap () {
    $.post('/map', {"url": window.location.href}, responseJSON => {
        const respObject = JSON.parse(responseJSON);
        for (let row = 0; row < 16; row++) {
            for (let col = 0; col < 24; col++) {
                populateMap(row, col, respObject[row][col]);
            }
        }
        loadMap()
    });
}


function populateMap(row, col, type) {
    map[row][col] = type;
}

let startX, startY;
let collideable = [];

// // load in the map
function loadMap() {
    scene.loadImages(["/sprites/wall.png", "/sprites/freeSpace.png", "/sprites/pothole.png", "/sprites/breakable.png",
            "/sprites/imm_tank.png", "/sprites/tank_space.png"], function()  {
            for (let row = 0; row < 16; row++) {
                for (let col = 0; col < 24; col++) {
                    let next;
                    if (map[row][col] === "u" || map[row][col] === "x") {
                        next = canvasbg.Sprite("/sprites/wall.png");
                        walls.push(next);
                        nonTrav.push(next);
                    } else if ([col] === "b") {
                        next = canvasbg.Sprite("/sprites/freeSpace.png");
                        next.size(45,45);
                        // put in location
                        next.move(col*TILE_SIZE, row*TILE_SIZE);
                        // update it
                        next.update();

                        let breakable = canvasbg.Sprite("/sprites/breakable.png");
                        breakable.size(45,45);
                        // put in location
                        breakable.move(col*TILE_SIZE, row*TILE_SIZE);
                        // update it
                        breakable.update();
                        collideable.push(breakable);
                        nonTrav.push(breakable);

                    } else if (map[row][col] === "p") {
                        next = canvasbg.Sprite("/sprites/pothole.png");
                        nonTrav.push(next);
                    } else {
                        next = canvasbg.Sprite("/sprites/freeSpace.png");
                    }
                    if (map[row][col] !== "b") {
                        // TEMP CODE
                        if(row > 8 && col > 12 && !placedEnemy && map[row][col] !== "u") {
                            placedEnemy = true;
                            next = canvasbg.Sprite("/sprites/tank_space.png");
                            enemy = canvasbg.Sprite("/sprites/imm_tank.png");
                            // make sure that it is to size
                            next.size(45,45);
                            enemy.move(col*TILE_SIZE, row*TILE_SIZE);
                            // put in location
                            next.move(col*TILE_SIZE, row*TILE_SIZE);
                            next.update();
                            enemy.update();
                            enemy.lastFire = Date.now();
                            collideable.push(enemy);
                            nonTrav.push(enemy);
                        } else {
                            // make sure that it is to size
                            next.size(45,45);
                            // put in location
                            next.move(col*TILE_SIZE, row*TILE_SIZE);
                            // update it
                            next.update();
                        }
                        if (startX === undefined && map[row][col] === "l") {
                            startX = col*TILE_SIZE + 5;
                            startY = row*TILE_SIZE + 5;
                        }
                    }
                }
            }
        });
    // load in the player
    scene.loadImages(["/sprites/one.png", "/sprites/two.png", "/sprites/three.png", "/sprites/tank.png",
        "/sprites/tank_cannon.png", "/sprites/bullet.png", "/sprites/tTreads.png",
        "/sprites/explo2.png", "/sprites/explo1.png", "/sprites/explo3.png"], function() {
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

        // now loading screen
        three = canvasbg.Sprite("/sprites/one.png");
        two = canvasbg.Sprite("/sprites/two.png");
        one = canvasbg.Sprite("/sprites/three.png");

        window.requestAnimationFrame(oneM);

    });

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
            b.move(sprite.x + 22 + direction[0], sprite.y + 22 + direction[1]);
        }


        if (user === sprite) {
            console.log("user firing");
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
        } else {
            bullet.movDir = forwardByAngle(sprite.angle, BULLET_SPEED);
        }
        bullets.push(bullet);
        sprite.lastFire = Date.now();
    }
}



function updateBullet() {
    // if game map is loaded
    if (ready) {
        // go through all bullets and check for collisions
        for(let b in bullets) {
            // retrieve bullet from array
            let bullet = bullets[b];
            // direction the bullet is moving
            let cord = bullet.movDir;
            bullet.sprite.move(cord[0],cord[1]);
            // if the bullet collides with a wall, remove it
            if (bullet.sprite.collidesWithArray(walls)) {
                bullet.sprite.remove();
                bullets.splice(b, 1);
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
                        // find it in non-traversable and remove, so player can drive over dead body
                        let ind = nonTrav.indexOf(collideable[i]);
                        if (ind >= 0) {
                            nonTrav.splice(ind, 1);
                        }
                        // remove from collideable
                        // GOKUL CHANGE
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
                    }
                }
                if (bullet.sprite.collidesWith(user)) {
                    isGameOver = true;
                    window.alert("Game OVER!");
                }
                if (!collided) {
                    bullet.sprite.update();
                }
            }

        }
    }

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

function enemyLogic() {
    if (ready) {
        // let dx = mousX - user.x;
        // let dy = mousY - user.y;
        // rot = Math.atan2(dy, dx);
        // uCannon.setAngle(0);
        if (user !== undefined && enemyDetector(enemy.x, enemy.y) <= 200) {
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


function placeTread(x , y, ang) {
    // first create tread and place it
    let tread = canvasbg.Sprite("/sprites/tTreads.png");
    // make sure that it is to size
    // put in location
    tread.move(x, y);
    tread.rotate(ang);
    // update it
    tread.update();
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

function main() {
    if (isGameOver) {

    } else {
        if (firstIteration) {
            let now1 = Date.now();
            while (Date.now() - now1 < 1000) {
            }
            three.remove();
            firstIteration = false;
        }
        let now = Date.now();
        let timeChange = (now - lastTime) / 1000.0;

        // // update all the entities on the screen
        // update(timeChange);
        // // redraw all the objects
        // render();


        userMove();
        // (Date.now() - lastFire) > 800
        if (space) {
            fire(user);
        }
        updateBullet();
        // check to see if the enemy is alive
        if (placedEnemy) {
            enemyLogic();
        }

        updateExplosions();

        lastTime = now;
        window.requestAnimationFrame(main);

        count++;
    }
}

$(document).ready(() => {

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

    // main();
});

function enemyDetector(x, y) {
    // get row and col
    let dist = Math.sqrt((user.x - x)*(user.x - x) + (user.y - y)*(user.y - y));
    return dist;
}

// function startScreen() {
//     let count = 0;
//     let one = canvasbg.Sprite("/sprites/one.png");
//     let two = canvasbg.Sprite("/sprites/two.png");
//     let three = canvasbg.Sprite("/sprites/three.png");
//     let x = 4;
//     let y = 4;
//     let now = Date.now();
//     one.move(x,y);
//
//     while (Date.now() - now < 1000) {
//         one.scale(3);
//         one.update();
//     }
//     main();
// }





