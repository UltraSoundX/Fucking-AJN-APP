package org.jsoup.nodes;

import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document.OutputSettings;

public class DocumentType extends Node {
    public DocumentType(String str, String str2, String str3, String str4) {
        super(str4);
        attr("name", str);
        attr("publicId", str2);
        attr("systemId", str3);
    }

    public String nodeName() {
        return "#doctype";
    }

    /* access modifiers changed from: 0000 */
    public void outerHtmlHead(StringBuilder sb, int i, OutputSettings outputSettings) {
        sb.append("<!DOCTYPE");
        if (!StringUtil.isBlank(attr("name"))) {
            sb.append(" ").append(attr("name"));
        }
        if (!StringUtil.isBlank(attr("publicId"))) {
            sb.append(" PUBLIC \"").append(attr("publicId")).append('\"');
        }
        if (!StringUtil.isBlank(attr("systemId"))) {
            sb.append(" \"").append(attr("systemId")).append('\"');
        }
        sb.append('>');
    }

    /* access modifiers changed from: 0000 */
    public void outerHtmlTail(StringBuilder sb, int i, OutputSettings outputSettings) {
    }
}
