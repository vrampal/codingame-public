from sys import stderr

# Hello reader, this is my submission for the Feb 2022 event.
# The implementation has a significant memory footprint,
# but it should be easy to understand if you know object-oriented design.


class Coin:
    odd_side: int
    even_side: int

    def __init__(self, odd_side: int, even_side: int):
        self.odd_side = odd_side
        self.even_side = even_side

    def __eq__(self, other):
        if not isinstance(other, Coin):
            return False
        return self.odd_side == other.odd_side and self.even_side == other.even_side

    def __hash__(self):
        return 1021 * self.odd_side + self.even_side


class Association:
    max_num: int
    all_odd_num: list[int] = []
    all_even_num: list[int] = []

    # Tracks what associations are possible for any integer
    __possible: dict[int, set[Coin]] = {}

    def __init__(self, coin_count: int):
        self.max_num = coin_count * 2

        # Generate all odd and even number once and for all
        for index in range(coin_count):
            self.all_odd_num.append(2 * index + 1)
            self.all_even_num.append(2 * index + 2)

        for index in range(1, self.max_num + 1):
            self.__possible[index] = set()

        # Set all associations as possible
        for odd in self.all_odd_num:
            for even in self.all_even_num:
                self.__add(Coin(odd, even))

    def __add(self, coin: Coin) -> None:
        self.__possible[coin.odd_side].add(coin)
        self.__possible[coin.even_side].add(coin)

    def remove(self, coin: Coin) -> None:
        if coin in self.__possible[coin.odd_side]:
            self.__possible[coin.odd_side].remove(coin)
        if coin in self.__possible[coin.even_side]:
            self.__possible[coin.even_side].remove(coin)

    def __is_known(self, number: int) -> bool:
        return len(self.__possible[number]) == 1

    def get_known(self, number: int) -> Coin:
        return next(iter(self.__possible[number]))

    # Remove impossible associations when a coin is known
    def __lock_coin(self, known_coin: Coin) -> None:
        for odd in self.all_odd_num:
            if odd != known_coin.odd_side:
                coin = Coin(odd, known_coin.even_side)
                self.remove(coin)
        for even in self.all_even_num:
            if even != known_coin.even_side:
                coin = Coin(known_coin.odd_side, even)
                self.remove(coin)

    def resolve(self) -> None:
        all_known: bool = False
        while not all_known:
            all_known = True
            for index in range(1, self.max_num + 1):
                if self.__is_known(index):
                    coin = self.get_known(index)
                    self.__lock_coin(coin)
                else:
                    all_known = False


def main():
    coin_count, toss_count = [int(i) for i in input().split()]
    association = Association(coin_count)

    for _ in range(toss_count):
        visible_odd = []
        visible_even = []
        for val_str in input().split():
            value = int(val_str)
            if (value % 2) == 0:
                visible_even.append(value)
            else:
                visible_odd.append(value)

        # Eliminate impossible associations
        for odd in visible_odd:
            for even in visible_even:
                coin = Coin(odd, even)
                association.remove(coin)

    association.resolve()

    solutions: list[str] = []
    for odd in association.all_odd_num:
        coin = association.get_known(odd)
        solutions.append(str(coin.even_side))
    print(' '.join(solutions))


if __name__ == '__main__':
    main()
