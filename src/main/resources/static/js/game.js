// TODO: enemy tanks


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
let USER_SPEED = 3;
let BULLET_SPEED = 5;
let USER_ROT = 0.02;



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
       default:
           ready = true;
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


// set up canvas
canvas = $('#canvas')[0];
canvas.height = 720;
canvas.width = 1080;
ctx = canvas.getContext("2d");

// create the Scene object and create the map
let scene = sjs.Scene({w:canvas.width, h:canvas.height});
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

$.post('/map', {"url": window.location.href}, responseJSON => {
    const respObject = JSON.parse(responseJSON);
    console.log(respObject);
    for (let row = 0; row < 16; row++) {
        for (let col = 0; col < 24; col++) {
                populateMap(row, col, respObject[row][col]);
        }
    }
});

function populateMap(row, col, type) {
    map[row][col] = type;
}

let startX, startY;
let collideable = [];

// load in the map
scene.loadImages(["/sprites/wall.png", "/sprites/freeSpace.png", "/sprites/pothole.png", "/sprites/breakable.png"],
    function()  {
    for (let row = 0; row < 16; row++) {
        for (let col = 0; col < 24; col++) {
            let next;
            if (map[row][col] === "u") {
                next = canvasbg.Sprite("/sprites/wall.png");
                walls.push(next);
                nonTrav.push(next);
            } else if (map[row][col] === "b") {
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

            } else if (map[row][col] === "p") {
                next = canvasbg.Sprite("/sprites/pothole.png");
                nonTrav.push(next);
            } else {
                next = canvasbg.Sprite("/sprites/freeSpace.png");
            }
            if (map[row][col] !== "b") {
                // make sure that it is to size
                next.size(45,45);
                // put in location
                next.move(col*TILE_SIZE, row*TILE_SIZE);
                // update it
                next.update();

                if (startX === undefined && map[row][col] === "l") {
                    startX = col*TILE_SIZE + 5;
                    startY = row*TILE_SIZE + 5;
                }
            }
        }
    }
});

// load in the player
scene.loadImages(["/sprites/tank.png","/sprites/tank_cannon.png", "/sprites/bullet.png", "/sprites/tTreads.png"],
    function() {
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
    uCannon = cannon;
});


// game loop
let count = 0;
let lastTime;
console.log(walls);

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
    }
}

function fire() {
    if (ready) {
        let b = canvasbg.Sprite("/sprites/bullet.png");
        console.log(b);
        // make sure that it is to size
        // put in location
        b.move(user.x + 5, user.y + 4);
        // update it
        b.update();
        // create an object for storage with bullet trajectory
        let bullet = new Bullet(b);
        bullet.movDir = forwardByAngle(uCannon.angle, BULLET_SPEED);
        bullets.push(bullet);
    }
}



function updateBullet() {
    if (ready) {
        for(let b in bullets) {
            let bullet = bullets[b];
            let cord = bullet.movDir;
            bullet.sprite.move(cord[0],cord[1]);
            if (bullet.sprite.collidesWithArray(walls)) {
                bullet.sprite.remove();
                bullets.splice(b, 1);
            } else {
                let collided = false;
                for (let i in collideable) {
                    if (bullet.sprite.collidesWith(collideable[i])) {
                        collideable[i].remove();
                        collideable.splice(i, 1);
                        bullet.sprite.remove();
                        bullets.splice(b,1);
                        collided = true;
                        break;
                    }
                }
                if (!collided) {
                    bullet.sprite.update();
                }
            }

            // } else if (bullet.sprite.collidesWithArray(collideable)) {
            //     bullet.sprite.remove();
            //     bullets.splice(b,1);
            // }
            // else {
            //     bullet.sprite.update();
            // }
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
    if(sKey) {
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
    if(aKey) {
        user.rotate(-USER_ROT);
        if (user.collidesWithArray(nonTrav)) {
            user.rotate(USER_ROT);
        }
        user.update();
    }
    if(dKey) {
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


function main() {
    let now = Date.now();
    let timeChange = (now - lastTime) / 1000.0;

    // // update all the entities on the screen
    // update(timeChange);
    // // redraw all the objects
    // render();
    userMove();
    if (space) {
        fire();
    }
    updateBullet();

    lastTime = now;
    window.requestAnimationFrame(main);

    count++;
}

$(document).ready(() => {
    main();
});



// create the Scene object






