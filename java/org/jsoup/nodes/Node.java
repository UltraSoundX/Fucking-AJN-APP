package org.jsoup.nodes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public abstract class Node implements Cloneable {
    Attributes attributes;
    String baseUri;
    List<Node> childNodes;
    Node parentNode;
    int siblingIndex;

    private static class OuterHtmlVisitor implements NodeVisitor {
        private StringBuilder accum;
        private OutputSettings out;

        OuterHtmlVisitor(StringBuilder sb, OutputSettings outputSettings) {
            this.accum = sb;
            this.out = outputSettings;
        }

        public void head(Node node, int i) {
            node.outerHtmlHead(this.accum, i, this.out);
        }

        public void tail(Node node, int i) {
            if (!node.nodeName().equals("#text")) {
                node.outerHtmlTail(this.accum, i, this.out);
            }
        }
    }

    public abstract String nodeName();

    /* access modifiers changed from: 0000 */
    public abstract void outerHtmlHead(StringBuilder sb, int i, OutputSettings outputSettings);

    /* access modifiers changed from: 0000 */
    public abstract void outerHtmlTail(StringBuilder sb, int i, OutputSettings outputSettings);

    protected Node(String str, Attributes attributes2) {
        Validate.notNull(str);
        Validate.notNull(attributes2);
        this.childNodes = new ArrayList(4);
        this.baseUri = str.trim();
        this.attributes = attributes2;
    }

    protected Node(String str) {
        this(str, new Attributes());
    }

    protected Node() {
        this.childNodes = Collections.emptyList();
        this.attributes = null;
    }

    public String attr(String str) {
        Validate.notNull(str);
        if (this.attributes.hasKey(str)) {
            return this.attributes.get(str);
        }
        if (str.toLowerCase().startsWith("abs:")) {
            return absUrl(str.substring("abs:".length()));
        }
        return "";
    }

    public Attributes attributes() {
        return this.attributes;
    }

    public Node attr(String str, String str2) {
        this.attributes.put(str, str2);
        return this;
    }

    public boolean hasAttr(String str) {
        Validate.notNull(str);
        if (str.startsWith("abs:")) {
            String substring = str.substring("abs:".length());
            if (this.attributes.hasKey(substring) && !absUrl(substring).equals("")) {
                return true;
            }
        }
        return this.attributes.hasKey(str);
    }

    public Node removeAttr(String str) {
        Validate.notNull(str);
        this.attributes.remove(str);
        return this;
    }

    public String baseUri() {
        return this.baseUri;
    }

    public void setBaseUri(final String str) {
        Validate.notNull(str);
        traverse(new NodeVisitor() {
            public void head(Node node, int i) {
                node.baseUri = str;
            }

            public void tail(Node node, int i) {
            }
        });
    }

    public String absUrl(String str) {
        Validate.notEmpty(str);
        String attr = attr(str);
        if (!hasAttr(str)) {
            return "";
        }
        try {
            URL url = new URL(this.baseUri);
            try {
                if (attr.startsWith("?")) {
                    attr = url.getPath() + attr;
                }
                return new URL(url, attr).toExternalForm();
            } catch (MalformedURLException e) {
                return "";
            }
        } catch (MalformedURLException e2) {
            return new URL(attr).toExternalForm();
        }
    }

    public Node childNode(int i) {
        return (Node) this.childNodes.get(i);
    }

    public List<Node> childNodes() {
        return Collections.unmodifiableList(this.childNodes);
    }

    public List<Node> childNodesCopy() {
        ArrayList arrayList = new ArrayList(this.childNodes.size());
        for (Node clone : this.childNodes) {
            arrayList.add(clone.clone());
        }
        return arrayList;
    }

    public final int childNodeSize() {
        return this.childNodes.size();
    }

    /* access modifiers changed from: protected */
    public Node[] childNodesAsArray() {
        return (Node[]) this.childNodes.toArray(new Node[childNodeSize()]);
    }

    public Node parent() {
        return this.parentNode;
    }

    public final Node parentNode() {
        return this.parentNode;
    }

    public Document ownerDocument() {
        if (this instanceof Document) {
            return (Document) this;
        }
        if (this.parentNode == null) {
            return null;
        }
        return this.parentNode.ownerDocument();
    }

    public void remove() {
        Validate.notNull(this.parentNode);
        this.parentNode.removeChild(this);
    }

    public Node before(String str) {
        addSiblingHtml(siblingIndex(), str);
        return this;
    }

    public Node before(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(siblingIndex(), node);
        return this;
    }

    public Node after(String str) {
        addSiblingHtml(siblingIndex() + 1, str);
        return this;
    }

    public Node after(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(siblingIndex() + 1, node);
        return this;
    }

    private void addSiblingHtml(int i, String str) {
        Validate.notNull(str);
        Validate.notNull(this.parentNode);
        List parseFragment = Parser.parseFragment(str, parent() instanceof Element ? (Element) parent() : null, baseUri());
        this.parentNode.addChildren(i, (Node[]) parseFragment.toArray(new Node[parseFragment.size()]));
    }

    public Node wrap(String str) {
        Validate.notEmpty(str);
        List parseFragment = Parser.parseFragment(str, parent() instanceof Element ? (Element) parent() : null, baseUri());
        Node node = (Node) parseFragment.get(0);
        if (node == null || !(node instanceof Element)) {
            return null;
        }
        Element element = (Element) node;
        Element deepChild = getDeepChild(element);
        this.parentNode.replaceChild(this, element);
        deepChild.addChildren(this);
        if (parseFragment.size() <= 0) {
            return this;
        }
        for (int i = 0; i < parseFragment.size(); i++) {
            Node node2 = (Node) parseFragment.get(i);
            node2.parentNode.removeChild(node2);
            element.appendChild(node2);
        }
        return this;
    }

    public Node unwrap() {
        Validate.notNull(this.parentNode);
        int i = this.siblingIndex;
        Node node = this.childNodes.size() > 0 ? (Node) this.childNodes.get(0) : null;
        this.parentNode.addChildren(i, childNodesAsArray());
        remove();
        return node;
    }

    private Element getDeepChild(Element element) {
        Elements children = element.children();
        if (children.size() > 0) {
            return getDeepChild((Element) children.get(0));
        }
        return element;
    }

    public void replaceWith(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.replaceChild(this, node);
    }

    /* access modifiers changed from: protected */
    public void setParentNode(Node node) {
        if (this.parentNode != null) {
            this.parentNode.removeChild(this);
        }
        this.parentNode = node;
    }

    /* access modifiers changed from: protected */
    public void replaceChild(Node node, Node node2) {
        Validate.isTrue(node.parentNode == this);
        Validate.notNull(node2);
        if (node2.parentNode != null) {
            node2.parentNode.removeChild(node2);
        }
        Integer valueOf = Integer.valueOf(node.siblingIndex());
        this.childNodes.set(valueOf.intValue(), node2);
        node2.parentNode = this;
        node2.setSiblingIndex(valueOf.intValue());
        node.parentNode = null;
    }

    /* access modifiers changed from: protected */
    public void removeChild(Node node) {
        Validate.isTrue(node.parentNode == this);
        this.childNodes.remove(node.siblingIndex());
        reindexChildren();
        node.parentNode = null;
    }

    /* access modifiers changed from: protected */
    public void addChildren(Node... nodeArr) {
        for (Node node : nodeArr) {
            reparentChild(node);
            this.childNodes.add(node);
            node.setSiblingIndex(this.childNodes.size() - 1);
        }
    }

    /* access modifiers changed from: protected */
    public void addChildren(int i, Node... nodeArr) {
        Validate.noNullElements(nodeArr);
        for (int length = nodeArr.length - 1; length >= 0; length--) {
            Node node = nodeArr[length];
            reparentChild(node);
            this.childNodes.add(i, node);
        }
        reindexChildren();
    }

    private void reparentChild(Node node) {
        if (node.parentNode != null) {
            node.parentNode.removeChild(node);
        }
        node.setParentNode(this);
    }

    private void reindexChildren() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.childNodes.size()) {
                ((Node) this.childNodes.get(i2)).setSiblingIndex(i2);
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public List<Node> siblingNodes() {
        if (this.parentNode == null) {
            return Collections.emptyList();
        }
        List<Node> list = this.parentNode.childNodes;
        ArrayList arrayList = new ArrayList(list.size() - 1);
        for (Node node : list) {
            if (node != this) {
                arrayList.add(node);
            }
        }
        return arrayList;
    }

    public Node nextSibling() {
        if (this.parentNode == null) {
            return null;
        }
        List<Node> list = this.parentNode.childNodes;
        Integer valueOf = Integer.valueOf(siblingIndex());
        Validate.notNull(valueOf);
        if (list.size() > valueOf.intValue() + 1) {
            return (Node) list.get(valueOf.intValue() + 1);
        }
        return null;
    }

    public Node previousSibling() {
        if (this.parentNode == null) {
            return null;
        }
        List<Node> list = this.parentNode.childNodes;
        Integer valueOf = Integer.valueOf(siblingIndex());
        Validate.notNull(valueOf);
        if (valueOf.intValue() > 0) {
            return (Node) list.get(valueOf.intValue() - 1);
        }
        return null;
    }

    public int siblingIndex() {
        return this.siblingIndex;
    }

    /* access modifiers changed from: protected */
    public void setSiblingIndex(int i) {
        this.siblingIndex = i;
    }

    public Node traverse(NodeVisitor nodeVisitor) {
        Validate.notNull(nodeVisitor);
        new NodeTraversor(nodeVisitor).traverse(this);
        return this;
    }

    public String outerHtml() {
        StringBuilder sb = new StringBuilder(128);
        outerHtml(sb);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void outerHtml(StringBuilder sb) {
        new NodeTraversor(new OuterHtmlVisitor(sb, getOutputSettings())).traverse(this);
    }

    /* access modifiers changed from: 0000 */
    public OutputSettings getOutputSettings() {
        return ownerDocument() != null ? ownerDocument().outputSettings() : new Document("").outputSettings();
    }

    public String toString() {
        return outerHtml();
    }

    /* access modifiers changed from: protected */
    public void indent(StringBuilder sb, int i, OutputSettings outputSettings) {
        sb.append("\n").append(StringUtil.padding(outputSettings.indentAmount() * i));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.parentNode != null) {
            i = this.parentNode.hashCode();
        } else {
            i = 0;
        }
        int i3 = i * 31;
        if (this.attributes != null) {
            i2 = this.attributes.hashCode();
        }
        return i3 + i2;
    }

    public Node clone() {
        Node doClone = doClone(null);
        LinkedList linkedList = new LinkedList();
        linkedList.add(doClone);
        while (!linkedList.isEmpty()) {
            Node node = (Node) linkedList.remove();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < node.childNodes.size()) {
                    Node doClone2 = ((Node) node.childNodes.get(i2)).doClone(node);
                    node.childNodes.set(i2, doClone2);
                    linkedList.add(doClone2);
                    i = i2 + 1;
                }
            }
        }
        return doClone;
    }

    /* access modifiers changed from: protected */
    public Node doClone(Node node) {
        try {
            Node node2 = (Node) super.clone();
            node2.parentNode = node;
            node2.siblingIndex = node == null ? 0 : this.siblingIndex;
            node2.attributes = this.attributes != null ? this.attributes.clone() : null;
            node2.baseUri = this.baseUri;
            node2.childNodes = new ArrayList(this.childNodes.size());
            for (Node add : this.childNodes) {
                node2.childNodes.add(add);
            }
            return node2;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
