package KVDataStorage;

import org.junit.Assert;

public class BLinkedListTest {
    @org.junit.Test
    public void bLinkedListTest() {
        //["LRUCache","put","put","get","put","get","put","get","get","get"]
        //[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        BLinkedList bLinkedList = new BLinkedList();
        Assert.assertEquals(0, bLinkedList.size);

        bLinkedList.addFirst(1, 1);
        bLinkedList.addFirst(2, 2);
        BLinkedList.Node firstNode = bLinkedList.getFirst();
        BLinkedList.Node lastNode = bLinkedList.getLast();
        Assert.assertEquals(2, bLinkedList.size);
        Assert.assertEquals(2, firstNode.key);
        Assert.assertEquals(2, firstNode.value);
        Assert.assertEquals(1, lastNode.key);
        Assert.assertEquals(1, lastNode.value);

        bLinkedList.remove(lastNode);
        bLinkedList.addFirst(1, 1);
        firstNode = bLinkedList.getFirst();
        lastNode = bLinkedList.getLast();
        Assert.assertEquals(2, bLinkedList.size);
        Assert.assertEquals(1, firstNode.key);
        Assert.assertEquals(1, firstNode.value);
        Assert.assertEquals(2, lastNode.key);
        Assert.assertEquals(2, lastNode.value);

        bLinkedList.removeLast();
        bLinkedList.addFirst(3, 3);
        firstNode = bLinkedList.getFirst();
        lastNode = bLinkedList.getLast();
        Assert.assertEquals(2, bLinkedList.size);
        Assert.assertEquals(3, firstNode.key);
        Assert.assertEquals(3, firstNode.value);
        Assert.assertEquals(1, lastNode.key);
        Assert.assertEquals(1, lastNode.value);

        bLinkedList.remove(lastNode);
        bLinkedList.addFirst(4, 4);
        firstNode = bLinkedList.getFirst();
        lastNode = bLinkedList.getLast();
        Assert.assertEquals(2, bLinkedList.size);
        Assert.assertEquals(4, firstNode.key);
        Assert.assertEquals(4, firstNode.value);
        Assert.assertEquals(3, lastNode.key);
        Assert.assertEquals(3, lastNode.value);

        bLinkedList.remove(lastNode);
        bLinkedList.addFirst(lastNode.key, lastNode.value);
        firstNode = bLinkedList.getFirst();
        lastNode = bLinkedList.getLast();
        Assert.assertEquals(2, bLinkedList.size);
        Assert.assertEquals(3, firstNode.key);
        Assert.assertEquals(3, firstNode.value);
        Assert.assertEquals(4, lastNode.key);
        Assert.assertEquals(4, lastNode.value);

        bLinkedList.remove(lastNode);
        bLinkedList.addFirst(lastNode.key, lastNode.value);
        firstNode = bLinkedList.getFirst();
        lastNode = bLinkedList.getLast();
        Assert.assertEquals(2, bLinkedList.size);
        Assert.assertEquals(4, firstNode.key);
        Assert.assertEquals(4, firstNode.value);
        Assert.assertEquals(3, lastNode.key);
        Assert.assertEquals(3, lastNode.value);
    }
}