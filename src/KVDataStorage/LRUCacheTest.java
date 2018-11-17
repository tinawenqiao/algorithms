package KVDataStorage;

import org.junit.Assert;

public class LRUCacheTest {
    @org.junit.Test
    public void putAndGetTest() throws Exception {
        //["LRUCache","put","put","get","put","get","put","get","get","get"]
        //[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        Assert.assertEquals(1, cache.get(1));

        cache.put(3, 3);
        Assert.assertEquals(-1, cache.get(2));

        cache.put(4, 4);
        Assert.assertEquals(-1, cache.get(1));
        Assert.assertEquals(3, cache.get(3));
        Assert.assertEquals(4, cache.get(4));
    }

    @org.junit.Test
    public void putAndGetTest2() throws Exception {
        //["LRUCache","put","put","put","put","get","get"]
        //[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
        LRUCache cache = new LRUCache(2);

        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        Assert.assertEquals(-1, cache.get(1));
        Assert.assertEquals(3, cache.get(2));
    }
}