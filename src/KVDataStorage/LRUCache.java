package KVDataStorage;

import java.util.HashMap;

public class LRUCache {
    public int capacity;
    public HashMap<Integer, BLinkedList.Node> cache;
    public BLinkedList bLinkedList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<Integer, BLinkedList.Node>();
        this.bLinkedList = new BLinkedList();
    }

    public int get(int key) {
        if (!this.cache.containsKey(key)) {
            return -1;
        }

        BLinkedList.Node node = cache.get(key);
        //Unlink the node and add it to first of BLinkedList
        bLinkedList.remove(node);
        bLinkedList.addFirst(node.key, node.value);
        cache.put(key, bLinkedList.getFirst());

        return node.value;
    }

    public void put(int key, int value) {
        boolean exists = cache.containsKey(key);

        if (exists) {
            BLinkedList.Node node = cache.get(key);
            node.value = value;
            //Unlink the node and add it to first of BLinkedList
            bLinkedList.remove(node);
            bLinkedList.addFirst(node.key, node.value);
            cache.put(key, bLinkedList.getFirst());
        } else {
            if (cache.size() == capacity) {
                //Need to invalidate least Recently Item
                BLinkedList.Node node = bLinkedList.removeLast();
                cache.remove(node.key);
                bLinkedList.addFirst(key, value);
                node = bLinkedList.getFirst();
                cache.put(key, node);
            } else {
                bLinkedList.addFirst(key, value);
                BLinkedList.Node node = bLinkedList.getFirst();
                cache.put(key, node);
            }
        }
    }
}

class BLinkedList {
    public Node first = null;
    public Node last = null;
    public int size;

    public BLinkedList() {
        this.size = 0;
    }

    public void addLast(int key, int value) {
        Node oldLast = this.last;
        Node newLast = new Node(oldLast, null, key, value);
        this.last = newLast;

        if (oldLast == null) {
            this.first = newLast;
        } else {
            oldLast.nextNode = newLast;
        }

        size++;
    }

    public void addFirst(int key, int value) {
        Node oldFirst = this.first;
        Node newFirst = new Node(null, oldFirst, key, value);

        this.first = newFirst;

        if (oldFirst == null) {
            this.last = newFirst;
        } else {
            oldFirst.preNode = newFirst;
        }

        size++;
    }

    public Node removeFirst() {
        if (this.first == null) {
            return null;
        }

        Node oldFirst = this.first;
        this.first = oldFirst.nextNode;
        oldFirst.nextNode = null;

        if (this.first == null) {
            this.last = null;
        } else {
            this.first.preNode = null;
        }

        size--;
        return oldFirst;
    }

    public Node removeLast() {
        if (this.last == null) {
            return null;
        }

        Node oldLast = this.last;
        this.last = oldLast.preNode;
        oldLast.preNode = null;

        if (this.last == null) {
            this.first = null;
        } else {
            this.last.nextNode = null;
        }

        size--;
        return oldLast;
    }

    public void remove(Node node) {
        Node preNode = node.preNode;
        Node nextNode = node.nextNode;

        node.nextNode = null;
        node.preNode = null;

        if (preNode == null && nextNode == null) {
            //node is the only item and list size is 0 if remove node
            this.first = null;
            this.last = null;
            return;
        }

        if (preNode == null) {
            //node to be removed is the first node
            nextNode.preNode = null;
            this.first = nextNode;
        } else {
            preNode.nextNode = nextNode;
        }

        if (nextNode == null) {
            //node to be removed is the last node
            preNode.nextNode = null;
            this.last = preNode;
        } else {
            nextNode.preNode = preNode;
        }

        size--;
    }

    public Node getLast() {
        return this.last;
    }

    public Node getFirst() {
        return this.first;
    }


    class Node {
        public int key;
        public int value;
        public Node preNode;
        public Node nextNode;

        public Node(Node pre, Node next, int key, int value) {
            this.preNode = pre;
            this.nextNode = next;
            this.key = key;
            this.value = value;
        }
    }
}
