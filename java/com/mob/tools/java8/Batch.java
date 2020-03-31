package com.mob.tools.java8;

import java.util.LinkedList;

class Batch<R> implements Function {
    private int limit;
    protected LinkedList<R> output = new LinkedList<>();

    static class Chunk<R> extends Batch<R> {
        public Chunk(int i) {
            super(i);
        }

        public void reset() {
            this.output = new LinkedList();
        }
    }

    static class Limit<R> extends Batch<R> {
        public Limit() {
            super(-1);
        }

        public Limit(int i) {
            super(i);
        }
    }

    static class Skip<R> extends Batch<R> {
        public Skip(int i) {
            super(i);
        }
    }

    public Batch(int i) {
        this.limit = i;
    }

    public int test(R r) {
        this.output.add(r);
        if (this.limit == -1) {
            return 0;
        }
        if (this.output.size() == this.limit) {
            return 1;
        }
        if (this.output.size() <= this.limit) {
            return 0;
        }
        this.output.removeLast();
        return 2;
    }

    public LinkedList<R> output() {
        return this.output;
    }
}
