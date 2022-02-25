from sys import stderr


def main():
    temper_count: int = int(input())  # the number of temperatures to analyse
    best_temper: int = 0
    smallest_norm: int = 999999

    for temper_str in input().split():
        # t: a temperature expressed as an integer ranging from -273 to 5526
        temper: int = int(temper_str)
        norm: int = temper * 2
        if temper < 0:
            norm = temper * -2 + 1
        if smallest_norm > norm:
            smallest_norm = norm
            best_temper = temper

    print(best_temper)


if __name__ == '__main__':
    main()
