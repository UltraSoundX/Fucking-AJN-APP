package org.jsoup.parser;

import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document.OutputSettings.Syntax;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class XmlTreeBuilder extends TreeBuilder {
    /* access modifiers changed from: protected */
    public void initialiseParse(String str, String str2, ParseErrorList parseErrorList) {
        super.initialiseParse(str, str2, parseErrorList);
        this.stack.add(this.doc);
        this.doc.outputSettings().syntax(Syntax.xml);
    }

    /* access modifiers changed from: protected */
    public boolean process(Token token) {
        switch (token.type) {
            case StartTag:
                insert(token.asStartTag());
                break;
            case EndTag:
                popStackToClose(token.asEndTag());
                break;
            case Comment:
                insert(token.asComment());
                break;
            case Character:
                insert(token.asCharacter());
                break;
            case Doctype:
                insert(token.asDoctype());
                break;
            case EOF:
                break;
            default:
                Validate.fail("Unexpected token type: " + token.type);
                break;
        }
        return true;
    }

    private void insertNode(Node node) {
        currentElement().appendChild(node);
    }

    /* access modifiers changed from: 0000 */
    public Element insert(StartTag startTag) {
        Tag valueOf = Tag.valueOf(startTag.name());
        Element element = new Element(valueOf, this.baseUri, startTag.attributes);
        insertNode(element);
        if (startTag.isSelfClosing()) {
            this.tokeniser.acknowledgeSelfClosingFlag();
            if (!valueOf.isKnownTag()) {
                valueOf.setSelfClosing();
            }
        } else {
            this.stack.add(element);
        }
        return element;
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [org.jsoup.nodes.XmlDeclaration] */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void insert(org.jsoup.parser.Token.Comment r6) {
        /*
            r5 = this;
            r3 = 1
            org.jsoup.nodes.Comment r0 = new org.jsoup.nodes.Comment
            java.lang.String r1 = r6.getData()
            java.lang.String r2 = r5.baseUri
            r0.<init>(r1, r2)
            boolean r1 = r6.bogus
            if (r1 == 0) goto L_0x003e
            java.lang.String r2 = r0.getData()
            int r1 = r2.length()
            if (r1 <= r3) goto L_0x003e
            java.lang.String r1 = "!"
            boolean r1 = r2.startsWith(r1)
            if (r1 != 0) goto L_0x002a
            java.lang.String r1 = "?"
            boolean r1 = r2.startsWith(r1)
            if (r1 == 0) goto L_0x003e
        L_0x002a:
            java.lang.String r3 = r2.substring(r3)
            org.jsoup.nodes.XmlDeclaration r1 = new org.jsoup.nodes.XmlDeclaration
            java.lang.String r0 = r0.baseUri()
            java.lang.String r4 = "!"
            boolean r2 = r2.startsWith(r4)
            r1.<init>(r3, r0, r2)
            r0 = r1
        L_0x003e:
            r5.insertNode(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.XmlTreeBuilder.insert(org.jsoup.parser.Token$Comment):void");
    }

    /* access modifiers changed from: 0000 */
    public void insert(Character character) {
        insertNode(new TextNode(character.getData(), this.baseUri));
    }

    /* access modifiers changed from: 0000 */
    public void insert(Doctype doctype) {
        insertNode(new DocumentType(doctype.getName(), doctype.getPublicIdentifier(), doctype.getSystemIdentifier(), this.baseUri));
    }

    private void popStackToClose(EndTag endTag) {
        String name = endTag.name();
        Element element = null;
        Iterator descendingIterator = this.stack.descendingIterator();
        while (true) {
            if (!descendingIterator.hasNext()) {
                break;
            }
            Element element2 = (Element) descendingIterator.next();
            if (element2.nodeName().equals(name)) {
                element = element2;
                break;
            }
        }
        if (element != null) {
            Iterator descendingIterator2 = this.stack.descendingIterator();
            while (descendingIterator2.hasNext()) {
                if (((Element) descendingIterator2.next()) == element) {
                    descendingIterator2.remove();
                    return;
                }
                descendingIterator2.remove();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public List<Node> parseFragment(String str, String str2, ParseErrorList parseErrorList) {
        initialiseParse(str, str2, parseErrorList);
        runParser();
        return this.doc.childNodes();
    }
}
