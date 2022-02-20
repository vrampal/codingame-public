const inputs: string[] = readline().split(' ');
const lightX: number = parseInt(inputs[0]); // the X position of the light of power
const lightY: number = parseInt(inputs[1]); // the Y position of the light of power
let thorX: number = parseInt(inputs[2]); // Thor's starting X position
let thorY: number = parseInt(inputs[3]); // Thor's starting Y position

while (true) {
    const remainingTurns: number = parseInt(readline()); // The remaining amount of turns Thor can move. Do not remove this line.

    let direction = "";
    if (lightY > thorY) {
        direction = "S";
		thorY += 1;
	} else if (lightY < thorY) {
		direction = "N";
		thorY -= 1;
	}

	if (lightX > thorX) {
		direction += "E";
		thorX += 1;
	} else if (lightX < thorX) {
		direction += "W";
		thorX -= 1;
	}

    // A single line providing the move to be made: N NE E SE S SW W or NW
    console.log(direction);
}
