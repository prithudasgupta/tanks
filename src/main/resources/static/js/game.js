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
let treads = [];

class Bullet{
    constructor(sprite) {
        this.sprite = sprite;
    }
}

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



// load in the map
scene.loadImages(["/sprites/wall.png", "/sprites/freeSpace.png"], function()  {
    for (let row = 0; row < 16; row++) {
        for (let col = 0; col < 24; col++) {
            if(row === 0 || row === 15 || col === 0 || col === 23) {
                let wall = canvasbg.Sprite("/sprites/wall.png");
                // make sure that it is to size
                wall.size(45,45);
                // put in location
                wall.move(col*TILE_SIZE, row*TILE_SIZE);
                // update it
                wall.update();
                map[row][col] = wall;
                walls.push(wall);
            }  else {
                let free = canvasbg.Sprite("/sprites/freeSpace.png");
                // make sure that it is to size
                free.size(45,45);
                // put in location
                free.move(col*TILE_SIZE, row*TILE_SIZE);
                // update it
                free.update();
                map[row][col] = free;
            }
        }
    }
    for (let i = 1; i < 5; i++) {
        let wall = canvasbg.Sprite("/sprites/wall.png");
        // make sure that it is to size
        wall.size(45,45);
        // put in location
        wall.move(3*TILE_SIZE, i*TILE_SIZE);
        // update it
        wall.update();
        map[i][3] = wall;
        walls.push(wall);
    }
});

// load in the player
scene.loadImages(["/sprites/tank.png","/sprites/tank_cannon.png", "/sprites/bullet.png", "/sprites/tTreads.png"],
    function() {
    let userTank = canvasbg.Sprite("/sprites/tank.png");
    let cannon = canvasbg.Sprite("/sprites/tank_cannon.png")
    // put in location
    userTank.move(TILE_SIZE + 5, TILE_SIZE + 5);
    cannon.move(TILE_SIZE+5, TILE_SIZE+ 5);
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

function forwardByAngle(angRads) {
    let x = 5 * Math.cos(angRads);
    let y = 5 * Math.sin(angRads);
    return [x,y];
}

function backwardByAngle(angRads) {
    let x = -(5 * Math.cos(angRads));
    let y = -(5 * Math.sin(angRads));
    return [x,y];
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
        bullet.movDir = forwardByAngle(uCannon.angle);
        console.log(bullet.movDir);
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
                bullets.splice(b,1);
            } else {
                bullet.sprite.update();
            }


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
    while (treads.length > 4) {
        let over = treads.pop();
        over.remove();
    }
    for (let i in treads) {
        let tred = treads[i];
        tred.setOpacity(1 - (i * 0.2));
        tred.update();
    }
}

function simpleMove() {
    if (wKey) {
        user.move(0,-5);
        uCannon.move(0,-5);
        if (user.collidesWithArray(walls)) {
            user.move(0,5);
            uCannon.move(0,5);
        } else {
            user.update();
            uCannon.update();
        }
    }
    if(sKey) {
        user.move(0,5);
        uCannon.move(0,5);
        if (user.collidesWithArray(walls)) {
            user.move(0,-5);
            uCannon.move(0,-5);
        } else {

            user.update();
            uCannon.update();
        }
    }
    if(aKey) {
        user.move(-5,0);
        uCannon.move(-5,0);
        if (user.collidesWithArray(walls)) {
            user.move(5,0);
            uCannon.move(5,0);
        }
        user.update();
        uCannon.update();
    }
    if(dKey) {
        user.move(5,0);
        uCannon.move(5,0);
        if (user.collidesWithArray(walls)) {
            user.move(-5,0);
            uCannon.move(-5,0);
        }
        user.update();
        uCannon.update();
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


function userMove() {
    if (wKey) {
        let mov = forwardByAngle(user.angle);
        user.move(mov[0], mov[1]);
        uCannon.move(mov[0], mov[1]);
        if (user.collidesWithArray(walls)) {
            // if there is a collision revert back to old location
            user.move(-mov[0], -mov[1]);
            uCannon.move(-mov[0], -mov[1]);
        } else {
            uCannon.update();
            user.update();
            placeTread(user.x-mov[0], user.y-mov[1], user.angle);
        }
    }
    if(sKey) {
        let mov = backwardByAngle(user.angle);
        user.move(mov[0], mov[1]);
        uCannon.move(mov[0], mov[1]);
        if (user.collidesWithArray(walls)) {
            user.move(-mov[0], -mov[1]);
            uCannon.move(-mov[0], -mov[1]);
        } else {
            uCannon.update();
            user.update();
            placeTread(user.x-mov[0], user.y-mov[1], user.angle);
        }
    }
    if(aKey) {
        user.rotate(-0.02);
        if (user.collidesWithArray(walls)) {
            user.rotate(0.02);
        }
        user.update();
    }
    if(dKey) {
        user.rotate(0.02);
        if (user.collidesWithArray(walls)) {
            user.rotate(-0.02);
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






