using System;
using System.Linq;
using System.Collections;
using System.Collections.Generic;

// Hello reader, this is my submission for the Feb 2022 event.
// The implementation has a significant memory footprint,
// but it should be easy to understand if you know object-oriented design.

class Association {
	int maxNum;
	public ICollection<int> allOddNum = new List<int>();
	public ICollection<int> allEvenNum = new List<int>();

	// Tracks what associations are possible for any integer
	IDictionary<int, ICollection<int>> possible = new Dictionary<int, ICollection<int>>();
	
	public Association(int coinCount) {
		this.maxNum = 2 * coinCount;

		// Generate all odd and even number once and for all
		for (int index = 0; index < coinCount; index++) {
			allOddNum.Add((2 * index) + 1);
			allEvenNum.Add((2 * index) + 2);
		}

		for (int num = 1; num <= maxNum; num++) {
			possible[num] = new HashSet<int>();
		}
		
		// Set all associations as possible
		foreach (int odd in allOddNum) {
			foreach (int even in allEvenNum) {
				possible[odd].Add(even);
				possible[even].Add(odd);
			}
		}
	}
	
	public int OtherSide(int side1) {
		return possible[side1].First();
	}
	
	public void Remove(int side1, int side2) {
		ICollection<int> possibleSide2 = possible[side1];
		bool modified = possibleSide2.Remove(side2);
		if (modified && (possibleSide2.Count() == 1)) {
			Cascade(side1);
		}
	}
	
	void Cascade(int side1) {
		int side2 = OtherSide(side1);
		possible[side2] = new List<int> {side1};
		for (int other_side1 = 1; other_side1 <= maxNum; other_side1++) {
			if (other_side1 != side1) {
				Remove(other_side1, side2);
			}
		}
	}
}

class Solution {

    void Run() {
        string[] inputs = Console.ReadLine().Split(' ');
        int coinCount = int.Parse(inputs[0]);

		Association association = new Association(coinCount);
		
        int tossCount = int.Parse(inputs[1]);
        for (int tossIndex = 0; tossIndex < tossCount; tossIndex++) {
			ICollection<int> values = new HashSet<int>();
            inputs = Console.ReadLine().Split(' ');
            for (int cointIndex = 0; cointIndex < coinCount; cointIndex++) {
				values.Add(int.Parse(inputs[cointIndex]));
			}
			// Eliminate impossible associations
			foreach (int odd in values) {
				foreach (int even in values) {
					association.Remove(odd, even);
				}
            }
        }

		IList<String> solutions = new List<String>();
		foreach (int odd in association.allOddNum) {
			int even = association.OtherSide(odd);
			solutions.Add(Convert.ToString(even));
		}
        Console.WriteLine(String.Join(" ", solutions));
    }

    static void Main(string[] args) {
        new Solution().Run();
    }
}
