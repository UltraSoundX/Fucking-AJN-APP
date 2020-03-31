package org.jsoup.nodes;

import org.jsoup.nodes.Document.OutputSettings;

public class XmlDeclaration extends Node {
    private static final String DECL_KEY = "declaration";
    private final boolean isProcessingInstruction;

    public XmlDeclaration(String str, String str2, boolean z) {
        super(str2);
        this.attributes.put(DECL_KEY, str);
        this.isProcessingInstruction = z;
    }

    public String nodeName() {
        return "#declaration";
    }

    public String getWholeDeclaration() {
        return this.attributes.get(DECL_KEY);
    }

    /* access modifiers changed from: 0000 */
    public void outerHtmlHead(StringBuilder sb, int i, OutputSettings outputSettings) {
        sb.append("<").append(this.isProcessingInstruction ? "!" : "?").append(getWholeDeclaration()).append(">");
    }

    /* access modifiers changed from: 0000 */
    public void outerHtmlTail(StringBuilder sb, int i, OutputSettings outputSettings) {
    }

    public String toString() {
        return outerHtml();
    }
}
