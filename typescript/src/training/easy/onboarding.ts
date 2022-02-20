while (true) {
    const enemy1: string = readline(); // name of enemy 1
    const dist1: number = parseInt(readline()); // distance to enemy 1
    const enemy2: string = readline(); // name of enemy 2
    const dist2: number = parseInt(readline()); // distance to enemy 2

    // Write an action using console.log()
    let target: string = enemy1;
    if (dist1 > dist2) {
        target = enemy2;
    }

    // You have to output a correct ship name to shoot ("Buzz", enemy1, enemy2, ...)
    console.log(target);
}
