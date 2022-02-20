const temperCount: number = parseInt(readline()); // the number of temperatures to analyse
let bestTemper: number = 0;
let smallestNorm: number = 999999;

const inputs: string[] = readline().split(' ');
for (let index = 0; index < temperCount; index++) {
    const temper: number = parseInt(inputs[index]);// a temperature expressed as an integer ranging from -273 to 5526
    let norm: number = temper * 2;
    if (temper < 0) {
        norm = temper * -2 + 1;
    }
    if (smallestNorm > norm) {
        smallestNorm = norm;
        bestTemper = temper;
    }
}

console.log(bestTemper);
