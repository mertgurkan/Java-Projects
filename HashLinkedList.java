// Student Name: Mert Gurkan
// ID Number: 260716883

public class HashLinkedList<K, V> {
	/*
	 * Fields
	 */
	private HashNode<K, V> head;

	private Integer size;

	/*
	 * Constructor
	 */
	HashLinkedList() {
		head = null;
		size = 0;
	}

	/*
	 * Add (Hash)node at the front of the linked list
	 */
	public void add(K key, V value) {
		// ADD CODE BELOW HERE
		
		HashNode<K, V> updatedNode = new HashNode<K, V>(key, value);
		if (this.isEmpty()) 
		{
			updatedNode.helpNext(null);
		} else {
			updatedNode.helpNext(this.getFirst());
		}
		this.setFirst(updatedNode);
		this.increaseSize();
		
		// ADD CODE ABOVE HERE
	}

	/*
	 * Get Hash(node) by key returns the node with key
	 */
	public HashNode<K, V> getListNode(K key) {
		// ADD CODE BELOW HERE
		
		if (this.isEmpty()) 
		{
			return null;
		}

		HashNode<K, V> recent = this.getFirst(); // start from the beginning of the list
		while (recent.getKey() != key && recent.getNext() != null)
		{
			recent = recent.getNext();
		}

		if (recent.getKey() == key)
		// finding the node with the key
		{
			return recent;
		} 
		else 
		{
			return null; // reached the final node with no key found
		}
		
		// ADD CODE ABOVE HERE
	}

	/*
	 * Remove the head node of the list Note: Used by remove method and next
	 * method of hash table Iterator
	 */
	public HashNode<K, V> removeFirst() {
		// ADD CODE BELOW HERE
		
		if (this.isEmpty()) 
		{
			return null;
		}

		HashNode<K, V> premierNode = this.getFirst();
		if (this.size() == 1) 
		{
			this.clear();
		} else {
			this.setFirst(premierNode.getNext());
			this.decreaseSize();
		}

		return premierNode;
		
		// ADD CODE ABOVE HERE
	}

	/*
	 * Remove Node by key from linked list
	 */
	public HashNode<K, V> remove(K key) {
		// ADD CODE BELOW HERE
		
		if (this.isEmpty()) 
		{
			return null;
		}

		HashNode<K, V> RemoveNode = this.getListNode(key);
		if (RemoveNode == null) 
		{
			return null; // removing failed, no node found by getListNode(K)
		}
		else if (RemoveNode.equals(this.getFirst()) || this.size() == 1) 
			// RemoveNode is the premier node of the list
		{																	
																	
			return this.removeFirst();
		}
		else 
		{ // RemoveNode is not the first in the list
			HashNode<K, V> prevNode = this.getPreviousNode(RemoveNode);
			if (prevNode == null) 
			{
				System.out.println("Error");
				return null; // removing error
			}
			prevNode.helpNext(RemoveNode.getNext());
			RemoveNode.helpNext(null);
			this.decreaseSize();
			return RemoveNode;
		}
		
		// ADD CODE ABOVE HERE
	}

	/*
	 * Delete the whole linked list
	 */
	public void clear() {
		head = null;
		size = 0;
	}

	/*
	 * Check if the list is empty
	 */
	boolean isEmpty() {
		return size == 0 ? true : false;
	}

	int size() {
		return this.size;
	}

	// ADD YOUR HELPER METHODS BELOW THIS
	
	public HashNode<K, V> getFirst() 
	{
		return this.head;
	}

	void setFirst(HashNode<K, V> newHead)
	{
		this.head = newHead;
	}

	void increaseSize() {
		this.size++;
	}

	void decreaseSize() {
		this.size--;
	}

	HashNode<K, V> getPreviousNode(HashNode<K, V> node) 
	{
		HashNode<K, V> recent = this.getFirst().getNext();
		HashNode<K, V> newRecent = this.getFirst();

		while (recent.getKey() != node.getKey() && recent.getNext() != null) 
		{
			recent = recent.getNext();
			newRecent = newRecent.getNext();
		}

		if (recent.getKey() == node.getKey()) 
		
		{ 
			return newRecent;
		}

		return null;
	}
	
	// ADD YOUR HELPER METHODS ABOVE THIS

}
