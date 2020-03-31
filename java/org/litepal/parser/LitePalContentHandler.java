package org.litepal.parser;

import com.baidu.mobstat.Config;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LitePalContentHandler extends DefaultHandler {
    private LitePalAttr litePalAttr;

    public void characters(char[] cArr, int i, int i2) throws SAXException {
    }

    public void endDocument() throws SAXException {
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
    }

    public void startDocument() throws SAXException {
        this.litePalAttr = LitePalAttr.getInstance();
        this.litePalAttr.getClassNames().clear();
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        int i = 0;
        if ("dbname".equalsIgnoreCase(str2)) {
            while (i < attributes.getLength()) {
                if ("value".equalsIgnoreCase(attributes.getLocalName(i))) {
                    this.litePalAttr.setDbName(attributes.getValue(i).trim());
                }
                i++;
            }
        } else if (Config.INPUT_DEF_VERSION.equalsIgnoreCase(str2)) {
            while (i < attributes.getLength()) {
                if ("value".equalsIgnoreCase(attributes.getLocalName(i))) {
                    this.litePalAttr.setVersion(Integer.parseInt(attributes.getValue(i).trim()));
                }
                i++;
            }
        } else if ("mapping".equalsIgnoreCase(str2)) {
            while (i < attributes.getLength()) {
                if ("class".equalsIgnoreCase(attributes.getLocalName(i))) {
                    this.litePalAttr.addClassName(attributes.getValue(i).trim());
                }
                i++;
            }
        } else if ("cases".equalsIgnoreCase(str2)) {
            while (i < attributes.getLength()) {
                if ("value".equalsIgnoreCase(attributes.getLocalName(i))) {
                    this.litePalAttr.setCases(attributes.getValue(i).trim());
                }
                i++;
            }
        } else if ("storage".equalsIgnoreCase(str2)) {
            while (i < attributes.getLength()) {
                if ("value".equalsIgnoreCase(attributes.getLocalName(i))) {
                    this.litePalAttr.setStorage(attributes.getValue(i).trim());
                }
                i++;
            }
        }
    }
}
