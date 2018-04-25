let TILE_SIZE = 45;
let BOARD_WIDTH = 24;
let BOARD_HEIGHT = 16;


// create the Scene object and create the map
let scene = sjs.Scene({w:1080, h:720});
let canvasbg = scene.Layer('background', {useCanvas:false, autoClear:true});


// setup the map
let map = [];
let walls = [];


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
        
 
$(document).ready(() => {

	console.log("here");

	for (let row = 0; row < 16; row++) {
    map[row] = [];
    for (let col = 0; col < 24; col++) {
        map[row][col] = "u";
    }
    
    loadMap();
    getMap();
    render();
    console.log(map);
}


});