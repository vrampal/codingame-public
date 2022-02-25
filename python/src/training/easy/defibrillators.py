from sys import stderr
from math import radians, cos, sqrt


class GeoCoord:
    __longi: float
    __lati: float

    def __init__(self, longi_str: str, lati_str: str):
        self.__longi = radians(self.__parse_double(longi_str))
        self.__lati = radians(self.__parse_double(lati_str))

    @staticmethod
    def __parse_double(value_str: str) -> float:
        return float(value_str.replace(',', '.'))

    def distance(self, other) -> float:
        x: float = (self.__longi - other.__longi) * cos((self.__lati + other.__lati) * 0.5)
        y: float = (self.__lati - other.__lati)
        return sqrt(x * x + y * y) * 6371.0


def main():
    user_coord: GeoCoord = GeoCoord(input(), input())

    closest_name: str = 'None'
    closest_dist: float = 9999999.9
    defib_count: int = int(input())
    for index in range(defib_count):
        defib_line: str = input()
        defib_data = defib_line.split(';')

        defib_name = defib_data[1]
        defib_coord = GeoCoord(defib_data[4], defib_data[5])

        dist: float = user_coord.distance(defib_coord)
        if closest_dist > dist:
            closest_dist = dist
            closest_name = defib_name

    print(closest_name)


if __name__ == '__main__':
    main()
