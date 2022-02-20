while (true) {
    let maxHeightIndex: number = 0;
    let maxHeight: number = parseInt(readline());

    for (let index = 1; index < 8; index++) {
        const mountainH: number = parseInt(readline()); // represents the height of one mountain.
        if (maxHeight < mountainH) {
            maxHeight = mountainH;
            maxHeightIndex = index
        }
    }

    console.log(maxHeightIndex);     // The index of the mountain to fire on.
}
