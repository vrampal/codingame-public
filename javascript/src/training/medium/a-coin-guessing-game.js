
// Hello reader, this is my submission for the Feb 2022 event.
// The implementation has a significant memory footprint,
// but it should be easy to understand if you know object-oriented design.

class Coin {
    oddSide;
    evenSide;

    constructor(oddSide, evenSide) {
        this.oddSide = oddSide;
        this.evenSide = evenSide;
    }

    toString() {
        return this.oddSide + "-" + this.evenSide;
    }
}

class Association {
    maxNum;
    allOddNum = [];
    allEvenNum = [];

    // Tracks what associations are possible for any integer
    possible = new Map();

    constructor(coinCount) {
        this.maxNum = coinCount * 2;
		
        // Generate all odd and even number once and for all
        for (let index = 0; index < coinCount; index++) {
            this.allOddNum.push((2 * index) + 1);
            this.allEvenNum.push((2 * index + 2));
        }

		for (let num = 1; num <= this.maxNum; num++) {
			this.possible.set(num, new Set());
		}
		
		// Set all associations as possible
		for (const odd of this.allOddNum) {
			for (const even of this.allEvenNum) {
				this.add(new Coin(odd, even));
			}
		}
    }

	add(coin) {
		this.possible.get(coin.oddSide).add(coin.toString());
		this.possible.get(coin.evenSide).add(coin.toString());
	}
	
	delete(coin) {
		this.possible.get(coin.oddSide).delete(coin.toString());
		this.possible.get(coin.evenSide).delete(coin.toString());
	}
	
	isKnown(num) {
		return (this.possible.get(num).size == 1);
	}
	
	getKnown(num) {
        const str = this.possible.get(num).values().next().value;
        const nums = str.split("-");
        const odd = parseInt(nums[0]);
        const even = parseInt(nums[1]);
        return new Coin(odd, even);
	}
	
	// Remove impossible associations when a coin is known
	lockCoin(knownCoin) {
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
	
	resolve() {
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


const inputs1 = readline().split(' ');
const coinCount = parseInt(inputs1[0]);
const assos = new Association(coinCount);

const tossCount = parseInt(inputs1[1]);
for (let tossIndex = 0; tossIndex < tossCount; tossIndex++) {
    const visibleOdd = new Set();
    const visibleEven = new Set();
    const inputs2 = readline().split(' ');
    for (let coinIndex = 0; coinIndex < coinCount; coinIndex++) {
        const value = parseInt(inputs2[coinIndex]);
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

const solutions = [];
for (const odd of assos.allOddNum) {
    const coin = assos.getKnown(odd);
    solutions.push(coin.evenSide.toString());
}
console.log(solutions.join(" "));
