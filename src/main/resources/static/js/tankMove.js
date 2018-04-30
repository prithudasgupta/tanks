let nextAngle = -1;
let nextRow = 0;
let nextCol = 0;


function moveBetween(endRow, endCol, startRow, startCol, sprite){

	const currAngle = sprite.angle % 6.28;
	let angleDiff;
	
	if (nextAngle === -1){
	
		nextRow = endRow;
		nextCol = endCol;
		if (endRow - startRow == 0){
			if (endCol - startCol == 1){
				nextAngle = 0;	
			}
			else{
				nextAngle = 3.1415;
			}
		}
		else if(endRow - startRow == 1){
			nextAngle = 1.5707;
		}
		else{
			nextAngle = 4.712;
		}
	}
	if (currAngle - nextAngle < 0.05 || nextAngle - currAngle < 0.05){
		let mov = forwardByAngle(sprite.angle, 2.5);
        sprite.move(mov[0], mov[1]);
       
       	nextAngle = -1;
   
	}
	else {
		console.log(currAngle, nextAngle);
		if (currAngle > nextAngle){
			sprite.rotate(-0.02);
		}
		else{
			sprite.rotate(0.02);
		}
	}
	sprite.update();
}

function forwardByAngle(angRads, speed) {
    let x = speed * Math.cos(angRads);
    let y = speed * Math.sin(angRads);
    return [x,y];
}