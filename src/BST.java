import java.util.*;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // 1. Add size (5%)
    public int size() {
        return size;
    }

    // 2. Implement put method
    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }
        Node current = root;
        while (true) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new Node(key, val);
                    size++;
                    break;
                }
                current = current.left;
            } else if (cmp > 0) {
                if (current.right == null) {
                    current.right = new Node(key, val);
                    size++;
                    break;
                }
                current = current.right;
            } else {
                current.val = val;
                break;
            }
        }
    }

    // 3. Implement get method
    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }
        return null;
    }

    // 4. Implement delete method
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        Node parent = null;
        Node current = node;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                parent = current;
                current = current.left;
            } else if (cmp > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }

        if (current == null) return node;

        if (current.left == null) {
            if (parent == null) return current.right;
            if (parent.left == current) parent.left = current.right;
            else parent.right = current.right;
        } else if (current.right == null) {
            if (parent == null) return current.left;
            if (parent.left == current) parent.left = current.left;
            else parent.right = current.left;
        } else {
            Node minNode = findMin(current.right);
            current.key = minNode.key;
            current.val = minNode.val;
            current.right = delete(current.right, minNode.key);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 5. Implement in-order traversal for iterator() (10%)
    public Iterable<K> iterator() {
        List<K> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            list.add(current.key);
            current = current.right;
        }

        return list;
    }

    // 6. Make it possible for both key and value to be accessible during iteration (10%)
    public Iterable<Map.Entry<K, V>> entryIterator() {
        List<Map.Entry<K, V>> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            list.add(new AbstractMap.SimpleEntry<>(current.key, current.val));
            current = current.right;
        }

        return list;
    }

    // Example usage in main method for testing
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        tree.put(1, "One");
        tree.put(2, "Two");
        tree.put(3, "Three");

        // Get key-value pairs using entryIterator
        for (Map.Entry<Integer, String> elem : tree.entryIterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}


