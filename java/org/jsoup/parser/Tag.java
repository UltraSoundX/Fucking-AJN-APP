package org.jsoup.parser;

import android.support.v4.app.NotificationCompat;
import com.baidu.mobstat.Config;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.xml.JSONTypes;
import org.jsoup.helper.Validate;

public class Tag {
    private static final String[] blockTags = {"html", "head", "body", "frameset", "script", "noscript", "style", "meta", "link", "title", "frame", "noframes", "section", "nav", "aside", "hgroup", TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_HEADER, "footer", "p", "h1", Config.EVENT_NATIVE_VIEW_HIERARCHY, Config.EVENT_H5_VIEW_HIERARCHY, "h4", "h5", "h6", "ul", "ol", "pre", "div", "blockquote", "hr", "address", "figure", "figcaption", "form", "fieldset", "ins", "del", "s", "dl", "dt", Config.DEVICE_ID_SEC, "li", "table", "caption", "thead", "tfoot", "tbody", "colgroup", "col", "tr", "th", Config.TEST_DEVICE_ID, "video", "audio", "canvas", "details", "menu", "plaintext"};
    private static final String[] emptyTags = {"meta", "link", "base", "frame", "img", "br", "wbr", "embed", "hr", Config.INPUT_PART, "keygen", "col", "command", Config.DEVICE_PART, "area", "basefont", "bgsound", "menuitem", "param", "source", "track"};
    private static final String[] formListedTags = {"button", "fieldset", Config.INPUT_PART, "keygen", JSONTypes.OBJECT, "output", "select", "textarea"};
    private static final String[] formSubmitTags = {Config.INPUT_PART, "keygen", JSONTypes.OBJECT, "select", "textarea"};
    private static final String[] formatAsInlineTags = {"title", Config.APP_VERSION_CODE, "p", "h1", Config.EVENT_NATIVE_VIEW_HIERARCHY, Config.EVENT_H5_VIEW_HIERARCHY, "h4", "h5", "h6", "pre", "address", "li", "th", Config.TEST_DEVICE_ID, "script", "style", "ins", "del", "s"};
    private static final String[] inlineTags = {JSONTypes.OBJECT, "base", "font", "tt", "i", "b", "u", "big", "small", "em", "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", "time", "acronym", "mark", "ruby", "rt", "rp", Config.APP_VERSION_CODE, "img", "br", "wbr", "map", "q", "sub", "sup", "bdo", "iframe", "embed", "span", Config.INPUT_PART, "select", "textarea", "label", "button", "optgroup", "option", "legend", "datalist", "keygen", "output", NotificationCompat.CATEGORY_PROGRESS, "meter", "area", "param", "source", "track", "summary", "command", Config.DEVICE_PART, "area", "basefont", "bgsound", "menuitem", "param", "source", "track"};
    private static final String[] preserveWhitespaceTags = {"pre", "plaintext", "title", "textarea"};
    private static final Map<String, Tag> tags = new HashMap();
    private boolean canContainBlock = true;
    private boolean canContainInline = true;
    private boolean empty = false;
    private boolean formList = false;
    private boolean formSubmit = false;
    private boolean formatAsBlock = true;
    private boolean isBlock = true;
    private boolean preserveWhitespace = false;
    private boolean selfClosing = false;
    private String tagName;

    static {
        for (String tag : blockTags) {
            register(new Tag(tag));
        }
        for (String tag2 : inlineTags) {
            Tag tag3 = new Tag(tag2);
            tag3.isBlock = false;
            tag3.canContainBlock = false;
            tag3.formatAsBlock = false;
            register(tag3);
        }
        for (String str : emptyTags) {
            Tag tag4 = (Tag) tags.get(str);
            Validate.notNull(tag4);
            tag4.canContainBlock = false;
            tag4.canContainInline = false;
            tag4.empty = true;
        }
        for (String str2 : formatAsInlineTags) {
            Tag tag5 = (Tag) tags.get(str2);
            Validate.notNull(tag5);
            tag5.formatAsBlock = false;
        }
        for (String str3 : preserveWhitespaceTags) {
            Tag tag6 = (Tag) tags.get(str3);
            Validate.notNull(tag6);
            tag6.preserveWhitespace = true;
        }
        for (String str4 : formListedTags) {
            Tag tag7 = (Tag) tags.get(str4);
            Validate.notNull(tag7);
            tag7.formList = true;
        }
        for (String str5 : formSubmitTags) {
            Tag tag8 = (Tag) tags.get(str5);
            Validate.notNull(tag8);
            tag8.formSubmit = true;
        }
    }

    private Tag(String str) {
        this.tagName = str.toLowerCase();
    }

    public String getName() {
        return this.tagName;
    }

    public static Tag valueOf(String str) {
        Validate.notNull(str);
        Tag tag = (Tag) tags.get(str);
        if (tag != null) {
            return tag;
        }
        String lowerCase = str.trim().toLowerCase();
        Validate.notEmpty(lowerCase);
        Tag tag2 = (Tag) tags.get(lowerCase);
        if (tag2 != null) {
            return tag2;
        }
        Tag tag3 = new Tag(lowerCase);
        tag3.isBlock = false;
        tag3.canContainBlock = true;
        return tag3;
    }

    public boolean isBlock() {
        return this.isBlock;
    }

    public boolean formatAsBlock() {
        return this.formatAsBlock;
    }

    public boolean canContainBlock() {
        return this.canContainBlock;
    }

    public boolean isInline() {
        return !this.isBlock;
    }

    public boolean isData() {
        return !this.canContainInline && !isEmpty();
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isSelfClosing() {
        return this.empty || this.selfClosing;
    }

    public boolean isKnownTag() {
        return tags.containsKey(this.tagName);
    }

    public static boolean isKnownTag(String str) {
        return tags.containsKey(str);
    }

    public boolean preserveWhitespace() {
        return this.preserveWhitespace;
    }

    public boolean isFormListed() {
        return this.formList;
    }

    public boolean isFormSubmittable() {
        return this.formSubmit;
    }

    /* access modifiers changed from: 0000 */
    public Tag setSelfClosing() {
        this.selfClosing = true;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) obj;
        if (this.canContainBlock != tag.canContainBlock) {
            return false;
        }
        if (this.canContainInline != tag.canContainInline) {
            return false;
        }
        if (this.empty != tag.empty) {
            return false;
        }
        if (this.formatAsBlock != tag.formatAsBlock) {
            return false;
        }
        if (this.isBlock != tag.isBlock) {
            return false;
        }
        if (this.preserveWhitespace != tag.preserveWhitespace) {
            return false;
        }
        if (this.selfClosing != tag.selfClosing) {
            return false;
        }
        if (this.formList != tag.formList) {
            return false;
        }
        if (this.formSubmit != tag.formSubmit) {
            return false;
        }
        if (!this.tagName.equals(tag.tagName)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = 1;
        int hashCode = ((this.isBlock ? 1 : 0) + (this.tagName.hashCode() * 31)) * 31;
        if (this.formatAsBlock) {
            i = 1;
        } else {
            i = 0;
        }
        int i9 = (i + hashCode) * 31;
        if (this.canContainBlock) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i10 = (i2 + i9) * 31;
        if (this.canContainInline) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i11 = (i3 + i10) * 31;
        if (this.empty) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i12 = (i4 + i11) * 31;
        if (this.selfClosing) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i5 + i12) * 31;
        if (this.preserveWhitespace) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i14 = (i6 + i13) * 31;
        if (this.formList) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i15 = (i7 + i14) * 31;
        if (!this.formSubmit) {
            i8 = 0;
        }
        return i15 + i8;
    }

    public String toString() {
        return this.tagName;
    }

    private static void register(Tag tag) {
        tags.put(tag.tagName, tag);
    }
}
