package org.jsoup.safety;

import com.baidu.mobstat.Config;
import com.tencent.android.tpush.common.MessageKey;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

public class Whitelist {
    private Map<TagName, Set<AttributeKey>> attributes = new HashMap();
    private Map<TagName, Map<AttributeKey, AttributeValue>> enforcedAttributes = new HashMap();
    private boolean preserveRelativeLinks = false;
    private Map<TagName, Map<AttributeKey, Set<Protocol>>> protocols = new HashMap();
    private Set<TagName> tagNames = new HashSet();

    static class AttributeKey extends TypedValue {
        AttributeKey(String str) {
            super(str);
        }

        static AttributeKey valueOf(String str) {
            return new AttributeKey(str);
        }
    }

    static class AttributeValue extends TypedValue {
        AttributeValue(String str) {
            super(str);
        }

        static AttributeValue valueOf(String str) {
            return new AttributeValue(str);
        }
    }

    static class Protocol extends TypedValue {
        Protocol(String str) {
            super(str);
        }

        static Protocol valueOf(String str) {
            return new Protocol(str);
        }
    }

    static class TagName extends TypedValue {
        TagName(String str) {
            super(str);
        }

        static TagName valueOf(String str) {
            return new TagName(str);
        }
    }

    static abstract class TypedValue {
        private String value;

        TypedValue(String str) {
            Validate.notNull(str);
            this.value = str;
        }

        public int hashCode() {
            return (this.value == null ? 0 : this.value.hashCode()) + 31;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            TypedValue typedValue = (TypedValue) obj;
            if (this.value == null) {
                if (typedValue.value != null) {
                    return false;
                }
                return true;
            } else if (!this.value.equals(typedValue.value)) {
                return false;
            } else {
                return true;
            }
        }

        public String toString() {
            return this.value;
        }
    }

    public static Whitelist none() {
        return new Whitelist();
    }

    public static Whitelist simpleText() {
        return new Whitelist().addTags("b", "em", "i", "strong", "u");
    }

    public static Whitelist basic() {
        return new Whitelist().addTags(Config.APP_VERSION_CODE, "b", "blockquote", "br", "cite", "code", Config.DEVICE_ID_SEC, "dl", "dt", "em", "i", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong", "sub", "sup", "u", "ul").addAttributes(Config.APP_VERSION_CODE, "href").addAttributes("blockquote", "cite").addAttributes("q", "cite").addProtocols(Config.APP_VERSION_CODE, "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addEnforcedAttribute(Config.APP_VERSION_CODE, "rel", "nofollow");
    }

    public static Whitelist basicWithImages() {
        return basic().addTags("img").addAttributes("img", "align", "alt", "height", "src", "title", "width").addProtocols("img", "src", "http", "https");
    }

    public static Whitelist relaxed() {
        String[] strArr = {"cite"};
        String[] strArr2 = {"span", "width"};
        String[] strArr3 = {"span", "width"};
        String[] strArr4 = {"align", "alt", "height", "src", "title", "width"};
        String[] strArr5 = {MessageKey.MSG_ACCEPT_TIME_START, "type"};
        String[] strArr6 = {"cite"};
        String[] strArr7 = {"summary", "width"};
        Whitelist addAttributes = new Whitelist().addTags(Config.APP_VERSION_CODE, "b", "blockquote", "br", "caption", "cite", "code", "col", "colgroup", Config.DEVICE_ID_SEC, "div", "dl", "dt", "em", "h1", Config.EVENT_NATIVE_VIEW_HIERARCHY, Config.EVENT_H5_VIEW_HIERARCHY, "h4", "h5", "h6", "i", "img", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong", "sub", "sup", "table", "tbody", Config.TEST_DEVICE_ID, "tfoot", "th", "thead", "tr", "u", "ul").addAttributes(Config.APP_VERSION_CODE, "href", "title").addAttributes("blockquote", strArr).addAttributes("col", strArr2).addAttributes("colgroup", strArr3).addAttributes("img", strArr4).addAttributes("ol", strArr5).addAttributes("q", strArr6).addAttributes("table", strArr7);
        return addAttributes.addAttributes(Config.TEST_DEVICE_ID, "abbr", "axis", "colspan", "rowspan", "width").addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width").addAttributes("ul", "type").addProtocols(Config.APP_VERSION_CODE, "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addProtocols("img", "src", "http", "https").addProtocols("q", "cite", "http", "https");
    }

    public Whitelist addTags(String... strArr) {
        Validate.notNull(strArr);
        for (String str : strArr) {
            Validate.notEmpty(str);
            this.tagNames.add(TagName.valueOf(str));
        }
        return this;
    }

    public Whitelist addAttributes(String str, String... strArr) {
        Validate.notEmpty(str);
        Validate.notNull(strArr);
        Validate.isTrue(strArr.length > 0, "No attributes supplied.");
        TagName valueOf = TagName.valueOf(str);
        if (!this.tagNames.contains(valueOf)) {
            this.tagNames.add(valueOf);
        }
        HashSet hashSet = new HashSet();
        for (String str2 : strArr) {
            Validate.notEmpty(str2);
            hashSet.add(AttributeKey.valueOf(str2));
        }
        if (this.attributes.containsKey(valueOf)) {
            ((Set) this.attributes.get(valueOf)).addAll(hashSet);
        } else {
            this.attributes.put(valueOf, hashSet);
        }
        return this;
    }

    public Whitelist addEnforcedAttribute(String str, String str2, String str3) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notEmpty(str3);
        TagName valueOf = TagName.valueOf(str);
        if (!this.tagNames.contains(valueOf)) {
            this.tagNames.add(valueOf);
        }
        AttributeKey valueOf2 = AttributeKey.valueOf(str2);
        AttributeValue valueOf3 = AttributeValue.valueOf(str3);
        if (this.enforcedAttributes.containsKey(valueOf)) {
            ((Map) this.enforcedAttributes.get(valueOf)).put(valueOf2, valueOf3);
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put(valueOf2, valueOf3);
            this.enforcedAttributes.put(valueOf, hashMap);
        }
        return this;
    }

    public Whitelist preserveRelativeLinks(boolean z) {
        this.preserveRelativeLinks = z;
        return this;
    }

    public Whitelist addProtocols(String str, String str2, String... strArr) {
        Map hashMap;
        Set set;
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notNull(strArr);
        TagName valueOf = TagName.valueOf(str);
        AttributeKey valueOf2 = AttributeKey.valueOf(str2);
        if (this.protocols.containsKey(valueOf)) {
            hashMap = (Map) this.protocols.get(valueOf);
        } else {
            hashMap = new HashMap();
            this.protocols.put(valueOf, hashMap);
        }
        if (hashMap.containsKey(valueOf2)) {
            set = (Set) hashMap.get(valueOf2);
        } else {
            Set hashSet = new HashSet();
            hashMap.put(valueOf2, hashSet);
            set = hashSet;
        }
        for (String str3 : strArr) {
            Validate.notEmpty(str3);
            set.add(Protocol.valueOf(str3));
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean isSafeTag(String str) {
        return this.tagNames.contains(TagName.valueOf(str));
    }

    /* access modifiers changed from: protected */
    public boolean isSafeAttribute(String str, Element element, Attribute attribute) {
        TagName valueOf = TagName.valueOf(str);
        AttributeKey valueOf2 = AttributeKey.valueOf(attribute.getKey());
        if (!this.attributes.containsKey(valueOf) || !((Set) this.attributes.get(valueOf)).contains(valueOf2)) {
            if (str.equals(":all") || !isSafeAttribute(":all", element, attribute)) {
                return false;
            }
            return true;
        } else if (!this.protocols.containsKey(valueOf)) {
            return true;
        } else {
            Map map = (Map) this.protocols.get(valueOf);
            return !map.containsKey(valueOf2) || testValidProtocol(element, attribute, (Set) map.get(valueOf2));
        }
    }

    private boolean testValidProtocol(Element element, Attribute attribute, Set<Protocol> set) {
        String str;
        String absUrl = element.absUrl(attribute.getKey());
        if (absUrl.length() == 0) {
            str = attribute.getValue();
        } else {
            str = absUrl;
        }
        if (!this.preserveRelativeLinks) {
            attribute.setValue(str);
        }
        for (Protocol protocol : set) {
            if (str.toLowerCase().startsWith(protocol.toString() + Config.TRACE_TODAY_VISIT_SPLIT)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public Attributes getEnforcedAttributes(String str) {
        Attributes attributes2 = new Attributes();
        TagName valueOf = TagName.valueOf(str);
        if (this.enforcedAttributes.containsKey(valueOf)) {
            for (Entry entry : ((Map) this.enforcedAttributes.get(valueOf)).entrySet()) {
                attributes2.put(((AttributeKey) entry.getKey()).toString(), ((AttributeValue) entry.getValue()).toString());
            }
        }
        return attributes2;
    }
}
