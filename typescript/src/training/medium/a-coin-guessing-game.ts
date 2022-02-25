
// Hello reader, this is my submission for the Feb 2022 event.
// The implementation has a significant memory footprint,
// but it should be easy to understand if you know object-oriented design.

class Association {
    allOddNum: number[] = [];
    allEvenNum: number[] = [];

    // Tracks what associations are possible for any integer
    private possible: Map<number, Set<number>> = new Map();

    constructor(coinCount: number) {
        // Generate all odd and even number once and for all
        for (let index = 0; index < coinCount; index++) {
            this.allOddNum.push((2 * index) + 1);
            this.allEvenNum.push((2 * index + 2));
        }

		for (let num = 1; num <= (coinCount * 2); num++) {
			this.possible.set(num, new Set<number>());
		}
		
		// Set all associations as possible
		for (const odd of this.allOddNum) {
			for (const even of this.allEvenNum) {
				this.possible.get(odd).add(even);
				this.possible.get(even).add(odd);
			}
		}
    }

	otherSide(side1: number): number {
		return this.possible.get(side1).values().next().value;
	}
	
	remove(side1: number, side2: number): void {
		const possibleSide2 = this.possible.get(side1);
		const modified = possibleSide2.delete(side2);
		if (modified && (possibleSide2.size == 1)) {
			this.cascade(side1);
		}
	}
	
	private cascade(side1: number): void {
		const side2 = this.otherSide(side1);
		this.possible.set(side2, new Set([side1]));
		for (const other_side1 of this.possible.keys()) {
			if (other_side1 != side1) {
				this.remove(other_side1, side2);
			}
		}
	}
}


const inputs1: string[] = readline().split(' ');
const coinCount: number = parseInt(inputs1[0]);
const association = new Association(coinCount);

const tossCount: number = parseInt(inputs1[1]);
for (let tossIndex = 0; tossIndex < tossCount; tossIndex++) {
    const values = new Set<number>();
    const inputs2: string[] = readline().split(' ');
    for (let coinIndex = 0; coinIndex < coinCount; coinIndex++) {
		values.add(parseInt(inputs2[coinIndex]));
	}
	// Eliminate impossible associations
	for (const side1 of values) {
		for (const side2 of values) {
			association.remove(side1, side2);
		}
	}
}

const solutions: string[] = [];
for (const odd of association.allOddNum) {
    const even = association.otherSide(odd);
    solutions.push(even.toString());
}
console.log(solutions.join(" "));
