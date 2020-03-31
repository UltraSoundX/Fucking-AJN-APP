package org.jsoup.parser;

import com.baidu.mobstat.Config;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.sf.json.xml.JSONTypes;
import org.jsoup.helper.DescendableLinkedList;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

class HtmlTreeBuilder extends TreeBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = (!HtmlTreeBuilder.class.desiredAssertionStatus());
    private static final String[] TagSearchButton = {"button"};
    private static final String[] TagSearchEndTags = {Config.DEVICE_ID_SEC, "dt", "li", "option", "optgroup", "p", "rp", "rt"};
    private static final String[] TagSearchList = {"ol", "ul"};
    private static final String[] TagSearchSelectScope = {"optgroup", "option"};
    private static final String[] TagSearchSpecial = {"address", "applet", "area", "article", "aside", "base", "basefont", "bgsound", "blockquote", "body", "br", "button", "caption", "center", "col", "colgroup", "command", Config.DEVICE_ID_SEC, "details", "dir", "div", "dl", "dt", "embed", "fieldset", "figcaption", "figure", "footer", "form", "frame", "frameset", "h1", Config.EVENT_NATIVE_VIEW_HIERARCHY, Config.EVENT_H5_VIEW_HIERARCHY, "h4", "h5", "h6", "head", TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_HEADER, "hgroup", "hr", "html", "iframe", "img", Config.INPUT_PART, "isindex", "li", "link", "listing", "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", JSONTypes.OBJECT, "ol", "p", "param", "plaintext", "pre", "script", "section", "select", "style", "summary", "table", "tbody", Config.TEST_DEVICE_ID, "textarea", "tfoot", "th", "thead", "title", "tr", "ul", "wbr", "xmp"};
    private static final String[] TagSearchTableScope = {"html", "table"};
    private static final String[] TagsScriptStyle = {"script", "style"};
    public static final String[] TagsSearchInScope = {"applet", "caption", "html", "table", Config.TEST_DEVICE_ID, "th", "marquee", JSONTypes.OBJECT};
    private boolean baseUriSetFromDoc = false;
    private Element contextElement;
    private FormElement formElement;
    private DescendableLinkedList<Element> formattingElements = new DescendableLinkedList<>();
    private boolean fosterInserts = false;
    private boolean fragmentParsing = false;
    private boolean framesetOk = true;
    private Element headElement;
    private HtmlTreeBuilderState originalState;
    private List<Character> pendingTableCharacters = new ArrayList();
    private HtmlTreeBuilderState state;

    HtmlTreeBuilder() {
    }

    /* access modifiers changed from: 0000 */
    public Document parse(String str, String str2, ParseErrorList parseErrorList) {
        this.state = HtmlTreeBuilderState.Initial;
        this.baseUriSetFromDoc = false;
        return super.parse(str, str2, parseErrorList);
    }

    /* access modifiers changed from: 0000 */
    public List<Node> parseFragment(String str, Element element, String str2, ParseErrorList parseErrorList) {
        this.state = HtmlTreeBuilderState.Initial;
        initialiseParse(str, str2, parseErrorList);
        this.contextElement = element;
        this.fragmentParsing = true;
        Element element2 = null;
        if (element != null) {
            if (element.ownerDocument() != null) {
                this.doc.quirksMode(element.ownerDocument().quirksMode());
            }
            String tagName = element.tagName();
            if (StringUtil.in(tagName, "title", "textarea")) {
                this.tokeniser.transition(TokeniserState.Rcdata);
            } else {
                if (StringUtil.in(tagName, "iframe", "noembed", "noframes", "style", "xmp")) {
                    this.tokeniser.transition(TokeniserState.Rawtext);
                } else if (tagName.equals("script")) {
                    this.tokeniser.transition(TokeniserState.ScriptData);
                } else if (tagName.equals("noscript")) {
                    this.tokeniser.transition(TokeniserState.Data);
                } else if (tagName.equals("plaintext")) {
                    this.tokeniser.transition(TokeniserState.Data);
                } else {
                    this.tokeniser.transition(TokeniserState.Data);
                }
            }
            Element element3 = new Element(Tag.valueOf("html"), str2);
            this.doc.appendChild(element3);
            this.stack.push(element3);
            resetInsertionMode();
            Elements parents = element.parents();
            parents.add(0, element);
            Iterator it = parents.iterator();
            while (true) {
                if (!it.hasNext()) {
                    element2 = element3;
                    break;
                }
                Element element4 = (Element) it.next();
                if (element4 instanceof FormElement) {
                    this.formElement = (FormElement) element4;
                    element2 = element3;
                    break;
                }
            }
        }
        runParser();
        if (element != null) {
            return element2.childNodes();
        }
        return this.doc.childNodes();
    }

    /* access modifiers changed from: protected */
    public boolean process(Token token) {
        this.currentToken = token;
        return this.state.process(token, this);
    }

    /* access modifiers changed from: 0000 */
    public boolean process(Token token, HtmlTreeBuilderState htmlTreeBuilderState) {
        this.currentToken = token;
        return htmlTreeBuilderState.process(token, this);
    }

    /* access modifiers changed from: 0000 */
    public void transition(HtmlTreeBuilderState htmlTreeBuilderState) {
        this.state = htmlTreeBuilderState;
    }

    /* access modifiers changed from: 0000 */
    public HtmlTreeBuilderState state() {
        return this.state;
    }

    /* access modifiers changed from: 0000 */
    public void markInsertionMode() {
        this.originalState = this.state;
    }

    /* access modifiers changed from: 0000 */
    public HtmlTreeBuilderState originalState() {
        return this.originalState;
    }

    /* access modifiers changed from: 0000 */
    public void framesetOk(boolean z) {
        this.framesetOk = z;
    }

    /* access modifiers changed from: 0000 */
    public boolean framesetOk() {
        return this.framesetOk;
    }

    /* access modifiers changed from: 0000 */
    public Document getDocument() {
        return this.doc;
    }

    /* access modifiers changed from: 0000 */
    public String getBaseUri() {
        return this.baseUri;
    }

    /* access modifiers changed from: 0000 */
    public void maybeSetBaseUri(Element element) {
        if (!this.baseUriSetFromDoc) {
            String absUrl = element.absUrl("href");
            if (absUrl.length() != 0) {
                this.baseUri = absUrl;
                this.baseUriSetFromDoc = true;
                this.doc.setBaseUri(absUrl);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isFragmentParsing() {
        return this.fragmentParsing;
    }

    /* access modifiers changed from: 0000 */
    public void error(HtmlTreeBuilderState htmlTreeBuilderState) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader.pos(), "Unexpected token [%s] when in state [%s]", this.currentToken.tokenType(), htmlTreeBuilderState));
        }
    }

    /* access modifiers changed from: 0000 */
    public Element insert(StartTag startTag) {
        if (startTag.isSelfClosing()) {
            Element insertEmpty = insertEmpty(startTag);
            this.stack.add(insertEmpty);
            this.tokeniser.transition(TokeniserState.Data);
            this.tokeniser.emit((Token) new EndTag(insertEmpty.tagName()));
            return insertEmpty;
        }
        Element element = new Element(Tag.valueOf(startTag.name()), this.baseUri, startTag.attributes);
        insert(element);
        return element;
    }

    /* access modifiers changed from: 0000 */
    public Element insert(String str) {
        Element element = new Element(Tag.valueOf(str), this.baseUri);
        insert(element);
        return element;
    }

    /* access modifiers changed from: 0000 */
    public void insert(Element element) {
        insertNode(element);
        this.stack.add(element);
    }

    /* access modifiers changed from: 0000 */
    public Element insertEmpty(StartTag startTag) {
        Tag valueOf = Tag.valueOf(startTag.name());
        Element element = new Element(valueOf, this.baseUri, startTag.attributes);
        insertNode(element);
        if (startTag.isSelfClosing()) {
            if (!valueOf.isKnownTag()) {
                valueOf.setSelfClosing();
                this.tokeniser.acknowledgeSelfClosingFlag();
            } else if (valueOf.isSelfClosing()) {
                this.tokeniser.acknowledgeSelfClosingFlag();
            }
        }
        return element;
    }

    /* access modifiers changed from: 0000 */
    public FormElement insertForm(StartTag startTag, boolean z) {
        FormElement formElement2 = new FormElement(Tag.valueOf(startTag.name()), this.baseUri, startTag.attributes);
        setFormElement(formElement2);
        insertNode(formElement2);
        if (z) {
            this.stack.add(formElement2);
        }
        return formElement2;
    }

    /* access modifiers changed from: 0000 */
    public void insert(Comment comment) {
        insertNode(new Comment(comment.getData(), this.baseUri));
    }

    /* access modifiers changed from: 0000 */
    public void insert(Character character) {
        Node node;
        String tagName = currentElement().tagName();
        if (tagName.equals("script") || tagName.equals("style")) {
            node = new DataNode(character.getData(), this.baseUri);
        } else {
            node = new TextNode(character.getData(), this.baseUri);
        }
        currentElement().appendChild(node);
    }

    private void insertNode(Node node) {
        if (this.stack.size() == 0) {
            this.doc.appendChild(node);
        } else if (isFosterInserts()) {
            insertInFosterParent(node);
        } else {
            currentElement().appendChild(node);
        }
        if ((node instanceof Element) && ((Element) node).tag().isFormListed() && this.formElement != null) {
            this.formElement.addElement((Element) node);
        }
    }

    /* access modifiers changed from: 0000 */
    public Element pop() {
        if (((Element) this.stack.peekLast()).nodeName().equals(Config.TEST_DEVICE_ID) && !this.state.name().equals("InCell")) {
            Validate.isFalse(true, "pop td not in cell");
        }
        if (((Element) this.stack.peekLast()).nodeName().equals("html")) {
            Validate.isFalse(true, "popping html!");
        }
        return (Element) this.stack.pollLast();
    }

    /* access modifiers changed from: 0000 */
    public void push(Element element) {
        this.stack.add(element);
    }

    /* access modifiers changed from: 0000 */
    public DescendableLinkedList<Element> getStack() {
        return this.stack;
    }

    /* access modifiers changed from: 0000 */
    public boolean onStack(Element element) {
        return isElementInQueue(this.stack, element);
    }

    private boolean isElementInQueue(DescendableLinkedList<Element> descendableLinkedList, Element element) {
        Iterator descendingIterator = descendableLinkedList.descendingIterator();
        while (descendingIterator.hasNext()) {
            if (((Element) descendingIterator.next()) == element) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public Element getFromStack(String str) {
        Iterator descendingIterator = this.stack.descendingIterator();
        while (descendingIterator.hasNext()) {
            Element element = (Element) descendingIterator.next();
            if (element.nodeName().equals(str)) {
                return element;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean removeFromStack(Element element) {
        Iterator descendingIterator = this.stack.descendingIterator();
        while (descendingIterator.hasNext()) {
            if (((Element) descendingIterator.next()) == element) {
                descendingIterator.remove();
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void popStackToClose(String str) {
        Iterator descendingIterator = this.stack.descendingIterator();
        while (descendingIterator.hasNext()) {
            if (((Element) descendingIterator.next()).nodeName().equals(str)) {
                descendingIterator.remove();
                return;
            }
            descendingIterator.remove();
        }
    }

    /* access modifiers changed from: 0000 */
    public void popStackToClose(String... strArr) {
        Iterator descendingIterator = this.stack.descendingIterator();
        while (descendingIterator.hasNext()) {
            if (StringUtil.in(((Element) descendingIterator.next()).nodeName(), strArr)) {
                descendingIterator.remove();
                return;
            }
            descendingIterator.remove();
        }
    }

    /* access modifiers changed from: 0000 */
    public void popStackToBefore(String str) {
        Iterator descendingIterator = this.stack.descendingIterator();
        while (descendingIterator.hasNext() && !((Element) descendingIterator.next()).nodeName().equals(str)) {
            descendingIterator.remove();
        }
    }

    /* access modifiers changed from: 0000 */
    public void clearStackToTableContext() {
        clearStackToContext("table");
    }

    /* access modifiers changed from: 0000 */
    public void clearStackToTableBodyContext() {
        clearStackToContext("tbody", "tfoot", "thead");
    }

    /* access modifiers changed from: 0000 */
    public void clearStackToTableRowContext() {
        clearStackToContext("tr");
    }

    private void clearStackToContext(String... strArr) {
        Iterator descendingIterator = this.stack.descendingIterator();
        while (descendingIterator.hasNext()) {
            Element element = (Element) descendingIterator.next();
            if (!StringUtil.in(element.nodeName(), strArr) && !element.nodeName().equals("html")) {
                descendingIterator.remove();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public Element aboveOnStack(Element element) {
        if ($assertionsDisabled || onStack(element)) {
            Iterator descendingIterator = this.stack.descendingIterator();
            while (descendingIterator.hasNext()) {
                if (((Element) descendingIterator.next()) == element) {
                    return (Element) descendingIterator.next();
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public void insertOnStackAfter(Element element, Element element2) {
        int lastIndexOf = this.stack.lastIndexOf(element);
        Validate.isTrue(lastIndexOf != -1);
        this.stack.add(lastIndexOf + 1, element2);
    }

    /* access modifiers changed from: 0000 */
    public void replaceOnStack(Element element, Element element2) {
        replaceInQueue(this.stack, element, element2);
    }

    private void replaceInQueue(LinkedList<Element> linkedList, Element element, Element element2) {
        int lastIndexOf = linkedList.lastIndexOf(element);
        Validate.isTrue(lastIndexOf != -1);
        linkedList.remove(lastIndexOf);
        linkedList.add(lastIndexOf, element2);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void resetInsertionMode() {
        /*
            r5 = this;
            r0 = 0
            org.jsoup.helper.DescendableLinkedList r1 = r5.stack
            java.util.Iterator r2 = r1.descendingIterator()
            r1 = r0
        L_0x0008:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0031
            java.lang.Object r0 = r2.next()
            org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
            boolean r3 = r2.hasNext()
            if (r3 != 0) goto L_0x00e7
            r1 = 1
            org.jsoup.nodes.Element r0 = r5.contextElement
            r4 = r0
            r0 = r1
            r1 = r4
        L_0x0020:
            java.lang.String r1 = r1.nodeName()
            java.lang.String r3 = "select"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0032
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.InSelect
            r5.transition(r0)
        L_0x0031:
            return
        L_0x0032:
            java.lang.String r3 = "td"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0044
            java.lang.String r3 = "td"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x004a
            if (r0 != 0) goto L_0x004a
        L_0x0044:
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.InCell
            r5.transition(r0)
            goto L_0x0031
        L_0x004a:
            java.lang.String r3 = "tr"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0058
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.InRow
            r5.transition(r0)
            goto L_0x0031
        L_0x0058:
            java.lang.String r3 = "tbody"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0070
            java.lang.String r3 = "thead"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0070
            java.lang.String r3 = "tfoot"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0076
        L_0x0070:
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.InTableBody
            r5.transition(r0)
            goto L_0x0031
        L_0x0076:
            java.lang.String r3 = "caption"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0084
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.InCaption
            r5.transition(r0)
            goto L_0x0031
        L_0x0084:
            java.lang.String r3 = "colgroup"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0092
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.InColumnGroup
            r5.transition(r0)
            goto L_0x0031
        L_0x0092:
            java.lang.String r3 = "table"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x00a0
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.InTable
            r5.transition(r0)
            goto L_0x0031
        L_0x00a0:
            java.lang.String r3 = "head"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x00ae
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.InBody
            r5.transition(r0)
            goto L_0x0031
        L_0x00ae:
            java.lang.String r3 = "body"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x00bd
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.InBody
            r5.transition(r0)
            goto L_0x0031
        L_0x00bd:
            java.lang.String r3 = "frameset"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x00cc
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.InFrameset
            r5.transition(r0)
            goto L_0x0031
        L_0x00cc:
            java.lang.String r3 = "html"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x00db
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.BeforeHead
            r5.transition(r0)
            goto L_0x0031
        L_0x00db:
            if (r0 == 0) goto L_0x00e4
            org.jsoup.parser.HtmlTreeBuilderState r0 = org.jsoup.parser.HtmlTreeBuilderState.InBody
            r5.transition(r0)
            goto L_0x0031
        L_0x00e4:
            r1 = r0
            goto L_0x0008
        L_0x00e7:
            r4 = r0
            r0 = r1
            r1 = r4
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilder.resetInsertionMode():void");
    }

    private boolean inSpecificScope(String str, String[] strArr, String[] strArr2) {
        return inSpecificScope(new String[]{str}, strArr, strArr2);
    }

    private boolean inSpecificScope(String[] strArr, String[] strArr2, String[] strArr3) {
        Iterator descendingIterator = this.stack.descendingIterator();
        while (descendingIterator.hasNext()) {
            String nodeName = ((Element) descendingIterator.next()).nodeName();
            if (StringUtil.in(nodeName, strArr)) {
                return true;
            }
            if (StringUtil.in(nodeName, strArr2)) {
                return false;
            }
            if (strArr3 != null && StringUtil.in(nodeName, strArr3)) {
                return false;
            }
        }
        Validate.fail("Should not be reachable");
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean inScope(String[] strArr) {
        return inSpecificScope(strArr, TagsSearchInScope, (String[]) null);
    }

    /* access modifiers changed from: 0000 */
    public boolean inScope(String str) {
        return inScope(str, null);
    }

    /* access modifiers changed from: 0000 */
    public boolean inScope(String str, String[] strArr) {
        return inSpecificScope(str, TagsSearchInScope, strArr);
    }

    /* access modifiers changed from: 0000 */
    public boolean inListItemScope(String str) {
        return inScope(str, TagSearchList);
    }

    /* access modifiers changed from: 0000 */
    public boolean inButtonScope(String str) {
        return inScope(str, TagSearchButton);
    }

    /* access modifiers changed from: 0000 */
    public boolean inTableScope(String str) {
        return inSpecificScope(str, TagSearchTableScope, (String[]) null);
    }

    /* access modifiers changed from: 0000 */
    public boolean inSelectScope(String str) {
        Iterator descendingIterator = this.stack.descendingIterator();
        while (descendingIterator.hasNext()) {
            String nodeName = ((Element) descendingIterator.next()).nodeName();
            if (nodeName.equals(str)) {
                return true;
            }
            if (!StringUtil.in(nodeName, TagSearchSelectScope)) {
                return false;
            }
        }
        Validate.fail("Should not be reachable");
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void setHeadElement(Element element) {
        this.headElement = element;
    }

    /* access modifiers changed from: 0000 */
    public Element getHeadElement() {
        return this.headElement;
    }

    /* access modifiers changed from: 0000 */
    public boolean isFosterInserts() {
        return this.fosterInserts;
    }

    /* access modifiers changed from: 0000 */
    public void setFosterInserts(boolean z) {
        this.fosterInserts = z;
    }

    /* access modifiers changed from: 0000 */
    public FormElement getFormElement() {
        return this.formElement;
    }

    /* access modifiers changed from: 0000 */
    public void setFormElement(FormElement formElement2) {
        this.formElement = formElement2;
    }

    /* access modifiers changed from: 0000 */
    public void newPendingTableCharacters() {
        this.pendingTableCharacters = new ArrayList();
    }

    /* access modifiers changed from: 0000 */
    public List<Character> getPendingTableCharacters() {
        return this.pendingTableCharacters;
    }

    /* access modifiers changed from: 0000 */
    public void setPendingTableCharacters(List<Character> list) {
        this.pendingTableCharacters = list;
    }

    /* access modifiers changed from: 0000 */
    public void generateImpliedEndTags(String str) {
        while (str != null && !currentElement().nodeName().equals(str) && StringUtil.in(currentElement().nodeName(), TagSearchEndTags)) {
            pop();
        }
    }

    /* access modifiers changed from: 0000 */
    public void generateImpliedEndTags() {
        generateImpliedEndTags(null);
    }

    /* access modifiers changed from: 0000 */
    public boolean isSpecial(Element element) {
        return StringUtil.in(element.nodeName(), TagSearchSpecial);
    }

    /* access modifiers changed from: 0000 */
    public void pushActiveFormattingElements(Element element) {
        int i = 0;
        Iterator descendingIterator = this.formattingElements.descendingIterator();
        while (true) {
            int i2 = i;
            if (!descendingIterator.hasNext()) {
                break;
            }
            Element element2 = (Element) descendingIterator.next();
            if (element2 == null) {
                break;
            }
            if (isSameFormattingElement(element, element2)) {
                i = i2 + 1;
            } else {
                i = i2;
            }
            if (i == 3) {
                descendingIterator.remove();
                break;
            }
        }
        this.formattingElements.add(element);
    }

    private boolean isSameFormattingElement(Element element, Element element2) {
        return element.nodeName().equals(element2.nodeName()) && element.attributes().equals(element2.attributes());
    }

    /* access modifiers changed from: 0000 */
    public void reconstructFormattingElements() {
        boolean z;
        Element element;
        int i;
        int size = this.formattingElements.size();
        if (size != 0 && this.formattingElements.getLast() != null && !onStack((Element) this.formattingElements.getLast())) {
            int i2 = size - 1;
            Element element2 = (Element) this.formattingElements.getLast();
            while (true) {
                if (i2 != 0) {
                    i2--;
                    Element element3 = (Element) this.formattingElements.get(i2);
                    if (element3 == null) {
                        z = false;
                        int i3 = i2;
                        element = element3;
                        i = i3;
                        break;
                    } else if (onStack(element3)) {
                        z = false;
                        int i4 = i2;
                        element = element3;
                        i = i4;
                        break;
                    } else {
                        element2 = element3;
                    }
                } else {
                    i = i2;
                    element = element2;
                    z = true;
                    break;
                }
            }
            while (true) {
                if (!z) {
                    int i5 = i + 1;
                    int i6 = i5;
                    element = (Element) this.formattingElements.get(i5);
                    i = i6;
                }
                Validate.notNull(element);
                Element insert = insert(element.nodeName());
                insert.attributes().addAll(element.attributes());
                this.formattingElements.add(i, insert);
                this.formattingElements.remove(i + 1);
                if (i != size - 1) {
                    z = false;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void clearFormattingElementsToLastMarker() {
        while (!this.formattingElements.isEmpty()) {
            Element element = (Element) this.formattingElements.peekLast();
            this.formattingElements.removeLast();
            if (element == null) {
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void removeFromActiveFormattingElements(Element element) {
        Iterator descendingIterator = this.formattingElements.descendingIterator();
        while (descendingIterator.hasNext()) {
            if (((Element) descendingIterator.next()) == element) {
                descendingIterator.remove();
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isInActiveFormattingElements(Element element) {
        return isElementInQueue(this.formattingElements, element);
    }

    /* access modifiers changed from: 0000 */
    public Element getActiveFormattingElement(String str) {
        Iterator descendingIterator = this.formattingElements.descendingIterator();
        while (descendingIterator.hasNext()) {
            Element element = (Element) descendingIterator.next();
            if (element == null) {
                break;
            } else if (element.nodeName().equals(str)) {
                return element;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void replaceActiveFormattingElement(Element element, Element element2) {
        replaceInQueue(this.formattingElements, element, element2);
    }

    /* access modifiers changed from: 0000 */
    public void insertMarkerToFormattingElements() {
        this.formattingElements.add(null);
    }

    /* access modifiers changed from: 0000 */
    public void insertInFosterParent(Node node) {
        Element element;
        boolean z;
        Element fromStack = getFromStack("table");
        if (fromStack == null) {
            element = (Element) this.stack.get(0);
            z = false;
        } else if (fromStack.parent() != null) {
            element = fromStack.parent();
            z = true;
        } else {
            element = aboveOnStack(fromStack);
            z = false;
        }
        if (z) {
            Validate.notNull(fromStack);
            fromStack.before(node);
            return;
        }
        element.appendChild(node);
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + this.currentToken + ", state=" + this.state + ", currentElement=" + currentElement() + '}';
    }
}
