package org.jsoup.parser;

import org.jsoup.helper.DescendableLinkedList;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

abstract class TreeBuilder {
    protected String baseUri;
    protected Token currentToken;
    protected Document doc;
    protected ParseErrorList errors;
    CharacterReader reader;
    protected DescendableLinkedList<Element> stack;
    Tokeniser tokeniser;

    /* access modifiers changed from: protected */
    public abstract boolean process(Token token);

    TreeBuilder() {
    }

    /* access modifiers changed from: protected */
    public void initialiseParse(String str, String str2, ParseErrorList parseErrorList) {
        Validate.notNull(str, "String input must not be null");
        Validate.notNull(str2, "BaseURI must not be null");
        this.doc = new Document(str2);
        this.reader = new CharacterReader(str);
        this.errors = parseErrorList;
        this.tokeniser = new Tokeniser(this.reader, parseErrorList);
        this.stack = new DescendableLinkedList<>();
        this.baseUri = str2;
    }

    /* access modifiers changed from: 0000 */
    public Document parse(String str, String str2) {
        return parse(str, str2, ParseErrorList.noTracking());
    }

    /* access modifiers changed from: 0000 */
    public Document parse(String str, String str2, ParseErrorList parseErrorList) {
        initialiseParse(str, str2, parseErrorList);
        runParser();
        return this.doc;
    }

    /* access modifiers changed from: protected */
    public void runParser() {
        Token read;
        do {
            read = this.tokeniser.read();
            process(read);
        } while (read.type != TokenType.EOF);
    }

    /* access modifiers changed from: protected */
    public Element currentElement() {
        return (Element) this.stack.getLast();
    }
}
