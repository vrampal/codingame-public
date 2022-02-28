from typing import Dict, List, Set
# Hello reader, this is my submission for the Feb 2022 event.
# The implementation has a significant memory footprint,
# but it should be easy to understand if you know object-oriented design.


class Association:
    all_odd_num: List[int] = []
    all_even_num: List[int] = []
    __possible: Dict[int, Set[int]] = {}  # Tracks what associations are possible for any integer

    def __init__(self, coin_count: int):
        # Generate all odd and even number once and for all
        for index in range(coin_count):
            self.all_odd_num.append(2 * index + 1)
            self.all_even_num.append(2 * index + 2)

        for index in range(1, (2 * coin_count) + 1):
            self.__possible[index] = set()

        # Set all associations as possible
        for odd in self.all_odd_num:
            for even in self.all_even_num:
                self.__possible[odd].add(even)
                self.__possible[even].add(odd)

    def other_side(self, side1: int) -> int:
        return next(iter(self.__possible[side1]))

    def remove(self, side1: int, side2: int):
        if side2 in self.__possible[side1]:
            self.__possible[side1].remove(side2)
            if len(self.__possible[side1]) == 1:
                self.__cascade(side1)

    def __cascade(self, side1: int) -> None:
        side2: int = self.other_side(side1)
        self.__possible[side2] = {side1}
        for other_side1 in self.__possible:
            if other_side1 != side1:
                self.remove(other_side1, side2)


def main():
    coin_count, toss_count = [int(i) for i in input().split()]
    association = Association(coin_count)

    for _ in range(toss_count):
        values = [int(i) for i in input().split()]
        for side1 in values:
            for side2 in values:
                association.remove(side1, side2)

    solutions = []
    for odd in association.all_odd_num:
        even: int = association.other_side(odd)
        solutions.append(str(even))
    print(' '.join(solutions))


if __name__ == '__main__':
    main()
