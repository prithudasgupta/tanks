let TILE_SIZE = 45;
let BOARD_WIDTH = 24;
let BOARD_HEIGHT = 16;
let sel;

let wall, pot, brek, cur;

// set variables for mouse display
let curRow = 0;
let curCol = 0;
let prev;
let offScreen;

// create the Scene object and create the map
let scene = sjs.Scene({w:1305, h:720});
let game = scene.Layer('background', {useCanvas:false, autoClear:true});


// setup the blank map
let map = [];

class Tile {
    constructor(sprite, type) {
        this.sprite = sprite;
        this.type = type;
        this.perWall = false;
    }

}

class Selected {
    constructor(type) {
        this.type = type;
        if(type === "u") {
            this.string = "/sprites/wall.png";
        } else if (type === "p") {
            this.string = "/sprites/pothole.png";
        } else if (type === "b") {
            this.string = "/sprites/breakable.png";
        } else {
            this.string = "/sprites/freeSpace.png";
        }
    }
}


//
for (let row = 0; row < 16; row++) {
    map[row] = [];
    for (let col = 0; col < 24; col++) {
        if (row == 0 || row == 15 || col == 0 || col == 23) {
            map[row][col] = "u";
        } else {
            map[row][col] = "t";
        }
    }
}


let walls = [];


function loadMap() {
    scene.loadImages(["/sprites/wall.png", "/sprites/freeSpace.png", "/sprites/pothole.png", "/sprites/breakable.png",
        "/sprites/imm_tank.png", "/sprites/tank_space.png", "/sprites/selected.png", "/sprites/menu.png", "/sprites/select.png"], function () {
        // loading map into view
        for (let row = 0; row < 16; row++) {
            for (let col = 0; col < 24; col++) {
                let next;
                if (map[row][col] === "u") {
                    next = game.Sprite("/sprites/wall.png");
                    map[row][col] = new Tile(next, "u");
                    map[row][col].perWall = true;
                } else {
                    next = game.Sprite("/sprites/freeSpace.png");
                    map[row][col] = new Tile(next, "t");
                }
                next.move(col * TILE_SIZE, row * TILE_SIZE);
                next.update();
            }
        }

        // loading menu on the right
        for (let row = 0; row < 16; row++) {
            for (let col = 24; col < 29; col++) {
                let next;
                if (row === 0 || row === 15 || col === 24 || col === 28) {
                    next = game.Sprite("/sprites/wall.png");
                    map[row][col] = new Tile(next, "u");
                } else if (row === 2 && col === 26) {
                    next = game.Sprite("/sprites/wall.png");
                    wall = next;
                    cur = wall;
                    map[row][col] = new Tile(next, "t");
                    sel = game.Sprite("/sprites/select.png");
                    sel.move((col - 1) * TILE_SIZE, row * TILE_SIZE);
                    sel.update();
                } else if (row === 4 && col === 26) {
                    next = game.Sprite("/sprites/pothole.png");
                    pot = next;
                    map[row][col] = new Tile(next, "t");
                } else if (row === 6 && col === 26) {
                    next = game.Sprite("/sprites/breakable.png");
                    brek = next;
                    map[row][col] = new Tile(next, "t");
                } else {
                    next = game.Sprite("/sprites/menu.png");
                    map[row][col] = new Tile(next, "t");
                }
                next.move(col * TILE_SIZE, row * TILE_SIZE);
                next.update();
            }
        }
    });

}

function updateMouse() {
    if(curRow !== undefined) {
        if ((map[curRow][curCol]).perWall === false) {
            // if (prev === undefined) {
            //     prev = map[curRow][curCol];
            //     map[curRow][curCol].sprite.loadImg("/sprites/selected.png");
            //     map[curRow][curCol].sprite.update();
            // } else {
            //     if (prev !== map[curRow][curCol]) {
            //         map[curRow][curCol].sprite.loadImg("/sprites/selected.png");
            //         map[curRow][curCol].sprite.update();
            //         if (prev.type === "t") {
            //             prev.sprite.loadImg("/sprites/freeSpace.png");
            //             prev.sprite.update();
            //         }
            //         prev = map[curRow][curCol];
            //     }
            // }
        }

    }
}
    $(document).ready(() => {

        document.addEventListener("mousemove", function(e) {
            if (e.clientY <= 720 && e.clientY >= 0 && e.clientX >= 0 && e.clientX <= 1080) {
                curRow = Math.floor(e.clientY/45);
                curCol = Math.floor(e.clientX/45);
                offScreen = false;
            }

        });
        document.addEventListener("click", function(e) {
            if (e.clientY <= 720 && e.clientY >= 0 && e.clientX >= 0 && e.clientX <= 1080) {
                if ((map[curRow][curCol]).perWall === false) {
                    if (map[curRow][curCol].type !== cur.sel.type) {
                        map[curRow][curCol].type = cur.sel.type;
                        map[curRow][curCol].sprite.loadImg(cur.sel.string);
                        map[curRow][curCol].sprite.update();
                    }

                }
            }
            if (wall.isPointIn(e.clientX, e.clientY)) {
                if (cur !== wall) {
                    sel.move((26 - 1) * TILE_SIZE, 2 * TILE_SIZE);
                    sel.update();
                    cur = wall;
                    cur.sel = new Selected("u");
                }
            }
            if (pot.isPointIn(e.clientX, e.clientY)) {
                if (pot !== wall) {
                    sel.move((26 - 1) * TILE_SIZE, 4 * TILE_SIZE);
                    sel.update();
                    cur = pot;
                    cur.sel = new Selected("p");
                }
            }
            if (brek.isPointIn(e.clientX, e.clientY)) {
                if (brek !== wall) {
                    sel.move((26 - 1) * TILE_SIZE, 6 * TILE_SIZE);
                    sel.update();
                    cur = brek;
                    cur.sel = new Selected("b");
                }
            }


        });
        loadMap();

        main();
            // getMap();
            // render();
            // console.log(map);
    });

function main() {
    updateMouse();

    window.requestAnimationFrame(main);

}
