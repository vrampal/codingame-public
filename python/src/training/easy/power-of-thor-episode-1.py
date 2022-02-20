import sys


def main():
    # light_x: the X position of the light of power
    # light_y: the Y position of the light of power
    # thor_x: Thor's starting X position
    # thor_y: Thor's starting Y position
    light_x, light_y, thor_x, thor_y = [int(i) for i in input().split()]

    while True:
        remaining_turns = int(input())  # The remaining amount of turns Thor can move. Do not remove this line.

        direction: str = ''
        if light_y > thor_y:
            direction = 'S'
            thor_y += 1
        else:
            if light_y < thor_y:
                direction = 'N'
                thor_y -= 1

        if light_x > thor_x:
            direction += 'E'
            thor_x += 1
        else:
            if light_x < thor_x:
                direction += 'W'
                thor_x -= 1

        # A single line providing the move to be made: N NE E SE S SW W or NW
        print(direction)


if __name__ == '__main__':
    main()
