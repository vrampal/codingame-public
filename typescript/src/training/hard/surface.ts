
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
    private _width: number;
    private _height: number;
    private _cells: string[] = [];
    private _zones: Zone[][] = [];

    constructor() {
        this._width = parseInt(readline());
        this._height = parseInt(readline());
        for (let rowIdx = 0; rowIdx < this._height; rowIdx++) {
            this._cells[rowIdx] = readline();
            this._zones[rowIdx] = [];
        }
    }

    private _cellExist(pos: Coord): boolean {
        return ((pos.x >= 0) && (pos.x < this._width) && (pos.y >=  0) && (pos.y < this._height));
    }

    private _getCell(pos: Coord): string {
        return this._cells[pos.y].charAt(pos.x);
    }

    private _isWater(pos: Coord): boolean {
        return (this._cellExist(pos) && this._getCell(pos) == 'O');
    }

    getZoneAt(pos: Coord): Zone {
        if (!this._isWater(pos)) {
            return new Zone();
        }
        const candidate: Zone = this._zones[pos.y][pos.x];
        if (candidate != null) {
            return candidate;
        }
        return this._floodFill(pos);
    }

    private _floodFill(pos: Coord): Zone {
        const zone: Zone = new Zone();
        const toFill: Coord[] = [pos];
        while (toFill.length > 0) {
            const pos: Coord = toFill.pop();
            if (this._zones[pos.y][pos.x] == null) {
                this._zones[pos.y][pos.x] = zone;
                zone.size += 1
                const adj: Coord[] = pos.adjacent();
                for (let i = 0; i < adj.length; i++) {
                    const nextPos: Coord = adj[i];
                    if (this._isWater(nextPos)) {
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
