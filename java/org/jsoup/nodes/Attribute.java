package org.jsoup.nodes;

import java.util.Arrays;
import java.util.Map.Entry;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Document.OutputSettings.Syntax;

public class Attribute implements Cloneable, Entry<String, String> {
    private static final String[] booleanAttributes = {"allowfullscreen", "async", "autofocus", "checked", "compact", "declare", "default", "defer", "disabled", "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize", "noshade", "novalidate", "nowrap", "open", "readonly", "required", "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch"};
    private String key;
    private String value;

    public Attribute(String str, String str2) {
        Validate.notEmpty(str);
        Validate.notNull(str2);
        this.key = str.trim().toLowerCase();
        this.value = str2;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        Validate.notEmpty(str);
        this.key = str.trim().toLowerCase();
    }

    public String getValue() {
        return this.value;
    }

    public String setValue(String str) {
        Validate.notNull(str);
        String str2 = this.value;
        this.value = str;
        return str2;
    }

    public String html() {
        StringBuilder sb = new StringBuilder();
        html(sb, new Document("").outputSettings());
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void html(StringBuilder sb, OutputSettings outputSettings) {
        sb.append(this.key);
        if (!shouldCollapseAttribute(outputSettings)) {
            sb.append("=\"");
            Entities.escape(sb, this.value, outputSettings, true, false, false);
            sb.append('\"');
        }
    }

    public String toString() {
        return html();
    }

    public static Attribute createFromEncoded(String str, String str2) {
        return new Attribute(str, Entities.unescape(str2, true));
    }

    /* access modifiers changed from: protected */
    public boolean isDataAttribute() {
        return this.key.startsWith("data-") && this.key.length() > "data-".length();
    }

    /* access modifiers changed from: protected */
    public final boolean shouldCollapseAttribute(OutputSettings outputSettings) {
        return ("".equals(this.value) || this.value.equalsIgnoreCase(this.key)) && outputSettings.syntax() == Syntax.html && Arrays.binarySearch(booleanAttributes, this.key) >= 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Attribute)) {
            return false;
        }
        Attribute attribute = (Attribute) obj;
        if (this.key == null ? attribute.key != null : !this.key.equals(attribute.key)) {
            return false;
        }
        if (this.value != null) {
            if (this.value.equals(attribute.value)) {
                return true;
            }
        } else if (attribute.value == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.key != null) {
            i = this.key.hashCode();
        } else {
            i = 0;
        }
        int i3 = i * 31;
        if (this.value != null) {
            i2 = this.value.hashCode();
        }
        return i3 + i2;
    }

    public Attribute clone() {
        try {
            return (Attribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
