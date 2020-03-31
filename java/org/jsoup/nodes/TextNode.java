package org.jsoup.nodes;

import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document.OutputSettings;

public class TextNode extends Node {
    private static final String TEXT_KEY = "text";
    String text;

    public TextNode(String str, String str2) {
        this.baseUri = str2;
        this.text = str;
    }

    public String nodeName() {
        return "#text";
    }

    public String text() {
        return normaliseWhitespace(getWholeText());
    }

    public TextNode text(String str) {
        this.text = str;
        if (this.attributes != null) {
            this.attributes.put(TEXT_KEY, str);
        }
        return this;
    }

    public String getWholeText() {
        return this.attributes == null ? this.text : this.attributes.get(TEXT_KEY);
    }

    public boolean isBlank() {
        return StringUtil.isBlank(getWholeText());
    }

    public TextNode splitText(int i) {
        boolean z;
        Validate.isTrue(i >= 0, "Split offset must be not be negative");
        if (i < this.text.length()) {
            z = true;
        } else {
            z = false;
        }
        Validate.isTrue(z, "Split offset must not be greater than current text length");
        String substring = getWholeText().substring(0, i);
        String substring2 = getWholeText().substring(i);
        text(substring);
        TextNode textNode = new TextNode(substring2, baseUri());
        if (parent() != null) {
            parent().addChildren(siblingIndex() + 1, textNode);
        }
        return textNode;
    }

    /* access modifiers changed from: 0000 */
    public void outerHtmlHead(StringBuilder sb, int i, OutputSettings outputSettings) {
        boolean z;
        if (outputSettings.prettyPrint() && ((siblingIndex() == 0 && (this.parentNode instanceof Element) && ((Element) this.parentNode).tag().formatAsBlock() && !isBlank()) || (outputSettings.outline() && siblingNodes().size() > 0 && !isBlank()))) {
            indent(sb, i, outputSettings);
        }
        if (!outputSettings.prettyPrint() || !(parent() instanceof Element) || Element.preserveWhitespace((Element) parent())) {
            z = false;
        } else {
            z = true;
        }
        Entities.escape(sb, getWholeText(), outputSettings, false, z, false);
    }

    /* access modifiers changed from: 0000 */
    public void outerHtmlTail(StringBuilder sb, int i, OutputSettings outputSettings) {
    }

    public String toString() {
        return outerHtml();
    }

    public static TextNode createFromEncoded(String str, String str2) {
        return new TextNode(Entities.unescape(str), str2);
    }

    static String normaliseWhitespace(String str) {
        return StringUtil.normaliseWhitespace(str);
    }

    static String stripLeadingWhitespace(String str) {
        return str.replaceFirst("^\\s+", "");
    }

    static boolean lastCharIsWhitespace(StringBuilder sb) {
        return sb.length() != 0 && sb.charAt(sb.length() + -1) == ' ';
    }

    private void ensureAttributes() {
        if (this.attributes == null) {
            this.attributes = new Attributes();
            this.attributes.put(TEXT_KEY, this.text);
        }
    }

    public String attr(String str) {
        ensureAttributes();
        return super.attr(str);
    }

    public Attributes attributes() {
        ensureAttributes();
        return super.attributes();
    }

    public Node attr(String str, String str2) {
        ensureAttributes();
        return super.attr(str, str2);
    }

    public boolean hasAttr(String str) {
        ensureAttributes();
        return super.hasAttr(str);
    }

    public Node removeAttr(String str) {
        ensureAttributes();
        return super.removeAttr(str);
    }

    public String absUrl(String str) {
        ensureAttributes();
        return super.absUrl(str);
    }
}
