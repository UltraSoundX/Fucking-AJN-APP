package org.jsoup.nodes;

import org.jsoup.nodes.Document.OutputSettings;

public class Comment extends Node {
    private static final String COMMENT_KEY = "comment";

    public Comment(String str, String str2) {
        super(str2);
        this.attributes.put(COMMENT_KEY, str);
    }

    public String nodeName() {
        return "#comment";
    }

    public String getData() {
        return this.attributes.get(COMMENT_KEY);
    }

    /* access modifiers changed from: 0000 */
    public void outerHtmlHead(StringBuilder sb, int i, OutputSettings outputSettings) {
        if (outputSettings.prettyPrint()) {
            indent(sb, i, outputSettings);
        }
        sb.append("<!--").append(getData()).append("-->");
    }

    /* access modifiers changed from: 0000 */
    public void outerHtmlTail(StringBuilder sb, int i, OutputSettings outputSettings) {
    }

    public String toString() {
        return outerHtml();
    }
}
