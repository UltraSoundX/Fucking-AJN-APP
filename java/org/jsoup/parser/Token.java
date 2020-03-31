package org.jsoup.parser;

import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

abstract class Token {
    TokenType type;

    static class Character extends Token {
        private final String data;

        Character(String str) {
            super();
            this.type = TokenType.Character;
            this.data = str;
        }

        /* access modifiers changed from: 0000 */
        public String getData() {
            return this.data;
        }

        public String toString() {
            return getData();
        }
    }

    static class Comment extends Token {
        boolean bogus;
        final StringBuilder data;

        Comment() {
            super();
            this.data = new StringBuilder();
            this.bogus = false;
            this.type = TokenType.Comment;
        }

        /* access modifiers changed from: 0000 */
        public String getData() {
            return this.data.toString();
        }

        public String toString() {
            return "<!--" + getData() + "-->";
        }
    }

    static class Doctype extends Token {
        boolean forceQuirks;
        final StringBuilder name;
        final StringBuilder publicIdentifier;
        final StringBuilder systemIdentifier;

        Doctype() {
            super();
            this.name = new StringBuilder();
            this.publicIdentifier = new StringBuilder();
            this.systemIdentifier = new StringBuilder();
            this.forceQuirks = false;
            this.type = TokenType.Doctype;
        }

        /* access modifiers changed from: 0000 */
        public String getName() {
            return this.name.toString();
        }

        /* access modifiers changed from: 0000 */
        public String getPublicIdentifier() {
            return this.publicIdentifier.toString();
        }

        public String getSystemIdentifier() {
            return this.systemIdentifier.toString();
        }

        public boolean isForceQuirks() {
            return this.forceQuirks;
        }
    }

    static class EOF extends Token {
        EOF() {
            super();
            this.type = TokenType.EOF;
        }
    }

    static class EndTag extends Tag {
        EndTag() {
            this.type = TokenType.EndTag;
        }

        EndTag(String str) {
            this();
            this.tagName = str;
        }

        public String toString() {
            return "</" + name() + ">";
        }
    }

    static class StartTag extends Tag {
        StartTag() {
            this.attributes = new Attributes();
            this.type = TokenType.StartTag;
        }

        StartTag(String str) {
            this();
            this.tagName = str;
        }

        StartTag(String str, Attributes attributes) {
            this();
            this.tagName = str;
            this.attributes = attributes;
        }

        public String toString() {
            if (this.attributes == null || this.attributes.size() <= 0) {
                return "<" + name() + ">";
            }
            return "<" + name() + " " + this.attributes.toString() + ">";
        }
    }

    static abstract class Tag extends Token {
        Attributes attributes;
        private String pendingAttributeName;
        private StringBuilder pendingAttributeValue;
        boolean selfClosing = false;
        protected String tagName;

        Tag() {
            super();
        }

        /* access modifiers changed from: 0000 */
        public void newAttribute() {
            Attribute attribute;
            if (this.attributes == null) {
                this.attributes = new Attributes();
            }
            if (this.pendingAttributeName != null) {
                if (this.pendingAttributeValue == null) {
                    attribute = new Attribute(this.pendingAttributeName, "");
                } else {
                    attribute = new Attribute(this.pendingAttributeName, this.pendingAttributeValue.toString());
                }
                this.attributes.put(attribute);
            }
            this.pendingAttributeName = null;
            if (this.pendingAttributeValue != null) {
                this.pendingAttributeValue.delete(0, this.pendingAttributeValue.length());
            }
        }

        /* access modifiers changed from: 0000 */
        public void finaliseTag() {
            if (this.pendingAttributeName != null) {
                newAttribute();
            }
        }

        /* access modifiers changed from: 0000 */
        public String name() {
            Validate.isFalse(this.tagName == null || this.tagName.length() == 0);
            return this.tagName;
        }

        /* access modifiers changed from: 0000 */
        public Tag name(String str) {
            this.tagName = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public boolean isSelfClosing() {
            return this.selfClosing;
        }

        /* access modifiers changed from: 0000 */
        public Attributes getAttributes() {
            return this.attributes;
        }

        /* access modifiers changed from: 0000 */
        public void appendTagName(String str) {
            if (this.tagName != null) {
                str = this.tagName.concat(str);
            }
            this.tagName = str;
        }

        /* access modifiers changed from: 0000 */
        public void appendTagName(char c) {
            appendTagName(String.valueOf(c));
        }

        /* access modifiers changed from: 0000 */
        public void appendAttributeName(String str) {
            if (this.pendingAttributeName != null) {
                str = this.pendingAttributeName.concat(str);
            }
            this.pendingAttributeName = str;
        }

        /* access modifiers changed from: 0000 */
        public void appendAttributeName(char c) {
            appendAttributeName(String.valueOf(c));
        }

        /* access modifiers changed from: 0000 */
        public void appendAttributeValue(String str) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(str);
        }

        /* access modifiers changed from: 0000 */
        public void appendAttributeValue(char c) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(c);
        }

        /* access modifiers changed from: 0000 */
        public void appendAttributeValue(char[] cArr) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(cArr);
        }

        private final void ensureAttributeValue() {
            if (this.pendingAttributeValue == null) {
                this.pendingAttributeValue = new StringBuilder();
            }
        }
    }

    enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        EOF
    }

    private Token() {
    }

    /* access modifiers changed from: 0000 */
    public String tokenType() {
        return getClass().getSimpleName();
    }

    /* access modifiers changed from: 0000 */
    public boolean isDoctype() {
        return this.type == TokenType.Doctype;
    }

    /* access modifiers changed from: 0000 */
    public Doctype asDoctype() {
        return (Doctype) this;
    }

    /* access modifiers changed from: 0000 */
    public boolean isStartTag() {
        return this.type == TokenType.StartTag;
    }

    /* access modifiers changed from: 0000 */
    public StartTag asStartTag() {
        return (StartTag) this;
    }

    /* access modifiers changed from: 0000 */
    public boolean isEndTag() {
        return this.type == TokenType.EndTag;
    }

    /* access modifiers changed from: 0000 */
    public EndTag asEndTag() {
        return (EndTag) this;
    }

    /* access modifiers changed from: 0000 */
    public boolean isComment() {
        return this.type == TokenType.Comment;
    }

    /* access modifiers changed from: 0000 */
    public Comment asComment() {
        return (Comment) this;
    }

    /* access modifiers changed from: 0000 */
    public boolean isCharacter() {
        return this.type == TokenType.Character;
    }

    /* access modifiers changed from: 0000 */
    public Character asCharacter() {
        return (Character) this;
    }

    /* access modifiers changed from: 0000 */
    public boolean isEOF() {
        return this.type == TokenType.EOF;
    }
}
