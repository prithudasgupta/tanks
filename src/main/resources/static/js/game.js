// TODO: firing bullets
// TODO: enemy tanks
// TODO: movement


// let canvas;
let TILE_SIZE = 45;
let BOARD_WIDTH = 24;
let BOARD_HEIGHT = 16;
let user, uCannon;
let aKey, sKey, wKey, dKey, space;
let ready = false;
let mousX, mousY, rot;
let bullets = [];

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
scene.loadImages(["/sprites/tank.png","/sprites/tank_cannon.png", "/sprites/bullet.png"], function() {
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


//TODO: I need the function for rotation direction for angle of bullot fire
function fire() {
    if (ready) {
        let bullet = canvasbg.Sprite("/sprites/bullet.png");
        // make sure that it is to size
        // put in location
        bullet.move(user.x, user.y);
        // update it
        bullet.update();
        bullets.push(bullet);
    }
}

function updateBullet() {
    if (ready) {
        for(let b in bullets) {
            let bullet = bullets[b];
            bullet.move(5,0);
            if (bullet.collidesWithArray(walls)) {
                bullet.remove();
                bullets.splice(b,1);
            } else {
                bullet.update();
            }


        }
    }

}

function simpleMove() {
    if (wKey) {
        user.move(0,-5);
        uCannon.move(0,-5);
        if (user.collidesWithArray(walls)) {
            user.move(0,5);
            uCannon.move(0,5);
        }
        user.update();
        uCannon.update();
    }
    if(sKey) {
        user.move(0,5);
        uCannon.move(0,5);
        if (user.collidesWithArray(walls)) {
            user.move(0,-5);
            uCannon.move(0,-5);
        }
        user.update();
        uCannon.update();
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

// TODO: Prithu implement rotation and movement (Collision is already there)
function userMove() {
    if (wKey) {
        user.move(5,0);
        if (user.collidesWithArray(walls)) {
            user.move(-5,0);
        }
        user.update();
    }
    if(sKey) {
        user.move(-5,0);
        if (user.collidesWithArray(walls)) {
            user.move(5,0);
        }
        user.update();
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
    simpleMove();
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






