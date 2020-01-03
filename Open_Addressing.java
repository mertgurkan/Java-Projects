package A1;

import static A1.main.*;

public class Open_Addressing {

	public int m; // number of SLOTS AVAILABLE
	public int A; // the default random number
	int w;
	int r;
	public int[] Table;

	//Constructor for the class. sets up the data structure for you
	protected Open_Addressing(int w, int seed) {

		this.w = w;
		this.r = (int) (w - 1) / 2 + 1;
		this.m = power2(r);
		this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
		this.Table = new int[m];
		//empty slots are initalized as -1, since all keys are positive
		for (int i = 0; i < m; i++) {
			Table[i] = -1;
		}

	}

	/**
	 * Implements the hash function g(k)
	 */
	public int probe(int key, int i) {
		//ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)    	
		return (((this.A * key % power2(this.w)) >> (this.w - this.r))+i) % power2(this.r);
	}

	/**
	 * Checks if slot n is empty
	 */
	public boolean isSlotEmpty(int hashValue) {
		return Table[hashValue] == -1;
	}

	/**
	 * Inserts key k into hash table. Returns the number of collisions
	 * encountered
	 */
	public int insertKey(int key) {
		//ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
		int x = 0;
		boolean find = false;
		int hashValue = probe(key, x);
		int col = 0;
		while(!find && x < m) {
			// Empty Slot
			if(isSlotEmpty(hashValue)) {
				Table[hashValue] = key;
				find = true;
			} 
			// Not empty
			else {
				// Already inserted
				if(Table[hashValue] == key) {
					return col;
				}
				// Else continue looking
				x++;
				col++;
				hashValue = probe(key, x);
				continue;
			}
		}
		return col;
	}

	/**
	 * Removes key k from hash table. Returns the number of collisions
	 * encountered
	 */
	public int removeKey(int key) {
		//ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
		int y = 0;
		boolean find = false;
		int hashValue = probe(key, y);
		int col = 0;
		while(!find && y < m) {
			// Key found
			if(key == Table[hashValue]) {
				// Place -2 instead of -1 to show removed
				Table[hashValue] = -2;
				find = true;
			} 
			// Key not found
			else {
				// Empty (key we want is unreachable)
				if(isSlotEmpty(hashValue)) {
					col++;
					break;
				}
				// Keep looking
				else {
					y++;
					col++;
					hashValue = probe(key, y);
					continue;
				}
			}
		}
		return col;
	}

}
