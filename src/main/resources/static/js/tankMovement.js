console.log("hello");

let canvas;
let TILE_SIZE = ($(window).height() / 16);
let BOARD_WIDTH = 24;
let BOARD_HEIGHT = 16;
let TANK_WIDTH = TILE_SIZE * 0.8;
let TANK_HEIGHT = TILE_SIZE * 0.6;


class block {
    constructor(type) {
        if(type === "wall") {
            this.color = "GREY";
        } else {
            this.color = "BLUE";
        }
        this.height =  ($(window).height() / 16);
        this.width = ($(window).height() / 16);
    }
}

let map = [];

for (let row = 0; row < 16; row++) {
    map[row] = [];
    for (let col = 0; col < 24; col++) {
        if(row == 0 || row == 15 || col == 0 || col == 23) {
            map[row][col] = new block("wall");
        } else {
            map[row][col] = new block("free");
        }
    }
}

function paintMap() {
    for (let row = 0; row < 16; row++) {
        for (let col = 0; col < 24; col++) {
            let x = col * TILE_SIZE;
            let y = row * TILE_SIZE;
            ctx.fillStyle = map[row][col].color;
            ctx.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        }
    }
}

function addTank() {
    ctx.fillStyle = "RED";
    ctx.fillRect(50, 50, TANK_WIDTH, TANK_HEIGHT);
}



$(document).ready(() => {

    // Setting up the canvas.
    canvas = $('#canvas')[0];
    canvas.width = BOARD_WIDTH * TILE_SIZE;
    canvas.height = BOARD_HEIGHT * TILE_SIZE;

    // TODO: Set up the canvas context.
    ctx = canvas.getContext("2d");
    paintMap();
    addTank();
});
