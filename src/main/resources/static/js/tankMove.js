


function moveBetween(endRow, endCol, startRow, startCol, sprite){
	let nextAngle;
	
	if (endRow - startRow == 0){
		if (endCol - startCol == 1){
			nextAngle = 0;
		}
		else{
			nextAngle = 180;
		}
	}
	else if(endRow - startRow == 1){
		nextAngle = 270;
	}
	else{
		nextAngle = 90;
	}
	
	console.log(nextAngle);
	sprite.setAngle(0);
	sprite.rotate(nextAngle);
	console.log("from " + sprite.x, sprite.y);
	
	console.log("moving it to " + (endRow *45) + ", "+ (endCol*45));
	
	console.log("diff " + " " + ((endRow * 45) - sprite.x) + " " + ((endCol*45) - sprite.y));
	sprite.move((endRow * 45) - sprite.x, (endCol*45) - sprite.y);
	sprite.update();
	console.log("After " + sprite.x, sprite.y);
	
}