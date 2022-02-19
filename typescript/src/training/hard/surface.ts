
class Coord {
    x: number;
    y: number;

    constructor(x: number, y: number) {
        this.x = x;
        this.y = y;
    }

    adjacent(): Coord[] {
        return [new Coord(this.x, this.y - 1), new Coord(this.x + 1, this.y),
            new Coord(this.x, this.y + 1), new Coord(this.x - 1, this.y),];
    }
}

class Zone {
    size: number;

    constructor() {
        this.size = 0;
    }
}

class Board {
    width: number;
    height: number;
    cells: string[] = [];
    zones: Zone[][] = [];

    constructor() {
        this.width = parseInt(readline());
        this.height = parseInt(readline());
        for (let rowIdx = 0; rowIdx < this.height; rowIdx++) {
            this.cells[rowIdx] = readline();
            this.zones[rowIdx] = [];
        }
    }

    cellExist(pos: Coord): boolean {
        return ((pos.x >= 0) && (pos.x < this.width) && (pos.y >=  0) && (pos.y < this.height));
    }

    getCell(pos: Coord): string {
        return this.cells[pos.y].charAt(pos.x);
    }

    isWater(pos: Coord): boolean {
        return (this.cellExist(pos) && this.getCell(pos) == 'O');
    }

    getZoneAt(pos: Coord): Zone {
        if (!this.isWater(pos)) {
            return new Zone();
        }
        const candidate: Zone = this.zones[pos.y][pos.x];
        if (candidate != null) {
            return candidate;
        }
        return this.floodFill(pos);
    }

    floodFill(pos: Coord): Zone {
        const zone: Zone = new Zone();
        const toFill: Coord[] = [pos];
        while (toFill.length > 0) {
            const pos: Coord = toFill.pop();
            if (this.zones[pos.y][pos.x] == null) {
                this.zones[pos.y][pos.x] = zone;
                zone.size += 1
                const adj: Coord[] = pos.adjacent();
                for (let i = 0; i < adj.length; i++) {
                    const nextPos: Coord = adj[i];
                    if (this.isWater(nextPos)) {
                        // Note: queue may contains duplicates
                        toFill.push(nextPos);
                    }
                }
            }
        }
        return zone;
    }
}

const board: Board = new Board();
const posCount: number = parseInt(readline());
for (let i = 0; i < posCount; i++) {
    const inputs: string[] = readline().split(' ');
    const pos: Coord = new Coord(parseInt(inputs[0]), parseInt(inputs[1]));
    const zone: Zone = board.getZoneAt(pos);
    console.log(zone.size);
}
