console.log("hello");

let canvas;
let TILE_SIZE = ($(window).height() / 16);
let BOARD_WIDTH = 24;
let BOARD_HEIGHT = 16;
let TANK_WIDTH = TILE_SIZE * 0.6;
let TANK_HEIGHT = TILE_SIZE * 0.4;


class Point {
    constructor() {
        this.X = null;
        this.Y = null;
    }
}


class Tank {
    constructor() {
        this.X;
        this.Y;
        this.deg = null;
    }
}

class Block {
    constructor(type) {
        if(type === "u") {
            this.color = "YELLOW";
        } 
        else if(type === "b"){
            this.color = "BROWN";
        }
        else if(type === "l"){
            this.color = "GREEN";
        }
        else if(type === "p"){
            this.color = "GREY";
        }
        this.height =  ($(window).height() / 16);
        this.width = ($(window).height() / 16);
    }
}

let map = [];

for (let row = 0; row < 16; row++) {
    map[row] = [];
    for (let col = 0; col < 24; col++) {
        map[row][col] = 0;
    }
}

function populateMap(row, col, type) {
    map[row][col] = new Block(type);
}

function paintMap(mapToPrint) {
    ctx.clearRect(0, 0, 24*TILE_SIZE, 16*TILE_SIZE);
    for (let row = 0; row < 16; row++) {
        for (let col = 0; col < 24; col++) {
            let x = col * TILE_SIZE;
            let y = row * TILE_SIZE;
            ctx.fillStyle = mapToPrint[row][col].color;
            ctx.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        }
    }
}

function blocksToCanvas(row, col){
	return new Point(row*TILE_SIZE, col*TILE_SIZE);
}


$(document).ready(() => {

    $(document).on("keydown", function() {
        // rotation event
       if (event.which === 65 || event.which === 68 || event.which === 87 || event.which === 83) {
           let direction;
           // a
           if (event.which === 65) {
               // left
               direction = 1;
           }
           // d
           if (event.which === 68) {
               // right
               direction = 0;
           }
           // w
           if (event.which === 87) {
               // forward
               direction = 2
           }
           // s
           if (event.which === 83) {
               direction = 3;
           }
           console.log(direction);
           $.post('/game', {'direction': direction, "height": 0.6, "width": 0.4}, responseJSON => {
               const responseObject = JSON.parse(responseJSON);
               
               
               user.Y = (responseObject.game.user.location.coordinates[1]  -.25) * TILE_SIZE;
               user.X = (responseObject.game.user.location.coordinates[0] - 0.3) * TILE_SIZE;
               user.deg = responseObject.game.user.angleForward.degrees;
               
               console.log(user.deg);

               ctx.fillStyle = "BLACK";
               ctx.fillRect(user.X, user.Y, TANK_WIDTH, TANK_HEIGHT);
               paintMap(map);
               
               ctx.save();

               ctx.beginPath();
               // move the rotation point to the center of the rect
               ctx.translate( user.X + TANK_WIDTH/2, user.Y + TANK_HEIGHT/2 );
               // rotate the rect
               ctx.rotate(user.deg*Math.PI/180);

               // draw the rect on the transformed context
               // Note: after transforming [0,0] is visually [x,y]
               //       so the rect needs to be offset accordingly when drawn
               ctx.rect( -TANK_WIDTH/2, -TANK_HEIGHT/2, TANK_WIDTH,TANK_HEIGHT);

               ctx.fillStyle = "BLACK";
               ctx.fill();

               // restore the context to its untranslated/unrotated state
               //ctx.resetTransform();
               ctx.restore();
               
               //console.log(user.deg);
               

//               ctx.fillStyle = "BLACK";
//               ctx.translate(user.X + TANK_WIDTH/2, user.Y + TANK_HEIGHT/2);
//               ctx.rotate(user.deg);
//               ctx.fillRect(user.X, user.Y, TANK_WIDTH, TANK_HEIGHT);
//               ctx.resetTransform();
           });
       }
    });

    // Setting up the canvas.
    canvas = $('#canvas')[0];
    canvas.width = BOARD_WIDTH * TILE_SIZE;
    canvas.height = BOARD_HEIGHT * TILE_SIZE;
    let user = new Tank();

    // TODO: Set up the canvas context.
    ctx = canvas.getContext("2d");
    
    let refresh = 0;
    if(performance.navigation.type == 1){
    		
    		refresh = 1;
    	
    }
    
    let postParams = {"refresh": refresh};

    $.post('/game', postParams, responseJSON => {
        const respObject = JSON.parse(responseJSON);
        console.log(respObject);
        for (let row = 0; row < 16; row++) {
            for (let col = 0; col < 24; col++) {
                
                    populateMap(row, col, respObject.representations[row][col]);
            }
        }
        paintMap(map);
        ctx.fillStyle = "BLACK";
        
        user.X = (respObject.game.user.location.coordinates[0] - .3) * TILE_SIZE;
        user.Y = (respObject.game.user.location.coordinates[1] - .25) * TILE_SIZE;
        ctx.fillRect(user.X, user.Y, TANK_WIDTH, TANK_HEIGHT);
    });


});
