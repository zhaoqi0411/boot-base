package com.papaxiong.support.struc.lru;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

/**
 * @author zhaoqi
 * @since 2021-04-04
 * <p>
 * LRU Hash+链表实现
 */
@Getter
@Setter
public class LRU_Hash<K, V> {

    public final Integer LRU_SIZE_THREAD_HOLD_DEFAULT = 10;


    public LRU_Hash() {
        threadHold = LRU_SIZE_THREAD_HOLD_DEFAULT;
    }
    public LRU_Hash(int size) {
        threadHold = size;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class Cache<K, V> {

        private K key;

        private V value;

        private Cache<K, V> pre;

        private Cache<K, V> next;
    }


    private Map<K, Cache<K, V>> cacheMap;


    private int size=0;

    private int threadHold;


    private Cache<K, V> head;

    private Cache<K, V> tail;


    public void addCache(K key, V value) {
        Cache<K, V> kvCache = cacheMap.get(key);
        if (Objects.nonNull(kvCache)) {
            return;
        }

        Cache<K, V> oldCache= head;
        Cache<K, V> cache = new Cache<>(key, value, null, oldCache);
        oldCache.pre=cache;
        head = cache;

        cacheMap.put(key,cache);

        size++;

        //超限弹出
        if (size > threadHold) {
            tail.pre.next = null;
            cacheMap.remove(tail);
            tail = null;
        }



    }


    public V getCache(K key) {
        Cache<K, V> kvCache = cacheMap.get(key);
        if(Objects.nonNull(kvCache)){
            hitCache(kvCache);
            return kvCache.getValue();
        }
        return null;

    }

    //命中缓存
    private void hitCache(Cache<K, V> kvCache) {
        if (kvCache == head) {
            return;
        }
        if (kvCache == tail) {
            tail = kvCache.pre;
            tail.next = null;
        } else {
            Cache<K, V> pre = tail.pre;
            Cache<K, V> next = tail.next;
            pre.next = next;
            next.pre = pre;

        }

        //头节点
        Cache<K, V> oldHead=head;
        oldHead.setPre(null);
        head=kvCache;
        head.pre=null;
        head.next=oldHead;




    }


}
