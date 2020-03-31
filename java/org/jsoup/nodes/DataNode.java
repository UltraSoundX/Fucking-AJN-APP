package org.jsoup.nodes;

import org.jsoup.nodes.Document.OutputSettings;

public class DataNode extends Node {
    private static final String DATA_KEY = "data";

    public DataNode(String str, String str2) {
        super(str2);
        this.attributes.put(DATA_KEY, str);
    }

    public String nodeName() {
        return "#data";
    }

    public String getWholeData() {
        return this.attributes.get(DATA_KEY);
    }

    public DataNode setWholeData(String str) {
        this.attributes.put(DATA_KEY, str);
        return this;
    }

    /* access modifiers changed from: 0000 */
    public void outerHtmlHead(StringBuilder sb, int i, OutputSettings outputSettings) {
        sb.append(getWholeData());
    }

    /* access modifiers changed from: 0000 */
    public void outerHtmlTail(StringBuilder sb, int i, OutputSettings outputSettings) {
    }

    public String toString() {
        return outerHtml();
    }

    public static DataNode createFromEncoded(String str, String str2) {
        return new DataNode(Entities.unescape(str), str2);
    }
}
