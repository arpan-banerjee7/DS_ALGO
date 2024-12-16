package google;

import java.util.*;
// UNION FIND
/*
You're given a list of elements. Each element has a unique id and 3 properties. Two elements are considered as duplicates if they share any
of the 3 properties. Please write a function that takes the input and returns all the duplicates.
E1: id1, p1, p2, p3
E2: id2, p1, p4, p5
E3: id3, p6, p7, p8


Output: {{id1, id2}, {id3}}
 */
public class DuplicateElementsSharingAProperty {

    static class Element {
        String id;
        String p1, p2, p3;

        public Element(String id, String p1, String p2, String p3) {
            this.id = id;
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }
    }

    public static List<Set<String>> findDuplicates(List<Element> elements) {
        // Step 1: Union-Find setup
        UnionFind uf = new UnionFind();

        // Step 2: Map to group IDs by properties
        Map<String, List<String>> propertyToIds = new HashMap<>();
        for (Element element : elements) {
            uf.add(element.id);
            propertyToIds.computeIfAbsent(element.p1, k -> new ArrayList<>()).add(element.id);
            propertyToIds.computeIfAbsent(element.p2, k -> new ArrayList<>()).add(element.id);
            propertyToIds.computeIfAbsent(element.p3, k -> new ArrayList<>()).add(element.id);
        }

        // Step 3: Union IDs that share the same property
        for (List<String> ids : propertyToIds.values()) {
            for (int i = 1; i < ids.size(); i++) {
                uf.union(ids.get(0), ids.get(i));
            }
        }

        // Step 4: Group IDs by their root parent
        Map<String, Set<String>> groups = new HashMap<>();
        for (Element element : elements) {
            String root = uf.find(element.id);
            groups.computeIfAbsent(root, k -> new HashSet<>()).add(element.id);
        }

        // Step 5: Convert groups to list of sets
        return new ArrayList<>(groups.values());
    }

    // Union-Find implementation
    static class UnionFind {
        private Map<String, String> parent = new HashMap<>();
        private Map<String, Integer> rank = new HashMap<>();

        public void add(String id) {
            if (!parent.containsKey(id)) {
                parent.put(id, id);
                rank.put(id, 1);
            }
        }

        public String find(String id) {
            if (!parent.get(id).equals(id)) {
                parent.put(id, find(parent.get(id))); // Path compression
            }
            return parent.get(id);
        }

        public void union(String id1, String id2) {
            String root1 = find(id1);
            String root2 = find(id2);

            if (!root1.equals(root2)) {
                if (rank.get(root1) > rank.get(root2)) {
                    parent.put(root2, root1);
                } else if (rank.get(root1) < rank.get(root2)) {
                    parent.put(root1, root2);
                } else {
                    parent.put(root2, root1);
                    rank.put(root1, rank.get(root1) + 1);
                }
            }
        }
    }

    // Example usage
    public static void main(String[] args) {
        List<Element> elements = Arrays.asList(
                new Element("id1", "p1", "p2", "p3"),
                new Element("id2", "p1", "p4", "p5"),
                new Element("id3", "p6", "p7", "p8")
        );

        List<Set<String>> duplicates = findDuplicates(elements);
        System.out.println(duplicates);
    }
}

