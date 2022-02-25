using System;
using System.Linq;
using System.IO;
using System.Text;
using System.Collections;
using System.Collections.Generic;

// Hello reader, this is my submission for the Feb 2022 event.
// The implementation has a significant memory footprint,
// but it should be easy to understand if you know object-oriented design.

class Coin {
    public int oddSide;
    public int evenSide;

    public Coin(int oddSide, int evenSide) {
        this.oddSide = oddSide;
        this.evenSide = evenSide;
    }

    public override bool Equals(object obj) {
        return obj is Coin coin &&
               oddSide == coin.oddSide &&
               evenSide == coin.evenSide;
    }

    public override int GetHashCode() {
        return 1021 * oddSide + evenSide;
    }
}

class Association {
	int maxNum;
	public ICollection<int> allOddNum = new List<int>();
	public ICollection<int> allEvenNum = new List<int>();

	// Tracks what associations are possible for any integer
	IDictionary<int, ISet<Coin>> possible = new Dictionary<int, ISet<Coin>>();
	
	public Association(int coinCount) {
		this.maxNum = 2 * coinCount;

		// Generate all odd and even number once and for all
		for (int index = 0; index < coinCount; index++) {
			allOddNum.Add((2 * index) + 1);
			allEvenNum.Add((2 * index) + 2);
		}

		for (int num = 1; num <= maxNum; num++) {
			possible.Add(num, new HashSet<Coin>());
		}
		
		// Set all associations as possible
		foreach (int odd in allOddNum) {
			foreach (int even in allEvenNum) {
				Add(new Coin(odd, even));
			}
		}
	}
	
	void Add(Coin coin) {
		possible[coin.oddSide].Add(coin);
		possible[coin.evenSide].Add(coin);
	}
	
	public void Remove(Coin coin) {
		possible[coin.oddSide].Remove(coin);
		possible[coin.evenSide].Remove(coin);
	}
	
	bool IsKnown(int number) {
		return (possible[number].Count() == 1);
	}
	
	public Coin GetKnown(int number) {
		return possible[number].First();
	}
	
	// Remove impossible associations when a coin is known
	void LockCoin(Coin knownCoin) {
		foreach (int odd in allOddNum) {
			if (odd != knownCoin.oddSide) {
				Coin coin = new Coin(odd, knownCoin.evenSide);
				Remove(coin);
			}
		}
		foreach (int even in allEvenNum) {
			if (even != knownCoin.evenSide) {
				Coin coin = new Coin(knownCoin.oddSide, even);
				Remove(coin);
			}
		}
	}
	
	public void Resolve() {
		bool allKnown = false;
		while (!allKnown) {
			allKnown = true;
			for (int index = 1; index <= maxNum; index++) {
				if (IsKnown(index)) {
					Coin coin = GetKnown(index);
					LockCoin(coin);
				} else {
					allKnown = false;
				}
			}
		}
	}

}

class Solution {

    void Run() {
        string[] inputs = Console.ReadLine().Split(' ');
        int coinCount = int.Parse(inputs[0]);

		Association assos = new Association(coinCount);
		
        int tossCount = int.Parse(inputs[1]);
        for (int tossIndex = 0; tossIndex < tossCount; tossIndex++) {
			ICollection<int> visibleOdd = new HashSet<int>();
			ICollection<int> visibleEven = new HashSet<int>();
            inputs = Console.ReadLine().Split(' ');
            for (int cointIndex = 0; cointIndex < coinCount; cointIndex++) {
                int value = int.Parse(inputs[cointIndex]);
				if ((value % 2) == 0) {
					visibleEven.Add(value);
				} else {
					visibleOdd.Add(value);
				}
                // Eliminate impossible associations
                foreach (int odd in visibleOdd) {
                    foreach (int even in visibleEven) {
                        Coin coin = new Coin(odd, even);
                        assos.Remove(coin);
                    }
                }
            }
        }

        assos.Resolve();

		IList<String> solutions = new List<String>();
		foreach (int odd in assos.allOddNum) {
			Coin coin = assos.GetKnown(odd);
			solutions.Add(Convert.ToString(coin.evenSide));
		}
        Console.WriteLine(String.Join(" ", solutions));
    }

    static void Main(string[] args) {
        new Solution().Run();
    }
}
