package org.jsoup.nodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Connection.KeyVal;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.Validate;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class FormElement extends Element {
    private final Elements elements = new Elements();

    public FormElement(Tag tag, String str, Attributes attributes) {
        super(tag, str, attributes);
    }

    public Elements elements() {
        return this.elements;
    }

    public FormElement addElement(Element element) {
        this.elements.add(element);
        return this;
    }

    public Connection submit() {
        String baseUri = hasAttr("action") ? absUrl("action") : baseUri();
        Validate.notEmpty(baseUri, "Could not determine a form action URL for submit. Ensure you set a base URI when parsing.");
        return Jsoup.connect(baseUri).data((Collection<KeyVal>) formData()).method(attr("method").toUpperCase().equals("POST") ? Method.POST : Method.GET);
    }

    public List<KeyVal> formData() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.elements.iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            if (element.tag().isFormSubmittable()) {
                String attr = element.attr("name");
                if (attr.length() != 0) {
                    if ("select".equals(element.tagName())) {
                        Iterator it2 = element.select("option[selected]").iterator();
                        while (it2.hasNext()) {
                            arrayList.add(HttpConnection.KeyVal.create(attr, ((Element) it2.next()).val()));
                        }
                    } else {
                        arrayList.add(HttpConnection.KeyVal.create(attr, element.val()));
                    }
                }
            }
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
