
// Hello reader, this is my submission for the Feb 2022 event.
// The implementation has a significant memory footprint,
// but it should be easy to understand if you know object-oriented design.

class Coin {
    oddSide: number;
    evenSide: number;

    constructor(oddSide: number, evenSide: number) {
        this.oddSide = oddSide;
        this.evenSide = evenSide;
    }

    toString(): string {
        return this.oddSide + "-" + this.evenSide;
    }
}

class Association {
    maxNum: number;
    allOddNum: number[] = [];
    allEvenNum: number[] = [];

    // Tracks what associations are possible for any integer
    private possible: Map<number, Set<string>> = new Map();

    constructor(coinCount: number) {
        this.maxNum = coinCount * 2;
		
        // Generate all odd and even number once and for all
        for (let index = 0; index < coinCount; index++) {
            this.allOddNum.push((2 * index) + 1);
            this.allEvenNum.push((2 * index + 2));
        }

		for (let num = 1; num <= this.maxNum; num++) {
			this.possible.set(num, new Set<string>());
		}
		
		// Set all associations as possible
		for (const odd of this.allOddNum) {
			for (const even of this.allEvenNum) {
				this.add(new Coin(odd, even));
			}
		}
    }

	private add(coin: Coin): void {
		this.possible.get(coin.oddSide).add(coin.toString());
		this.possible.get(coin.evenSide).add(coin.toString());
	}
	
	delete(coin: Coin): void {
		this.possible.get(coin.oddSide).delete(coin.toString());
		this.possible.get(coin.evenSide).delete(coin.toString());
	}
	
	private isKnown(num: number): boolean {
		return (this.possible.get(num).size == 1);
	}
	
	getKnown(num: number): Coin {
        const str: string = this.possible.get(num).values().next().value;
        const nums = str.split("-");
        const odd = parseInt(nums[0]);
        const even = parseInt(nums[1]);
        return new Coin(odd, even);
	}
	
	// Remove impossible associations when a coin is known
	private lockCoin(knownCoin: Coin): void {
		for (const odd of this.allOddNum) {
			if (odd != knownCoin.oddSide) {
				const coin = new Coin(odd, knownCoin.evenSide);
				this.delete(coin);
			}
		}
		for (const even of this.allEvenNum) {
			if (even != knownCoin.evenSide) {
				const coin = new Coin(knownCoin.oddSide, even);
				this.delete(coin);
			}
		}
	}
	
	resolve(): void {
		let allKnown = false;
		while (!allKnown) {
			allKnown = true;
			for (let index = 1; index <= this.maxNum; index++) {
				if (this.isKnown(index)) {
					const coin = this.getKnown(index);
					this.lockCoin(coin);
				} else {
					allKnown = false;
				}
			}
		}
	}
}


const inputs1: string[] = readline().split(' ');
const coinCount: number = parseInt(inputs1[0]);
const assos = new Association(coinCount);

const tossCount: number = parseInt(inputs1[1]);
for (let tossIndex = 0; tossIndex < tossCount; tossIndex++) {
    const visibleOdd = new Set<number>();
    const visibleEven = new Set<number>();
    const inputs2: string[] = readline().split(' ');
    for (let coinIndex = 0; coinIndex < coinCount; coinIndex++) {
        const value: number = parseInt(inputs2[coinIndex]);
        if ((value % 2) == 0) {
            visibleEven.add(value);
        } else {
            visibleOdd.add(value);
        }
        // Eliminate impossible associations
        for (const odd of visibleOdd) {
            for (const even of visibleEven) {
                const coin = new Coin(odd, even);
                assos.delete(coin);
            }
        }
    }
}
assos.resolve();

const solutions: string[] = [];
for (const odd of assos.allOddNum) {
    const coin = assos.getKnown(odd);
    solutions.push(coin.evenSide.toString());
}
console.log(solutions.join(" "));
