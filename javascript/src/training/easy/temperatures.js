const temperCount = parseInt(readline()); // the number of temperatures to analyse
let bestTemper = 0;
let smallestNorm = 999999;

const inputs = readline().split(' ');
for (let index = 0; index < temperCount; index++) {
    const temper = parseInt(inputs[index]);// a temperature expressed as an integer ranging from -273 to 5526
    let norm = temper * 2;
    if (temper < 0) {
        norm = temper * -2 + 1;
    }
    if (smallestNorm > norm) {
        smallestNorm = norm;
        bestTemper = temper;
    }
}

console.log(bestTemper);
