package google;

import java.util.*;

/*
Most Recent Searches You're working on a search bar and want to implement a "most recent searches" feature. When
someone clicks the search bar without typing anything, you want to show the user the N most recent searches.
This will show the user what other people are interested in. Design a data structure that accepts the most recent user
search string and returns an iterable of the N most recent user search strings. This question is based on a 2009 Xoogler
 question, but unfortunately the old author is not visible in the new version of go/iq , so I cannot credit them here.
 I have re-disguised the question to make the answer less-easily searchable.
 */

public class MostRecentSearches {

    private final LinkedHashSet<String> searchHistory; // To store and maintain unique recent searches
    private final int capacity; // Maximum number of recent searches to store

    public MostRecentSearches(int capacity) {
        this.capacity = capacity;
        this.searchHistory = new LinkedHashSet<>();
    }

    // Method to save a search and return the N most recent searches
    public List<String> saveSearch(String searchQuery) {
        // Remove the search query if it already exists to maintain recent order
        if (searchHistory.contains(searchQuery)) {
            searchHistory.remove(searchQuery);
        }

        // If the size exceeds the capacity, remove the oldest search
        if (searchHistory.size() == capacity) {
            Iterator<String> it = searchHistory.iterator();
            it.next();
            it.remove(); // Removes the oldest search (first element)
        }

        // Add the current search query to the end
        searchHistory.add(searchQuery);

        // Return the most recent searches as a list
        return getRecentSearches();
    }

    // Method to return the N most recent searches when the user clicks the search bar
    public List<String> getRecentSearches() {
        List<String> result = new ArrayList<>(searchHistory);
        Collections.reverse(result); // Reverse to show the most recent searches first
        return result;
    }

    public static void main(String[] args) {
        MostRecentSearches recentSearches = new MostRecentSearches(5);

        // Add searches
        System.out.println(recentSearches.saveSearch("Apple")); // [Apple]
        System.out.println(recentSearches.saveSearch("Banana")); // [Banana, Apple]
        System.out.println(recentSearches.saveSearch("Cherry")); // [Cherry, Banana, Apple]
        System.out.println(recentSearches.saveSearch("Date")); // [Date, Cherry, Banana, Apple]
        System.out.println(recentSearches.saveSearch("Elderberry")); // [Elderberry, Date, Cherry, Banana, Apple]

        // Clicking the search bar (without adding a new search)
        System.out.println(recentSearches.getRecentSearches()); // [Elderberry, Date, Cherry, Banana, Apple]

        // Adding a search that already exists
        System.out.println(recentSearches.saveSearch("Banana")); // [Banana, Elderberry, Date, Cherry, Apple]

        // Adding a new search that exceeds capacity
        System.out.println(recentSearches.saveSearch("Fig")); // [Fig, Banana, Elderberry, Date, Cherry]
    }
}

/*
LRU type implementation

import java.util.*;

class Node {
    String searchQuery;
    Node next;
    Node prev;

    Node(String searchQuery) {
        this.searchQuery = searchQuery;
        next = null;
        prev = null;
    }
}

class RecentSearchesLRU {
    private final Node head;
    private final Node tail;
    private final Map<String, Node> map; // To store search queries and their corresponding nodes
    private final int capacity;

    public RecentSearchesLRU(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(""); // Dummy head
        this.tail = new Node(""); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }

    // Method to add a search query
    public void addSearch(String searchQuery) {
        // If the search query already exists, remove it first (to update its order)
        if (map.containsKey(searchQuery)) {
            remove(map.get(searchQuery));
        }

        // If capacity is full, remove the least recently used search (tail.prev)
        if (map.size() == capacity) {
            remove(tail.prev);
        }

        // Insert the new search query at the head (most recent position)
        insert(new Node(searchQuery));
    }

    // Method to retrieve the most recent searches
    public List<String> getRecentSearches() {
        List<String> recentSearches = new ArrayList<>();
        Node current = head.next;

        // Traverse from head to tail (ignoring dummy nodes)
        while (current != tail) {
            recentSearches.add(current.searchQuery);
            current = current.next;
        }

        return recentSearches;
    }

    // Helper method to remove a node
    private void remove(Node node) {
        map.remove(node.searchQuery);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper method to insert a node at the head
    private void insert(Node node) {
        map.put(node.searchQuery, node);
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        RecentSearchesLRU recentSearches = new RecentSearchesLRU(5);

        // Adding searches
        recentSearches.addSearch("Apple");
        System.out.println(recentSearches.getRecentSearches()); // [Apple]

        recentSearches.addSearch("Banana");
        System.out.println(recentSearches.getRecentSearches()); // [Banana, Apple]

        recentSearches.addSearch("Cherry");
        recentSearches.addSearch("Date");
        recentSearches.addSearch("Elderberry");
        System.out.println(recentSearches.getRecentSearches()); // [Elderberry, Date, Cherry, Banana, Apple]

        // Adding a search that already exists (promotes to most recent)
        recentSearches.addSearch("Banana");
        System.out.println(recentSearches.getRecentSearches()); // [Banana, Elderberry, Date, Cherry, Apple]

        // Adding a new search exceeding capacity
        recentSearches.addSearch("Fig");
        System.out.println(recentSearches.getRecentSearches()); // [Fig, Banana, Elderberry, Date, Cherry]
    }
}

 */


