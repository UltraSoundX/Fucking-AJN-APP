package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

public class Elements implements Cloneable, List<Element> {
    private List<Element> contents;

    public Elements() {
        this.contents = new ArrayList();
    }

    public Elements(int i) {
        this.contents = new ArrayList(i);
    }

    public Elements(Collection<Element> collection) {
        this.contents = new ArrayList(collection);
    }

    public Elements(List<Element> list) {
        this.contents = list;
    }

    public Elements(Element... elementArr) {
        this(Arrays.asList(elementArr));
    }

    public Elements clone() {
        try {
            Elements elements = (Elements) super.clone();
            ArrayList arrayList = new ArrayList();
            elements.contents = arrayList;
            for (Element clone : this.contents) {
                arrayList.add(clone.clone());
            }
            return elements;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public String attr(String str) {
        for (Element element : this.contents) {
            if (element.hasAttr(str)) {
                return element.attr(str);
            }
        }
        return "";
    }

    public boolean hasAttr(String str) {
        for (Element hasAttr : this.contents) {
            if (hasAttr.hasAttr(str)) {
                return true;
            }
        }
        return false;
    }

    public Elements attr(String str, String str2) {
        for (Element attr : this.contents) {
            attr.attr(str, str2);
        }
        return this;
    }

    public Elements removeAttr(String str) {
        for (Element removeAttr : this.contents) {
            removeAttr.removeAttr(str);
        }
        return this;
    }

    public Elements addClass(String str) {
        for (Element addClass : this.contents) {
            addClass.addClass(str);
        }
        return this;
    }

    public Elements removeClass(String str) {
        for (Element removeClass : this.contents) {
            removeClass.removeClass(str);
        }
        return this;
    }

    public Elements toggleClass(String str) {
        for (Element element : this.contents) {
            element.toggleClass(str);
        }
        return this;
    }

    public boolean hasClass(String str) {
        for (Element hasClass : this.contents) {
            if (hasClass.hasClass(str)) {
                return true;
            }
        }
        return false;
    }

    public String val() {
        if (size() > 0) {
            return first().val();
        }
        return "";
    }

    public Elements val(String str) {
        for (Element val : this.contents) {
            val.val(str);
        }
        return this;
    }

    public String text() {
        StringBuilder sb = new StringBuilder();
        for (Element element : this.contents) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(element.text());
        }
        return sb.toString();
    }

    public boolean hasText() {
        for (Element hasText : this.contents) {
            if (hasText.hasText()) {
                return true;
            }
        }
        return false;
    }

    public String html() {
        StringBuilder sb = new StringBuilder();
        for (Element element : this.contents) {
            if (sb.length() != 0) {
                sb.append("\n");
            }
            sb.append(element.html());
        }
        return sb.toString();
    }

    public String outerHtml() {
        StringBuilder sb = new StringBuilder();
        for (Element element : this.contents) {
            if (sb.length() != 0) {
                sb.append("\n");
            }
            sb.append(element.outerHtml());
        }
        return sb.toString();
    }

    public String toString() {
        return outerHtml();
    }

    public Elements tagName(String str) {
        for (Element tagName : this.contents) {
            tagName.tagName(str);
        }
        return this;
    }

    public Elements html(String str) {
        for (Element html : this.contents) {
            html.html(str);
        }
        return this;
    }

    public Elements prepend(String str) {
        for (Element prepend : this.contents) {
            prepend.prepend(str);
        }
        return this;
    }

    public Elements append(String str) {
        for (Element append : this.contents) {
            append.append(str);
        }
        return this;
    }

    public Elements before(String str) {
        for (Element before : this.contents) {
            before.before(str);
        }
        return this;
    }

    public Elements after(String str) {
        for (Element after : this.contents) {
            after.after(str);
        }
        return this;
    }

    public Elements wrap(String str) {
        Validate.notEmpty(str);
        for (Element wrap : this.contents) {
            wrap.wrap(str);
        }
        return this;
    }

    public Elements unwrap() {
        for (Element unwrap : this.contents) {
            unwrap.unwrap();
        }
        return this;
    }

    public Elements empty() {
        for (Element empty : this.contents) {
            empty.empty();
        }
        return this;
    }

    public Elements remove() {
        for (Element remove : this.contents) {
            remove.remove();
        }
        return this;
    }

    public Elements select(String str) {
        return Selector.select(str, (Iterable<Element>) this);
    }

    public Elements not(String str) {
        return Selector.filterOut(this, Selector.select(str, (Iterable<Element>) this));
    }

    public Elements eq(int i) {
        if (this.contents.size() <= i) {
            return new Elements();
        }
        return new Elements(get(i));
    }

    public boolean is(String str) {
        return !select(str).isEmpty();
    }

    public Elements parents() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Element parents : this.contents) {
            linkedHashSet.addAll(parents.parents());
        }
        return new Elements((Collection<Element>) linkedHashSet);
    }

    public Element first() {
        if (this.contents.isEmpty()) {
            return null;
        }
        return (Element) this.contents.get(0);
    }

    public Element last() {
        if (this.contents.isEmpty()) {
            return null;
        }
        return (Element) this.contents.get(this.contents.size() - 1);
    }

    public Elements traverse(NodeVisitor nodeVisitor) {
        Validate.notNull(nodeVisitor);
        NodeTraversor nodeTraversor = new NodeTraversor(nodeVisitor);
        for (Element traverse : this.contents) {
            nodeTraversor.traverse(traverse);
        }
        return this;
    }

    public List<FormElement> forms() {
        ArrayList arrayList = new ArrayList();
        for (Element element : this.contents) {
            if (element instanceof FormElement) {
                arrayList.add((FormElement) element);
            }
        }
        return arrayList;
    }

    public int size() {
        return this.contents.size();
    }

    public boolean isEmpty() {
        return this.contents.isEmpty();
    }

    public boolean contains(Object obj) {
        return this.contents.contains(obj);
    }

    public Iterator<Element> iterator() {
        return this.contents.iterator();
    }

    public Object[] toArray() {
        return this.contents.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.contents.toArray(tArr);
    }

    public boolean add(Element element) {
        return this.contents.add(element);
    }

    public boolean remove(Object obj) {
        return this.contents.remove(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.contents.containsAll(collection);
    }

    public boolean addAll(Collection<? extends Element> collection) {
        return this.contents.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends Element> collection) {
        return this.contents.addAll(i, collection);
    }

    public boolean removeAll(Collection<?> collection) {
        return this.contents.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return this.contents.retainAll(collection);
    }

    public void clear() {
        this.contents.clear();
    }

    public boolean equals(Object obj) {
        return this.contents.equals(obj);
    }

    public int hashCode() {
        return this.contents.hashCode();
    }

    public Element get(int i) {
        return (Element) this.contents.get(i);
    }

    public Element set(int i, Element element) {
        return (Element) this.contents.set(i, element);
    }

    public void add(int i, Element element) {
        this.contents.add(i, element);
    }

    public Element remove(int i) {
        return (Element) this.contents.remove(i);
    }

    public int indexOf(Object obj) {
        return this.contents.indexOf(obj);
    }

    public int lastIndexOf(Object obj) {
        return this.contents.lastIndexOf(obj);
    }

    public ListIterator<Element> listIterator() {
        return this.contents.listIterator();
    }

    public ListIterator<Element> listIterator(int i) {
        return this.contents.listIterator(i);
    }

    public List<Element> subList(int i, int i2) {
        return this.contents.subList(i, i2);
    }
}
