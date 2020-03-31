package com.mob.tools.gui;

import com.mob.tools.MobLog;
import java.util.Iterator;
import java.util.LinkedList;

public class CachePool<K, V> {
    private int capacity;
    private OnRemoveListener<K, V> listener;
    private LinkedList<Node<K, V>> pool = new LinkedList<>();
    private int poolSize;

    private class Node<K, V> {
        /* access modifiers changed from: private */
        public long cacheTime;
        public K key;
        /* access modifiers changed from: private */
        public int size;
        public V value;

        private Node() {
        }
    }

    public interface OnRemoveListener<K, V> {
        void onRemove(K k, V v);
    }

    public CachePool(int i) {
        this.capacity = i;
    }

    public void setOnRemoveListener(OnRemoveListener<K, V> onRemoveListener) {
        this.listener = onRemoveListener;
    }

    public synchronized boolean put(K k, V v, int i) {
        boolean z;
        if (this.pool != null && this.capacity > 0) {
            try {
                Node node = new Node();
                node.key = k;
                node.value = v;
                node.cacheTime = System.currentTimeMillis();
                node.size = i;
                this.pool.add(0, node);
                this.poolSize += i;
                while (this.poolSize > this.capacity) {
                    Node node2 = (Node) this.pool.removeLast();
                    if (node2 != null) {
                        this.poolSize -= node2.size;
                        if (this.listener != null) {
                            this.listener.onRemove(node2.key, node2.value);
                        }
                    }
                }
                z = true;
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }
        z = false;
        return z;
    }

    public synchronized boolean put(K k, V v) {
        return put(k, v, 1);
    }

    public synchronized V get(K k) {
        V v;
        Node node;
        if (this.pool != null && this.capacity > 0) {
            while (this.poolSize > this.capacity) {
                try {
                    Node node2 = (Node) this.pool.removeLast();
                    if (node2 != null) {
                        this.poolSize -= node2.size;
                        if (this.listener != null) {
                            this.listener.onRemove(node2.key, node2.value);
                        }
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().w(th);
                }
            }
            Iterator it = this.pool.iterator();
            while (true) {
                if (!it.hasNext()) {
                    node = null;
                    break;
                }
                node = (Node) it.next();
                if (node != null && ((k == null && node.key == null) || (k != null && k.equals(node.key)))) {
                    break;
                }
            }
            if (node != null) {
                this.pool.set(0, node);
                node.cacheTime = System.currentTimeMillis();
                v = node.value;
            }
        }
        v = null;
        return v;
    }

    public synchronized void clear() {
        if (this.pool != null && this.capacity > 0) {
            if (this.listener == null) {
                this.pool.clear();
            } else {
                while (this.pool.size() > 0) {
                    Node node = (Node) this.pool.removeLast();
                    if (node != null) {
                        this.poolSize -= node.size;
                        if (this.listener != null) {
                            this.listener.onRemove(node.key, node.value);
                        }
                    }
                }
            }
            this.poolSize = 0;
        }
    }

    public synchronized void trimBeforeTime(long j) {
        int i;
        if (this.pool != null && this.capacity > 0) {
            int size = this.pool.size() - 1;
            while (size >= 0) {
                if (((Node) this.pool.get(size)).cacheTime < j) {
                    Node node = (Node) this.pool.remove(size);
                    if (node != null) {
                        this.poolSize -= node.size;
                        if (this.listener != null) {
                            this.listener.onRemove(node.key, node.value);
                        }
                    }
                    i = size;
                } else {
                    i = size - 1;
                }
                size = i;
            }
            while (this.poolSize > this.capacity) {
                Node node2 = (Node) this.pool.removeLast();
                if (node2 != null) {
                    this.poolSize -= node2.size;
                    if (this.listener != null) {
                        this.listener.onRemove(node2.key, node2.value);
                    }
                }
            }
        }
    }

    public synchronized int size() {
        return this.poolSize;
    }
}
