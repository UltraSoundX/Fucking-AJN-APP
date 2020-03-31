package org.jsoup.nodes;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document.OutputSettings;

public class Attributes implements Cloneable, Iterable<Attribute> {
    protected static final String dataPrefix = "data-";
    /* access modifiers changed from: private */
    public LinkedHashMap<String, Attribute> attributes = null;

    private class Dataset extends AbstractMap<String, String> {

        private class DatasetIterator implements Iterator<Entry<String, String>> {
            private Attribute attr;
            private Iterator<Attribute> attrIter;

            private DatasetIterator() {
                this.attrIter = Attributes.this.attributes.values().iterator();
            }

            public boolean hasNext() {
                while (this.attrIter.hasNext()) {
                    this.attr = (Attribute) this.attrIter.next();
                    if (this.attr.isDataAttribute()) {
                        return true;
                    }
                }
                return false;
            }

            public Entry<String, String> next() {
                return new Attribute(this.attr.getKey().substring(Attributes.dataPrefix.length()), this.attr.getValue());
            }

            public void remove() {
                Attributes.this.attributes.remove(this.attr.getKey());
            }
        }

        private class EntrySet extends AbstractSet<Entry<String, String>> {
            private EntrySet() {
            }

            public Iterator<Entry<String, String>> iterator() {
                return new DatasetIterator();
            }

            public int size() {
                int i = 0;
                while (new DatasetIterator().hasNext()) {
                    i++;
                }
                return i;
            }
        }

        private Dataset() {
            if (Attributes.this.attributes == null) {
                Attributes.this.attributes = new LinkedHashMap(2);
            }
        }

        public Set<Entry<String, String>> entrySet() {
            return new EntrySet();
        }

        public String put(String str, String str2) {
            String access$300 = Attributes.dataKey(str);
            String str3 = Attributes.this.hasKey(access$300) ? ((Attribute) Attributes.this.attributes.get(access$300)).getValue() : null;
            Attributes.this.attributes.put(access$300, new Attribute(access$300, str2));
            return str3;
        }
    }

    public String get(String str) {
        Validate.notEmpty(str);
        if (this.attributes == null) {
            return "";
        }
        Attribute attribute = (Attribute) this.attributes.get(str.toLowerCase());
        return attribute != null ? attribute.getValue() : "";
    }

    public void put(String str, String str2) {
        put(new Attribute(str, str2));
    }

    public void put(Attribute attribute) {
        Validate.notNull(attribute);
        if (this.attributes == null) {
            this.attributes = new LinkedHashMap<>(2);
        }
        this.attributes.put(attribute.getKey(), attribute);
    }

    public void remove(String str) {
        Validate.notEmpty(str);
        if (this.attributes != null) {
            this.attributes.remove(str.toLowerCase());
        }
    }

    public boolean hasKey(String str) {
        return this.attributes != null && this.attributes.containsKey(str.toLowerCase());
    }

    public int size() {
        if (this.attributes == null) {
            return 0;
        }
        return this.attributes.size();
    }

    public void addAll(Attributes attributes2) {
        if (attributes2.size() != 0) {
            if (this.attributes == null) {
                this.attributes = new LinkedHashMap<>(attributes2.size());
            }
            this.attributes.putAll(attributes2.attributes);
        }
    }

    public Iterator<Attribute> iterator() {
        return asList().iterator();
    }

    public List<Attribute> asList() {
        if (this.attributes == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(this.attributes.size());
        for (Entry value : this.attributes.entrySet()) {
            arrayList.add(value.getValue());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Map<String, String> dataset() {
        return new Dataset();
    }

    public String html() {
        StringBuilder sb = new StringBuilder();
        html(sb, new Document("").outputSettings());
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public void html(StringBuilder sb, OutputSettings outputSettings) {
        if (this.attributes != null) {
            for (Entry value : this.attributes.entrySet()) {
                Attribute attribute = (Attribute) value.getValue();
                sb.append(" ");
                attribute.html(sb, outputSettings);
            }
        }
    }

    public String toString() {
        return html();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Attributes)) {
            return false;
        }
        Attributes attributes2 = (Attributes) obj;
        if (this.attributes != null) {
            if (this.attributes.equals(attributes2.attributes)) {
                return true;
            }
        } else if (attributes2.attributes == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.attributes != null) {
            return this.attributes.hashCode();
        }
        return 0;
    }

    public Attributes clone() {
        if (this.attributes == null) {
            return new Attributes();
        }
        try {
            Attributes attributes2 = (Attributes) super.clone();
            attributes2.attributes = new LinkedHashMap<>(this.attributes.size());
            Iterator it = iterator();
            while (it.hasNext()) {
                Attribute attribute = (Attribute) it.next();
                attributes2.attributes.put(attribute.getKey(), attribute.clone());
            }
            return attributes2;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public static String dataKey(String str) {
        return dataPrefix + str;
    }
}
