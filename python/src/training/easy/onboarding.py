import sys


def main():
    while True:
        enemy1 = input()  # name of enemy 1
        dist1 = int(input())  # distance to enemy 1
        enemy2 = input()  # name of enemy 2
        dist2 = int(input())  # distance to enemy 2

        target = enemy1
        if dist1 > dist2:
            target = enemy2

        # You have to output a correct ship name to shoot ("Buzz", enemy1, enemy2, ...)
        print(target)


if __name__ == '__main__':
    main()
