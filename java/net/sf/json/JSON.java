package net.sf.json;

import java.io.Writer;

public interface JSON {
    boolean isArray();

    boolean isEmpty();

    int size();

    String toString(int i);

    String toString(int i, int i2);

    Writer write(Writer writer);
}
