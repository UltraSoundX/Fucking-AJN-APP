package org.jsoup.parser;

import com.baidu.mobstat.Config;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.util.Iterator;
import net.sf.json.xml.JSONTypes;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document.QuirksMode;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;

enum HtmlTreeBuilderState {
    Initial {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                Doctype asDoctype = token.asDoctype();
                htmlTreeBuilder.getDocument().appendChild(new DocumentType(asDoctype.getName(), asDoctype.getPublicIdentifier(), asDoctype.getSystemIdentifier(), htmlTreeBuilder.getBaseUri()));
                if (asDoctype.isForceQuirks()) {
                    htmlTreeBuilder.getDocument().quirksMode(QuirksMode.quirks);
                }
                htmlTreeBuilder.transition(BeforeHtml);
                return true;
            } else {
                htmlTreeBuilder.transition(BeforeHtml);
                return htmlTreeBuilder.process(token);
            }
        }
    },
    BeforeHtml {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            } else {
                if (!token.isStartTag() || !token.asStartTag().name().equals("html")) {
                    if (token.isEndTag()) {
                        if (StringUtil.in(token.asEndTag().name(), "head", "body", "html", "br")) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                    }
                    if (!token.isEndTag()) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.insert(token.asStartTag());
                htmlTreeBuilder.transition(BeforeHead);
            }
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.insert("html");
            htmlTreeBuilder.transition(BeforeHead);
            return htmlTreeBuilder.process(token);
        }
    },
    BeforeHead {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag() && token.asStartTag().name().equals("html")) {
                return InBody.process(token, htmlTreeBuilder);
            } else {
                if (!token.isStartTag() || !token.asStartTag().name().equals("head")) {
                    if (token.isEndTag()) {
                        if (StringUtil.in(token.asEndTag().name(), "head", "body", "html", "br")) {
                            htmlTreeBuilder.process(new StartTag("head"));
                            return htmlTreeBuilder.process(token);
                        }
                    }
                    if (token.isEndTag()) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.process(new StartTag("head"));
                    return htmlTreeBuilder.process(token);
                }
                htmlTreeBuilder.setHeadElement(htmlTreeBuilder.insert(token.asStartTag()));
                htmlTreeBuilder.transition(InHead);
                return true;
            }
        }
    },
    InHead {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            }
            switch (token.type) {
                case Comment:
                    htmlTreeBuilder.insert(token.asComment());
                    return true;
                case Doctype:
                    htmlTreeBuilder.error(this);
                    return false;
                case StartTag:
                    StartTag asStartTag = token.asStartTag();
                    String name = asStartTag.name();
                    if (name.equals("html")) {
                        return InBody.process(token, htmlTreeBuilder);
                    }
                    if (StringUtil.in(name, "base", "basefont", "bgsound", "command", "link")) {
                        Element insertEmpty = htmlTreeBuilder.insertEmpty(asStartTag);
                        if (!name.equals("base") || !insertEmpty.hasAttr("href")) {
                            return true;
                        }
                        htmlTreeBuilder.maybeSetBaseUri(insertEmpty);
                        return true;
                    } else if (name.equals("meta")) {
                        htmlTreeBuilder.insertEmpty(asStartTag);
                        return true;
                    } else if (name.equals("title")) {
                        HtmlTreeBuilderState.handleRcData(asStartTag, htmlTreeBuilder);
                        return true;
                    } else {
                        if (StringUtil.in(name, "noframes", "style")) {
                            HtmlTreeBuilderState.handleRawtext(asStartTag, htmlTreeBuilder);
                            return true;
                        } else if (name.equals("noscript")) {
                            htmlTreeBuilder.insert(asStartTag);
                            htmlTreeBuilder.transition(InHeadNoscript);
                            return true;
                        } else if (name.equals("script")) {
                            htmlTreeBuilder.tokeniser.transition(TokeniserState.ScriptData);
                            htmlTreeBuilder.markInsertionMode();
                            htmlTreeBuilder.transition(Text);
                            htmlTreeBuilder.insert(asStartTag);
                            return true;
                        } else if (!name.equals("head")) {
                            return anythingElse(token, htmlTreeBuilder);
                        } else {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                    }
                case EndTag:
                    String name2 = token.asEndTag().name();
                    if (name2.equals("head")) {
                        htmlTreeBuilder.pop();
                        htmlTreeBuilder.transition(AfterHead);
                        return true;
                    }
                    if (StringUtil.in(name2, "body", "html", "br")) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, TreeBuilder treeBuilder) {
            treeBuilder.process(new EndTag("head"));
            return treeBuilder.process(token);
        }
    },
    InHeadNoscript {
        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0086, code lost:
            if (org.jsoup.helper.StringUtil.in(r8.asStartTag().name(), "basefont", "bgsound", "link", "meta", "noframes", "style") != false) goto L_0x0088;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00c8, code lost:
            if (org.jsoup.helper.StringUtil.in(r8.asStartTag().name(), "head", "noscript") == false) goto L_0x00ca;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(org.jsoup.parser.Token r8, org.jsoup.parser.HtmlTreeBuilder r9) {
            /*
                r7 = this;
                r6 = 2
                r1 = 1
                r0 = 0
                boolean r2 = r8.isDoctype()
                if (r2 == 0) goto L_0x000e
                r9.error(r7)
            L_0x000c:
                r0 = r1
            L_0x000d:
                return r0
            L_0x000e:
                boolean r2 = r8.isStartTag()
                if (r2 == 0) goto L_0x002b
                org.jsoup.parser.Token$StartTag r2 = r8.asStartTag()
                java.lang.String r2 = r2.name()
                java.lang.String r3 = "html"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x002b
                org.jsoup.parser.HtmlTreeBuilderState r0 = InBody
                boolean r0 = r9.process(r8, r0)
                goto L_0x000d
            L_0x002b:
                boolean r2 = r8.isEndTag()
                if (r2 == 0) goto L_0x004a
                org.jsoup.parser.Token$EndTag r2 = r8.asEndTag()
                java.lang.String r2 = r2.name()
                java.lang.String r3 = "noscript"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x004a
                r9.pop()
                org.jsoup.parser.HtmlTreeBuilderState r0 = InHead
                r9.transition(r0)
                goto L_0x000c
            L_0x004a:
                boolean r2 = org.jsoup.parser.HtmlTreeBuilderState.isWhitespace(r8)
                if (r2 != 0) goto L_0x0088
                boolean r2 = r8.isComment()
                if (r2 != 0) goto L_0x0088
                boolean r2 = r8.isStartTag()
                if (r2 == 0) goto L_0x0090
                org.jsoup.parser.Token$StartTag r2 = r8.asStartTag()
                java.lang.String r2 = r2.name()
                r3 = 6
                java.lang.String[] r3 = new java.lang.String[r3]
                java.lang.String r4 = "basefont"
                r3[r0] = r4
                java.lang.String r4 = "bgsound"
                r3[r1] = r4
                java.lang.String r4 = "link"
                r3[r6] = r4
                r4 = 3
                java.lang.String r5 = "meta"
                r3[r4] = r5
                r4 = 4
                java.lang.String r5 = "noframes"
                r3[r4] = r5
                r4 = 5
                java.lang.String r5 = "style"
                r3[r4] = r5
                boolean r2 = org.jsoup.helper.StringUtil.in(r2, r3)
                if (r2 == 0) goto L_0x0090
            L_0x0088:
                org.jsoup.parser.HtmlTreeBuilderState r0 = InHead
                boolean r0 = r9.process(r8, r0)
                goto L_0x000d
            L_0x0090:
                boolean r2 = r8.isEndTag()
                if (r2 == 0) goto L_0x00ac
                org.jsoup.parser.Token$EndTag r2 = r8.asEndTag()
                java.lang.String r2 = r2.name()
                java.lang.String r3 = "br"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x00ac
                boolean r0 = r7.anythingElse(r8, r9)
                goto L_0x000d
            L_0x00ac:
                boolean r2 = r8.isStartTag()
                if (r2 == 0) goto L_0x00ca
                org.jsoup.parser.Token$StartTag r2 = r8.asStartTag()
                java.lang.String r2 = r2.name()
                java.lang.String[] r3 = new java.lang.String[r6]
                java.lang.String r4 = "head"
                r3[r0] = r4
                java.lang.String r4 = "noscript"
                r3[r1] = r4
                boolean r1 = org.jsoup.helper.StringUtil.in(r2, r3)
                if (r1 != 0) goto L_0x00d0
            L_0x00ca:
                boolean r1 = r8.isEndTag()
                if (r1 == 0) goto L_0x00d5
            L_0x00d0:
                r9.error(r7)
                goto L_0x000d
            L_0x00d5:
                boolean r0 = r7.anythingElse(r8, r9)
                goto L_0x000d
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass5.process(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            htmlTreeBuilder.process(new EndTag("noscript"));
            return htmlTreeBuilder.process(token);
        }
    },
    AfterHead {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
            } else if (token.isStartTag()) {
                StartTag asStartTag = token.asStartTag();
                String name = asStartTag.name();
                if (name.equals("html")) {
                    return htmlTreeBuilder.process(token, InBody);
                }
                if (name.equals("body")) {
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    htmlTreeBuilder.transition(InBody);
                } else if (name.equals("frameset")) {
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InFrameset);
                } else {
                    if (StringUtil.in(name, "base", "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "title")) {
                        htmlTreeBuilder.error(this);
                        Element headElement = htmlTreeBuilder.getHeadElement();
                        htmlTreeBuilder.push(headElement);
                        htmlTreeBuilder.process(token, InHead);
                        htmlTreeBuilder.removeFromStack(headElement);
                    } else if (name.equals("head")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    } else {
                        anythingElse(token, htmlTreeBuilder);
                    }
                }
            } else if (token.isEndTag()) {
                if (StringUtil.in(token.asEndTag().name(), "body", "html")) {
                    anythingElse(token, htmlTreeBuilder);
                } else {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            } else {
                anythingElse(token, htmlTreeBuilder);
            }
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.process(new StartTag("body"));
            htmlTreeBuilder.framesetOk(true);
            return htmlTreeBuilder.process(token);
        }
    },
    InBody {
        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:370:0x08dd  */
        /* JADX WARNING: Removed duplicated region for block: B:376:0x0915 A[LOOP:9: B:375:0x0913->B:376:0x0915, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:383:0x0944  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(org.jsoup.parser.Token r13, org.jsoup.parser.HtmlTreeBuilder r14) {
            /*
                r12 = this;
                int[] r0 = org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType
                org.jsoup.parser.Token$TokenType r1 = r13.type
                int r1 = r1.ordinal()
                r0 = r0[r1]
                switch(r0) {
                    case 1: goto L_0x0044;
                    case 2: goto L_0x004c;
                    case 3: goto L_0x0051;
                    case 4: goto L_0x06a3;
                    case 5: goto L_0x000f;
                    default: goto L_0x000d;
                }
            L_0x000d:
                r0 = 1
            L_0x000e:
                return r0
            L_0x000f:
                org.jsoup.parser.Token$Character r0 = r13.asCharacter()
                java.lang.String r1 = r0.getData()
                java.lang.String r2 = org.jsoup.parser.HtmlTreeBuilderState.nullString
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0026
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x0026:
                boolean r1 = r14.framesetOk()
                if (r1 == 0) goto L_0x0039
                boolean r1 = org.jsoup.parser.HtmlTreeBuilderState.isWhitespace(r0)
                if (r1 == 0) goto L_0x0039
                r14.reconstructFormattingElements()
                r14.insert(r0)
                goto L_0x000d
            L_0x0039:
                r14.reconstructFormattingElements()
                r14.insert(r0)
                r0 = 0
                r14.framesetOk(r0)
                goto L_0x000d
            L_0x0044:
                org.jsoup.parser.Token$Comment r0 = r13.asComment()
                r14.insert(r0)
                goto L_0x000d
            L_0x004c:
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x0051:
                org.jsoup.parser.Token$StartTag r2 = r13.asStartTag()
                java.lang.String r0 = r2.name()
                java.lang.String r1 = "html"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x0094
                r14.error(r12)
                org.jsoup.helper.DescendableLinkedList r0 = r14.getStack()
                java.lang.Object r0 = r0.getFirst()
                org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
                org.jsoup.nodes.Attributes r1 = r2.getAttributes()
                java.util.Iterator r2 = r1.iterator()
            L_0x0076:
                boolean r1 = r2.hasNext()
                if (r1 == 0) goto L_0x000d
                java.lang.Object r1 = r2.next()
                org.jsoup.nodes.Attribute r1 = (org.jsoup.nodes.Attribute) r1
                java.lang.String r3 = r1.getKey()
                boolean r3 = r0.hasAttr(r3)
                if (r3 != 0) goto L_0x0076
                org.jsoup.nodes.Attributes r3 = r0.attributes()
                r3.put(r1)
                goto L_0x0076
            L_0x0094:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartToHead
                boolean r1 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r1 == 0) goto L_0x00a6
                org.jsoup.parser.HtmlTreeBuilderState r0 = InHead
                boolean r0 = r14.process(r13, r0)
                goto L_0x000e
            L_0x00a6:
                java.lang.String r1 = "body"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x010a
                r14.error(r12)
                org.jsoup.helper.DescendableLinkedList r1 = r14.getStack()
                int r0 = r1.size()
                r3 = 1
                if (r0 == r3) goto L_0x00d6
                int r0 = r1.size()
                r3 = 2
                if (r0 <= r3) goto L_0x00d9
                r0 = 1
                java.lang.Object r0 = r1.get(r0)
                org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
                java.lang.String r0 = r0.nodeName()
                java.lang.String r3 = "body"
                boolean r0 = r0.equals(r3)
                if (r0 != 0) goto L_0x00d9
            L_0x00d6:
                r0 = 0
                goto L_0x000e
            L_0x00d9:
                r0 = 0
                r14.framesetOk(r0)
                r0 = 1
                java.lang.Object r0 = r1.get(r0)
                org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
                org.jsoup.nodes.Attributes r1 = r2.getAttributes()
                java.util.Iterator r2 = r1.iterator()
            L_0x00ec:
                boolean r1 = r2.hasNext()
                if (r1 == 0) goto L_0x000d
                java.lang.Object r1 = r2.next()
                org.jsoup.nodes.Attribute r1 = (org.jsoup.nodes.Attribute) r1
                java.lang.String r3 = r1.getKey()
                boolean r3 = r0.hasAttr(r3)
                if (r3 != 0) goto L_0x00ec
                org.jsoup.nodes.Attributes r3 = r0.attributes()
                r3.put(r1)
                goto L_0x00ec
            L_0x010a:
                java.lang.String r1 = "frameset"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x016b
                r14.error(r12)
                org.jsoup.helper.DescendableLinkedList r1 = r14.getStack()
                int r0 = r1.size()
                r3 = 1
                if (r0 == r3) goto L_0x013a
                int r0 = r1.size()
                r3 = 2
                if (r0 <= r3) goto L_0x013d
                r0 = 1
                java.lang.Object r0 = r1.get(r0)
                org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
                java.lang.String r0 = r0.nodeName()
                java.lang.String r3 = "body"
                boolean r0 = r0.equals(r3)
                if (r0 != 0) goto L_0x013d
            L_0x013a:
                r0 = 0
                goto L_0x000e
            L_0x013d:
                boolean r0 = r14.framesetOk()
                if (r0 != 0) goto L_0x0146
                r0 = 0
                goto L_0x000e
            L_0x0146:
                r0 = 1
                java.lang.Object r0 = r1.get(r0)
                org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
                org.jsoup.nodes.Element r3 = r0.parent()
                if (r3 == 0) goto L_0x0156
                r0.remove()
            L_0x0156:
                int r0 = r1.size()
                r3 = 1
                if (r0 <= r3) goto L_0x0161
                r1.removeLast()
                goto L_0x0156
            L_0x0161:
                r14.insert(r2)
                org.jsoup.parser.HtmlTreeBuilderState r0 = InFrameset
                r14.transition(r0)
                goto L_0x000d
            L_0x016b:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartPClosers
                boolean r1 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r1 == 0) goto L_0x018c
                java.lang.String r0 = "p"
                boolean r0 = r14.inButtonScope(r0)
                if (r0 == 0) goto L_0x0187
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "p"
                r0.<init>(r1)
                r14.process(r0)
            L_0x0187:
                r14.insert(r2)
                goto L_0x000d
            L_0x018c:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.Headings
                boolean r1 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r1 == 0) goto L_0x01c5
                java.lang.String r0 = "p"
                boolean r0 = r14.inButtonScope(r0)
                if (r0 == 0) goto L_0x01a8
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "p"
                r0.<init>(r1)
                r14.process(r0)
            L_0x01a8:
                org.jsoup.nodes.Element r0 = r14.currentElement()
                java.lang.String r0 = r0.nodeName()
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.Headings
                boolean r0 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r0 == 0) goto L_0x01c0
                r14.error(r12)
                r14.pop()
            L_0x01c0:
                r14.insert(r2)
                goto L_0x000d
            L_0x01c5:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartPreListing
                boolean r1 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r1 == 0) goto L_0x01ea
                java.lang.String r0 = "p"
                boolean r0 = r14.inButtonScope(r0)
                if (r0 == 0) goto L_0x01e1
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "p"
                r0.<init>(r1)
                r14.process(r0)
            L_0x01e1:
                r14.insert(r2)
                r0 = 0
                r14.framesetOk(r0)
                goto L_0x000d
            L_0x01ea:
                java.lang.String r1 = "form"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x0216
                org.jsoup.nodes.FormElement r0 = r14.getFormElement()
                if (r0 == 0) goto L_0x01fe
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x01fe:
                java.lang.String r0 = "p"
                boolean r0 = r14.inButtonScope(r0)
                if (r0 == 0) goto L_0x0210
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "p"
                r0.<init>(r1)
                r14.process(r0)
            L_0x0210:
                r0 = 1
                r14.insertForm(r2, r0)
                goto L_0x000d
            L_0x0216:
                java.lang.String r1 = "li"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x027a
                r0 = 0
                r14.framesetOk(r0)
                org.jsoup.helper.DescendableLinkedList r3 = r14.getStack()
                int r0 = r3.size()
                int r0 = r0 + -1
                r1 = r0
            L_0x022d:
                if (r1 <= 0) goto L_0x024b
                java.lang.Object r0 = r3.get(r1)
                org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
                java.lang.String r4 = r0.nodeName()
                java.lang.String r5 = "li"
                boolean r4 = r4.equals(r5)
                if (r4 == 0) goto L_0x0262
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "li"
                r0.<init>(r1)
                r14.process(r0)
            L_0x024b:
                java.lang.String r0 = "p"
                boolean r0 = r14.inButtonScope(r0)
                if (r0 == 0) goto L_0x025d
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "p"
                r0.<init>(r1)
                r14.process(r0)
            L_0x025d:
                r14.insert(r2)
                goto L_0x000d
            L_0x0262:
                boolean r4 = r14.isSpecial(r0)
                if (r4 == 0) goto L_0x0276
                java.lang.String r0 = r0.nodeName()
                java.lang.String[] r4 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartLiBreakers
                boolean r0 = org.jsoup.helper.StringUtil.in(r0, r4)
                if (r0 == 0) goto L_0x024b
            L_0x0276:
                int r0 = r1 + -1
                r1 = r0
                goto L_0x022d
            L_0x027a:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.DdDt
                boolean r1 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r1 == 0) goto L_0x02e4
                r0 = 0
                r14.framesetOk(r0)
                org.jsoup.helper.DescendableLinkedList r3 = r14.getStack()
                int r0 = r3.size()
                int r0 = r0 + -1
                r1 = r0
            L_0x0293:
                if (r1 <= 0) goto L_0x02b5
                java.lang.Object r0 = r3.get(r1)
                org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
                java.lang.String r4 = r0.nodeName()
                java.lang.String[] r5 = org.jsoup.parser.HtmlTreeBuilderState.Constants.DdDt
                boolean r4 = org.jsoup.helper.StringUtil.in(r4, r5)
                if (r4 == 0) goto L_0x02cc
                org.jsoup.parser.Token$EndTag r1 = new org.jsoup.parser.Token$EndTag
                java.lang.String r0 = r0.nodeName()
                r1.<init>(r0)
                r14.process(r1)
            L_0x02b5:
                java.lang.String r0 = "p"
                boolean r0 = r14.inButtonScope(r0)
                if (r0 == 0) goto L_0x02c7
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "p"
                r0.<init>(r1)
                r14.process(r0)
            L_0x02c7:
                r14.insert(r2)
                goto L_0x000d
            L_0x02cc:
                boolean r4 = r14.isSpecial(r0)
                if (r4 == 0) goto L_0x02e0
                java.lang.String r0 = r0.nodeName()
                java.lang.String[] r4 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartLiBreakers
                boolean r0 = org.jsoup.helper.StringUtil.in(r0, r4)
                if (r0 == 0) goto L_0x02b5
            L_0x02e0:
                int r0 = r1 + -1
                r1 = r0
                goto L_0x0293
            L_0x02e4:
                java.lang.String r1 = "plaintext"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x030a
                java.lang.String r0 = "p"
                boolean r0 = r14.inButtonScope(r0)
                if (r0 == 0) goto L_0x02fe
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "p"
                r0.<init>(r1)
                r14.process(r0)
            L_0x02fe:
                r14.insert(r2)
                org.jsoup.parser.Tokeniser r0 = r14.tokeniser
                org.jsoup.parser.TokeniserState r1 = org.jsoup.parser.TokeniserState.PLAINTEXT
                r0.transition(r1)
                goto L_0x000d
            L_0x030a:
                java.lang.String r1 = "button"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x0338
                java.lang.String r0 = "button"
                boolean r0 = r14.inButtonScope(r0)
                if (r0 == 0) goto L_0x032c
                r14.error(r12)
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "button"
                r0.<init>(r1)
                r14.process(r0)
                r14.process(r2)
                goto L_0x000d
            L_0x032c:
                r14.reconstructFormattingElements()
                r14.insert(r2)
                r0 = 0
                r14.framesetOk(r0)
                goto L_0x000d
            L_0x0338:
                java.lang.String r1 = "a"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x036f
                java.lang.String r0 = "a"
                org.jsoup.nodes.Element r0 = r14.getActiveFormattingElement(r0)
                if (r0 == 0) goto L_0x0363
                r14.error(r12)
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "a"
                r0.<init>(r1)
                r14.process(r0)
                java.lang.String r0 = "a"
                org.jsoup.nodes.Element r0 = r14.getFromStack(r0)
                if (r0 == 0) goto L_0x0363
                r14.removeFromActiveFormattingElements(r0)
                r14.removeFromStack(r0)
            L_0x0363:
                r14.reconstructFormattingElements()
                org.jsoup.nodes.Element r0 = r14.insert(r2)
                r14.pushActiveFormattingElements(r0)
                goto L_0x000d
            L_0x036f:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.Formatters
                boolean r1 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r1 == 0) goto L_0x0385
                r14.reconstructFormattingElements()
                org.jsoup.nodes.Element r0 = r14.insert(r2)
                r14.pushActiveFormattingElements(r0)
                goto L_0x000d
            L_0x0385:
                java.lang.String r1 = "nobr"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x03b1
                r14.reconstructFormattingElements()
                java.lang.String r0 = "nobr"
                boolean r0 = r14.inScope(r0)
                if (r0 == 0) goto L_0x03a8
                r14.error(r12)
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "nobr"
                r0.<init>(r1)
                r14.process(r0)
                r14.reconstructFormattingElements()
            L_0x03a8:
                org.jsoup.nodes.Element r0 = r14.insert(r2)
                r14.pushActiveFormattingElements(r0)
                goto L_0x000d
            L_0x03b1:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartApplets
                boolean r1 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r1 == 0) goto L_0x03ca
                r14.reconstructFormattingElements()
                r14.insert(r2)
                r14.insertMarkerToFormattingElements()
                r0 = 0
                r14.framesetOk(r0)
                goto L_0x000d
            L_0x03ca:
                java.lang.String r1 = "table"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x03fe
                org.jsoup.nodes.Document r0 = r14.getDocument()
                org.jsoup.nodes.Document$QuirksMode r0 = r0.quirksMode()
                org.jsoup.nodes.Document$QuirksMode r1 = org.jsoup.nodes.Document.QuirksMode.quirks
                if (r0 == r1) goto L_0x03f0
                java.lang.String r0 = "p"
                boolean r0 = r14.inButtonScope(r0)
                if (r0 == 0) goto L_0x03f0
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "p"
                r0.<init>(r1)
                r14.process(r0)
            L_0x03f0:
                r14.insert(r2)
                r0 = 0
                r14.framesetOk(r0)
                org.jsoup.parser.HtmlTreeBuilderState r0 = InTable
                r14.transition(r0)
                goto L_0x000d
            L_0x03fe:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartEmptyFormatters
                boolean r1 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r1 == 0) goto L_0x0414
                r14.reconstructFormattingElements()
                r14.insertEmpty(r2)
                r0 = 0
                r14.framesetOk(r0)
                goto L_0x000d
            L_0x0414:
                java.lang.String r1 = "input"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x0437
                r14.reconstructFormattingElements()
                org.jsoup.nodes.Element r0 = r14.insertEmpty(r2)
                java.lang.String r1 = "type"
                java.lang.String r0 = r0.attr(r1)
                java.lang.String r1 = "hidden"
                boolean r0 = r0.equalsIgnoreCase(r1)
                if (r0 != 0) goto L_0x000d
                r0 = 0
                r14.framesetOk(r0)
                goto L_0x000d
            L_0x0437:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartMedia
                boolean r1 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r1 == 0) goto L_0x0446
                r14.insertEmpty(r2)
                goto L_0x000d
            L_0x0446:
                java.lang.String r1 = "hr"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x0469
                java.lang.String r0 = "p"
                boolean r0 = r14.inButtonScope(r0)
                if (r0 == 0) goto L_0x0460
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "p"
                r0.<init>(r1)
                r14.process(r0)
            L_0x0460:
                r14.insertEmpty(r2)
                r0 = 0
                r14.framesetOk(r0)
                goto L_0x000d
            L_0x0469:
                java.lang.String r1 = "image"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x048a
                java.lang.String r0 = "svg"
                org.jsoup.nodes.Element r0 = r14.getFromStack(r0)
                if (r0 != 0) goto L_0x0485
                java.lang.String r0 = "img"
                org.jsoup.parser.Token$Tag r0 = r2.name(r0)
                boolean r0 = r14.process(r0)
                goto L_0x000e
            L_0x0485:
                r14.insert(r2)
                goto L_0x000d
            L_0x048a:
                java.lang.String r1 = "isindex"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x0553
                r14.error(r12)
                org.jsoup.nodes.FormElement r0 = r14.getFormElement()
                if (r0 == 0) goto L_0x049e
                r0 = 0
                goto L_0x000e
            L_0x049e:
                org.jsoup.parser.Tokeniser r0 = r14.tokeniser
                r0.acknowledgeSelfClosingFlag()
                org.jsoup.parser.Token$StartTag r0 = new org.jsoup.parser.Token$StartTag
                java.lang.String r1 = "form"
                r0.<init>(r1)
                r14.process(r0)
                org.jsoup.nodes.Attributes r0 = r2.attributes
                java.lang.String r1 = "action"
                boolean r0 = r0.hasKey(r1)
                if (r0 == 0) goto L_0x04c8
                org.jsoup.nodes.FormElement r0 = r14.getFormElement()
                java.lang.String r1 = "action"
                org.jsoup.nodes.Attributes r3 = r2.attributes
                java.lang.String r4 = "action"
                java.lang.String r3 = r3.get(r4)
                r0.attr(r1, r3)
            L_0x04c8:
                org.jsoup.parser.Token$StartTag r0 = new org.jsoup.parser.Token$StartTag
                java.lang.String r1 = "hr"
                r0.<init>(r1)
                r14.process(r0)
                org.jsoup.parser.Token$StartTag r0 = new org.jsoup.parser.Token$StartTag
                java.lang.String r1 = "label"
                r0.<init>(r1)
                r14.process(r0)
                org.jsoup.nodes.Attributes r0 = r2.attributes
                java.lang.String r1 = "prompt"
                boolean r0 = r0.hasKey(r1)
                if (r0 == 0) goto L_0x051f
                org.jsoup.nodes.Attributes r0 = r2.attributes
                java.lang.String r1 = "prompt"
                java.lang.String r0 = r0.get(r1)
            L_0x04ee:
                org.jsoup.parser.Token$Character r1 = new org.jsoup.parser.Token$Character
                r1.<init>(r0)
                r14.process(r1)
                org.jsoup.nodes.Attributes r1 = new org.jsoup.nodes.Attributes
                r1.<init>()
                org.jsoup.nodes.Attributes r0 = r2.attributes
                java.util.Iterator r2 = r0.iterator()
            L_0x0501:
                boolean r0 = r2.hasNext()
                if (r0 == 0) goto L_0x0522
                java.lang.Object r0 = r2.next()
                org.jsoup.nodes.Attribute r0 = (org.jsoup.nodes.Attribute) r0
                java.lang.String r3 = r0.getKey()
                java.lang.String[] r4 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartInputAttribs
                boolean r3 = org.jsoup.helper.StringUtil.in(r3, r4)
                if (r3 != 0) goto L_0x0501
                r1.put(r0)
                goto L_0x0501
            L_0x051f:
                java.lang.String r0 = "This is a searchable index. Enter search keywords: "
                goto L_0x04ee
            L_0x0522:
                java.lang.String r0 = "name"
                java.lang.String r2 = "isindex"
                r1.put(r0, r2)
                org.jsoup.parser.Token$StartTag r0 = new org.jsoup.parser.Token$StartTag
                java.lang.String r2 = "input"
                r0.<init>(r2, r1)
                r14.process(r0)
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "label"
                r0.<init>(r1)
                r14.process(r0)
                org.jsoup.parser.Token$StartTag r0 = new org.jsoup.parser.Token$StartTag
                java.lang.String r1 = "hr"
                r0.<init>(r1)
                r14.process(r0)
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "form"
                r0.<init>(r1)
                r14.process(r0)
                goto L_0x000d
            L_0x0553:
                java.lang.String r1 = "textarea"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x0573
                r14.insert(r2)
                org.jsoup.parser.Tokeniser r0 = r14.tokeniser
                org.jsoup.parser.TokeniserState r1 = org.jsoup.parser.TokeniserState.Rcdata
                r0.transition(r1)
                r14.markInsertionMode()
                r0 = 0
                r14.framesetOk(r0)
                org.jsoup.parser.HtmlTreeBuilderState r0 = Text
                r14.transition(r0)
                goto L_0x000d
            L_0x0573:
                java.lang.String r1 = "xmp"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x0599
                java.lang.String r0 = "p"
                boolean r0 = r14.inButtonScope(r0)
                if (r0 == 0) goto L_0x058d
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "p"
                r0.<init>(r1)
                r14.process(r0)
            L_0x058d:
                r14.reconstructFormattingElements()
                r0 = 0
                r14.framesetOk(r0)
                org.jsoup.parser.HtmlTreeBuilderState.handleRawtext(r2, r14)
                goto L_0x000d
            L_0x0599:
                java.lang.String r1 = "iframe"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x05aa
                r0 = 0
                r14.framesetOk(r0)
                org.jsoup.parser.HtmlTreeBuilderState.handleRawtext(r2, r14)
                goto L_0x000d
            L_0x05aa:
                java.lang.String r1 = "noembed"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x05b7
                org.jsoup.parser.HtmlTreeBuilderState.handleRawtext(r2, r14)
                goto L_0x000d
            L_0x05b7:
                java.lang.String r1 = "select"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x0603
                r14.reconstructFormattingElements()
                r14.insert(r2)
                r0 = 0
                r14.framesetOk(r0)
                org.jsoup.parser.HtmlTreeBuilderState r0 = r14.state()
                org.jsoup.parser.HtmlTreeBuilderState r1 = InTable
                boolean r1 = r0.equals(r1)
                if (r1 != 0) goto L_0x05f5
                org.jsoup.parser.HtmlTreeBuilderState r1 = InCaption
                boolean r1 = r0.equals(r1)
                if (r1 != 0) goto L_0x05f5
                org.jsoup.parser.HtmlTreeBuilderState r1 = InTableBody
                boolean r1 = r0.equals(r1)
                if (r1 != 0) goto L_0x05f5
                org.jsoup.parser.HtmlTreeBuilderState r1 = InRow
                boolean r1 = r0.equals(r1)
                if (r1 != 0) goto L_0x05f5
                org.jsoup.parser.HtmlTreeBuilderState r1 = InCell
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x05fc
            L_0x05f5:
                org.jsoup.parser.HtmlTreeBuilderState r0 = InSelectInTable
                r14.transition(r0)
                goto L_0x000d
            L_0x05fc:
                org.jsoup.parser.HtmlTreeBuilderState r0 = InSelect
                r14.transition(r0)
                goto L_0x000d
            L_0x0603:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartOptions
                boolean r1 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r1 == 0) goto L_0x062f
                org.jsoup.nodes.Element r0 = r14.currentElement()
                java.lang.String r0 = r0.nodeName()
                java.lang.String r1 = "option"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0627
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r1 = "option"
                r0.<init>(r1)
                r14.process(r0)
            L_0x0627:
                r14.reconstructFormattingElements()
                r14.insert(r2)
                goto L_0x000d
            L_0x062f:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartRuby
                boolean r1 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r1 == 0) goto L_0x0661
                java.lang.String r0 = "ruby"
                boolean r0 = r14.inScope(r0)
                if (r0 == 0) goto L_0x000d
                r14.generateImpliedEndTags()
                org.jsoup.nodes.Element r0 = r14.currentElement()
                java.lang.String r0 = r0.nodeName()
                java.lang.String r1 = "ruby"
                boolean r0 = r0.equals(r1)
                if (r0 != 0) goto L_0x065c
                r14.error(r12)
                java.lang.String r0 = "ruby"
                r14.popStackToBefore(r0)
            L_0x065c:
                r14.insert(r2)
                goto L_0x000d
            L_0x0661:
                java.lang.String r1 = "math"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x0676
                r14.reconstructFormattingElements()
                r14.insert(r2)
                org.jsoup.parser.Tokeniser r0 = r14.tokeniser
                r0.acknowledgeSelfClosingFlag()
                goto L_0x000d
            L_0x0676:
                java.lang.String r1 = "svg"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x068b
                r14.reconstructFormattingElements()
                r14.insert(r2)
                org.jsoup.parser.Tokeniser r0 = r14.tokeniser
                r0.acknowledgeSelfClosingFlag()
                goto L_0x000d
            L_0x068b:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartDrop
                boolean r0 = org.jsoup.helper.StringUtil.in(r0, r1)
                if (r0 == 0) goto L_0x069b
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x069b:
                r14.reconstructFormattingElements()
                r14.insert(r2)
                goto L_0x000d
            L_0x06a3:
                org.jsoup.parser.Token$EndTag r0 = r13.asEndTag()
                java.lang.String r7 = r0.name()
                java.lang.String r1 = "body"
                boolean r1 = r7.equals(r1)
                if (r1 == 0) goto L_0x06c8
                java.lang.String r0 = "body"
                boolean r0 = r14.inScope(r0)
                if (r0 != 0) goto L_0x06c1
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x06c1:
                org.jsoup.parser.HtmlTreeBuilderState r0 = AfterBody
                r14.transition(r0)
                goto L_0x000d
            L_0x06c8:
                java.lang.String r1 = "html"
                boolean r1 = r7.equals(r1)
                if (r1 == 0) goto L_0x06e3
                org.jsoup.parser.Token$EndTag r1 = new org.jsoup.parser.Token$EndTag
                java.lang.String r2 = "body"
                r1.<init>(r2)
                boolean r1 = r14.process(r1)
                if (r1 == 0) goto L_0x000d
                boolean r0 = r14.process(r0)
                goto L_0x000e
            L_0x06e3:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyEndClosers
                boolean r1 = org.jsoup.helper.StringUtil.in(r7, r1)
                if (r1 == 0) goto L_0x0712
                boolean r0 = r14.inScope(r7)
                if (r0 != 0) goto L_0x06f9
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x06f9:
                r14.generateImpliedEndTags()
                org.jsoup.nodes.Element r0 = r14.currentElement()
                java.lang.String r0 = r0.nodeName()
                boolean r0 = r0.equals(r7)
                if (r0 != 0) goto L_0x070d
                r14.error(r12)
            L_0x070d:
                r14.popStackToClose(r7)
                goto L_0x000d
            L_0x0712:
                java.lang.String r1 = "form"
                boolean r1 = r7.equals(r1)
                if (r1 == 0) goto L_0x0749
                org.jsoup.nodes.FormElement r0 = r14.getFormElement()
                r1 = 0
                r14.setFormElement(r1)
                if (r0 == 0) goto L_0x072a
                boolean r1 = r14.inScope(r7)
                if (r1 != 0) goto L_0x0730
            L_0x072a:
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x0730:
                r14.generateImpliedEndTags()
                org.jsoup.nodes.Element r1 = r14.currentElement()
                java.lang.String r1 = r1.nodeName()
                boolean r1 = r1.equals(r7)
                if (r1 != 0) goto L_0x0744
                r14.error(r12)
            L_0x0744:
                r14.removeFromStack(r0)
                goto L_0x000d
            L_0x0749:
                java.lang.String r1 = "p"
                boolean r1 = r7.equals(r1)
                if (r1 == 0) goto L_0x0781
                boolean r1 = r14.inButtonScope(r7)
                if (r1 != 0) goto L_0x0768
                r14.error(r12)
                org.jsoup.parser.Token$StartTag r1 = new org.jsoup.parser.Token$StartTag
                r1.<init>(r7)
                r14.process(r1)
                boolean r0 = r14.process(r0)
                goto L_0x000e
            L_0x0768:
                r14.generateImpliedEndTags(r7)
                org.jsoup.nodes.Element r0 = r14.currentElement()
                java.lang.String r0 = r0.nodeName()
                boolean r0 = r0.equals(r7)
                if (r0 != 0) goto L_0x077c
                r14.error(r12)
            L_0x077c:
                r14.popStackToClose(r7)
                goto L_0x000d
            L_0x0781:
                java.lang.String r0 = "li"
                boolean r0 = r7.equals(r0)
                if (r0 == 0) goto L_0x07ae
                boolean r0 = r14.inListItemScope(r7)
                if (r0 != 0) goto L_0x0795
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x0795:
                r14.generateImpliedEndTags(r7)
                org.jsoup.nodes.Element r0 = r14.currentElement()
                java.lang.String r0 = r0.nodeName()
                boolean r0 = r0.equals(r7)
                if (r0 != 0) goto L_0x07a9
                r14.error(r12)
            L_0x07a9:
                r14.popStackToClose(r7)
                goto L_0x000d
            L_0x07ae:
                java.lang.String[] r0 = org.jsoup.parser.HtmlTreeBuilderState.Constants.DdDt
                boolean r0 = org.jsoup.helper.StringUtil.in(r7, r0)
                if (r0 == 0) goto L_0x07dd
                boolean r0 = r14.inScope(r7)
                if (r0 != 0) goto L_0x07c4
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x07c4:
                r14.generateImpliedEndTags(r7)
                org.jsoup.nodes.Element r0 = r14.currentElement()
                java.lang.String r0 = r0.nodeName()
                boolean r0 = r0.equals(r7)
                if (r0 != 0) goto L_0x07d8
                r14.error(r12)
            L_0x07d8:
                r14.popStackToClose(r7)
                goto L_0x000d
            L_0x07dd:
                java.lang.String[] r0 = org.jsoup.parser.HtmlTreeBuilderState.Constants.Headings
                boolean r0 = org.jsoup.helper.StringUtil.in(r7, r0)
                if (r0 == 0) goto L_0x0814
                java.lang.String[] r0 = org.jsoup.parser.HtmlTreeBuilderState.Constants.Headings
                boolean r0 = r14.inScope(r0)
                if (r0 != 0) goto L_0x07f7
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x07f7:
                r14.generateImpliedEndTags(r7)
                org.jsoup.nodes.Element r0 = r14.currentElement()
                java.lang.String r0 = r0.nodeName()
                boolean r0 = r0.equals(r7)
                if (r0 != 0) goto L_0x080b
                r14.error(r12)
            L_0x080b:
                java.lang.String[] r0 = org.jsoup.parser.HtmlTreeBuilderState.Constants.Headings
                r14.popStackToClose(r0)
                goto L_0x000d
            L_0x0814:
                java.lang.String r0 = "sarcasm"
                boolean r0 = r7.equals(r0)
                if (r0 == 0) goto L_0x0822
                boolean r0 = r12.anyOtherEndTag(r13, r14)
                goto L_0x000e
            L_0x0822:
                java.lang.String[] r0 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyEndAdoptionFormatters
                boolean r0 = org.jsoup.helper.StringUtil.in(r7, r0)
                if (r0 == 0) goto L_0x0962
                r0 = 0
                r6 = r0
            L_0x082e:
                r0 = 8
                if (r6 >= r0) goto L_0x000d
                org.jsoup.nodes.Element r8 = r14.getActiveFormattingElement(r7)
                if (r8 != 0) goto L_0x083e
                boolean r0 = r12.anyOtherEndTag(r13, r14)
                goto L_0x000e
            L_0x083e:
                boolean r0 = r14.onStack(r8)
                if (r0 != 0) goto L_0x084d
                r14.error(r12)
                r14.removeFromActiveFormattingElements(r8)
                r0 = 1
                goto L_0x000e
            L_0x084d:
                java.lang.String r0 = r8.nodeName()
                boolean r0 = r14.inScope(r0)
                if (r0 != 0) goto L_0x085d
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x085d:
                org.jsoup.nodes.Element r0 = r14.currentElement()
                if (r0 == r8) goto L_0x0866
                r14.error(r12)
            L_0x0866:
                r5 = 0
                r2 = 0
                r1 = 0
                org.jsoup.helper.DescendableLinkedList r4 = r14.getStack()
                int r9 = r4.size()
                r0 = 0
                r3 = r0
            L_0x0873:
                if (r3 >= r9) goto L_0x089c
                r0 = 64
                if (r3 >= r0) goto L_0x089c
                java.lang.Object r0 = r4.get(r3)
                org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
                if (r0 != r8) goto L_0x0893
                int r0 = r3 + -1
                java.lang.Object r0 = r4.get(r0)
                org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
                r1 = 1
                r11 = r1
                r1 = r0
                r0 = r11
            L_0x088d:
                int r2 = r3 + 1
                r3 = r2
                r2 = r1
                r1 = r0
                goto L_0x0873
            L_0x0893:
                if (r1 == 0) goto L_0x09ba
                boolean r10 = r14.isSpecial(r0)
                if (r10 == 0) goto L_0x09ba
                r5 = r0
            L_0x089c:
                if (r5 != 0) goto L_0x08ab
                java.lang.String r0 = r8.nodeName()
                r14.popStackToClose(r0)
                r14.removeFromActiveFormattingElements(r8)
                r0 = 1
                goto L_0x000e
            L_0x08ab:
                r0 = 0
                r4 = r0
                r1 = r5
                r0 = r5
            L_0x08af:
                r3 = 3
                if (r4 >= r3) goto L_0x08cf
                boolean r3 = r14.onStack(r0)
                if (r3 == 0) goto L_0x08bc
                org.jsoup.nodes.Element r0 = r14.aboveOnStack(r0)
            L_0x08bc:
                boolean r3 = r14.isInActiveFormattingElements(r0)
                if (r3 != 0) goto L_0x08cd
                r14.removeFromStack(r0)
                r3 = r0
                r0 = r1
            L_0x08c7:
                int r1 = r4 + 1
                r4 = r1
                r1 = r0
                r0 = r3
                goto L_0x08af
            L_0x08cd:
                if (r0 != r8) goto L_0x091d
            L_0x08cf:
                java.lang.String r0 = r2.nodeName()
                java.lang.String[] r3 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyEndTableFosters
                boolean r0 = org.jsoup.helper.StringUtil.in(r0, r3)
                if (r0 == 0) goto L_0x0944
                org.jsoup.nodes.Element r0 = r1.parent()
                if (r0 == 0) goto L_0x08e6
                r1.remove()
            L_0x08e6:
                r14.insertInFosterParent(r1)
            L_0x08e9:
                org.jsoup.nodes.Element r2 = new org.jsoup.nodes.Element
                org.jsoup.parser.Tag r0 = r8.tag()
                java.lang.String r1 = r14.getBaseUri()
                r2.<init>(r0, r1)
                org.jsoup.nodes.Attributes r0 = r2.attributes()
                org.jsoup.nodes.Attributes r1 = r8.attributes()
                r0.addAll(r1)
                java.util.List r0 = r5.childNodes()
                int r1 = r5.childNodeSize()
                org.jsoup.nodes.Node[] r1 = new org.jsoup.nodes.Node[r1]
                java.lang.Object[] r0 = r0.toArray(r1)
                org.jsoup.nodes.Node[] r0 = (org.jsoup.nodes.Node[]) r0
                int r3 = r0.length
                r1 = 0
            L_0x0913:
                if (r1 >= r3) goto L_0x0951
                r4 = r0[r1]
                r2.appendChild(r4)
                int r1 = r1 + 1
                goto L_0x0913
            L_0x091d:
                org.jsoup.nodes.Element r3 = new org.jsoup.nodes.Element
                java.lang.String r9 = r0.nodeName()
                org.jsoup.parser.Tag r9 = org.jsoup.parser.Tag.valueOf(r9)
                java.lang.String r10 = r14.getBaseUri()
                r3.<init>(r9, r10)
                r14.replaceActiveFormattingElement(r0, r3)
                r14.replaceOnStack(r0, r3)
                if (r1 != r5) goto L_0x0936
            L_0x0936:
                org.jsoup.nodes.Element r0 = r1.parent()
                if (r0 == 0) goto L_0x093f
                r1.remove()
            L_0x093f:
                r3.appendChild(r1)
                r0 = r3
                goto L_0x08c7
            L_0x0944:
                org.jsoup.nodes.Element r0 = r1.parent()
                if (r0 == 0) goto L_0x094d
                r1.remove()
            L_0x094d:
                r2.appendChild(r1)
                goto L_0x08e9
            L_0x0951:
                r5.appendChild(r2)
                r14.removeFromActiveFormattingElements(r8)
                r14.removeFromStack(r8)
                r14.insertOnStackAfter(r5, r2)
                int r0 = r6 + 1
                r6 = r0
                goto L_0x082e
            L_0x0962:
                java.lang.String[] r0 = org.jsoup.parser.HtmlTreeBuilderState.Constants.InBodyStartApplets
                boolean r0 = org.jsoup.helper.StringUtil.in(r7, r0)
                if (r0 == 0) goto L_0x099c
                java.lang.String r0 = "name"
                boolean r0 = r14.inScope(r0)
                if (r0 != 0) goto L_0x000d
                boolean r0 = r14.inScope(r7)
                if (r0 != 0) goto L_0x0980
                r14.error(r12)
                r0 = 0
                goto L_0x000e
            L_0x0980:
                r14.generateImpliedEndTags()
                org.jsoup.nodes.Element r0 = r14.currentElement()
                java.lang.String r0 = r0.nodeName()
                boolean r0 = r0.equals(r7)
                if (r0 != 0) goto L_0x0994
                r14.error(r12)
            L_0x0994:
                r14.popStackToClose(r7)
                r14.clearFormattingElementsToLastMarker()
                goto L_0x000d
            L_0x099c:
                java.lang.String r0 = "br"
                boolean r0 = r7.equals(r0)
                if (r0 == 0) goto L_0x09b4
                r14.error(r12)
                org.jsoup.parser.Token$StartTag r0 = new org.jsoup.parser.Token$StartTag
                java.lang.String r1 = "br"
                r0.<init>(r1)
                r14.process(r0)
                r0 = 0
                goto L_0x000e
            L_0x09b4:
                boolean r0 = r12.anyOtherEndTag(r13, r14)
                goto L_0x000e
            L_0x09ba:
                r0 = r1
                r1 = r2
                goto L_0x088d
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass7.process(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        /* access modifiers changed from: 0000 */
        public boolean anyOtherEndTag(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            Element element;
            String name = token.asEndTag().name();
            Iterator descendingIterator = htmlTreeBuilder.getStack().descendingIterator();
            do {
                if (descendingIterator.hasNext()) {
                    element = (Element) descendingIterator.next();
                    if (element.nodeName().equals(name)) {
                        htmlTreeBuilder.generateImpliedEndTags(name);
                        if (!name.equals(htmlTreeBuilder.currentElement().nodeName())) {
                            htmlTreeBuilder.error(this);
                        }
                        htmlTreeBuilder.popStackToClose(name);
                    }
                }
                return true;
            } while (!htmlTreeBuilder.isSpecial(element));
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    Text {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter()) {
                htmlTreeBuilder.insert(token.asCharacter());
            } else if (token.isEOF()) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
                return htmlTreeBuilder.process(token);
            } else if (token.isEndTag()) {
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
            }
            return true;
        }
    },
    InTable {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter()) {
                htmlTreeBuilder.newPendingTableCharacters();
                htmlTreeBuilder.markInsertionMode();
                htmlTreeBuilder.transition(InTableText);
                return htmlTreeBuilder.process(token);
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag()) {
                StartTag asStartTag = token.asStartTag();
                String name = asStartTag.name();
                if (name.equals("caption")) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.insertMarkerToFormattingElements();
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InCaption);
                    return true;
                } else if (name.equals("colgroup")) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InColumnGroup);
                    return true;
                } else if (name.equals("col")) {
                    htmlTreeBuilder.process(new StartTag("colgroup"));
                    return htmlTreeBuilder.process(token);
                } else {
                    if (StringUtil.in(name, "tbody", "tfoot", "thead")) {
                        htmlTreeBuilder.clearStackToTableContext();
                        htmlTreeBuilder.insert(asStartTag);
                        htmlTreeBuilder.transition(InTableBody);
                        return true;
                    }
                    if (StringUtil.in(name, Config.TEST_DEVICE_ID, "th", "tr")) {
                        htmlTreeBuilder.process(new StartTag("tbody"));
                        return htmlTreeBuilder.process(token);
                    } else if (name.equals("table")) {
                        htmlTreeBuilder.error(this);
                        if (htmlTreeBuilder.process(new EndTag("table"))) {
                            return htmlTreeBuilder.process(token);
                        }
                        return true;
                    } else {
                        if (StringUtil.in(name, "style", "script")) {
                            return htmlTreeBuilder.process(token, InHead);
                        }
                        if (name.equals(Config.INPUT_PART)) {
                            if (!asStartTag.attributes.get("type").equalsIgnoreCase("hidden")) {
                                return anythingElse(token, htmlTreeBuilder);
                            }
                            htmlTreeBuilder.insertEmpty(asStartTag);
                            return true;
                        } else if (!name.equals("form")) {
                            return anythingElse(token, htmlTreeBuilder);
                        } else {
                            htmlTreeBuilder.error(this);
                            if (htmlTreeBuilder.getFormElement() != null) {
                                return false;
                            }
                            htmlTreeBuilder.insertForm(asStartTag, false);
                            return true;
                        }
                    }
                }
            } else if (token.isEndTag()) {
                String name2 = token.asEndTag().name();
                if (!name2.equals("table")) {
                    if (!StringUtil.in(name2, "body", "caption", "col", "colgroup", "html", "tbody", Config.TEST_DEVICE_ID, "tfoot", "th", "thead", "tr")) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                } else if (!htmlTreeBuilder.inTableScope(name2)) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else {
                    htmlTreeBuilder.popStackToClose("table");
                    htmlTreeBuilder.resetInsertionMode();
                    return true;
                }
            } else if (!token.isEOF()) {
                return anythingElse(token, htmlTreeBuilder);
            } else {
                if (!htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                    return true;
                }
                htmlTreeBuilder.error(this);
                return true;
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            if (!StringUtil.in(htmlTreeBuilder.currentElement().nodeName(), "table", "tbody", "tfoot", "thead", "tr")) {
                return htmlTreeBuilder.process(token, InBody);
            }
            htmlTreeBuilder.setFosterInserts(true);
            boolean process = htmlTreeBuilder.process(token, InBody);
            htmlTreeBuilder.setFosterInserts(false);
            return process;
        }
    },
    InTableText {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 5:
                    Character asCharacter = token.asCharacter();
                    if (asCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.getPendingTableCharacters().add(asCharacter);
                    return true;
                default:
                    if (htmlTreeBuilder.getPendingTableCharacters().size() > 0) {
                        for (Character character : htmlTreeBuilder.getPendingTableCharacters()) {
                            if (!HtmlTreeBuilderState.isWhitespace(character)) {
                                htmlTreeBuilder.error(this);
                                if (StringUtil.in(htmlTreeBuilder.currentElement().nodeName(), "table", "tbody", "tfoot", "thead", "tr")) {
                                    htmlTreeBuilder.setFosterInserts(true);
                                    htmlTreeBuilder.process(character, InBody);
                                    htmlTreeBuilder.setFosterInserts(false);
                                } else {
                                    htmlTreeBuilder.process(character, InBody);
                                }
                            } else {
                                htmlTreeBuilder.insert(character);
                            }
                        }
                        htmlTreeBuilder.newPendingTableCharacters();
                    }
                    htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
                    return htmlTreeBuilder.process(token);
            }
        }
    },
    InCaption {
        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0091, code lost:
            if (org.jsoup.helper.StringUtil.in(r10.asStartTag().name(), "caption", "col", "colgroup", "tbody", com.baidu.mobstat.Config.TEST_DEVICE_ID, "tfoot", "th", "thead", "tr") == false) goto L_0x0093;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(org.jsoup.parser.Token r10, org.jsoup.parser.HtmlTreeBuilder r11) {
            /*
                r9 = this;
                r8 = 4
                r7 = 3
                r6 = 2
                r1 = 1
                r0 = 0
                boolean r2 = r10.isEndTag()
                if (r2 == 0) goto L_0x0052
                org.jsoup.parser.Token$EndTag r2 = r10.asEndTag()
                java.lang.String r2 = r2.name()
                java.lang.String r3 = "caption"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0052
                org.jsoup.parser.Token$EndTag r2 = r10.asEndTag()
                java.lang.String r2 = r2.name()
                boolean r2 = r11.inTableScope(r2)
                if (r2 != 0) goto L_0x002d
                r11.error(r9)
            L_0x002c:
                return r0
            L_0x002d:
                r11.generateImpliedEndTags()
                org.jsoup.nodes.Element r0 = r11.currentElement()
                java.lang.String r0 = r0.nodeName()
                java.lang.String r2 = "caption"
                boolean r0 = r0.equals(r2)
                if (r0 != 0) goto L_0x0043
                r11.error(r9)
            L_0x0043:
                java.lang.String r0 = "caption"
                r11.popStackToClose(r0)
                r11.clearFormattingElementsToLastMarker()
                org.jsoup.parser.HtmlTreeBuilderState r0 = InTable
                r11.transition(r0)
            L_0x0050:
                r0 = r1
                goto L_0x002c
            L_0x0052:
                boolean r2 = r10.isStartTag()
                if (r2 == 0) goto L_0x0093
                org.jsoup.parser.Token$StartTag r2 = r10.asStartTag()
                java.lang.String r2 = r2.name()
                r3 = 9
                java.lang.String[] r3 = new java.lang.String[r3]
                java.lang.String r4 = "caption"
                r3[r0] = r4
                java.lang.String r4 = "col"
                r3[r1] = r4
                java.lang.String r4 = "colgroup"
                r3[r6] = r4
                java.lang.String r4 = "tbody"
                r3[r7] = r4
                java.lang.String r4 = "td"
                r3[r8] = r4
                r4 = 5
                java.lang.String r5 = "tfoot"
                r3[r4] = r5
                r4 = 6
                java.lang.String r5 = "th"
                r3[r4] = r5
                r4 = 7
                java.lang.String r5 = "thead"
                r3[r4] = r5
                r4 = 8
                java.lang.String r5 = "tr"
                r3[r4] = r5
                boolean r2 = org.jsoup.helper.StringUtil.in(r2, r3)
                if (r2 != 0) goto L_0x00a9
            L_0x0093:
                boolean r2 = r10.isEndTag()
                if (r2 == 0) goto L_0x00bf
                org.jsoup.parser.Token$EndTag r2 = r10.asEndTag()
                java.lang.String r2 = r2.name()
                java.lang.String r3 = "table"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x00bf
            L_0x00a9:
                r11.error(r9)
                org.jsoup.parser.Token$EndTag r0 = new org.jsoup.parser.Token$EndTag
                java.lang.String r2 = "caption"
                r0.<init>(r2)
                boolean r0 = r11.process(r0)
                if (r0 == 0) goto L_0x0050
                boolean r0 = r11.process(r10)
                goto L_0x002c
            L_0x00bf:
                boolean r2 = r10.isEndTag()
                if (r2 == 0) goto L_0x010b
                org.jsoup.parser.Token$EndTag r2 = r10.asEndTag()
                java.lang.String r2 = r2.name()
                r3 = 10
                java.lang.String[] r3 = new java.lang.String[r3]
                java.lang.String r4 = "body"
                r3[r0] = r4
                java.lang.String r4 = "col"
                r3[r1] = r4
                java.lang.String r1 = "colgroup"
                r3[r6] = r1
                java.lang.String r1 = "html"
                r3[r7] = r1
                java.lang.String r1 = "tbody"
                r3[r8] = r1
                r1 = 5
                java.lang.String r4 = "td"
                r3[r1] = r4
                r1 = 6
                java.lang.String r4 = "tfoot"
                r3[r1] = r4
                r1 = 7
                java.lang.String r4 = "th"
                r3[r1] = r4
                r1 = 8
                java.lang.String r4 = "thead"
                r3[r1] = r4
                r1 = 9
                java.lang.String r4 = "tr"
                r3[r1] = r4
                boolean r1 = org.jsoup.helper.StringUtil.in(r2, r3)
                if (r1 == 0) goto L_0x010b
                r11.error(r9)
                goto L_0x002c
            L_0x010b:
                org.jsoup.parser.HtmlTreeBuilderState r0 = InBody
                boolean r0 = r11.process(r10, r0)
                goto L_0x002c
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass11.process(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }
    },
    InColumnGroup {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            }
            switch (AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insert(token.asComment());
                    return true;
                case 2:
                    htmlTreeBuilder.error(this);
                    return true;
                case 3:
                    StartTag asStartTag = token.asStartTag();
                    String name = asStartTag.name();
                    if (name.equals("html")) {
                        return htmlTreeBuilder.process(token, InBody);
                    }
                    if (!name.equals("col")) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    htmlTreeBuilder.insertEmpty(asStartTag);
                    return true;
                case 4:
                    if (!token.asEndTag().name().equals("colgroup")) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    if (htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTable);
                    return true;
                case 6:
                    if (!htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    return true;
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, TreeBuilder treeBuilder) {
            if (treeBuilder.process(new EndTag("colgroup"))) {
                return treeBuilder.process(token);
            }
            return true;
        }
    },
    InTableBody {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 3:
                    StartTag asStartTag = token.asStartTag();
                    String name = asStartTag.name();
                    if (name.equals("tr")) {
                        htmlTreeBuilder.clearStackToTableBodyContext();
                        htmlTreeBuilder.insert(asStartTag);
                        htmlTreeBuilder.transition(InRow);
                        break;
                    } else {
                        if (StringUtil.in(name, "th", Config.TEST_DEVICE_ID)) {
                            htmlTreeBuilder.error(this);
                            htmlTreeBuilder.process(new StartTag("tr"));
                            return htmlTreeBuilder.process(asStartTag);
                        }
                        if (StringUtil.in(name, "caption", "col", "colgroup", "tbody", "tfoot", "thead")) {
                            return exitTableBody(token, htmlTreeBuilder);
                        }
                        return anythingElse(token, htmlTreeBuilder);
                    }
                case 4:
                    String name2 = token.asEndTag().name();
                    if (StringUtil.in(name2, "tbody", "tfoot", "thead")) {
                        if (htmlTreeBuilder.inTableScope(name2)) {
                            htmlTreeBuilder.clearStackToTableBodyContext();
                            htmlTreeBuilder.pop();
                            htmlTreeBuilder.transition(InTable);
                            break;
                        } else {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                    } else if (name2.equals("table")) {
                        return exitTableBody(token, htmlTreeBuilder);
                    } else {
                        if (!StringUtil.in(name2, "body", "caption", "col", "colgroup", "html", Config.TEST_DEVICE_ID, "th", "tr")) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
            return true;
        }

        private boolean exitTableBody(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (htmlTreeBuilder.inTableScope("tbody") || htmlTreeBuilder.inTableScope("thead") || htmlTreeBuilder.inScope("tfoot")) {
                htmlTreeBuilder.clearStackToTableBodyContext();
                htmlTreeBuilder.process(new EndTag(htmlTreeBuilder.currentElement().nodeName()));
                return htmlTreeBuilder.process(token);
            }
            htmlTreeBuilder.error(this);
            return false;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InTable);
        }
    },
    InRow {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag()) {
                StartTag asStartTag = token.asStartTag();
                String name = asStartTag.name();
                if (StringUtil.in(name, "th", Config.TEST_DEVICE_ID)) {
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InCell);
                    htmlTreeBuilder.insertMarkerToFormattingElements();
                } else {
                    if (StringUtil.in(name, "caption", "col", "colgroup", "tbody", "tfoot", "thead", "tr")) {
                        return handleMissingTr(token, htmlTreeBuilder);
                    }
                    return anythingElse(token, htmlTreeBuilder);
                }
            } else if (!token.isEndTag()) {
                return anythingElse(token, htmlTreeBuilder);
            } else {
                String name2 = token.asEndTag().name();
                if (name2.equals("tr")) {
                    if (!htmlTreeBuilder.inTableScope(name2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTableBody);
                } else if (name2.equals("table")) {
                    return handleMissingTr(token, htmlTreeBuilder);
                } else {
                    if (!StringUtil.in(name2, "tbody", "tfoot", "thead")) {
                        if (!StringUtil.in(name2, "body", "caption", "col", "colgroup", "html", Config.TEST_DEVICE_ID, "th")) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        htmlTreeBuilder.error(this);
                        return false;
                    } else if (!htmlTreeBuilder.inTableScope(name2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    } else {
                        htmlTreeBuilder.process(new EndTag("tr"));
                        return htmlTreeBuilder.process(token);
                    }
                }
            }
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InTable);
        }

        private boolean handleMissingTr(Token token, TreeBuilder treeBuilder) {
            if (treeBuilder.process(new EndTag("tr"))) {
                return treeBuilder.process(token);
            }
            return false;
        }
    },
    InCell {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isEndTag()) {
                String name = token.asEndTag().name();
                if (!StringUtil.in(name, Config.TEST_DEVICE_ID, "th")) {
                    if (StringUtil.in(name, "body", "caption", "col", "colgroup", "html")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (!StringUtil.in(name, "table", "tbody", "tfoot", "thead", "tr")) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    if (!htmlTreeBuilder.inTableScope(name)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    closeCell(htmlTreeBuilder);
                    return htmlTreeBuilder.process(token);
                } else if (!htmlTreeBuilder.inTableScope(name)) {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.transition(InRow);
                    return false;
                } else {
                    htmlTreeBuilder.generateImpliedEndTags();
                    if (!htmlTreeBuilder.currentElement().nodeName().equals(name)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(name);
                    htmlTreeBuilder.clearFormattingElementsToLastMarker();
                    htmlTreeBuilder.transition(InRow);
                    return true;
                }
            } else {
                if (token.isStartTag()) {
                    if (StringUtil.in(token.asStartTag().name(), "caption", "col", "colgroup", "tbody", Config.TEST_DEVICE_ID, "tfoot", "th", "thead", "tr")) {
                        if (htmlTreeBuilder.inTableScope(Config.TEST_DEVICE_ID) || htmlTreeBuilder.inTableScope("th")) {
                            closeCell(htmlTreeBuilder);
                            return htmlTreeBuilder.process(token);
                        }
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                }
                return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InBody);
        }

        private void closeCell(HtmlTreeBuilder htmlTreeBuilder) {
            if (htmlTreeBuilder.inTableScope(Config.TEST_DEVICE_ID)) {
                htmlTreeBuilder.process(new EndTag(Config.TEST_DEVICE_ID));
            } else {
                htmlTreeBuilder.process(new EndTag("th"));
            }
        }
    },
    InSelect {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insert(token.asComment());
                    break;
                case 2:
                    htmlTreeBuilder.error(this);
                    return false;
                case 3:
                    StartTag asStartTag = token.asStartTag();
                    String name = asStartTag.name();
                    if (name.equals("html")) {
                        return htmlTreeBuilder.process(asStartTag, InBody);
                    }
                    if (name.equals("option")) {
                        htmlTreeBuilder.process(new EndTag("option"));
                        htmlTreeBuilder.insert(asStartTag);
                        break;
                    } else if (name.equals("optgroup")) {
                        if (htmlTreeBuilder.currentElement().nodeName().equals("option")) {
                            htmlTreeBuilder.process(new EndTag("option"));
                        } else if (htmlTreeBuilder.currentElement().nodeName().equals("optgroup")) {
                            htmlTreeBuilder.process(new EndTag("optgroup"));
                        }
                        htmlTreeBuilder.insert(asStartTag);
                        break;
                    } else if (name.equals("select")) {
                        htmlTreeBuilder.error(this);
                        return htmlTreeBuilder.process(new EndTag("select"));
                    } else {
                        if (StringUtil.in(name, Config.INPUT_PART, "keygen", "textarea")) {
                            htmlTreeBuilder.error(this);
                            if (!htmlTreeBuilder.inSelectScope("select")) {
                                return false;
                            }
                            htmlTreeBuilder.process(new EndTag("select"));
                            return htmlTreeBuilder.process(asStartTag);
                        } else if (name.equals("script")) {
                            return htmlTreeBuilder.process(token, InHead);
                        } else {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                    }
                case 4:
                    String name2 = token.asEndTag().name();
                    if (name2.equals("optgroup")) {
                        if (htmlTreeBuilder.currentElement().nodeName().equals("option") && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()) != null && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()).nodeName().equals("optgroup")) {
                            htmlTreeBuilder.process(new EndTag("option"));
                        }
                        if (!htmlTreeBuilder.currentElement().nodeName().equals("optgroup")) {
                            htmlTreeBuilder.error(this);
                            break;
                        } else {
                            htmlTreeBuilder.pop();
                            break;
                        }
                    } else if (name2.equals("option")) {
                        if (!htmlTreeBuilder.currentElement().nodeName().equals("option")) {
                            htmlTreeBuilder.error(this);
                            break;
                        } else {
                            htmlTreeBuilder.pop();
                            break;
                        }
                    } else if (name2.equals("select")) {
                        if (htmlTreeBuilder.inSelectScope(name2)) {
                            htmlTreeBuilder.popStackToClose(name2);
                            htmlTreeBuilder.resetInsertionMode();
                            break;
                        } else {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                    } else {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                case 5:
                    Character asCharacter = token.asCharacter();
                    if (!asCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                        htmlTreeBuilder.insert(asCharacter);
                        break;
                    } else {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                case 6:
                    if (!htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                        htmlTreeBuilder.error(this);
                        break;
                    }
                    break;
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    InSelectInTable {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag()) {
                if (StringUtil.in(token.asStartTag().name(), "caption", "table", "tbody", "tfoot", "thead", "tr", Config.TEST_DEVICE_ID, "th")) {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.process(new EndTag("select"));
                    return htmlTreeBuilder.process(token);
                }
            }
            if (token.isEndTag()) {
                if (StringUtil.in(token.asEndTag().name(), "caption", "table", "tbody", "tfoot", "thead", "tr", Config.TEST_DEVICE_ID, "th")) {
                    htmlTreeBuilder.error(this);
                    if (!htmlTreeBuilder.inTableScope(token.asEndTag().name())) {
                        return false;
                    }
                    htmlTreeBuilder.process(new EndTag("select"));
                    return htmlTreeBuilder.process(token);
                }
            }
            return htmlTreeBuilder.process(token, InSelect);
        }
    },
    AfterBody {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return htmlTreeBuilder.process(token, InBody);
            }
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag() && token.asStartTag().name().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (!token.isEndTag() || !token.asEndTag().name().equals("html")) {
                    if (!token.isEOF()) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.transition(InBody);
                        return htmlTreeBuilder.process(token);
                    }
                } else if (htmlTreeBuilder.isFragmentParsing()) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else {
                    htmlTreeBuilder.transition(AfterAfterBody);
                }
            }
            return true;
        }
    },
    InFrameset {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag()) {
                StartTag asStartTag = token.asStartTag();
                String name = asStartTag.name();
                if (name.equals("html")) {
                    return htmlTreeBuilder.process(asStartTag, InBody);
                }
                if (name.equals("frameset")) {
                    htmlTreeBuilder.insert(asStartTag);
                } else if (name.equals("frame")) {
                    htmlTreeBuilder.insertEmpty(asStartTag);
                } else if (name.equals("noframes")) {
                    return htmlTreeBuilder.process(asStartTag, InHead);
                } else {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            } else if (!token.isEndTag() || !token.asEndTag().name().equals("frameset")) {
                if (!token.isEOF()) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else if (!htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                    htmlTreeBuilder.error(this);
                    return true;
                }
            } else if (htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                htmlTreeBuilder.error(this);
                return false;
            } else {
                htmlTreeBuilder.pop();
                if (!htmlTreeBuilder.isFragmentParsing() && !htmlTreeBuilder.currentElement().nodeName().equals("frameset")) {
                    htmlTreeBuilder.transition(AfterFrameset);
                }
            }
            return true;
        }
    },
    AfterFrameset {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag() && token.asStartTag().name().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (token.isEndTag() && token.asEndTag().name().equals("html")) {
                    htmlTreeBuilder.transition(AfterAfterFrameset);
                } else if (token.isStartTag() && token.asStartTag().name().equals("noframes")) {
                    return htmlTreeBuilder.process(token, InHead);
                } else {
                    if (!token.isEOF()) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                }
            }
            return true;
        }
    },
    AfterAfterBody {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (token.isDoctype() || HtmlTreeBuilderState.isWhitespace(token) || (token.isStartTag() && token.asStartTag().name().equals("html"))) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (!token.isEOF()) {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.transition(InBody);
                    return htmlTreeBuilder.process(token);
                }
            }
            return true;
        }
    },
    AfterAfterFrameset {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (token.isDoctype() || HtmlTreeBuilderState.isWhitespace(token) || (token.isStartTag() && token.asStartTag().name().equals("html"))) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (!token.isEOF()) {
                    if (token.isStartTag() && token.asStartTag().name().equals("noframes")) {
                        return htmlTreeBuilder.process(token, InHead);
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                }
            }
            return true;
        }
    },
    ForeignContent {
        /* access modifiers changed from: 0000 */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return true;
        }
    };
    
    /* access modifiers changed from: private */
    public static String nullString;

    private static final class Constants {
        /* access modifiers changed from: private */
        public static final String[] DdDt = null;
        /* access modifiers changed from: private */
        public static final String[] Formatters = null;
        /* access modifiers changed from: private */
        public static final String[] Headings = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyEndAdoptionFormatters = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyEndClosers = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyEndTableFosters = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyStartApplets = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyStartDrop = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyStartEmptyFormatters = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyStartInputAttribs = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyStartLiBreakers = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyStartMedia = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyStartOptions = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyStartPClosers = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyStartPreListing = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyStartRuby = null;
        /* access modifiers changed from: private */
        public static final String[] InBodyStartToHead = null;

        private Constants() {
        }

        static {
            InBodyStartToHead = new String[]{"base", "basefont", "bgsound", "command", "link", "meta", "noframes", "script", "style", "title"};
            InBodyStartPClosers = new String[]{"address", "article", "aside", "blockquote", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_HEADER, "hgroup", "menu", "nav", "ol", "p", "section", "summary", "ul"};
            Headings = new String[]{"h1", Config.EVENT_NATIVE_VIEW_HIERARCHY, Config.EVENT_H5_VIEW_HIERARCHY, "h4", "h5", "h6"};
            InBodyStartPreListing = new String[]{"pre", "listing"};
            InBodyStartLiBreakers = new String[]{"address", "div", "p"};
            DdDt = new String[]{Config.DEVICE_ID_SEC, "dt"};
            Formatters = new String[]{"b", "big", "code", "em", "font", "i", "s", "small", "strike", "strong", "tt", "u"};
            InBodyStartApplets = new String[]{"applet", "marquee", JSONTypes.OBJECT};
            InBodyStartEmptyFormatters = new String[]{"area", "br", "embed", "img", "keygen", "wbr"};
            InBodyStartMedia = new String[]{"param", "source", "track"};
            InBodyStartInputAttribs = new String[]{"name", "action", "prompt"};
            InBodyStartOptions = new String[]{"optgroup", "option"};
            InBodyStartRuby = new String[]{"rp", "rt"};
            InBodyStartDrop = new String[]{"caption", "col", "colgroup", "frame", "head", "tbody", Config.TEST_DEVICE_ID, "tfoot", "th", "thead", "tr"};
            InBodyEndClosers = new String[]{"address", "article", "aside", "blockquote", "button", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_HEADER, "hgroup", "listing", "menu", "nav", "ol", "pre", "section", "summary", "ul"};
            InBodyEndAdoptionFormatters = new String[]{Config.APP_VERSION_CODE, "b", "big", "code", "em", "font", "i", "nobr", "s", "small", "strike", "strong", "tt", "u"};
            InBodyEndTableFosters = new String[]{"table", "tbody", "tfoot", "thead", "tr"};
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder);

    static {
        nullString = String.valueOf(0);
    }

    /* access modifiers changed from: private */
    public static boolean isWhitespace(Token token) {
        if (!token.isCharacter()) {
            return false;
        }
        String data = token.asCharacter().getData();
        for (int i = 0; i < data.length(); i++) {
            if (!StringUtil.isWhitespace(data.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static void handleRcData(StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.insert(startTag);
        htmlTreeBuilder.tokeniser.transition(TokeniserState.Rcdata);
        htmlTreeBuilder.markInsertionMode();
        htmlTreeBuilder.transition(Text);
    }

    /* access modifiers changed from: private */
    public static void handleRawtext(StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.insert(startTag);
        htmlTreeBuilder.tokeniser.transition(TokeniserState.Rawtext);
        htmlTreeBuilder.markInsertionMode();
        htmlTreeBuilder.transition(Text);
    }
}
