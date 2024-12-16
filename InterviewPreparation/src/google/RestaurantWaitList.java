package google;

import java.util.HashMap;
import java.util.Map;
/* Ref: https://leetcode.com/discuss/interview-experience/1461079/google-swe-l3l4-dublin-ireland-reject
 * <p>
 * Build a data structure to perform three operations (Restaurant is full initially):
 * 1) waitList (string customer_name, int table_size):
 * Add customer with given name and table size they want to book into the whitelist
 * 2) leave (string customer_name):
 * Customer wants to leave the whitelist so remove them.
 * 3) serve (int table_size):
 * This means restaurant now has a free table of size equal to table_size. Find the best customer to serve from waitlist
 * Best Customer: Customer whose required size is less than or equal to the table_size. If multiple customers are matching use first come first serve.
 * For e.g. if whitelist has customers with these table requirements => [2, 3, 4, 5, 5, 7] and restaurant is serving table_size = 6 then best customer is index 3 (0-based indexing).
 */
public class RestaurantWaitList {
    static class Customer {
        int id;
        String name;
        int groupSize;

        Customer(int id, String name, int groupSize) {
            this.id = id;
            this.name = name;
            this.groupSize = groupSize;
        }
    }

    static class Node {
        Customer customer;
        Node prev, next;

        Node(Customer customer) {
            this.customer = customer;
        }
    }

    static class WaitList {
        private Map<Integer, Node> customerMap; // To store customers for O(1) access by ID
        private Node head, tail;               // Pointers to the start and end of the linked list

        WaitList() {
            customerMap = new HashMap<>();
            head = new Node(null); // Dummy head
            tail = new Node(null); // Dummy tail
            head.next = tail;
            tail.prev = head;
        }

        // Add a customer to the waitlist
        void addCustomer(Customer customer) {
            if (customerMap.containsKey(customer.id)) {
                throw new IllegalArgumentException("Customer ID already present in the waitlist");
            }

            Node node = new Node(customer);
            addToTail(node);
            customerMap.put(customer.id, node);
        }

        // Helper method to add a node to the tail of the doubly linked list
        private void addToTail(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
        }

        // Remove a customer from the waitlist
        void removeCustomer(Customer customer) {
            if (!customerMap.containsKey(customer.id)) {
                throw new IllegalArgumentException("Customer not found in the waitlist");
            }

            Node node = customerMap.get(customer.id);
            removeNode(node);
            customerMap.remove(customer.id);
        }

        // Helper method to remove a node from the doubly linked list
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // Assign a table to the first party that fits the given table size
        String assignTable(int tableSize) {
            Node current = head.next; // Start from the first actual node
            while (current != tail) {
                if (current.customer.groupSize <= tableSize) {
                    String customerName = current.customer.name;
                    removeNode(current);
                    customerMap.remove(current.customer.id);
                    return customerName;
                }
                current = current.next;
            }
            throw new IllegalArgumentException("No customer found for the given table size");
        }
    }


    public static void main(String[] args) {
        WaitList waitList = new WaitList();

        // Add customers
        Customer customer1 = new Customer(1, "Alice", 4);
        Customer customer2 = new Customer(2, "Bob", 2);
        Customer customer3 = new Customer(3, "Charlie", 6);

        waitList.addCustomer(customer1);
        waitList.addCustomer(customer2);
        waitList.addCustomer(customer3);

        // Assign a table
        System.out.println("Assigned to: " + waitList.assignTable(4)); // Should assign Alice
        System.out.println("Assigned to: " + waitList.assignTable(2)); // Should assign Bob

        // Remove a customer
        waitList.removeCustomer(customer3); // Charlie is removed

        try {
            System.out.println("Assigned to: " + waitList.assignTable(6)); // No one fits, should throw exception
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



