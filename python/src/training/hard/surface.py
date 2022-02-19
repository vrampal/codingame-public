import sys


class Coord:
    x: int
    y: int

    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y

    def adjacent(self) -> list:
        return [Coord(self.x, self.y - 1), Coord(self.x + 1, self.y),
                Coord(self.x, self.y + 1), Coord(self.x - 1, self.y)]


class Zone:
    size: int

    def __init__(self):
        self.size = 0


class Board:
    __width: int
    __height: int
    __cells: list
    __zone: list

    def __init__(self):
        self.__width = int(input())
        self.__height = int(input())
        self.__cells = list()
        self.__zones = list()
        for rowIdx in range(self.__height):
            self.__cells.append(input())
            self.__zones.append([None for _ in range(self.__width)])

    def __cell_exist(self, pos: Coord) -> bool:
        return (pos.x >= 0) and (pos.x < self.__width) and (pos.y >= 0) and (pos.y < self.__height)

    def __get_cell(self, pos: Coord) -> str:
        return self.__cells[pos.y][pos.x]

    def __is_water(self, pos: Coord) -> bool:
        return self.__cell_exist(pos) and self.__get_cell(pos) == 'O'

    def get_zone(self, pos: Coord) -> Zone:
        if not self.__is_water(pos):
            return Zone()
        candidate: Zone = self.__zones[pos.y][pos.x]
        if candidate:
            return candidate
        return self.__flood_fill(pos)

    def __flood_fill(self, start: Coord) -> Zone:
        zone: Zone = Zone()
        to_fill = list()
        to_fill.append(start)
        while to_fill:
            pos: Coord = to_fill.pop()
            if self.__zones[pos.y][pos.x] is None:
                self.__zones[pos.y][pos.x] = zone
                zone.size += 1
                for next_pos in pos.adjacent():
                    if self.__is_water(next_pos):
                        to_fill.append(next_pos)
        return zone


def main():
    board = Board()
    pos_count = int(input())
    for posIdx in range(pos_count):
        x, y = [int(val) for val in input().split()]
        pos = Coord(x, y)
        zone = board.get_zone(pos)
        print(zone.size)


if __name__ == '__main__':
    main()
