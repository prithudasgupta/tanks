// let nextAngle = -1;
// let nextRow = 0;
// let nextCol = 0;

/**
 * A function that moves an enemy tank to a predetermined position
 * @param enemyObj The object of the enemy tank which contains the sprite, along with
 * 			information that explains that destination of the tank.
 */
function moveBetween(enemyObj){
	let currAngle = enemyObj.sprite.angle % 6.28;
	let angleDiff;
	if (Math.abs(currAngle - enemyObj.nextAngle) < 0.019){
		let mov = forwardByAngle(enemyObj.sprite.angle, 2.5);
        enemyObj.sprite.move(mov[0], mov[1]);

	}
	else {
		//console.log(currAngle, enemyObj.nextAngle);
		if (currAngle > enemyObj.nextAngle){
			enemyObj.sprite.rotate(-0.02);
		}
		else{
			enemyObj.sprite.rotate(0.02);
		}
	}
	enemyObj.sprite.update();
}

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