package assignments;

import java.util.HashMap;
import java.util.Map;

class Node {
	int key;
	int val;
	Node next;
	Node prev;

	Node(int key, int val) {
		this.key = key;
		this.val = val;
		next = null;
		prev = null;
	}
}

class LRUCache {
	Node head = new Node(0, 0);
	Node tail = new Node(0, 0);
	Map<Integer, Node> map;
	int capacity;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		head.next = tail;
		tail.prev = head;
		map = new HashMap<Integer, Node>();
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			remove(node);
			insert(node);
			return node.val;
		}
		return -1;
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			remove(map.get(key));
		}
		if (map.size() == capacity) {
			remove(tail.prev);
		}
		insert(new Node(key, value));
	}

	private void remove(Node node) {
		map.remove(node.key);
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.prev = null;
		node.next = null;
	}

	private void insert(Node node) {
		map.put(node.key, node);
		Node temp = head.next;
		head.next = node;
		node.prev = head;
		node.next = temp;
		temp.prev = node;

	}
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
