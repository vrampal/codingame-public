class Coord {
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }

  adjacent() {
    return [new Coord(this.x, this.y - 1), new Coord(this.x + 1, this.y),
      new Coord(this.x, this.y + 1), new Coord(this.x - 1, this.y)];
  }
}

class Zone {
  constructor() {
    this.size = 0;
  }
}

class Board {
  constructor() {
    this._width = parseInt(readline());
    this._height = parseInt(readline());
    this._cells = []
    this._zones = []
    for (let rowIdx = 0; rowIdx < this._height; rowIdx++) {
      this._cells[rowIdx] = readline();
      this._zones[rowIdx] = [];
    }
  }

  _cellExist(pos) {
    return pos.x >= 0 && pos.x < this._width && pos.y >= 0 && pos.y < this._height;
  }

  _getCell(pos) {
    return this._cells[pos.y].charAt(pos.x);
  }

  _isWater(pos) {
    return this._cellExist(pos) && this._getCell(pos) == "O";
  }

  getZoneAt(pos) {
    if (!this._isWater(pos)) {
      return new Zone();
    }
    const candidate = this._zones[pos.y][pos.x];
    if (candidate != null) {
      return candidate;
    }
    return this._floodFill(pos);
  }

  _floodFill(start) {
    const zone = new Zone();
    const toFill = [start];
    while (toFill.length > 0) {
      const pos = toFill.pop();
      if (this._zones[pos.y][pos.x] == null) {
        this._zones[pos.y][pos.x] = zone;
        zone.size += 1;
        const adj = pos.adjacent();
        for (let i = 0; i < adj.length; i++) {
          const nextPos = adj[i];
          if (this._isWater(nextPos)) {
            toFill.push(nextPos);
          }
        }
      }
    }
    return zone;
  }
}

const board = new Board();
const posCount = parseInt(readline());
for (let i = 0; i < posCount; i++) {
  const inputs = readline().split(" ");
  const pos = new Coord(parseInt(inputs[0]), parseInt(inputs[1]));
  const zone = board.getZoneAt(pos);
  console.log(zone.size);
}
