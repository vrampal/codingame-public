from sys import stderr


def main():
    while True:
        max_height_index: int = 0
        max_height: int = int(input())

        for index in range(1, 8):
            mountain_h: int = int(input())  # represents the height of one mountain.
            if max_height < mountain_h:
                max_height = mountain_h
                max_height_index = index

        # The index of the mountain to fire on.
        print(max_height_index)


if __name__ == '__main__':
    main()
